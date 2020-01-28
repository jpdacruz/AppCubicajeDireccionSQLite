package com.jpdacruz.appcubicajedireccion.clases;

import java.io.Serializable;

public class SiloSuma implements Serializable {

    private String tipoGrano;
    private double cubicaje;

    public SiloSuma() {
    }

    public SiloSuma(String tipoGrano, double cubicaje) {
        this.tipoGrano = tipoGrano;
        this.cubicaje = cubicaje;
    }

    public String getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(String tipoGrano) {
        this.tipoGrano = tipoGrano;
    }

    public double getCubicaje() {
        return cubicaje;
    }

    public void setCubicaje(double cubicaje) {
        this.cubicaje = cubicaje;
    }

    @Override
    public String toString() {
        return "SiloSuma{" +
                "tipoGrano='" + tipoGrano + '\'' +
                ", cubicaje=" + cubicaje +
                '}';
    }
}
