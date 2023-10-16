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
public class Proceso {

    public int pid;
    private ArrayList<Instruccion> instrucciones;

    public Proceso(int pid, ArrayList<Instruccion> instrucciones) {
        this.pid = pid;
        this.instrucciones = instrucciones;
    }
    public Proceso() {
      }
    
    

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(ArrayList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }
    
  
}
