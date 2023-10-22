/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author megui
 */
public class MemoryManagementUnit {
    public int memoriaReal;
    public int memoriaOcupada;
    public int direccionVirtual;
    public HashMap<String, ArrayList<Pagina>> mapa;
    public ArrayList<String> tablaSimbolos;
    public int idActual;
    
    public MemoryManagementUnit(){
        this.mapa = new HashMap();
        this.tablaSimbolos = new ArrayList();
        idActual = 0;
        this.memoriaReal = 100;
    }

    public int getMemoriaOcupada() {
        return memoriaOcupada;
    }

    public void setMemoriaOcupada(int memoriaOcupada) {
        this.memoriaOcupada = memoriaOcupada;
    }

    public int getIdActual() {
        return idActual;
    }

    public void setIdActual(int idActual) {
        this.idActual = idActual;
    }

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
        if(mapa.size() < memoriaReal){
            int numPaginas = Integer.parseInt(instruccion.getParametros().get(1))%4==0 ? 
                                Integer.parseInt(instruccion.getParametros().get(1))/4 
                                : Integer.parseInt(instruccion.getParametros().get(1))/4 + 1;
            System.out.println("Proceso: " + instruccion.getParametros().get(0) + " Numero de paginas necesarias: " + numPaginas);
            ArrayList<Pagina> paginasValores = new ArrayList();
            for(int i = 0; i < numPaginas; i++){
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                Pagina pagina = new Pagina(idActual,1,1,1,true,"1",formattedDate);
                paginasValores.add(pagina);
                idActual++;
            }
            if(mapa.get(instruccion.getParametros().get(0)) != null){
                mapa.get(instruccion.getParametros().get(0)).addAll(paginasValores);
            }else{
                mapa.put(instruccion.getParametros().get(0), paginasValores);
            }
        }else{
            System.out.println("Se quedo sin espacio");
        }
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
