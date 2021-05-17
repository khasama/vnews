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

import androidx.annotation.Nullable;

import com.example.vnews.Activity.LogRegActivity;
import com.example.vnews.Activity.MainActivity;
import com.example.vnews.R;

public class FragmentProfile extends Fragment {

    private Context mContext;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button btnLogout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("asd", "profile");
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        anhXa(view);
        init();
        return view;
    }

    private void init() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
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
        btnLogout = view.findViewById(R.id.btnLogout);
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
        }
    }


}
