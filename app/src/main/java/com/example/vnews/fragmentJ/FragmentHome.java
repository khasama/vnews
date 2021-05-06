package com.example.vnews.fragmentJ;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vnews.R;
import com.example.vnews.Url.Url;
import com.example.vnews.adapterJ.BannerVpAdapter;
import com.example.vnews.adapterJ.NewsAdapter;
import com.example.vnews.modelJ.Banner;
import com.example.vnews.modelJ.News;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    ViewPager viewPager;
    private Context mContext;
    ArrayList<Banner> arrayBanner = new ArrayList<>();
    BannerVpAdapter bannerAdapter;

    ArrayList<News> arrayNews;
    NewsAdapter newsAdapter;
    RecyclerView rvNews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        anhXa(view);
        getData();
        getNews();
        return view;
    }

    private void getNews() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.News, response -> {
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    private void getData() {

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.Banner, response -> {
            if(response != null){
                String id = "";
                String Title = "";
                String NgayDang = "";
                String Nguon = "";
                String Anh = "";
                Log.e("asd", "get Data");
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id = jsonObject.getString("id");
                        Title = jsonObject.getString("title");
                        NgayDang = jsonObject.getString("ngaydang");
                        Nguon = jsonObject.getString("nguon");
                        Anh = jsonObject.getString("img");
                        arrayBanner.add(new Banner(id, Title, NgayDang, Nguon, Anh));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.e("aaa", arrayBanner.get(2).getTitle());

                bannerAdapter = new BannerVpAdapter(getActivity(), arrayBanner);
                viewPager.setAdapter(bannerAdapter);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);

    }

    private void anhXa(View view) {
        viewPager = view.findViewById(R.id.vpBanner);
        rvNews = view.findViewById(R.id.rvTinMoi);
        arrayNews = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(), arrayNews);
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

}
