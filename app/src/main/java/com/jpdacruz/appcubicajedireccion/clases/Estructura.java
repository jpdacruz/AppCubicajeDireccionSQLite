package com.jpdacruz.appcubicajedireccion.clases;

public class Estructura {

    private String estructura;
    private int imagenEstructura;
    private int imagenFlecha;

    public Estructura(String estructura, int imagenEstructura, int imagenFlecha) {
        this.estructura = estructura;
        this.imagenEstructura = imagenEstructura;
        this.imagenFlecha = imagenFlecha;
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

    public int getImagenFlecha() {
        return imagenFlecha;
    }

    public void setImagenFlecha(int imagenFlecha) {
        this.imagenFlecha = imagenFlecha;
    }
}
