package com.example.vnews.adapterJ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vnews.R;
import com.example.vnews.modelJ.MenuRight;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuRightAdapter extends BaseAdapter {
    ArrayList<MenuRight> arrMenuright;
    Context context;

    public MenuRightAdapter(ArrayList<MenuRight> arrMenuright, Context context) {
        this.arrMenuright = arrMenuright;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrMenuright.size();
    }

    @Override
    public Object getItem(int position) {
        return arrMenuright.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView tvTenMenu;
        ImageView ivAnhMenu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_menuphai, null);
            viewHolder.tvTenMenu = convertView.findViewById(R.id.tvTenMenu);
            viewHolder.ivAnhMenu = convertView.findViewById(R.id.ivAnhMenu);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MenuRight menuRight = (MenuRight) getItem(position);
        viewHolder.tvTenMenu.setText(menuRight.getTenMenu());
        Picasso.with(context).load(menuRight.getAnhMenu()).into(viewHolder.ivAnhMenu);
        return convertView;
    }
}
