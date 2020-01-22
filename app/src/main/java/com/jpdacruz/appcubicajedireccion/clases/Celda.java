package com.jpdacruz.appcubicajedireccion.clases;

import java.io.Serializable;

public class Celda implements Serializable {

    private int idAuto;
    private String id;
    private String tipoGrano;
    private double phGrano;
    private double largoCelda;
    private double anchoCelda;
    private double altoGrano;
    private String tipoCelda;
    private double cono;
    private double copete;
    private double totalm3;
    private double totaltons;

    public Celda() {
    }

    public Celda(int idAuto, String id, String tipoGrano, double phGrano, double largoCelda,
                 double anchoCelda, double altoGrano, String tipoCelda, double cono,
                 double copete, double totalm3, double totaltons) {

        this.idAuto = idAuto;
        this.id = id;
        this.tipoGrano = tipoGrano;
        this.phGrano = phGrano;
        this.largoCelda = largoCelda;
        this.anchoCelda = anchoCelda;
        this.altoGrano = altoGrano;
        this.cono = cono;
        this.tipoCelda = tipoCelda;
        this.copete = copete;
        this.totalm3 = totalm3;
        this.totaltons = totaltons;
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

    public String getTipoCelda() {
        return tipoCelda;
    }

    public void setTipoCelda(String tipoCelda) {
        this.tipoCelda = tipoCelda;
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
                "idAuto=" + idAuto +
                ", id='" + id + '\'' +
                ", tipoGrano='" + tipoGrano + '\'' +
                ", phGrano=" + phGrano +
                ", largoCelda=" + largoCelda +
                ", anchoCelda=" + anchoCelda +
                ", altoGrano=" + altoGrano +
                ", cono=" + cono +
                ", tipoCelda='" + tipoCelda + '\'' +
                ", copete=" + copete +
                ", totalm3=" + totalm3 +
                ", totaltons=" + totaltons +
                '}';
    }
}
