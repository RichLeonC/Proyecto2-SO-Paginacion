/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author megui
 */
public class Estadisticas {
    public int nProcesis;
    public String simTiempo;
    public int ramKB ;
    public float ramPorcentaje;
    public int paginasCargadas;
    public int paginasSinCargar;
    public String desperdicioTiempo;
    public int desperdicioPorcentaje;

    public Estadisticas(int nProcesis, String simTiempo, int ramKB, float ramPorcentaje, int paginasCargadas, int paginasSinCargar, String desperdicioTiempo, int desperdicioPorcentaje) {
        this.nProcesis = nProcesis;
        this.simTiempo = simTiempo;
        this.ramKB = ramKB;
        this.ramPorcentaje = ramPorcentaje;
        this.paginasCargadas = paginasCargadas;
        this.paginasSinCargar = paginasSinCargar;
        this.desperdicioTiempo = desperdicioTiempo;
        this.desperdicioPorcentaje = desperdicioPorcentaje;
    }

    public int getnProcesis() {
        return nProcesis;
    }

    public void setnProcesis(int nProcesis) {
        this.nProcesis = nProcesis;
    }

    public String getSimTiempo() {
        return simTiempo;
    }

    public void setSimTiempo(String simTiempo) {
        this.simTiempo = simTiempo;
    }

    public int getRamKB() {
        return ramKB;
    }

    public void setRamKB(int ramKB) {
        this.ramKB = ramKB;
    }

    public float getRamPorcentaje() {
        return ramPorcentaje;
    }

    public void setRamPorcentaje(float ramPorcentaje) {
        this.ramPorcentaje = ramPorcentaje;
    }

    public int getPaginasCargadas() {
        return paginasCargadas;
    }

    public void setPaginasCargadas(int paginasCargadas) {
        this.paginasCargadas = paginasCargadas;
    }

    public int getPaginasSinCargar() {
        return paginasSinCargar;
    }

    public void setPaginasSinCargar(int paginasSinCargar) {
        this.paginasSinCargar = paginasSinCargar;
    }

    public String getDesperdicioTiempo() {
        return desperdicioTiempo;
    }

    public void setDesperdicioTiempo(String desperdicioTiempo) {
        this.desperdicioTiempo = desperdicioTiempo;
    }

    public int getDesperdicioPorcentaje() {
        return desperdicioPorcentaje;
    }

    public void setDesperdicioPorcentaje(int desperdicioPorcentaje) {
        this.desperdicioPorcentaje = desperdicioPorcentaje;
    }
    
    
    
}
