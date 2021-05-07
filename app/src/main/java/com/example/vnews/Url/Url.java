package com.example.vnews.Url;

public class Url {
    public static String domain = "http://tnews.ga/";
    public static String Banner = domain + "api/slide.php";
    public static String News = domain + "api/news.php";
    public static String getTechNews(String idLoaitin){
        String url = domain+"api/congnghe.php?idLoaiTin="+idLoaitin;
        return url;
    }
}
