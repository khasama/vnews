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

public class FragmentKhampha extends Fragment {
    private Context mContext;
    RecyclerView rvMayTinh, rvIpad, rvDienThoai;
    ArrayList<News> arrayMayTinh, arrayIpad, arrayDienThoai;
    NewsAdapter newsAdapter1, newsAdapter2, newsAdapter3;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("asd", "khampha");
        View view = inflater.inflate(R.layout.fragment_khampha, container, false);
        anhXa(view);
        getDataMT();
        getDataIP();
        getDataDT();
        return view;
    }

    private void getDataDT() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.getTechNews("5"), response -> {
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
                        arrayDienThoai.add(new News(id, Title, NgayDang, Nguon, Loai, Anh));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rvDienThoai.setAdapter(newsAdapter3);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataIP() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.getTechNews("1"), response -> {
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
                        arrayIpad.add(new News(id, Title, NgayDang, Nguon, Loai, Anh));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rvIpad.setAdapter(newsAdapter2);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataMT() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.getTechNews("13"), response -> {
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
                        arrayMayTinh.add(new News(id, Title, NgayDang, Nguon, Loai, Anh));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                rvMayTinh.setAdapter(newsAdapter1);
            }
        }, error -> Log.e("aaa", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    private void anhXa(View view) {
        rvMayTinh = view.findViewById(R.id.rvMayTinh);
        rvIpad = view.findViewById(R.id.rvIpad);
        rvDienThoai = view.findViewById(R.id.rvDienThoai);

        arrayMayTinh = new ArrayList<>();
        arrayIpad = new ArrayList<>();
        arrayDienThoai = new ArrayList<>();

        newsAdapter1 = new NewsAdapter(getContext(), arrayMayTinh);
        newsAdapter2 = new NewsAdapter(getContext(), arrayIpad);
        newsAdapter3 = new NewsAdapter(getContext(), arrayDienThoai);

        rvMayTinh.setHasFixedSize(true);
        rvMayTinh.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvIpad.setHasFixedSize(true);
        rvIpad.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvDienThoai.setHasFixedSize(true);
        rvDienThoai.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
