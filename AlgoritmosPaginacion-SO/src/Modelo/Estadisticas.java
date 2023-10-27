/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author megui
 */
public class Estadisticas {
    public int nProcesos;
    public int simTiempo;
    public int ramKB ;
    public float ramPorcentaje;
    public int virtualKB ;
    public float virtualPorcentaje;
    public int paginasCargadas;
    public int paginasSinCargar;
    public int desperdicioTiempo;
    public float desperdicioPorcentaje;
    public float fragmentacion;

    public Estadisticas(){
    }

    public int nProcesos() {
        return nProcesos;
    }

    public void nProcesos(int nProcesos) {
        this.nProcesos = nProcesos;
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
    
    public void setDesperdicioPorcentaje(int desperdicioPorcentaje) {
        this.desperdicioPorcentaje = desperdicioPorcentaje;
    }
    
    
    
}
