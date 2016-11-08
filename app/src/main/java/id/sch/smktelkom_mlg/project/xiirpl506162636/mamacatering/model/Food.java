package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.model;


import java.io.Serializable;

public class Food implements Serializable {
    public String judul;
    public String deskripsi;
    public String detail;
    public String price;
    public String foto;


    public Food(String judul, String deskripsi, String detail, String price, String foto) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.detail = detail;
        this.price = price;
        this.foto = foto;
    }
}