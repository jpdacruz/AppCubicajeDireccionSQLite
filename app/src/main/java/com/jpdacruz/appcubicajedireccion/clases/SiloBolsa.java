package com.jpdacruz.appcubicajedireccion.clases;

import java.io.Serializable;

public class SiloBolsa implements Serializable {

    private int idAuto;
    private String id;
    private String tipoGrano;
    private double pHgrano;
    private double largoSB;
    private double anchoSB;
    private double alturaBase;
    private double alturaParabola;
    private double metrosCubicosSB;
    private double toneladasSB;

    public SiloBolsa(String id, String tipoGrano, double pHgrano, double largoSB, double anchoSB,
                     double alturaBase, double alturaParabola, double metrosCubicosSB, double toneladasSB) {

        this.id = id;
        this.tipoGrano = tipoGrano;
        this.pHgrano = pHgrano;
        this.largoSB = largoSB;
        this.anchoSB = anchoSB;
        this.alturaBase = alturaBase;
        this.alturaParabola = alturaParabola;
        this.metrosCubicosSB = metrosCubicosSB;
        this.toneladasSB = toneladasSB;
    }

    public SiloBolsa() {
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

    public double getpHgrano() {
        return pHgrano;
    }

    public void setpHgrano(double pHgrano) {
        this.pHgrano = pHgrano;
    }

    public double getLargoSB() {
        return largoSB;
    }

    public void setLargoSB(double largoSB) {
        this.largoSB = largoSB;
    }

    public double getAnchoSB() {
        return anchoSB;
    }

    public void setAnchoSB(double anchoSB) {
        this.anchoSB = anchoSB;
    }

    public double getAlturaBase() {
        return alturaBase;
    }

    public void setAlturaBase(double alturaBase) {
        this.alturaBase = alturaBase;
    }

    public double getAlturaParabola() {
        return alturaParabola;
    }

    public void setAlturaParabola(double alturaParabola) {
        this.alturaParabola = alturaParabola;
    }

    public double getMetrosCubicosSB() {
        return metrosCubicosSB;
    }

    public void setMetrosCubicosSB(double metrosCubicosSB) {
        this.metrosCubicosSB = metrosCubicosSB;
    }

    public double getToneladasSB() {
        return toneladasSB;
    }

    public void setToneladasSB(double toneladasSB) {
        this.toneladasSB = toneladasSB;
    }

    @Override
    public String toString() {
        return "SiloBolsa{" +
                "idAuto=" + idAuto +
                ", id='" + id + '\'' +
                ", tipoGrano='" + tipoGrano + '\'' +
                ", pHgrano=" + pHgrano +
                ", largoSB=" + largoSB +
                ", anchoSB=" + anchoSB +
                ", alturaBase=" + alturaBase +
                ", alturaParabola=" + alturaParabola +
                ", metrosCubicosSB=" + metrosCubicosSB +
                ", toneladasSB=" + toneladasSB +
                '}';
    }
}
