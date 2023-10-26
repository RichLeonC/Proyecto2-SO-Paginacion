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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ArrayList<Integer> procesos;

    private int semilla;
    public Random random;
    private int nProcesos;
    private int nOperaciones;
    ArrayList<Instruccion> instrucciones;

    private final Semaphore pauseSemaphore = new Semaphore(1);

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

    public void ejecutarProceso(int idProceso) {
        if (!procesos.contains(idProceso)) {
            procesos.add(idProceso);
        }
    }

    public void setFuturesReferences() {
        if (instrucciones.isEmpty()) {
            String operacionesString = adminOperaciones.generarOperaciones(nProcesos, nOperaciones);
            instrucciones = adminOperaciones.stringToOperaciones(operacionesString);
        }
        for (Instruccion instr : instrucciones) {
            if (instr.getTipoInstruccion().equals(USE)) {
                mmu.futuresReferences.add(instr);
                //  System.out.println("instr: "+instr.getTipoInstruccion()+" ("+instr.getParametros());

            }

        }

    }

    public void inicializar() throws InterruptedException, ExecutionException {

        for (int dir = 0; dir <= 101; dir++) {
            Main.simulacion.setCellColorALG(0, dir, Color.WHITE);
            Main.simulacion.setCellColorOPT(0, dir, Color.WHITE);

        }
        if (instrucciones.isEmpty()) {
            String operacionesString = adminOperaciones.generarOperaciones(nProcesos, nOperaciones);
            instrucciones = adminOperaciones.stringToOperaciones(operacionesString);
        }
        ExecutorService executor = Executors.newFixedThreadPool(2); //PARA USAR HILOS

        for (Instruccion instr : instrucciones) {
            pauseSemaphore.acquire();
            pauseSemaphore.release();
            switch (instr.getTipoInstruccion()) {
                case NEW:
                    System.out.println("New");
                    System.out.println(instr.getParametros().get(0));
                    ejecutarProceso(Integer.parseInt(instr.getParametros().get(0)));
                    //ESTADISTICAS ALG
                    Main.estadisticasAlg.nProcesos = procesos.size();
                    //ESTADISTICAS OPT
                    Main.estadisticasOPT.nProcesos = procesos.size();

                    //Hilo 1
                    Future<?> futureNew = executor.submit(() -> {
                        try {
                            mmu.intruccionNew(instr);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Computadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    // Hilo 2
                    Future<?> futureNewOpt = executor.submit(() -> {
                        try {
                            mmu.intruccionNewOPT(instr);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Computadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    //Esperar a que ambos hilos terminen
                    futureNew.get();
                    futureNewOpt.get();

                    break;
                case USE:
                    //System.out.println("Use");
                    Future<?> futureUseOPT = executor.submit(() -> {
                        try {
                            mmu.instruccionUseOPT(instr);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Computadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    Future<?> futureUseALG = executor.submit(() -> {
                        try {
                            mmu.instruccionUse(instr);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Computadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    futureUseOPT.get();
                    futureUseALG.get();

                    break;
                case DELETE:
                    //System.out.println("Delete");

                    Future<?> futureDeleteOPT = executor.submit(() -> {
                        try {
                            mmu.instruccionDeleteOPT(instr);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Computadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    Future<?> futureDeleteALG = executor.submit(() -> {
                        try {
                            mmu.instruccionDelete(instr);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Computadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    futureDeleteOPT.get();
                    futureDeleteALG.get();

                    break;
                case KILL:
                    //System.out.println("Kill");
                    //ESTADISTICAS ALG
                    if (!procesos.isEmpty()) {
                        System.out.println("Entra a borrar");
                        procesos.remove(0);
                    }
                    Main.estadisticasAlg.nProcesos = procesos.size();
                    //ESTADISTICAS OPT
                    Main.estadisticasOPT.nProcesos = procesos.size();

                    Future<?> futureKillOPT = executor.submit(() -> {
                        try {
                            mmu.instruccionKillOPT(instr);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Computadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    Future<?> futureKillALG = executor.submit(() -> {
                        try {
                            mmu.instruccionKill(instr);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Computadora.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    futureKillOPT.get();
                    futureKillALG.get();
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
        try {
            pauseSemaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void reanudar() {
        pauseSemaphore.release();
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

    public int getProcesos() {
        return procesos.size();
    }

}
