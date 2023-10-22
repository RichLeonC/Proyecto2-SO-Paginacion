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
    public int direccionFisica;
    public int direccionVirtual;
    public int direccionDisco;
    public boolean banderaRV;
    public String  marking;
    public String timestamp;

    public Pagina(int id, int direccionFisica, int direccionVirtual, int direccionDisco, boolean banderaRV, String marking, String timestamp) {
        this.id = id;
        this.direccionFisica = direccionFisica;
        this.direccionVirtual = direccionVirtual;
        this.direccionDisco = direccionDisco;
        this.banderaRV = banderaRV;
        this.marking = marking;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDireccionFisica() {
        return direccionFisica;
    }

    public void setDireccionFisica(int direccionFisica) {
        this.direccionFisica = direccionFisica;
    }

    public int getDireccionVirtual() {
        return direccionVirtual;
    }

    public void setDireccionVirtual(int direccionVirtual) {
        this.direccionVirtual = direccionVirtual;
    }

    public int getDireccionDisco() {
        return direccionDisco;
    }

    public void setDireccionDisco(int direccionDisco) {
        this.direccionDisco = direccionDisco;
    }

    public boolean isBanderaRV() {
        return banderaRV;
    }

    public void setBanderaRV(boolean banderaRV) {
        this.banderaRV = banderaRV;
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
    
    
    public Pagina() {
    }
    
}
