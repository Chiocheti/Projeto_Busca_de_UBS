/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetoupa;

/**
 *
 * @author gabri
 */
public class Municipio {
    
    private String UF;
    private String Nome_UF;
    private String Reg_Geo_Inter;
    private String Nome_Reg_Geo_Inter;
    private String Reg_Geo_Imed;
    private String Nome_Reg_Geo_Imed;
    private String Mes_Geo;
    private String Nome_Mes_Geo;
    private String Mic_Reg_Geo;
    private String Nome_Mic_Reg_Geo;
    private String Municipio;
    private String Cod_Muni_Comp;
    private String Nome_Munic;

    public Municipio(String UF, String Nome_UF, String Reg_Geo_Inter, String Nome_Reg_Geo_Inter, String Reg_Geo_Imed, String Nome_Reg_Geo_Imed, String Mes_Geo, String Nome_Mes_Geo, String Mic_Reg_Geo, String Nome_Mic_Reg_Geo, String Municipio, String Cod_Muni_Comp, String Nome_Munic) {
        this.UF = UF;
        this.Nome_UF = Nome_UF;
        this.Reg_Geo_Inter = Reg_Geo_Inter;
        this.Nome_Reg_Geo_Inter = Nome_Reg_Geo_Inter;
        this.Reg_Geo_Imed = Reg_Geo_Imed;
        this.Nome_Reg_Geo_Imed = Nome_Reg_Geo_Imed;
        this.Mes_Geo = Mes_Geo;
        this.Nome_Mes_Geo = Nome_Mes_Geo;
        this.Mic_Reg_Geo = Mic_Reg_Geo;
        this.Nome_Mic_Reg_Geo = Nome_Mic_Reg_Geo;
        this.Municipio = Municipio;
        this.Cod_Muni_Comp = Cod_Muni_Comp;
        this.Nome_Munic = Nome_Munic;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getNome_UF() {
        return Nome_UF;
    }

    public void setNome_UF(String Nome_UF) {
        this.Nome_UF = Nome_UF;
    }

    public String getReg_Geo_Inter() {
        return Reg_Geo_Inter;
    }

    public void setReg_Geo_Inter(String Reg_Geo_Inter) {
        this.Reg_Geo_Inter = Reg_Geo_Inter;
    }

    public String getNome_Reg_Geo_Inter() {
        return Nome_Reg_Geo_Inter;
    }

    public void setNome_Reg_Geo_Inter(String Nome_Reg_Geo_Inter) {
        this.Nome_Reg_Geo_Inter = Nome_Reg_Geo_Inter;
    }

    public String getReg_Geo_Imed() {
        return Reg_Geo_Imed;
    }

    public void setReg_Geo_Imed(String Reg_Geo_Imed) {
        this.Reg_Geo_Imed = Reg_Geo_Imed;
    }

    public String getNome_Reg_Geo_Imed() {
        return Nome_Reg_Geo_Imed;
    }

    public void setNome_Reg_Geo_Imed(String Nome_Reg_Geo_Imed) {
        this.Nome_Reg_Geo_Imed = Nome_Reg_Geo_Imed;
    }

    public String getMes_Geo() {
        return Mes_Geo;
    }

    public void setMes_Geo(String Mes_Geo) {
        this.Mes_Geo = Mes_Geo;
    }

    public String getNome_Mes_Geo() {
        return Nome_Mes_Geo;
    }

    public void setNome_Mes_Geo(String Nome_Mes_Geo) {
        this.Nome_Mes_Geo = Nome_Mes_Geo;
    }

    public String getMic_Reg_Geo() {
        return Mic_Reg_Geo;
    }

    public void setMic_Reg_Geo(String Mic_Reg_Geo) {
        this.Mic_Reg_Geo = Mic_Reg_Geo;
    }

    public String getNome_Mic_Reg_Geo() {
        return Nome_Mic_Reg_Geo;
    }

    public void setNome_Mic_Reg_Geo(String Nome_Mic_Reg_Geo) {
        this.Nome_Mic_Reg_Geo = Nome_Mic_Reg_Geo;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public String getCod_Muni_Comp() {
        return Cod_Muni_Comp;
    }

    public void setCod_Muni_Comp(String Cod_Muni_Comp) {
        this.Cod_Muni_Comp = Cod_Muni_Comp;
    }

    public String getNome_Munic() {
        return Nome_Munic;
    }

    public void setNome_Munic(String Nome_Munic) {
        this.Nome_Munic = Nome_Munic;
    }

    @Override
    public String toString() {
        return "Municipio{" + "UF=" + UF + ", Nome_UF=" + Nome_UF + ", Reg_Geo_Inter=" + Reg_Geo_Inter + ", Nome_Reg_Geo_Inter=" + Nome_Reg_Geo_Inter + ", Reg_Geo_Imed=" + Reg_Geo_Imed + ", Nome_Reg_Geo_Imed=" + Nome_Reg_Geo_Imed + ", Mes_Geo=" + Mes_Geo + ", Nome_Mes_Geo=" + Nome_Mes_Geo + ", Mic_Reg_Geo=" + Mic_Reg_Geo + ", Nome_Mic_Reg_Geo=" + Nome_Mic_Reg_Geo + ", Municipio=" + Municipio + ", Cod_Muni_Comp=" + Cod_Muni_Comp + ", Nome_Munic=" + Nome_Munic + '}';
    }
}