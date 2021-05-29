package com.example.vnews.fragmentJ;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.vnews.Activity.LogRegActivity;
import com.example.vnews.Activity.MainActivity;
import com.example.vnews.R;
import com.example.vnews.Url.Url;
import com.example.vnews.modelJ.News;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentProfile extends Fragment {

    private Context mContext;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    LinearLayout llLogout;
    ImageView ivAvatar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        anhXa(view);
        return view;
    }

    private void init() {
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivities(new Intent[]{intent});
            }
        });
    }

    private void anhXa(View view) {
        ivAvatar = view.findViewById(R.id.ivAvatar);
        llLogout = view.findViewById(R.id.llLogout);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = sharedPreferences.edit();
    }

    private void checkLogin() {
        initPreferences();
        String savedData = sharedPreferences.getString("idUser", "");
        if(savedData.length() == 0 ){
            Intent intent = new Intent(mContext, LogRegActivity.class);
            mContext.startActivities(new Intent[]{intent});
        }else{
            init();
            getAvatar(savedData);
        }
    }

    private void getAvatar(String savedData) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url.urlUser + savedData, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    String id = "";
                    String userName = "";
                    String email = "";
                    String avatar = "";

                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getString("id");
                            userName = jsonObject.getString("user-name");
                            email = jsonObject.getString("email");
                            avatar = jsonObject.getString("avatar");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Picasso.with(mContext).load(avatar).into(ivAvatar);
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
