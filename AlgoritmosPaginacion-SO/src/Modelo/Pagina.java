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

    public String id;    
    public String pid;
    public String loaded;
    public String direccionVirtual;
    public String direccionFisica;
    public String direccionDisco;
    public String timestamp;
    public String  marking;
    public float size; 

    public Pagina() {
    }
    

    public Pagina(String id, String pid, String loaded, String direccionVirtual,String direccionFisica,  String direccionDisco, String timestamp,String marking, float size) {
        this.id = id;
        this.pid = pid;
        this.loaded = loaded;
        this.direccionVirtual = direccionVirtual;
        this.direccionFisica = direccionFisica;
        this.direccionDisco = direccionDisco;
        this.marking = marking;
        this.timestamp = timestamp;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccionFisica() {
        return direccionFisica;
    }

    public void setDireccionFisica(String direccionFisica) {
        this.direccionFisica = direccionFisica;
    }

    public String getDireccionVirtual() {
        return direccionVirtual;
    }

    public void setDireccionVirtual(String direccionVirtual) {
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
