package com.example.vnews.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vnews.R;
import com.example.vnews.Url.Url;
import com.example.vnews.adapterJ.NewsAdapter;
import com.example.vnews.modelJ.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText etFind;
    ImageView ivFind;
    ArrayList<News> arrayList;
    NewsAdapter newsAdapter;
    RecyclerView rvListFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        anhXa();
        init();
    }

    private void init() {
        etFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String q = etFind.getText().toString().trim();
                if(q.length() > 2){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlSearch + q, new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {

                            if(response.length() != 0){
                                newsAdapter.clear();
                                String id = "";
                                String Title = "";
                                String NgayDang = "";
                                String Nguon = "";
                                String Anh = "";
                                String Loai = "";
                                for(int i = 0; i < response.length(); i++){
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        id = jsonObject.getString("id");
                                        Title = jsonObject.getString("title");
                                        NgayDang = jsonObject.getString("ngaydang");
                                        Nguon = jsonObject.getString("nguon");
                                        Anh = jsonObject.getString("img");
                                        Loai = jsonObject.getString("loai");
                                        arrayList.add(new News(id, Title, NgayDang, Nguon, Loai, Anh));
                                        newsAdapter.notifyDataSetChanged();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                rvListFind.setAdapter(newsAdapter);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("aaa", error.toString());
                        }
                    });
                    requestQueue.add(jsonArrayRequest);
                }
            }
        });
    }

    private void anhXa() {
        etFind = findViewById(R.id.etFind);
        ivFind = findViewById(R.id.ivFind);
        arrayList = new ArrayList<>();
        newsAdapter = new NewsAdapter(getApplicationContext(), arrayList);
        rvListFind = findViewById(R.id.rvListFind);
        rvListFind.setHasFixedSize(true);
        rvListFind.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
    }
}