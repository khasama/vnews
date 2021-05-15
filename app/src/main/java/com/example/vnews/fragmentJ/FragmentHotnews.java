package com.example.vnews.fragmentJ;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vnews.R;
import com.example.vnews.Url.Url;
import com.example.vnews.adapterJ.NewsAdapter;
import com.example.vnews.modelJ.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHotnews extends Fragment {
    private Context mContext;
    RecyclerView rvTinHot;
    ArrayList<News> arrayNews;
    NewsAdapter newsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("asd", "hotss");
        View view = inflater.inflate(R.layout.fragment_hotnews, container, false);
        anhxa(view);
        getDataTH();
        return view;
    }

    private void getDataTH() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.HotNews, response -> {
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
                rvTinHot.setAdapter(newsAdapter);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void anhxa(View view) {
        rvTinHot = view.findViewById(R.id.rvTinHot);
        arrayNews = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(), arrayNews);
        rvTinHot.setHasFixedSize(true);
        rvTinHot.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
