/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Backend.TipoAlgoritmo;
import static Backend.TipoAlgoritmo.FIFO;
import static Backend.TipoAlgoritmo.MRU;
import static Backend.TipoAlgoritmo.OPT;
import static Backend.TipoAlgoritmo.RANDOM;
import static Backend.TipoAlgoritmo.SECOND_CHANCE;
import Vista.Configuracion;
import Vista.Main;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author megui
 */
public class MemoryManagementUnit {

    public int memoriaReal;
    public int memoriaRealOPT;
    public int direccionVirtual;
    public int direccionVirtualOPT;
    public int memoriaOcupada;
    public int memoriaOcupadaOPT;
    public HashMap<String, ArrayList<Pagina>> mapa;
    public ArrayList<Pagina> tablaSimbolos;
    public HashMap<String, ArrayList<Pagina>> mapaOPT;
    public ArrayList<Pagina> tablaSimbolosOPT;
    public int idActual;
    public int idActualOPT;
    public int direccionRamActualID = 1;
    public int direccionRamActualOPT = 1;
    public int direccionVirtualActualID = 1;
    public int direccionVirtualActualOPT = 1;

    public int direccionDiscoActualID = 1;
    public int direccionDiscoActualOPT = 1;

    public int punteroActual = 1;
    public int punteroActualOPT = 1;

    public TipoAlgoritmo algoritmo;

    public ArrayList<Instruccion> futuresReferences;
    public boolean isOpt = false;

    public MemoryManagementUnit() {
        this.mapa = new HashMap();
        this.tablaSimbolos = new ArrayList();
        this.mapaOPT = new HashMap();
        this.tablaSimbolosOPT = new ArrayList();
        idActual = 0;
        idActualOPT = 0;
        this.memoriaReal = 100;
        this.memoriaRealOPT = 100;
        this.memoriaOcupada = 0;
        this.memoriaOcupadaOPT = 0;
        futuresReferences = new ArrayList();
    }

    public int getDireccionRamActualID() {
        return direccionRamActualID;
    }

    public void setDireccionRamActualID(int direccionRamActualID) {
        this.direccionRamActualID = direccionRamActualID;
    }

    public int getDireccionVirtualActualID() {
        return direccionVirtualActualID;
    }

    public void setDireccionVirtualActualID(int direccionVirtualActualID) {
        this.direccionVirtualActualID = direccionVirtualActualID;
    }

    public int getDireccionDiscoActualID() {
        return direccionDiscoActualID;
    }

    public void setDireccionDiscoActualID(int direccionDiscoActualID) {
        this.direccionDiscoActualID = direccionDiscoActualID;
    }

    public int getPunteroActual() {
        return punteroActual;
    }

    public void setPunteroActual(int punteroActual) {
        this.punteroActual = punteroActual;
    }

    public TipoAlgoritmo getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(TipoAlgoritmo algoritmo) {
        this.algoritmo = algoritmo;
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

    public HashMap<String, ArrayList<Pagina>> getMapaOPT() {
        return mapaOPT;
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

    public void ejecutarIntruccionesProceso(Proceso proceso) {
    }

    public Pagina getPageByID(int id) {
        for (Pagina page : tablaSimbolos) {
            if (page.id.equals(String.valueOf(id))) {
                return page;
            }
        }
        return null;
    }

    public Pagina getPageByIDOPT(int id) {
        for (Pagina page : tablaSimbolosOPT) {
            if (page.id.equals(String.valueOf(id))) {
                return page;
            }
        }
        return null;
    }

    public int fifo() {
        Pagina pageFIActual = tablaSimbolos.get(0);
        for (Pagina page : tablaSimbolos) {
            //System.out.println("Comparando pagina elegida actual" + pageFIActual.id + " con la pagina " + page.id);
            if (!pageFIActual.isLoaded() || page.loaded.equals("X") && esMasVieja(pageFIActual.timestamp, page.timestamp)) {
                //System.out.println("Se reemplazan");
                pageFIActual = page;
            } else {
                //System.out.println("Se mantiene");
            }
        }
        return Integer.parseInt(pageFIActual.id);
    }

    public int secondChance() {
        Pagina pageSCActual = tablaSimbolos.get(0);
        System.out.println("entraaaaaaaa");
        for (Pagina page : tablaSimbolos) {
            if (!pageSCActual.isLoaded() || (page.loaded.equals("X") && esMasVieja(pageSCActual.timestamp, page.timestamp) && "0".equals(page.getMarking()))) {
                pageSCActual = page;
                System.out.println("se elimino" + Integer.parseInt(pageSCActual.id) );

            } else {
               page.setMarking("0");
                System.out.println("se van a eliminar en la siguiente vuelta");

            }
        }

        return Integer.parseInt(pageSCActual.id);
    }

    public int mru() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");

        Pagina paginaMasReciente = tablaSimbolos.get(0);

        for (Pagina pagina : tablaSimbolos) {
            try {
                Date fechaPagina = sdf.parse(pagina.getMarking());
                Date fechaMasReciente = sdf.parse(paginaMasReciente.getMarking());

                if (fechaPagina.after(fechaMasReciente)) {
                    paginaMasReciente = pagina;
                }
            } catch (ParseException ex) {
                Logger.getLogger(MemoryManagementUnit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("La página más reciente es: " + paginaMasReciente.getId());

        return Integer.parseInt(paginaMasReciente.id);
    }

    public int random() {
        ArrayList<Pagina> paginas = new ArrayList();
        for (Pagina page : tablaSimbolos) {
            if (page.isLoaded()) {
                paginas.add(page);
            }
        }
        int index = Main.computadora.random.nextInt(paginas.size() - 1);
        return Integer.parseInt(paginas.get(index).id);
    }

    public int optimum(ArrayList<Pagina> valores) {
        int distanciaMaxima = -1;
        Pagina paginaRemplazable = null;
        for (Pagina page : tablaSimbolosOPT) { //Obtenemos 
            if (page.isLoaded()&&!valores.contains(page)) {
                int distancia = getDistanciaFutura(page);
  
                if (distancia == -1) {
                    return Integer.parseInt(page.id);
                } else if (distancia > distanciaMaxima) {
                    distanciaMaxima = distancia;
                    paginaRemplazable = page;
                }
            }

        }
        return Integer.parseInt(paginaRemplazable.id);
    }

    private int getDistanciaFutura(Pagina page) {
        for (Instruccion instr : futuresReferences) {
            if (instr.esReferenciaAPagina(page, mapaOPT)) {
                return futuresReferences.indexOf(instr);
            }
        }
        return -1; //No  va a ser referencia en un futuro

    }

    public int getDireccionLibre() {
        int dir;
        for (dir = 0; dir <= 100; dir++) {
            if (Main.simulacion.getCellColorALG(0, dir) == Color.WHITE) {
                return dir;
            }
        }
        return dir;
    }

    public int getDireccionLibreOPT() {
        int dir;
        for (dir = 0; dir <= 100; dir++) {

            if (Main.simulacion.getCellColorOPT(0, dir) == Color.WHITE) {
                return dir;
            }
        }
        return dir;
    }

    public int llamarAlgoritmo() {
        switch (algoritmo) {
            case SECOND_CHANCE:
                return secondChance();
            case FIFO:
                return fifo();
            case MRU:
                return mru();
            case RANDOM:
                return random();
            default:
                return -1;

        }
    }

    public static boolean esMasVieja(String fechaFIActual, String fecha2) {
        System.out.println("Fecha vieja: " + fechaFIActual + " Fecha nueva: " + fecha2);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        try {
            Date date1 = sdf.parse(fechaFIActual);
            Date date2 = sdf.parse(fecha2);
            // Compara las dos fechas
            if (date2.before(date1)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int intruccionNew(Instruccion instruccion) throws InterruptedException {
        int numPaginas = ((Integer) Integer.parseInt(instruccion.getParametros().get(1)) / 4096) % 4 == 0
                && ((Integer) Integer.parseInt(instruccion.getParametros().get(1)) / 4096) != 0
                ? (Integer) Integer.parseInt(instruccion.getParametros().get(1)) / 4096
                : (Integer) Integer.parseInt(instruccion.getParametros().get(1)) / 4096 + 1;
        System.out.println("Al proceso " + instruccion.getParametros().get(0) + " Numero de paginas a crear: " + numPaginas);
        ArrayList<Pagina> paginasValores = new ArrayList();
        Main.estadisticasAlg.fragmentacion += 4 - Integer.parseInt(instruccion.getParametros().get(1))/1024%4;
        int espacioDisponible = 100;
        for(Pagina page : tablaSimbolos){
            if(!page.isLoaded()){
                espacioDisponible--;
            }
        }
        int sumaKB = 0;
        for (int i = 0; i < numPaginas; i++) {
            int dir = getDireccionLibre();
            //System.out.println("Direccion libre: " + dir);
            if (dir < 100 && numPaginas < espacioDisponible) {
                //System.out.println("HIT");
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                Pagina pagina = new Pagina();
                int paginaSize = 0;
                if(i == numPaginas-1){
                    paginaSize = Integer.parseInt(instruccion.getParametros().get(1))/1024%4;
                    System.out.println("tamaño de página: " + paginaSize);
                }else{
                    paginaSize = 4;
                }
                sumaKB += paginaSize;
                
                if (algoritmo == TipoAlgoritmo.SECOND_CHANCE) {
                    pagina = new Pagina(String.valueOf(idActual), instruccion.getParametros().get(0), "X", String.valueOf(direccionVirtualActualID), String.valueOf(dir + 1), "", formattedDate, "0",paginaSize);

                } else if (algoritmo == TipoAlgoritmo.MRU) {
                    pagina = new Pagina(String.valueOf(idActual), instruccion.getParametros().get(0), "X", String.valueOf(direccionVirtualActualID), String.valueOf(dir + 1), "", formattedDate, formattedDate,paginaSize);
                    System.out.println("MRU");
                } else if (algoritmo == TipoAlgoritmo.RANDOM || algoritmo == TipoAlgoritmo.FIFO) {
                    pagina = new Pagina(String.valueOf(idActual), instruccion.getParametros().get(0), "X", String.valueOf(direccionVirtualActualID), String.valueOf(dir + 1), "", formattedDate, "true",paginaSize);
                    System.out.println("cualquier otro");
                }
                paginasValores.add(pagina);
                tablaSimbolos.add(pagina);
                direccionRamActualID++;
                direccionVirtualActualID++;
                idActual++;
                memoriaOcupada++;
                TimeUnit.SECONDS.sleep(1);
                Main.estadisticasAlg.simTiempo += 1;
                Main.estadisticasAlg.desperdicioPorcentaje = Main.estadisticasAlg.desperdicioTiempo * 100 / Main.estadisticasAlg.simTiempo;
                Main.estadisticasAlg.paginasCargadas++;

            } else {
                //System.out.println("FAIL");
                int idReemplazo = llamarAlgoritmo();
                //System.out.println("Pagina a reemplazar: " + idReemplazo);
                int direccionRAM = reemplazar(idReemplazo);
                //System.out.println("Direccion a usar : " + direccionRAM);
                //LLAMA AL ALGORITMO
                //DETERMINAR MARKING
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                Pagina pagina = new Pagina();
                
                int paginaSize = 0;
                if(i == numPaginas-1){
                    paginaSize = Integer.parseInt(instruccion.getParametros().get(1))/1024%4;
                    System.out.println("tamaño de página: " + paginaSize);
                }else{
                    paginaSize = 4;
                }
                sumaKB += paginaSize;
                
                if (algoritmo == TipoAlgoritmo.SECOND_CHANCE) {
                    pagina = new Pagina(String.valueOf(idActual), instruccion.getParametros().get(0), "X", String.valueOf(direccionVirtualActualID), String.valueOf(direccionRAM), "", formattedDate, "0",paginaSize);

                } else if (algoritmo == TipoAlgoritmo.MRU) {
                    pagina = new Pagina(String.valueOf(idActual), instruccion.getParametros().get(0), "X", String.valueOf(direccionVirtualActualID), String.valueOf(direccionRAM), "", formattedDate, formattedDate,paginaSize);
                    System.out.println("MRU");
                } else if (algoritmo == TipoAlgoritmo.RANDOM || algoritmo == TipoAlgoritmo.FIFO) {
                    pagina = new Pagina(String.valueOf(idActual), instruccion.getParametros().get(0), "X", String.valueOf(direccionVirtualActualID), String.valueOf(direccionRAM), "", formattedDate, "true",paginaSize);
                    System.out.println("cualquier otro");
                }
                paginasValores.add(pagina);
                tablaSimbolos.add(pagina);
                direccionVirtualActualID++;
                direccionDiscoActualID++;
                idActual++;
                TimeUnit.SECONDS.sleep(5);
                Main.estadisticasAlg.simTiempo += 5;
                Main.estadisticasAlg.desperdicioTiempo += 5;
                Main.estadisticasAlg.desperdicioPorcentaje = Main.estadisticasAlg.desperdicioTiempo * 100 / Main.estadisticasAlg.simTiempo;
                Main.estadisticasAlg.paginasSinCargar++;
            }
            Main.simulacion.showPages();
        }
        Main.estadisticasAlg.ramKB += sumaKB;
        Main.estadisticasAlg.ramPorcentaje = Main.estadisticasAlg.ramKB * 100 / 400;
        String ptr = String.valueOf(punteroActual);
        punteroActual++;
        mapa.put(ptr, paginasValores);
        Main.simulacion.showPages();
        return direccionRamActualID;
    }

    public int intruccionNewOPT(Instruccion instruccion) throws InterruptedException {
        int numPaginas = ((Integer) Integer.parseInt(instruccion.getParametros().get(1)) / 4096) % 4 == 0
                && ((Integer) Integer.parseInt(instruccion.getParametros().get(1)) / 4096) != 0
                ? (Integer) Integer.parseInt(instruccion.getParametros().get(1)) / 4096
                : (Integer) Integer.parseInt(instruccion.getParametros().get(1)) / 4096 + 1;
        //System.out.println("Al proceso " + instruccion.getParametros().get(0) + " Numero de paginas a crear: " + numPaginas);
        //Main.estadisticasOPT.ramKB += Integer.parseInt( instruccion.getParametros().get(1))/1024;
        ArrayList<Pagina> paginasValores = new ArrayList();
        Main.estadisticasOPT.fragmentacion += 4 - Integer.parseInt(instruccion.getParametros().get(1))/1024%4;
        int sumaKB = 0;
        int espacioDisponible = 100;
        for(Pagina page : tablaSimbolosOPT){
            if(!page.isLoaded()){
                espacioDisponible--;
            }
        }
        for (int i = 0; i < numPaginas; i++) {
            int dir = getDireccionLibreOPT();
            //System.out.println("Direccion libre: " + dir);
            if (dir < 100  && numPaginas < espacioDisponible) {
                //System.out.println("HIT");
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                
                int paginaSize = 0;
                if(i == numPaginas-1){
                    paginaSize = Integer.parseInt(instruccion.getParametros().get(1))/1024%4;
                    System.out.println("tamaño de página: " + paginaSize);
                }else{
                    paginaSize = 4;
                }
                sumaKB += paginaSize;
                
                Pagina pagina = new Pagina(String.valueOf(idActualOPT), instruccion.getParametros().get(0), "X", String.valueOf(direccionVirtualActualOPT), String.valueOf(dir + 1), "", formattedDate, "true",paginaSize);
                paginasValores.add(pagina);
                tablaSimbolosOPT.add(pagina);
                direccionRamActualOPT++;
                direccionVirtualActualOPT++;
                idActualOPT++;
                memoriaOcupadaOPT++;
                TimeUnit.SECONDS.sleep(1);
                Main.estadisticasOPT.simTiempo += 1;
                Main.estadisticasOPT.desperdicioPorcentaje = Main.estadisticasOPT.desperdicioTiempo * 100 / Main.estadisticasOPT.simTiempo;
                Main.estadisticasOPT.paginasCargadas++;

            } else {
                System.out.println("FAIL");
                ArrayList<Pagina> pagesA; 
                int idReemplazo = optimum(paginasValores);
//                while(paginasValores.contains(getPageByIDOPT(idReemplazo))){
//                    
//                
//                }
                //System.out.println("Pagina a reemplazar: " + idReemplazo);
                int direccionRAM = reemplazarOPT(idReemplazo);
                //System.out.println("Direccion a usar : " + direccionRAM);

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                //System.out.println("Param: " + instruccion.getParametros().get(0));
                int paginaSize = 0;
                if(i == numPaginas-1){
                    paginaSize = Integer.parseInt(instruccion.getParametros().get(1))/1024%4;
                    System.out.println("tamaño de página: " + paginaSize);
                }else{
                    paginaSize = 4;
                }
                sumaKB += paginaSize;
                Pagina pagina = new Pagina(String.valueOf(idActualOPT), instruccion.getParametros().get(0), "X", String.valueOf(direccionVirtualActualOPT), String.valueOf(direccionRAM), String.valueOf(direccionDiscoActualOPT), formattedDate, "true",paginaSize);
                paginasValores.add(pagina);
                tablaSimbolosOPT.add(pagina);
                direccionVirtualActualOPT++;
                direccionDiscoActualOPT++;
                idActualOPT++;
                TimeUnit.SECONDS.sleep(5);
                Main.estadisticasOPT.desperdicioTiempo += 5;
                Main.estadisticasOPT.simTiempo += 5;
                Main.estadisticasOPT.desperdicioPorcentaje = Main.estadisticasOPT.desperdicioTiempo * 100 / Main.estadisticasOPT.simTiempo;
                Main.estadisticasOPT.paginasSinCargar++;
            }
            Main.simulacion.showPagesOpt();
        }
        Main.estadisticasOPT.ramKB += sumaKB;
        Main.estadisticasOPT.ramPorcentaje = Main.estadisticasOPT.ramKB * 100 / 400;
        String ptr = String.valueOf(punteroActualOPT);
        punteroActualOPT++;
        mapaOPT.put(ptr, paginasValores);
        Main.simulacion.showPagesOpt();
        return direccionRamActualOPT;
    }

    public int reemplazar(int idReemplazo) {
        getPageByID(idReemplazo).loaded = "";
        int dir = Integer.parseInt(getPageByID(idReemplazo).direccionFisica);
        Main.estadisticasAlg.ramKB -= getPageByID(idReemplazo).size;
        Main.estadisticasAlg.ramPorcentaje = Main.estadisticasAlg.ramKB * 100 / 400;
        getPageByID(idReemplazo).direccionFisica = "";
        return dir;
    }

    public int reemplazarOPT(int idReemplazo) {
        getPageByIDOPT(idReemplazo).loaded = "";
        int dir = Integer.parseInt(getPageByIDOPT(idReemplazo).direccionFisica);
        Main.estadisticasOPT.ramKB -= getPageByIDOPT(idReemplazo).size;
        Main.estadisticasOPT.ramPorcentaje = Main.estadisticasOPT.ramKB * 100 / 400;
        getPageByIDOPT(idReemplazo).direccionFisica = "";
        return dir;
    }

    public void guardarPuntero(String puntero) {
    }

    public String secondChanceMarking(Pagina pagina) {
        if (pagina.loaded.equals("X")) {
            pagina.setMarking("1");
            System.out.println("Marcado 1 second");
        } else {

        }
        return pagina.getMarking(); // Devuelve el marcado calculado
    }

    public String mruMarking(Pagina pagina) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        String formattedDate = sdf.format(date);
        if (pagina.loaded.equals("X")) {
            pagina.setMarking(formattedDate);
            //System.out.println("Marcado fecha nueva" + formattedDate);
        } else {

        }
        return pagina.getMarking(); // Devuelve el marcado calculado

    }

    public String optimumMarking() {
        return "";
    }

    public String determinarMarking(Pagina pagina) {
        switch (algoritmo) {
            case SECOND_CHANCE:
                return secondChanceMarking(pagina);
            case FIFO:
                return "";
            case MRU:
                return mruMarking(pagina);
            case RANDOM:
                return "";
            case OPT:
                return optimumMarking();
            default:
                return "";

        }
    }

    public void instruccionUse(Instruccion instruccion) throws InterruptedException {
        if (mapa.get(instruccion.getParametros().get(0)) != null) {
            for (Pagina page : mapa.get(instruccion.getParametros().get(0))) { //Por cada una de las paginas asignadas a ese puntero

                if (!page.isLoaded()) {
                    System.out.println("No está en RAM"); //FALLO
                    //LLAMA AL ALGORITMO
                    int idReemplazo = llamarAlgoritmo();
                    //System.out.println("Pagina a reemplazar: " + idReemplazo);
                    int direccionRAM = reemplazar(idReemplazo);
                    Main.estadisticasAlg.ramKB += page.size;
                    Main.estadisticasAlg.ramPorcentaje = Main.estadisticasAlg.ramKB * 100 / 400;
                    page.loaded = "X";
                    page.direccionFisica = String.valueOf(direccionRAM);
                    //DETERMINAR EL MARKING
                    //determinarMarking(page);
                    String direccion = "";
                    page.setDireccionFisica(direccion);

                    Main.estadisticasAlg.simTiempo += 5;
                    Main.estadisticasAlg.desperdicioTiempo += 5;
                    Main.estadisticasAlg.desperdicioPorcentaje = Main.estadisticasAlg.desperdicioTiempo * 100 / Main.estadisticasAlg.simTiempo;
                    

                } else {
                    //System.out.println("Si está en RAM"); //HIT
                    determinarMarking(page);
                    Main.estadisticasAlg.simTiempo += 1;
                    Main.estadisticasAlg.desperdicioPorcentaje = Main.estadisticasAlg.desperdicioTiempo * 100 / Main.estadisticasAlg.simTiempo;

                }
                TimeUnit.SECONDS.sleep(1);
                Main.simulacion.showPages();

            }

        }

    }

    public void instruccionUseOPT(Instruccion instruccion) throws InterruptedException {

        if (mapaOPT.get(instruccion.getParametros().get(0)) != null) {
            if (!futuresReferences.isEmpty()) {
               // System.out.println("A eliminar: " + instruccion.getParametros());
                futuresReferences.remove(instruccion);
            }
            for (Pagina page : mapaOPT.get(instruccion.getParametros().get(0))) { //Por cada una de las paginas asignadas a ese puntero

                if (!page.isLoaded()) {
                    //System.out.println("No está en RAM"); //FALLO
                    //LLAMA AL ALGORITMO
                    int idReemplazo = optimum(mapaOPT.get(instruccion.getParametros().get(0)));
                    //System.out.println("Pagina a reemplazar: " + idReemplazo);
                    Main.estadisticasOPT.ramKB += page.size;
                    Main.estadisticasOPT.ramPorcentaje = Main.estadisticasOPT.ramKB * 100 / 400;
                    int direccionRAM = reemplazarOPT(idReemplazo);
                    page.loaded = "X";
                    page.direccionFisica = String.valueOf(direccionRAM);
                    
                    //DETERMINAR EL MARKING
                    determinarMarking(page);
                    String direccion = "";
                    page.setDireccionFisica(direccion);

                    Main.estadisticasOPT.simTiempo += 5;
                    Main.estadisticasOPT.desperdicioTiempo += 5;
                    Main.estadisticasOPT.desperdicioPorcentaje = Main.estadisticasOPT.desperdicioTiempo * 100 / Main.estadisticasOPT.simTiempo;

                } else {
                    //System.out.println("Si está en RAM"); //HIT

                    Main.estadisticasOPT.simTiempo += 1;
                    Main.estadisticasOPT.desperdicioPorcentaje = Main.estadisticasOPT.desperdicioTiempo * 100 / Main.estadisticasOPT.simTiempo;

                }
                TimeUnit.SECONDS.sleep(1);

                Main.simulacion.showPagesOpt();

            }

        }

    }

    public void instruccionDelete(Instruccion instruccion) throws InterruptedException {
//        System.out.println("Puntero a eliminar: " + instruccion.getParametros().get(0));
//        System.out.println(mapa.size());
        ArrayList<Pagina> paginas = mapa.remove(instruccion.getParametros().get(0));
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            for (int j = 0; j < paginas.size(); j++) {
                if (tablaSimbolos.get(i).id.equals(paginas.get(j).id)) {
                    if(tablaSimbolos.get(i).isLoaded()){
                        Main.estadisticasAlg.paginasCargadas--;
                        Main.estadisticasAlg.ramKB -= tablaSimbolos.get(i).size;
                        Main.estadisticasAlg.ramPorcentaje = Main.estadisticasAlg.ramKB * 100 / 400;
                    }else{
                        Main.estadisticasAlg.paginasSinCargar--;
                    }
                    
                    if(tablaSimbolos.get(i).size != 4){
                        Main.estadisticasAlg.fragmentacion -= 4 - tablaSimbolos.get(i).size;
                    }
                    
                    int ptr = Integer.parseInt(tablaSimbolos.get(i).direccionFisica) - 1;
                    tablaSimbolos.remove(i);
                    Main.simulacion.setCellColorALG(0, ptr, Color.WHITE);
                    TimeUnit.SECONDS.sleep(1);
                    Main.estadisticasAlg.simTiempo += 1;
                    Main.estadisticasAlg.desperdicioPorcentaje = Main.estadisticasAlg.desperdicioTiempo * 100 / Main.estadisticasAlg.simTiempo;
                    Main.simulacion.showPages();

                }
            }
        }

    }

    public void instruccionDeleteOPT(Instruccion instruccion) throws InterruptedException {
//        System.out.println("Puntero a eliminar: " + instruccion.getParametros().get(0));
//        System.out.println(mapa.size());
        ArrayList<Pagina> paginas = mapaOPT.remove(instruccion.getParametros().get(0));
        for (int i = 0; i < tablaSimbolosOPT.size(); i++) {
            for (int j = 0; j < paginas.size(); j++) {
                if (tablaSimbolosOPT.get(i).id.equals(paginas.get(j).id)) {
                    if(tablaSimbolosOPT.get(i).isLoaded()){
                        Main.estadisticasOPT.paginasCargadas--;
                        int ptr = Integer.parseInt(tablaSimbolosOPT.get(i).direccionFisica) - 1;
                        Main.simulacion.setCellColorOPT(0, ptr, Color.WHITE);
                        Main.estadisticasOPT.ramKB -= tablaSimbolosOPT.get(i).size;
                        Main.estadisticasOPT.ramPorcentaje = Main.estadisticasOPT.ramKB * 100 / 400;
                    }else{
                        Main.estadisticasOPT.paginasSinCargar--;
                    }
                    
                    if(tablaSimbolosOPT.get(i).size != 4){
                        Main.estadisticasOPT.fragmentacion -= 4 - tablaSimbolosOPT.get(i).size;
                    }
                    
                    tablaSimbolosOPT.remove(i);
                    
                    TimeUnit.SECONDS.sleep(1);
                    Main.estadisticasOPT.simTiempo += 1;
                    Main.estadisticasOPT.desperdicioPorcentaje = Main.estadisticasOPT.desperdicioTiempo * 100 / Main.estadisticasOPT.simTiempo;
                    Main.simulacion.showPagesOpt();

                }
            }
        }

    }

    public void deletePuntero(String puntero) throws InterruptedException {

        System.out.println("Puntero a eliminar: " + puntero);
        System.out.println(mapa.size());
        ArrayList<Pagina> paginas = mapa.remove(puntero);
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            for (int j = 0; j < paginas.size(); j++) {
                if (tablaSimbolos.get(i).id.equals(paginas.get(j).id)) {
                    if(tablaSimbolos.get(i).isLoaded()){
                        Main.estadisticasAlg.paginasCargadas--;
                        int ptr = Integer.parseInt(tablaSimbolos.get(i).direccionFisica) - 1;
                        Main.simulacion.setCellColorALG(0, ptr, Color.WHITE);
                        Main.estadisticasAlg.ramKB -= tablaSimbolos.get(i).size;
                        Main.estadisticasAlg.ramPorcentaje = Main.estadisticasAlg.ramKB * 100 / 400;
                    }else{
                        Main.estadisticasAlg.paginasSinCargar--;
                    }
                    
                    if(tablaSimbolos.get(i).size != 4){
                        Main.estadisticasAlg.fragmentacion -= 4-tablaSimbolos.get(i).size;
                    }
                    
                    tablaSimbolos.remove(i);
                    System.out.println("Entra a borrar no opt1");
                    System.out.println("Entra a borrar no opt2");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Entra a borrar no opt");
                    Main.estadisticasAlg.simTiempo += 1;
                    Main.estadisticasAlg.desperdicioPorcentaje = Main.estadisticasAlg.desperdicioTiempo * 100 / Main.estadisticasAlg.simTiempo;
                    System.out.println("Entra a borrar no opt");
                    Main.simulacion.showPages();

                }
            }
        }
        System.out.println("Final DELETE");

    }

    public void deletePunteroOPT(String puntero) throws InterruptedException {

        System.out.println("Puntero a eliminar: " + puntero);
        System.out.println(mapaOPT.size());
        ArrayList<Pagina> paginas = mapaOPT.remove(puntero);
        for (int i = 0; i < tablaSimbolosOPT.size(); i++) {
            for (int j = 0; j < paginas.size(); j++) {
                if (tablaSimbolosOPT.get(i).id.equals(paginas.get(j).id)) {
                    if(tablaSimbolosOPT.get(i).isLoaded()){
                        Main.estadisticasOPT.paginasCargadas--;
                        int ptr = Integer.parseInt(tablaSimbolosOPT.get(i).direccionFisica) - 1;
                        Main.simulacion.setCellColorOPT(0, ptr, Color.WHITE);
                        Main.estadisticasOPT.ramKB -= tablaSimbolosOPT.get(i).size;
                        Main.estadisticasOPT.ramPorcentaje = Main.estadisticasOPT.ramKB * 100 / 400;
                    }else{
                        Main.estadisticasOPT.paginasSinCargar--;
                    }
                    
                    if(tablaSimbolosOPT.get(i).size != 4){
                        Main.estadisticasOPT.fragmentacion-= 4-tablaSimbolosOPT.get(i).size;
                    }
                    
                    System.out.println("Entra a borrar opt");
                    
                    tablaSimbolosOPT.remove(i);
                    
                    TimeUnit.SECONDS.sleep(1);
                    Main.estadisticasOPT.simTiempo += 1;
                    Main.estadisticasOPT.desperdicioPorcentaje = Main.estadisticasOPT.desperdicioTiempo * 100 / Main.estadisticasOPT.simTiempo;
                    Main.simulacion.showPagesOpt();

                }
            }
        }
        System.out.println("Final DELETE OPT");

    }

    public void instruccionKill(Instruccion instruccion) throws InterruptedException {

        Set<String> llavesSet = mapa.keySet();
        ArrayList<String> llaves = new ArrayList<>(llavesSet);
        for (String llave : llaves) {
            ArrayList<Pagina> paginas = mapa.get(llave);
            for (int i = 0; i < paginas.size(); i++) {
                if (paginas.get(i).pid.equals(String.valueOf(instruccion.getParametros().get(0)))) {
                    deletePuntero(llave);
                    break;
                }
            }
        }
        Main.simulacion.showPages();

    }

    public void instruccionKillOPT(Instruccion instruccion) throws InterruptedException {

        Set<String> llavesSet = mapaOPT.keySet();
        ArrayList<String> llaves = new ArrayList<>(llavesSet);
        for (String llave : llaves) {
            ArrayList<Pagina> paginas = mapaOPT.get(llave);
            for (int i = 0; i < paginas.size(); i++) {
                if (paginas.get(i).pid.equals(String.valueOf(instruccion.getParametros().get(0)))) {
                    deletePunteroOPT(llave);
                    break;
                }
            }
        }
        Main.simulacion.showPagesOpt();

    }

    private void sleep(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
