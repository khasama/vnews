package com.example.vnews.Url;

import android.webkit.WebSettings;

public class Url {
    public static String domain = "http://tnews.ga/";
    public static String Banner = domain + "api/slide.php";
    public static String News = domain + "api/news.php";
    public static String HotNews = domain + "api/hotnews.php";
    public static String Content = domain + "api/content.php?idTin=";
    public static String Tinlienquan = domain + "api/tinlienquan.php?idLoaiTin=";
    public static String getTechNews(String idLoaitin){
        String url = domain+"api/congnghe.php?idLoaiTin="+idLoaitin;
        return url;
    }
    public static String getInfo(String idTin){
        String url = domain+"api/info.php?idTin="+idTin;
        return url;
    }

}
