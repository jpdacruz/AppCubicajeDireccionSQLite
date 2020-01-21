package com.jpdacruz.appcubicajedireccion.clases;

public class Celda {

    private int idAuto;
    private String id;
    private String tipoGrano;
    private double phGrano;
    private double tamañoCelda;
    private double altoGrano;
    private double cono;
    private String tipoCelda;
    private double copete;
    private double totalm3;
    private double totaltons;

    public Celda(String id, String tipoGrano,
                 double phGrano, double tamañoCelda, double altoGrano,
                 double cono, double copete, String tipoCelda, double totalm3, double totaltons) {

        this.id = id;
        this.tipoGrano = tipoGrano;
        this.phGrano = phGrano;
        this.tamañoCelda = tamañoCelda;
        this.altoGrano = altoGrano;
        this.cono = cono;
        this.copete = copete;
        this.tipoCelda = tipoCelda;
        this.totalm3 = totalm3;
        this.totaltons = totaltons;
    }

    public Celda() {

    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
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

    public double getTamañoCelda() {
        return tamañoCelda;
    }

    public void setTamañoCelda(double tamañoCelda) {
        this.tamañoCelda = tamañoCelda;
    }

    public double getAltoGrano() {
        return altoGrano;
    }

    public void setAltoGrano(double altoGrano) {
        this.altoGrano = altoGrano;
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

    public String getTipoCelda() {
        return tipoCelda;
    }

    public void setTipoCelda(String tipoCelda) {
        this.tipoCelda = tipoCelda;
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
                "idAuto=" + idAuto +
                ", id='" + id + '\'' +
                ", tipoGrano='" + tipoGrano + '\'' +
                ", phGrano=" + phGrano +
                ", tamañoCelda=" + tamañoCelda +
                ", altoGrano=" + altoGrano +
                ", cono=" + cono +
                ", tipoCelda='" + tipoCelda + '\'' +
                ", copete=" + copete +
                ", totalm3=" + totalm3 +
                ", totaltons=" + totaltons +
                '}';
    }
}
