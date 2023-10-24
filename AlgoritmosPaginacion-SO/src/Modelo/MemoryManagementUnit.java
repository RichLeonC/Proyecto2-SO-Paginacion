/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.Main;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author megui
 */
public class MemoryManagementUnit {
    public int memoriaReal;
    public int direccionVirtual;
    public int memoriaOcupada;
    public HashMap<String, ArrayList<Pagina>> mapa;
    public ArrayList<Pagina> tablaSimbolos;
    public int idActual;
    public int direccionRamActualID = 1;
    public int direccionVirtualActualID = 1;
    public int direccionDiscoActualID = 1;
    public int punteroActual = 1;
    
    public MemoryManagementUnit(){
        this.mapa = new HashMap();
        this.tablaSimbolos = new ArrayList();
        idActual = 0;
        this.memoriaReal = 100;
        this.memoriaOcupada = 0;
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

    public ArrayList<Pagina> getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void setTablaSimbolos(ArrayList<Pagina> tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }
    
    public void ejecutarIntruccionesProceso(Proceso proceso){
    }
   
    public void fifo() {

    }
    
    public int getDireccionLibre(){
        int dir;
        for(dir = 0; dir <= 101; dir++){
            if(Main.simulacion.getCellColorALG(0, dir) == Color.WHITE){
                return dir;
            }
        }
        return dir;
    }
     
    public int intruccionNew(Instruccion instruccion) throws InterruptedException{
        int numPaginas = ((Integer)Integer.parseInt(instruccion.getParametros().get(1))/4096) % 4 == 0 &&
                                ((Integer)Integer.parseInt(instruccion.getParametros().get(1))/4096)  != 0? 
                               (Integer) Integer.parseInt(instruccion.getParametros().get(1))/4096
                                : (Integer) Integer.parseInt(instruccion.getParametros().get(1))/4096 + 1;
        System.out.println("Al proceso " + instruccion.getParametros().get(0) + " Numero de paginas a crear: " + numPaginas);
        ArrayList<Pagina> paginasValores = new ArrayList();
        for(int i = 0; i < numPaginas; i++){
            int dir = getDireccionLibre();
            System.out.println("Direccion libre: " + dir);
            if(dir <= 100){
                System.out.println("HIT");
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                Pagina pagina = new Pagina(String.valueOf(idActual),instruccion.getParametros().get(0),"X",String.valueOf(direccionVirtualActualID), String.valueOf(dir+1),"",formattedDate,"true");
                paginasValores.add(pagina);
                tablaSimbolos.add(pagina);
                direccionRamActualID++;
                direccionVirtualActualID++;
                idActual++;
                memoriaOcupada++;
                TimeUnit.SECONDS.sleep(1);
            
            }else{
                System.out.println("FAIL");
                //LLAMA AL ALGORITMO
                /*Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                Pagina pagina = new Pagina(String.valueOf(idActual),instruccion.getParametros().get(0),"",String.valueOf(direccionVirtualActualID), "",String.valueOf(direccionDiscoActualID) ,formattedDate,"true");
                paginasValores.add(pagina);
                tablaSimbolos.add(pagina);
                direccionVirtualActualID++;
                direccionDiscoActualID++;
                idActual++;
                TimeUnit.SECONDS.sleep(5);*/
            }
        Main.simulacion.showPages();
        }
        String ptr = String.valueOf(punteroActual);
        punteroActual++;
        mapa.put(ptr, paginasValores);
        return direccionRamActualID;
    }
    
    public void guardarPuntero(String puntero){
    }
    
    public void instruccionUse(Instruccion instruccion) throws InterruptedException{
        if(mapa.get(instruccion.getParametros().get(0)) != null){
            for(Pagina page : mapa.get(instruccion.getParametros().get(0))){ //Por cada una de las paginas asignadas a ese puntero
                if(page.direccionFisica.equals("")){
                    System.out.println("No está en RAM");
                    //LLAMA AL ALGORITMO
                    String direccion = "-1";
                    page.setDireccionFisica(direccion);
                }
                else{
                    System.out.println("Si está en RAM");
                }
                TimeUnit.SECONDS.sleep(1);
                Main.simulacion.showPages();
            }
            
        }
        
    }
    
    public void instruccionDelete(Instruccion instruccion) throws InterruptedException{
        System.out.println("Puntero a eliminar: " + instruccion.getParametros().get(0));
        System.out.println(mapa.size());
        ArrayList<Pagina> paginas = mapa.remove(instruccion.getParametros().get(0));
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            for (int j = 0; j < paginas.size(); j++) {
                if(tablaSimbolos.get(i).id.equals(paginas.get(j).id)){
                    int ptr = Integer.parseInt(tablaSimbolos.get(i).direccionFisica)-1;
                    tablaSimbolos.remove(i);
                    Main.simulacion.setCellColorALG(0,ptr,Color.WHITE);
                    TimeUnit.SECONDS.sleep(1);
                    Main.simulacion.showPages();
                                        

                }
            }
        }
    }
    
    public void deletePuntero(String puntero) throws InterruptedException{
        System.out.println("Puntero a eliminar: " + puntero);
        System.out.println(mapa.size());
        ArrayList<Pagina> paginas = mapa.remove(puntero);
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            for (int j = 0; j < paginas.size(); j++) {
                if(tablaSimbolos.get(i).id.equals(paginas.get(j).id)){
                    int ptr = Integer.parseInt(tablaSimbolos.get(i).direccionFisica)-1;
                    tablaSimbolos.remove(i);
                    Main.simulacion.setCellColorALG(0,ptr,Color.WHITE);
                    TimeUnit.SECONDS.sleep(1);
                    Main.simulacion.showPages();
                                        

                }
            }
        }
    }
     
    public void instruccionKill(Instruccion instruccion) throws InterruptedException{
        Set<String> llavesSet = mapa.keySet();
        ArrayList<String> llaves = new ArrayList<>(llavesSet);
        for(String llave : llaves){
            ArrayList<Pagina> paginas = mapa.get(llave);
            for(int i = 0; i < paginas.size(); i++){
                if(paginas.get(i).pid.equals(String.valueOf(instruccion.getParametros().get(0)))){
                    deletePuntero(llave);
                    break;
                }
            }
        }
        TimeUnit.SECONDS.sleep(1);
        Main.simulacion.showPages();
    }
 
    private void sleep(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}
