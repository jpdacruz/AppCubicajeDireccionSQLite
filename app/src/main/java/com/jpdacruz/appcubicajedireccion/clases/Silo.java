package com.jpdacruz.appcubicajedireccion.clases;

public class Silo {

    private String id;
    private String tipoGrano;
    private double phGrano;
    private double diametro;
    private double largoChapa;
    private int cantChapasLargo;
    private double altoChapa;
    private double altoGrano;
    private double volumenGrano;
    private double cono;
    private double copete;
    private double totalm3;
    private double totaltons;

    public Silo() {
    }

    public Silo(String id,
                String tipoGrano,
                double phGrano,
                double diametro,
                double largoChapa,
                int cantChapasLargo,
                double altoChapa,
                double altoGrano,
                double volumenGrano,
                double cono,
                double copete,
                double totalm3,
                double totaltons) {

        this.id = id;
        this.tipoGrano = tipoGrano;
        this.phGrano = phGrano;
        this.diametro = diametro;
        this.largoChapa = largoChapa;
        this.cantChapasLargo = cantChapasLargo;
        this.altoChapa = altoChapa;
        this.altoGrano = altoGrano;
        this.volumenGrano = volumenGrano;
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

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    public double getLargoChapa() {
        return largoChapa;
    }

    public void setLargoChapa(double largoChapa) {
        this.largoChapa = largoChapa;
    }

    public int getCantChapasLargo() {
        return cantChapasLargo;
    }

    public void setCantChapasLargo(int cantChapasLargo) {
        this.cantChapasLargo = cantChapasLargo;
    }

    public double getAltoChapa() {
        return altoChapa;
    }

    public void setAltoChapa(double altoChapa) {
        this.altoChapa = altoChapa;
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
}
