package com.example.android.logisticsmanager;

/**
 * Created by Dragos Andrei Olaru on 10.02.2017.
 */

public class Turism extends Auto {
    String departament;
    String combustibil;

    public Turism(Auto a, String departament, String combustibil) {
        this.departament = departament;
        this.combustibil = combustibil;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getCombustibil() {
        return combustibil;
    }

    public void setCombustibil(String combustibil) {
        this.combustibil = combustibil;
    }
}
