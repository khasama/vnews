package com.example.vnews.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vnews.R;
import com.example.vnews.Url.Url;
import com.example.vnews.adapterJ.CommentAdapter;
import com.example.vnews.adapterJ.NewsAdapter;
import com.example.vnews.modelJ.Comment;
import com.example.vnews.modelJ.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadActivity extends AppCompatActivity {
    TextView tvTitleTin, tvNguonNgay;
    WebView wvContent;

    ArrayList<News> arrayNews;
    NewsAdapter newsAdapter;
    RecyclerView rvNews;

    LinearLayout llComment;
    RecyclerView rvListCmt;
    ArrayList<Comment> arrayCmt;
    CommentAdapter commentAdapter;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        anhXa();
        initPreferences();
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
                        getComment(idTin);
                        checkLogin(idTin);
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

    private void checkLogin(String idTin) {
        String savedData = sharedPreferences.getString("idUser", "");
        if(savedData.length() == 0 ){

        }else{
            addComment(idTin, savedData);
        }
    }

    private void addComment(String idTin, String savedData) {
        View view = getLayoutInflater().inflate(R.layout.component_cmt, null);
        EditText etCmt = view.findViewById(R.id.etCmt);
        ImageButton ibSendCmt = view.findViewById(R.id.ibSendCmt);

        ibSendCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cmt = etCmt.getText().toString().trim();
                String idTinTuc = idTin;
                String idUser = savedData;
                if(!cmt.isEmpty()){
                    sendComment(cmt, idTinTuc, idUser);
                    etCmt.setText("");
                }else {
                    Toast.makeText(ReadActivity.this, "Vui lòng nhập đầy đủ !!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        llComment.addView(view);
    }

    public void sendComment(String cmt, String idTinTuc, String idUser) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.sendComment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("success")){
                    getComment(idTinTuc);
                }else{
                    Toast.makeText(getApplicationContext(), "Đã xảy ra lỗi vui lòng thử lại sau !!!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("abbbb", error.toString());
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("cmt", cmt);
                data.put("idUser", idUser);
                data.put("idTinTuc", idTinTuc);
                return data;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getComment(String idTinTuc) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlShowCmt + idTinTuc, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    commentAdapter.clear();
                    String idCmt = "";
                    String userName = "";
                    String content = "";
                    String date = "";
                    String img = "";
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            idCmt = jsonObject.getString("id-cmt");
                            userName = jsonObject.getString("user-name");
                            content = jsonObject.getString("content");
                            date = jsonObject.getString("date");
                            img = jsonObject.getString("img");
                            arrayCmt.add(new Comment(idCmt, userName, content, date, img));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    rvListCmt.setAdapter(commentAdapter);
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

        rvListCmt = findViewById(R.id.rvListCmt);
        arrayCmt = new ArrayList<>();
        commentAdapter = new CommentAdapter(getApplicationContext(), arrayCmt);
        rvListCmt.setHasFixedSize(true);
        rvListCmt.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        llComment = findViewById(R.id.llComment);
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}