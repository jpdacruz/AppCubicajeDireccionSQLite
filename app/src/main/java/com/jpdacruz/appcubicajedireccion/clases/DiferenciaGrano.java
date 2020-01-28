package com.jpdacruz.appcubicajedireccion.clases;

public class DiferenciaGrano  {

    private int idAuto;
    private String grano;
    private double cubicajeKg;
    private double afip;
    private double diferencia;
    private double porcentaje;
    private String masOmenos;

    public DiferenciaGrano() {

    }

    public DiferenciaGrano
            (int idAuto, String grano, double cubicajeKg, double afip, double diferencia, double porcentaje, String masOmenos) {

        this.idAuto = idAuto;
        this.grano = grano;
        this.cubicajeKg = cubicajeKg;
        this.afip = afip;
        this.diferencia = diferencia;
        this.porcentaje = porcentaje;
        this.masOmenos = masOmenos;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    public String getGrano() {
        return grano;
    }

    public void setGrano(String grano) {
        this.grano = grano;
    }

    public double getCubicajeKg() {
        return cubicajeKg;
    }

    public void setCubicajeKg(double cubicajeKg) {
        this.cubicajeKg = cubicajeKg;
    }

    public double getAfip() {
        return afip;
    }

    public void setAfip(double afip) {
        this.afip = afip;
    }

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getMasOmenos() {
        return masOmenos;
    }

    public void setMasOmenos(String masOmenos) {
        this.masOmenos = masOmenos;
    }

    @Override
    public String toString() {
        return "DiferenciaGrano{" +
                "idAuto=" + idAuto +
                ", grano='" + grano + '\'' +
                ", cubicajeKg=" + cubicajeKg +
                ", afip=" + afip +
                ", diferencia=" + diferencia +
                ", porcentaje=" + porcentaje +
                ", masOmenos='" + masOmenos + '\'' +
                '}';
    }
}
