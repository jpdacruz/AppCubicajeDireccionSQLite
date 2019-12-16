package com.jpdacruz.appcubicajedireccion.clases;

public class Celda {

    private String id;
    private String tipoGrano;
    private double phGrano;
    private double largoCelda;
    private double anchoCelda;
    private double altoCelda;
    private double altoGrano;
    private double volumenGrano;
    private double volumenM3;
    private double cono;
    private double copete;
    private double totalm3;
    private double totaltons;

    public Celda() {
    }

    public Celda(String id,
                 String tipoGrano,
                 double phGrano,
                 double largoCelda,
                 double anchoCelda,
                 double altoCelda,
                 double altoGrano,
                 double volumenGrano,
                 double volumenM3,
                 double cono,
                 double copete,
                 double totalm3,
                 double totaltons) {

        this.id = id;
        this.tipoGrano = tipoGrano;
        this.phGrano = phGrano;
        this.largoCelda = largoCelda;
        this.anchoCelda = anchoCelda;
        this.altoCelda = altoCelda;
        this.altoGrano = altoGrano;
        this.volumenGrano = volumenGrano;
        this.volumenM3 = volumenM3;
        this.cono = cono;
        this.copete = copete;
        this.totalm3 = totalm3;
        this.totaltons = totaltons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoGrano() {
        return tipoGrano;
    }

    public void setTipoGrano(String tipoGrano) {
        this.tipoGrano = tipoGrano;
    }

    public double getPhGrano() {
        return phGrano;
    }

    public void setPhGrano(double phGrano) {
        this.phGrano = phGrano;
    }

    public double getLargoCelda() {
        return largoCelda;
    }

    public void setLargoCelda(double largoCelda) {
        this.largoCelda = largoCelda;
    }

    public double getAnchoCelda() {
        return anchoCelda;
    }

    public void setAnchoCelda(double anchoCelda) {
        this.anchoCelda = anchoCelda;
    }

    public double getAltoCelda() {
        return altoCelda;
    }

    public void setAltoCelda(double altoCelda) {
        this.altoCelda = altoCelda;
    }

    public double getAltoGrano() {
        return altoGrano;
    }

    public void setAltoGrano(double altoGrano) {
        this.altoGrano = altoGrano;
    }

    public double getVolumenGrano() {
        return volumenGrano;
    }

    public void setVolumenGrano(double volumenGrano) {
        this.volumenGrano = volumenGrano;
    }

    public double getVolumenM3() {
        return volumenM3;
    }

    public void setVolumenM3(double volumenM3) {
        this.volumenM3 = volumenM3;
    }

    public double getCono() {
        return cono;
    }

    public void setCono(double cono) {
        this.cono = cono;
    }

    public double getCopete() {
        return copete;
    }

    public void setCopete(double copete) {
        this.copete = copete;
    }

    public double getTotalm3() {
        return totalm3;
    }

    public void setTotalm3(double totalm3) {
        this.totalm3 = totalm3;
    }

    public double getTotaltons() {
        return totaltons;
    }

    public void setTotaltons(double totaltons) {
        this.totaltons = totaltons;
    }

    @Override
    public String toString() {
        return "Celda{" +
                "id='" + id + '\'' +
                ", tipoGrano='" + tipoGrano + '\'' +
                ", phGrano=" + phGrano +
                ", largoCelda=" + largoCelda +
                ", anchoCelda=" + anchoCelda +
                ", altoCelda=" + altoCelda +
                ", altoGrano=" + altoGrano +
                ", volumenGrano=" + volumenGrano +
                ", volumenM3=" + volumenM3 +
                ", cono=" + cono +
                ", copete=" + copete +
                ", totalm3=" + totalm3 +
                ", totaltons=" + totaltons +
                '}';
    }
}
