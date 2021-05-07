package com.example.vnews.fragmentJ;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

public class FragmentTechnical extends Fragment {
    private Context mContext;
    RecyclerView rvDSCN, rvTTMT, rvCNTL;
    ArrayList<News> arrayNews1, arrayNews2, arrayNews3;
    NewsAdapter newsAdapter1, newsAdapter2, newsAdapter3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technical, container, false);
        anhXa(view);
        getDataDSCN();
        getDataTTMT();
        getDataCNTL();
        return view;
    }

    private void getDataCNTL() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.getTechNews("12"), response -> {
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
                        arrayNews3.add(new News(id, Title, NgayDang, Nguon, Loai, Anh));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rvCNTL.setAdapter(newsAdapter3);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataTTMT() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.getTechNews("6"), response -> {
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
                        arrayNews2.add(new News(id, Title, NgayDang, Nguon, Loai, Anh));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rvTTMT.setAdapter(newsAdapter2);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataDSCN() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.getTechNews("11"), response -> {
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
                        arrayNews1.add(new News(id, Title, NgayDang, Nguon, Loai, Anh));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rvDSCN.setAdapter(newsAdapter1);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void anhXa(View view) {
        rvDSCN = view.findViewById(R.id.rvDSCN);
        rvTTMT = view.findViewById(R.id.rvTTMT);
        rvCNTL = view.findViewById(R.id.rvCNTL);

        arrayNews1 = new ArrayList<>();
        arrayNews2 = new ArrayList<>();
        arrayNews3 = new ArrayList<>();

        newsAdapter1 = new NewsAdapter(getContext(), arrayNews1);
        newsAdapter2 = new NewsAdapter(getContext(), arrayNews2);
        newsAdapter3 = new NewsAdapter(getContext(), arrayNews3);

        rvDSCN.setHasFixedSize(true);
        rvDSCN.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvTTMT.setHasFixedSize(true);
        rvTTMT.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvCNTL.setHasFixedSize(true);
        rvCNTL.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }
}
