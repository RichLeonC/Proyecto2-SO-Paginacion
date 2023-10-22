/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author megui
 */
public class MemoryManagementUnit {
    public int memoriaReal;
    public int direccionVirtual;
    public HashMap<String, ArrayList<Pagina>> mapa;
    public ArrayList<String> tablaSimbolos;

    public int getMemoriaReal() {
        return memoriaReal;
    }

    public void setMemoriaReal(int memoriaReal) {
        this.memoriaReal = memoriaReal;
    }

    public int getDireccionVirtual() {
        return direccionVirtual;
    }

    public void setDireccionVirtual(int direccionVirtual) {
        this.direccionVirtual = direccionVirtual;
    }

    public HashMap<String, ArrayList<Pagina>> getMapa() {
        return mapa;
    }

    public void setMapa(HashMap<String, ArrayList<Pagina>> mapa) {
        this.mapa = mapa;
    }

    public ArrayList<String> getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void setTablaSimbolos(ArrayList<String> tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }
    
    public void ejecutarIntruccionesProceso(Proceso proceso){
    }
     
    public String intruccionNew(Instruccion instruccion){
        return null;
    }
    
    public void guardarPuntero(String puntero){
    }
    
    public void instruccionUse(Instruccion instruccion){
    }
    
    public void instruccionDelete(Instruccion instruccion){
    }
     
    public void instruccionKil(Instruccion instruccion){
    }
    
}
