package com.jpdacruz.appcubicajedireccion.clases;

public class Estructura {

    private String estructura;
    private int imagenEstructura;


    public Estructura(String estructura, int imagenEstructura) {
        this.estructura = estructura;
        this.imagenEstructura = imagenEstructura;

    }

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public int getImagenEstructura() {
        return imagenEstructura;
    }

    public void setImagenEstructura(int imagenEstructura) {
        this.imagenEstructura = imagenEstructura;
    }
}
