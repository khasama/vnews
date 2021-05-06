package com.example.vnews.modelJ;

public class News {
    public String ID;
    public String Title;
    public String NgayDang;
    public String Nguon;
    public String Loai;
    public String Anh;

    public News(String ID, String title, String ngayDang, String nguon, String loai, String anh) {
        this.ID = ID;
        Title = title;
        NgayDang = ngayDang;
        Nguon = nguon;
        Loai = loai;
        Anh = anh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(String ngayDang) {
        NgayDang = ngayDang;
    }

    public String getNguon() {
        return Nguon;
    }

    public void setNguon(String nguon) {
        Nguon = nguon;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }
}
