package com.example.android.logisticsmanager;

/**
 * Created by Dragos Andrei Olaru on 10.02.2017.
 */

public class Utilitara extends Auto {
    String licenta;

    public Utilitara(String nr_inm, String marca, String tip, String data, String sofer, String licenta) {
        super(nr_inm, marca, tip, data, sofer);
        this.licenta = licenta;
    }

    public Utilitara(Auto a) {
    }

    public Utilitara(String licenta) {
        this.licenta = licenta;
    }

    public Utilitara() {
    }
    //    public Utilitara(Auto aut, String licenta) {
//        this.licenta = licenta;
//    }

    public String getLicenta() {
        return licenta;
    }

    public void setLicenta(String licenta) {
        this.licenta = licenta;
    }

    public String areRemorca() {
        return "cu remorca";
    }
}
