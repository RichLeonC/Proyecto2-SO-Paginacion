/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author megui
 */
public class Pagina {

    public int id;    
    public String pid;
    public String loaded;
    public int direccionVirtual;
    public String direccionFisica;
    public String direccionDisco;
    public String timestamp;
    public String  marking;
    

    public Pagina(int id, String pid, String loaded, int direccionVirtual,String direccionFisica,  String direccionDisco, String timestamp,String marking) {
        this.id = id;
        this.pid = pid;
        this.loaded = loaded;
        this.direccionVirtual = direccionVirtual;
        this.direccionFisica = direccionFisica;
        this.direccionDisco = direccionDisco;
        this.marking = marking;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionFisica() {
        return direccionFisica;
    }

    public void setDireccionFisica(String direccionFisica) {
        this.direccionFisica = direccionFisica;
    }

    public int getDireccionVirtual() {
        return direccionVirtual;
    }

    public void setDireccionVirtual(int direccionVirtual) {
        this.direccionVirtual = direccionVirtual;
    }

    public String getDireccionDisco() {
        return direccionDisco;
    }

    public void setDireccionDisco(String direccionDisco) {
        this.direccionDisco = direccionDisco;
    }

    public boolean isLoaded() {
        return loaded.equals("X");
    }

    public void setBanderaRV(String banderaRV) {
        this.loaded = banderaRV;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    
}
