package com.example.vnews.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vnews.R;
import com.example.vnews.Url.Url;
import com.example.vnews.adapterJ.NewsAdapter;
import com.example.vnews.modelJ.News;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {
    TextView tvTitleTin, tvNguonNgay;
    WebView wvContent;

    ArrayList<News> arrayNews;
    NewsAdapter newsAdapter;
    RecyclerView rvNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        anhXa();
        getData();
    }

    private void getData() {
        Bundle bd = getIntent().getExtras();
        if( bd != null){
            String idTin = bd.getString("idTin");
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.getInfo(idTin), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if(response != null){
                        String ID = "";
                        String Title = "";
                        String idLoaiTin = "";
                        String NguonTin = "";
                        String NgayDang = "";
                        String LuotXem = "";
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                ID = jsonObject.getString("id");
                                Title = jsonObject.getString("title");
                                idLoaiTin = jsonObject.getString("idloai");
                                NguonTin = jsonObject.getString("nguon");
                                NgayDang = jsonObject.getString("ngaydang");
                                LuotXem = jsonObject.getString("view");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        tvTitleTin.setText(Title);
                        tvNguonNgay.setText(NguonTin + " - " + NgayDang);
                        getContent(idTin);
                        getTLQ(idLoaiTin);
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

    private void getTLQ(String idLoaiTin) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.Tinlienquan + idLoaiTin, response -> {
            if(response != null){
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
                        arrayNews.add(new News(id, Title, NgayDang, Nguon, Loai, Anh));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rvNews.setAdapter(newsAdapter);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getContent(String idTin) {
        WebSettings webSettings = wvContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvContent.loadUrl(Url.Content + idTin);
    }

    private void anhXa() {
        tvTitleTin = findViewById(R.id.tvTitleTin);
        tvNguonNgay = findViewById(R.id.tvNguonNgay);
        wvContent = findViewById(R.id.wvContent);

        rvNews = findViewById(R.id.rvTinLienQuan);
        arrayNews = new ArrayList<>();
        newsAdapter = new NewsAdapter(getApplicationContext(), arrayNews);
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
    }
}