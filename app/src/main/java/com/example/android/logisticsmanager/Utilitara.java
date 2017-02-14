package com.example.android.logisticsmanager;

/**
 * Created by Dragos Andrei Olaru on 10.02.2017.
 */

public class Utilitara extends Auto {
    Boolean remorca;

    public Utilitara(String nr_inm, String marca, String tip, String data, String sofer, Boolean remorca) {
        super(nr_inm, marca, tip, data, sofer);
        this.remorca = remorca;
    }

    public Utilitara(Auto auto, Boolean remorca) {
        super();
        this.remorca = remorca;
    }

    public Utilitara(Auto a) {
    }

    public Utilitara(Boolean remorca) {
        this.remorca = remorca;
    }

    public Utilitara() {
    }

    public Boolean getRemorca() {
        return remorca;
    }

    public void setRemorca(Boolean remorca) {
        this.remorca = remorca;
    }
//    public String getStareRemorca(){
//        if (getRemorca()==true){
//            return "Are Remorca";
//        }else
//            return "Nu are remorca";
//    }
}
