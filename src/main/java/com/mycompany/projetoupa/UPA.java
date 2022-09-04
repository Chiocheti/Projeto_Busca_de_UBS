/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetoupa;

/**
 *
 * @author gabri
 */
public class UPA {
    
    private Integer CNES;
    private Integer UFM;
    private Integer IBGE;
    private String Nome;
    private String Logradouro;
    private String Bairro;

    public UPA(Integer CNES, Integer UFM, Integer IBGE, String Nome, String Logradouro, String Bairro) {
        this.CNES = CNES;
        this.UFM = UFM;
        this.IBGE = IBGE;
        this.Nome = Nome;
        this.Logradouro = Logradouro;
        this.Bairro = Bairro;
    }

    public Integer getCNES() {
        return CNES;
    }

    public void setCNES(Integer CNES) {
        this.CNES = CNES;
    }

    public Integer getUF() {
        return UFM;
    }

    public void setUF(Integer UFM) {
        this.UFM = UFM;
    }

    public Integer getIBGE() {
        return IBGE;
    }

    public void setIBGE(Integer IBGE) {
        this.IBGE = IBGE;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String Logradouro) {
        this.Logradouro = Logradouro;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    @Override
    public String toString() {
        return "UPA{" + "CNES=" + CNES + ", UFM=" + UFM + ", IBGE=" + IBGE + ", Nome=" + Nome + ", Logradouro=" + Logradouro + ", Bairro=" + Bairro + '}';
    }
}
