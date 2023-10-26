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
import Vista.Main;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.SwingWorker;

public class Computadora extends SwingWorker<Void, Void> {

    private final int nucleosProcesamiento;
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
    public Random random;
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

    public boolean ejecutarProceso(int idProceso) {
        return true;
    }

    public void setFuturesReferences() {
        if (instrucciones.isEmpty()) {
            String operacionesString = adminOperaciones.generarOperaciones(nProcesos, nOperaciones);
            instrucciones = adminOperaciones.stringToOperaciones(operacionesString);
        }
        for (Instruccion instr : instrucciones) {
            if (instr.getTipoInstruccion().equals(USE)) {
                mmu.futuresReferences.add(instr);
            }

        }

    }

    public void inicializar() throws InterruptedException {
        for (int dir = 0; dir <= 101; dir++) {
            Main.simulacion.setCellColorALG(0, dir, Color.WHITE);
            Main.simulacion.setCellColorOPT(0, dir, Color.WHITE);

        }
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
                    mmu.intruccionNewOPT(instr);

                    break;
                case USE:
                    //System.out.println("Use");
                    mmu.isOpt = false;
                    mmu.instruccionUse(instr);
                    mmu.isOpt = true;
                   mmu.instruccionUse(instr);
                    break;
                case DELETE:
                    //System.out.println("Delete");
                    mmu.isOpt = false;
                    mmu.instruccionDelete(instr);
                    mmu.isOpt = true;
                    mmu.instruccionDelete(instr);
                    break;
                case KILL:
                    //System.out.println("Kill");
                    mmu.isOpt = false;
                    mmu.instruccionKill(instr);
                    mmu.isOpt = true;
                    mmu.instruccionKill(instr);
                    break;
                default:
                    throw new AssertionError(instr.getTipoInstruccion().name());
            }
        }

    }

    public int getSemilla() {
        return semilla;
    }

    public void setSemilla(int semilla) {
        this.semilla = semilla;
        setRandom(new Random(semilla));
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getnProcesos() {
        return nProcesos;
    }

    public void setnProcesos(int nProcesos) {
        this.nProcesos = nProcesos;
    }

    public int getnOperaciones() {
        return nOperaciones;
    }

    public void setnOperaciones(int nOperaciones) {
        this.nOperaciones = nOperaciones;
    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(ArrayList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Override
    protected Void doInBackground() throws Exception {
        inicializar();
        return null;
    }

    public void setInicializarAtributos(int semilla, TipoAlgoritmo algoritmo, int nProcesos, int nOperaciones, ArrayList<Instruccion> instrucciones) {
        this.semilla = semilla;
        this.random = new Random(semilla);
        mmu.algoritmo = algoritmo;
        this.nProcesos = nProcesos;
        this.nOperaciones = nOperaciones;
        this.instrucciones = instrucciones;

    }

    public void pausar() {

    }

    public void reanudar() {

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
