/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Modelo.Instruccion;
import Modelo.MemoryManagementUnit;
import Modelo.Proceso;
import static Modelo.TipoInstruccion.DELETE;
import static Modelo.TipoInstruccion.KILL;
import static Modelo.TipoInstruccion.NEW;
import static Modelo.TipoInstruccion.USE;
import java.io.File;
import java.util.ArrayList;
import javax.swing.SwingWorker;

public class Computadora extends SwingWorker<Void, Void> {

    private final int nucleosProcesamiento;
    private TipoAlgoritmo algoritmo;
    private int instruccionesPorSegundo;
    private int tiempoAccesoDisco;
    private int ram;
    private int discoDuro;
    private final int paginaSize;
    private MemoryManagementUnit mmu;
    private AdminOperaciones adminOperaciones;
    private int reloj;
    private ArrayList<Proceso> procesos;

    private int semilla;
    private int nProcesos;
    private int nOperaciones;
    ArrayList<Instruccion> instrucciones;

    public Computadora() {
        nucleosProcesamiento = 1;
        instruccionesPorSegundo = 1;
        tiempoAccesoDisco = 5; //va de 5 en 5 segundos
        ram = 400; //KB
        discoDuro = 1000;
        paginaSize = 4; //KB
        mmu = new MemoryManagementUnit();
        reloj = 1; //segundos
        procesos = new ArrayList();
        adminOperaciones = new AdminOperaciones();

    }

    public void setAlgoritmo(TipoAlgoritmo algoritmo) {
        this.algoritmo = algoritmo;

    }

    public void fifo() { //*Por que retorna el tipo del algoritmo?

    }

    public void secondChance() {

    }

    public void mru() {

    }

    public void rnd() {

    }

    public boolean ejecutarProceso(int idProceso) {
        return true;
    }

    public void inicializar(TipoAlgoritmo algoritmo, ArrayList<Instruccion> instrucciones) throws InterruptedException {
  
    }

    public void inicializar() throws InterruptedException {
        if (instrucciones.isEmpty()) {
            String operacionesString = adminOperaciones.generarOperaciones(nProcesos, nOperaciones);
            instrucciones = adminOperaciones.stringToOperaciones(operacionesString);
        }

        for (Instruccion instr : instrucciones) {
            switch (instr.getTipoInstruccion()) {
                case NEW:
                    System.out.println("New");
                    System.out.println(instr.getParametros().get(0));
                    mmu.intruccionNew(instr);
                    break;
                case USE:
                    //System.out.println("Use");
                    mmu.instruccionUse(instr);
                    break;
                case DELETE:
                    //System.out.println("Delete");
                    mmu.instruccionDelete(instr);
                    break;
                case KILL:
                    //System.out.println("Kill");
                    mmu.instruccionKill(instr);
                    break;
                default:
                    throw new AssertionError(instr.getTipoInstruccion().name());
            }
        }

    }

    @Override
    protected Void doInBackground() throws Exception {
        inicializar();
        return null;
    }

    public void setInicializarAtributos(int semilla, TipoAlgoritmo algoritmo, int nProcesos, int nOperaciones,ArrayList<Instruccion> instrucciones) {
        this.semilla = semilla;
        this.algoritmo = algoritmo;
        this.nProcesos = nProcesos;
        this.nOperaciones = nOperaciones;
        this.instrucciones =  instrucciones;
       
    }

    public void pausar() {

    }

    public void reanudar() {

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

    public AdminOperaciones getAdminOperaciones() {
        return adminOperaciones;
    }

    public void setAdminOperaciones(AdminOperaciones adminOperaciones) {
        this.adminOperaciones = adminOperaciones;
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
