package com.jpdacruz.appcubicajedireccion.clases;

import java.io.Serializable;

public class Silo implements Serializable {

    private int idAuto;
    private String id;
    private String tipoGrano;
    private double phGrano;
    private double diametro;
    private double altoGrano;
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
                double altoGrano,
                double cono,
                double copete,
                double totalm3,
                double totaltons) {

        this.id = id;
        this.tipoGrano = tipoGrano;
        this.phGrano = phGrano;
        this.diametro = diametro;
        this.altoGrano = altoGrano;
        this.cono = cono;
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

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
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
        return "Silo{" +
                "idAuto=" + idAuto +
                ", id='" + id + '\'' +
                ", tipoGrano='" + tipoGrano + '\'' +
                ", phGrano=" + phGrano +
                ", diametro=" + diametro +
                ", altoGrano=" + altoGrano +
                ", cono=" + cono +
                ", copete=" + copete +
                ", totalm3=" + totalm3 +
                ", totaltons=" + totaltons +
                '}';
    }
}
