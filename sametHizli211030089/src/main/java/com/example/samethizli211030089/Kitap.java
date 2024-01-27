package com.example.samethizli211030089;

import java.io.Serializable;
import java.time.LocalDate;

public class Kitap implements Serializable {
    private String KitapAdi,YazarAdi,KitapDili,KitapTürü,YayinEvi;
    private Integer IsbnNo,Basımyili;
    private LocalDate yayinTarihi;


    public Kitap(String kitapAdi, String yazarAdi, String kitapDili, String kitapTürü, String yayinEvi, Integer isbnNo, Integer basımyili, LocalDate yayinTarihi) {
        this.KitapAdi = kitapAdi;
        this.YazarAdi = yazarAdi;
        this.KitapDili = kitapDili;
        this.KitapTürü = kitapTürü;
        this.YayinEvi = yayinEvi;
        this.IsbnNo = isbnNo;
        this.Basımyili = basımyili;
        this.yayinTarihi = yayinTarihi;
    }

    public String getKitapAdi() {
        return KitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        KitapAdi = kitapAdi;
    }

    public String getYazarAdi() {
        return YazarAdi;
    }

    public void setYazarAdi(String yazarAdi) {
        YazarAdi = yazarAdi;
    }

    public String getKitapDili() {
        return KitapDili;
    }

    public void setKitapDili(String kitapDili) {
        KitapDili = kitapDili;
    }

    public String getKitapTürü() {
        return KitapTürü;
    }

    public void setKitapTürü(String kitapTürü) {
        KitapTürü = kitapTürü;
    }

    public String getYayinEvi() {
        return YayinEvi;
    }

    public void setYayinEvi(String yayinEvi) {
        YayinEvi = yayinEvi;
    }

    public Integer getIsbnNo() {
        return IsbnNo;
    }

    public void setIsbnNo(Integer isbnNo) {
        IsbnNo = isbnNo;
    }

    public Integer getBasımyili() {
        return Basımyili;
    }

    public void setBasımyili(Integer basımyili) {
        Basımyili = basımyili;
    }

    public LocalDate getYayinTarihi() {
        return yayinTarihi;
    }

    public void setYayinTarihi(LocalDate yayinTarihi) {
        this.yayinTarihi = yayinTarihi;
    }
}
