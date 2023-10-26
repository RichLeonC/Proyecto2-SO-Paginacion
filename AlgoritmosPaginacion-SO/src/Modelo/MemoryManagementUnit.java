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
         for (Pagina page: tablaSimbolos){
             if (!pageSCActual.isLoaded() || page.loaded.equals("X") && esMasVieja(pageSCActual.timestamp, page.timestamp ) && "1".equals(page.getMarking())){
                  pageSCActual = page; 
                  
             } else {
                 System.out.println("MANTENER PERO OCUPA SER MARCADO COMO VISITADO ");
                 pageSCActual.setMarking("1");
             }        
         }
        
        return Integer.parseInt(pageSCActual.id);
    }

    public int mru() {
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
        
         Pagina paginaMasReciente = tablaSimbolos.get(0);

        for (Pagina pagina : tablaSimbolos) {
             try {
                 Date fechaPagina = sdf.parse(pagina.getTimestamp());
                 Date fechaMasReciente = sdf.parse(paginaMasReciente.getTimestamp());
                 
                 if (fechaPagina.after(fechaMasReciente)) {
                     paginaMasReciente = pagina;
                 }} catch (ParseException ex) {
                 Logger.getLogger(MemoryManagementUnit.class.getName()).log(Level.SEVERE, null, ex);
             }
        }

        System.out.println("La página más reciente es: " + paginaMasReciente.getId());
    
        return -1;
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

    public int optimum() {
        int distanciaMaxima = -1;
        Pagina paginaRemplazable = null;
        for (Pagina page : tablaSimbolos) { //Obtenemos 
            int distancia = getDistanciaFutura(page);
            if (distancia == -1) {
                return Integer.parseInt(page.id);
            } else if (distancia > distanciaMaxima) {
                distanciaMaxima = distancia;
                paginaRemplazable = page;
            }

        }
        return Integer.parseInt(paginaRemplazable.id);
    }

    private int getDistanciaFutura(Pagina page) {
        for (Instruccion instr : futuresReferences) {
            if (instr.esReferenciaAPagina(page, mapa)) {
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
            case OPT:
                return optimum();
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

        public int intruccionNew(Instruccion instruccion) throws InterruptedException{
        int numPaginas = ((Integer)Integer.parseInt(instruccion.getParametros().get(1))/4096) % 4 == 0 &&
                                ((Integer)Integer.parseInt(instruccion.getParametros().get(1))/4096)  != 0? 
                               (Integer) Integer.parseInt(instruccion.getParametros().get(1))/4096
                                : (Integer) Integer.parseInt(instruccion.getParametros().get(1))/4096 + 1;
        System.out.println("Al proceso " + instruccion.getParametros().get(0) + " Numero de paginas a crear: " + numPaginas);
        ArrayList<Pagina> paginasValores = new ArrayList();
        for(int i = 0; i < numPaginas; i++){
            int dir = getDireccionLibre();
            //System.out.println("Direccion libre: " + dir);
            if(dir < 100){
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
                int idReemplazo = llamarAlgoritmo();
                System.out.println("Pagina a reemplazar: " + idReemplazo);
                int direccionRAM = reemplazar(idReemplazo);
                System.out.println("Direccion a usar : " + direccionRAM );
                //LLAMA AL ALGORITMO
                //DETERMINAR MARKING
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                Pagina pagina = new Pagina(String.valueOf(idActual),instruccion.getParametros().get(0),"X",String.valueOf(direccionVirtualActualID),String.valueOf(direccionRAM),String.valueOf(direccionDiscoActualID) ,formattedDate,"true");
                paginasValores.add(pagina);
                tablaSimbolos.add(pagina);
                direccionVirtualActualID++;
                direccionDiscoActualID++;
                idActual++;
                TimeUnit.SECONDS.sleep(5);
            }
        Main.simulacion.showPages();
        }
        String ptr = String.valueOf(punteroActual);
        punteroActual++;
        mapa.put(ptr, paginasValores);
        Main.simulacion.showPages();
        return direccionRamActualID;
    }
        
       public int intruccionNewOPT(Instruccion instruccion) throws InterruptedException{
        int numPaginas = ((Integer)Integer.parseInt(instruccion.getParametros().get(1))/4096) % 4 == 0 &&
                                ((Integer)Integer.parseInt(instruccion.getParametros().get(1))/4096)  != 0? 
                               (Integer) Integer.parseInt(instruccion.getParametros().get(1))/4096
                                : (Integer) Integer.parseInt(instruccion.getParametros().get(1))/4096 + 1;
        System.out.println("Al proceso " + instruccion.getParametros().get(0) + " Numero de paginas a crear: " + numPaginas);
        ArrayList<Pagina> paginasValores = new ArrayList();
        for(int i = 0; i < numPaginas; i++){
            int dir = getDireccionLibre();
            //System.out.println("Direccion libre: " + dir);
            if(dir < 100){
                System.out.println("HIT");
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                Pagina pagina = new Pagina(String.valueOf(idActualOPT),instruccion.getParametros().get(0),"X",String.valueOf(direccionVirtualActualOPT), String.valueOf(dir+1),"",formattedDate,"true");
                paginasValores.add(pagina);
                tablaSimbolosOPT.add(pagina);
                direccionRamActualOPT++;
                direccionVirtualActualOPT++;
                idActualOPT++;
                memoriaOcupadaOPT++;
                TimeUnit.SECONDS.sleep(1);
            
            }else{
                System.out.println("FAIL");
                int idReemplazo = optimum();
                System.out.println("Pagina a reemplazar: " + idReemplazo);
                int direccionRAM = reemplazar(idReemplazo);
                System.out.println("Direccion a usar : " + direccionRAM );
                //LLAMA AL ALGORITMO
                //DETERMINAR MARKING
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
                String formattedDate = sdf.format(date);
                Pagina pagina = new Pagina(String.valueOf(idActualOPT),instruccion.getParametros().get(0),"X",String.valueOf(direccionVirtualActualOPT),String.valueOf(direccionRAM),String.valueOf(direccionDiscoActualOPT) ,formattedDate,"true");
                paginasValores.add(pagina);
                tablaSimbolosOPT.add(pagina);
                direccionVirtualActualOPT++;
                direccionDiscoActualOPT++;
                idActualOPT++;
                TimeUnit.SECONDS.sleep(5);
            }
        Main.simulacion.showPagesOpt();
        }
        String ptr = String.valueOf(punteroActualOPT);
        punteroActualOPT++;
        mapaOPT.put(ptr, paginasValores);
        Main.simulacion.showPagesOpt();
        return direccionRamActualOPT;
    }

    public int reemplazar(int idReemplazo) {
        getPageByID(idReemplazo).loaded = "";;
        int dir = Integer.parseInt(getPageByID(idReemplazo).direccionFisica);
        getPageByID(idReemplazo).direccionFisica = "";
        return dir;
    }

    public void guardarPuntero(String puntero) {
    }
    
    public String secondChanceMarking(){
         for (Pagina page: tablaSimbolos){
                page.setMarking("0");
         }
          
        return  "0";
    }
    
    public String mruMarking(){
       
         
        
        
        
        return "";
    }

    public String optimumMarking() {
        return "";
    }

    public String determinarMarking() {
        switch (algoritmo) {
            case SECOND_CHANCE:
                return secondChanceMarking();
            case FIFO:
                return "";
            case MRU:
                return mruMarking();
            case RANDOM:
                return "";
            case OPT:
                return optimumMarking();
            default:
                return "";

        }
    }

    public void instruccionUse(Instruccion instruccion) throws InterruptedException {
        HashMap<String, ArrayList<Pagina>> mapa1;
        if (isOpt) {
            mapa1 = mapaOPT;
        } else {
            mapa1 = mapa;
        }
        if (mapa1.get(instruccion.getParametros().get(0)) != null) {
            if (!futuresReferences.isEmpty()) {
                futuresReferences.remove(instruccion);
            }
            for (Pagina page : mapa1.get(instruccion.getParametros().get(0))) { //Por cada una de las paginas asignadas a ese puntero

                if (page.direccionFisica.equals("")) {
                    //System.out.println("No está en RAM");
                    //LLAMA AL ALGORITMO
                    //DETERMINAR EL MARKING
                    determinarMarking();
                    String direccion = "-1";
                    page.setDireccionFisica(direccion);
                } else {
                    //System.out.println("Si está en RAM");
                }
                TimeUnit.SECONDS.sleep(1);

                if (isOpt) {
                    Main.simulacion.showPagesOpt();
                } else {
                    Main.simulacion.showPages();
                }
            }

        }

    }

    public void instruccionDelete(Instruccion instruccion) throws InterruptedException {
//        System.out.println("Puntero a eliminar: " + instruccion.getParametros().get(0));
//        System.out.println(mapa.size());
        if (isOpt) {
            ArrayList<Pagina> paginas = mapaOPT.remove(instruccion.getParametros().get(0));
            for (int i = 0; i < tablaSimbolosOPT.size(); i++) {
                for (int j = 0; j < paginas.size(); j++) {
                    if (tablaSimbolosOPT.get(i).id.equals(paginas.get(j).id)) {
                        int ptr = Integer.parseInt(tablaSimbolosOPT.get(i).direccionFisica) - 1;
                        tablaSimbolosOPT.remove(i);
                        Main.simulacion.setCellColorOPT(0, ptr, Color.WHITE);
                        TimeUnit.SECONDS.sleep(1);
                        Main.simulacion.showPagesOpt();

                    }
                }
            }
        } else {
            ArrayList<Pagina> paginas = mapa.remove(instruccion.getParametros().get(0));
            for (int i = 0; i < tablaSimbolos.size(); i++) {
                for (int j = 0; j < paginas.size(); j++) {
                    if (tablaSimbolos.get(i).id.equals(paginas.get(j).id)) {
                        int ptr = Integer.parseInt(tablaSimbolos.get(i).direccionFisica) - 1;
                        tablaSimbolos.remove(i);
                        Main.simulacion.setCellColorALG(0, ptr, Color.WHITE);
                        TimeUnit.SECONDS.sleep(1);
                        Main.simulacion.showPages();

                    }
                }
            }

        }

    }

    public void deletePuntero(String puntero) throws InterruptedException {
        if (isOpt) {
            System.out.println("Puntero a eliminar: " + puntero);
            System.out.println(mapaOPT.size());
            ArrayList<Pagina> paginas = mapaOPT.remove(puntero);
            for (int i = 0; i < tablaSimbolosOPT.size(); i++) {
                for (int j = 0; j < paginas.size(); j++) {
                    if (tablaSimbolosOPT.get(i).id.equals(paginas.get(j).id)) {
                        int ptr = Integer.parseInt(tablaSimbolosOPT.get(i).direccionFisica) - 1;
                        tablaSimbolosOPT.remove(i);
                        Main.simulacion.setCellColorOPT(0, ptr, Color.WHITE);
                        TimeUnit.SECONDS.sleep(1);
                        Main.simulacion.showPagesOpt();

                    }
                }
            }

        } else {
            System.out.println("Puntero a eliminar: " + puntero);
            System.out.println(mapa.size());
            ArrayList<Pagina> paginas = mapa.remove(puntero);
            for (int i = 0; i < tablaSimbolos.size(); i++) {
                for (int j = 0; j < paginas.size(); j++) {
                    if (tablaSimbolos.get(i).id.equals(paginas.get(j).id)) {
                        int ptr = Integer.parseInt(tablaSimbolos.get(i).direccionFisica) - 1;
                        tablaSimbolos.remove(i);
                        Main.simulacion.setCellColorALG(0, ptr, Color.WHITE);
                        TimeUnit.SECONDS.sleep(1);
                        Main.simulacion.showPages();

                    }
                }
            }

        }

    }

    public void instruccionKill(Instruccion instruccion) throws InterruptedException {
        if (isOpt) {
            Set<String> llavesSet = mapaOPT.keySet();
            ArrayList<String> llaves = new ArrayList<>(llavesSet);
            for (String llave : llaves) {
                ArrayList<Pagina> paginas = mapaOPT.get(llave);
                for (int i = 0; i < paginas.size(); i++) {
                    if (paginas.get(i).pid.equals(String.valueOf(instruccion.getParametros().get(0)))) {
                        deletePuntero(llave);
                        break;
                    }
                }
            }
            TimeUnit.SECONDS.sleep(1);
            Main.simulacion.showPagesOpt();

        } else {
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
            TimeUnit.SECONDS.sleep(1);
            Main.simulacion.showPages();

        }

    }

    private void sleep(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
