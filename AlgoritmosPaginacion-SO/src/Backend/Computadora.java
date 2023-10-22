/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Modelo.Instruccion;
import Modelo.MemoryManagementUnit;
import Modelo.Proceso;
import java.io.File;
import java.util.ArrayList;

public class Computadora {
    
    private final int nucleosProcesamiento;
    private TipoAlgoritmo algoritmo;
    private int instruccionesPorSegundo;
    private int tiempoAccesoDisco;
    private int ram;
    private int discoDuro;
    private final int paginaSize;
    private MemoryManagementUnit mmu;
    private AdminOperaciones admOperaciones;
    private int reloj;
    private ArrayList<Proceso> procesos;
    
    public Computadora(){
        nucleosProcesamiento = 1;
        instruccionesPorSegundo = 1;
        tiempoAccesoDisco = 5; //va de 5 en 5 segundos
        ram = 400; //KB
        discoDuro = 1000;
        paginaSize = 4; //KB
        mmu = new MemoryManagementUnit();
        reloj = 1; //segundos
        procesos = new ArrayList();
    
    }
    
   public void setAlgoritmo(TipoAlgoritmo algoritmo) {
        this.algoritmo = algoritmo;
       
    }
   
   public void fifo(){ //*Por que retorna el tipo del algoritmo?
 
   
   }
   
    public void secondChance(ArrayList<Instruccion> instrucciones){
        for(Instruccion instr : instrucciones){
            switch(instr.getTipoInstruccion()){
                case NEW:
                    System.out.println("New");
                    mmu.intruccionNew(instr);
                    break;
                case USE:
                    System.out.println("Use");
                    break;
                case DELETE:
                    System.out.println("Delete");
                    break;
                case KILL:
                    System.out.println("Kill");
                    break;
                default:
                    throw new AssertionError(instr.getTipoInstruccion().name());
            }
        }
    }
        
public void mru(){
      
   
   }
   
   public void rnd(){
       
   }
   
   public boolean ejecutarProceso(int idProceso){
       return true;
   }
   
   public void inicializar(int semilla,TipoAlgoritmo algoritmo,File archivo){
       
   }
   
   public void inicializar(int semilla,TipoAlgoritmo algoritmo,int nProcesos,int nOperaciones){}
   
   public void pausar(){
   
   }
   
   public void reanudar(){
   
   }
    

    public TipoAlgoritmo getAlgoritmo() {
        return algoritmo;
    }


    public int getInstruccionesPorSegundo() {
        return instruccionesPorSegundo;
    }

    public void setInstruccionesPorSegundo(int instruccionesPorSegundo) {
        this.instruccionesPorSegundo = instruccionesPorSegundo;
    }

    public int getTiempoAccesoDisco() {
        return tiempoAccesoDisco;
    }

    public void setTiempoAccesoDisco(int tiempoAccesoDisco) {
        this.tiempoAccesoDisco = tiempoAccesoDisco;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(int discoDuro) {
        this.discoDuro = discoDuro;
    }

    public MemoryManagementUnit getMmu() {
        return mmu;
    }

    public void setMmu(MemoryManagementUnit mmu) {
        this.mmu = mmu;
    }

    public AdminOperaciones getAdmOperaciones() {
        return admOperaciones;
    }

    public void setAdmOperaciones(AdminOperaciones admOperaciones) {
        this.admOperaciones = admOperaciones;
    }

    public int getReloj() {
        return reloj;
    }

    public void setReloj(int reloj) {
        this.reloj = reloj;
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }

    public void setProcesos(ArrayList<Proceso> procesos) {
        this.procesos = procesos;
    }
    
    
    
}
