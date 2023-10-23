/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Modelo.Instruccion;
import Modelo.TipoInstruccion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author acost
 */
public class AdminOperaciones {

    public AdminOperaciones() {
    }
    
    /**
     * Convierte una cadena de operaciones en una lista de Instrucciones.
     *
     * @param operacionesString La cadena de operaciones en el formato adecuado.
     * @return Una lista de Instrucciones.
     */
    public ArrayList<Instruccion> stringToOperaciones(String operacionesString) {
        ArrayList<Instruccion> operaciones = new ArrayList<>();
        String[] lineas = operacionesString.split("\n");
        for (String linea : lineas) {
            String[] partes = linea.split("\\(");
            if (partes.length != 2) {
                // Si la línea no tiene el formato esperado, la ignoramos
                continue;
            }
            
            String nombreInstruccion = partes[0];
            String parametrosRaw = partes[1].replaceAll("\\)", "");
            String[] parametrosArray = parametrosRaw.split(",");
            
            TipoInstruccion tipo = TipoInstruccion.valueOf(nombreInstruccion.toUpperCase());
            ArrayList<String> parametros = new ArrayList<>();
            for (String param : parametrosArray) {
                parametros.add(param.trim());
            }
            
            Instruccion instruccion = new Instruccion(tipo, parametros);
            operaciones.add(instruccion);
        }
        return operaciones;
    }
    
    public String cargarArchivoOperaciones(String nombreArchivo){
        File archivo = new File(nombreArchivo);

        try {
            // Crear un objeto Scanner para leer el archivo
            Scanner scanner = new Scanner(archivo);

            // Inicializar una cadena para almacenar el contenido del archivo
            StringBuilder contenido = new StringBuilder();

            // Leer el archivo línea por línea
            while (scanner.hasNextLine()) {
                contenido.append(scanner.nextLine()).append("\n");
            }

            // Cerrar el scanner
            scanner.close();

            // Devolver el contenido del archivo como una cadena
            return contenido.toString();
        } catch (FileNotFoundException e) {
            // Manejar una excepción si el archivo no se encuentra
            System.err.println("Error: Archivo no encontrado: " + nombreArchivo);
            e.printStackTrace();
            return null;
        }
    }
    
    public String generarOperaciones(int numProcesos, int numOperaciones){
        Random random = new Random();
        StringBuilder operacionesString = new StringBuilder();
        
        ArrayList<Integer> posiblesNew = new ArrayList();        
        ArrayList<Integer> procesos = new ArrayList();


        ArrayList<Integer> posiblesKill = new ArrayList();       
      
        HashMap<Integer, Integer> posiblesUse = new HashMap();
        
        //Por cada proceso, todos pueden ser creados ahorita
        //posiblesNew.add(1);
        procesos.add(1);
        for(int i = 1; i <= numProcesos; i++){
            posiblesNew.add(i);
        }

        String[] tiposInstruccion = {"new", "use", "delete", "kill"};
        int punteros = 0;
        
        int killsAntes = 20*numProcesos/100;
        
        int killsDespues = numProcesos - killsAntes;
        
        System.out.println(killsAntes);
        
        int killsHechas =0;
        
        for (int i = 0; i < numOperaciones - killsDespues; i++) {
            String tipo = tiposInstruccion[random.nextInt(tiposInstruccion.length)];
            //operacionesString.append(tipo).append("(").append(proceso);
            boolean anoto = false;
            if (tipo.equals("new") && !posiblesNew.isEmpty()) { //Si es new y aun no se ha hecho kill de todos los procesos
                int proceso;
                if(procesos.size() <= numProcesos){
                    proceso = procesos.size();
                    procesos.add(procesos.size()+1);
                    posiblesNew.add(proceso);
                }else{
                    proceso = posiblesNew.get(random.nextInt(posiblesNew.size()));
                } //Elijo uno de los posibles procesos
                /*if(posiblesNew.size() < numProcesos && random.nextInt(2)==1){
                    proceso = procesos.get(posiblesNew.size()-1);
                    proceso++;
                    posiblesNew.add(proceso);
                }*/
                operacionesString.append(tipo).append("(").append(proceso); //Lo anota
                anoto = true;
                int tamano = random.nextInt(0,40); //Elige un tamaño random
                operacionesString.append(",").append(tamano); //Se mete el tamaño
                punteros++; //Eso genera un puntero posible de usar
                posiblesUse.put(punteros,proceso); //Se mete a los posibles Use y lo asocia con el proceso que lo creo
                if(!posiblesKill.contains(proceso)){
                    posiblesKill.add(proceso);
                }
            }
            else if(tipo.equals("use") && !posiblesUse.isEmpty()){ //Si hay punteros creados y va a usar uno
                ArrayList<Integer> punterosActuales = new ArrayList<>(posiblesUse.keySet()); //Obtiene los posibles punteros
                int puntero = punterosActuales.get(random.nextInt(punterosActuales.size())); // Elige un puntero random a usar
                operacionesString.append(tipo).append("(").append(puntero); // Lo anota
                anoto = true;
            }
            else if(tipo.equals("delete") && !posiblesUse.isEmpty()){ //Si hay punteros creados y va a borrar uno
                ArrayList<Integer> punterosActuales = new ArrayList<>(posiblesUse.keySet()); //Obtiene los posibles punteros
                int puntero = punterosActuales.get(random.nextInt(punterosActuales.size())); // Elige un ptr a borrar
                operacionesString.append(tipo).append("(").append(puntero); // Lo anota
                posiblesUse.remove(puntero);
                anoto = true;
            }
            else if(tipo.equals("kill") && 
                    ! posiblesKill.isEmpty() && killsHechas < killsAntes){
                int proceso = posiblesKill.remove(random.nextInt(posiblesKill.size())); //Elige un proceso random a eliminar
                for(int x = 0; x < posiblesNew.size(); x++){
                    if(posiblesNew.get(x)== proceso){
                        posiblesNew.remove(x);
                    }
                }
                
                operacionesString.append(tipo).append("(").append(proceso); //Lo anota
                Iterator<Map.Entry<Integer, Integer>> iter = posiblesUse.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iter.next();
                    if (entry.getValue().equals(proceso)) {
                        iter.remove(); // Elimina el elemento si el valor coincide
                    }
                }
                anoto = true;
                killsHechas++;
            }
            if(anoto){
                operacionesString.append(")\n");
            }else{
                //System.out.println("No entra a nada");
                i--;
            }
        }
        if(killsHechas == 0){
            for (int i = 0; i < killsAntes; i++) {
                int proceso = posiblesKill.remove(random.nextInt(posiblesKill.size())); //Elige un proceso random a eliminar
                for(int x = 0; x < posiblesNew.size(); x++){
                    if(posiblesNew.get(x)== proceso){
                        System.out.println("Entro aqui kk");
                        posiblesNew.remove(x);
                    }
                }

                operacionesString.append("kill").append("(").append(proceso); //Lo anota
                Iterator<Map.Entry<Integer, Integer>> iter = posiblesUse.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iter.next();
                    if (entry.getValue().equals(proceso)) {
                        iter.remove(); // Elimina el elemento si el valor coincide
                    }
                }
                killsHechas++;
                operacionesString.append(")\n");
            }
        }
        while(!posiblesKill.isEmpty()){
            int proceso = posiblesKill.remove(random.nextInt(posiblesKill.size())); //Elige un proceso random a eliminar
            killsHechas++;
            operacionesString.append("kill").append("(").append(proceso); //Lo anota
            operacionesString.append(")\n");
        }
        // Ahora puedes imprimir o usar la cadena generada

        return operacionesString.toString();
    }
    
    public String generarOperaciones(int numProcesos, int numOperaciones, int seed){
        Random random = new Random(seed);
        StringBuilder operacionesString = new StringBuilder();
        
        ArrayList<Integer> posiblesNew = new ArrayList();        
        ArrayList<Integer> procesos = new ArrayList();


        ArrayList<Integer> posiblesKill = new ArrayList();       
      
        HashMap<Integer, Integer> posiblesUse = new HashMap();
        
        //Por cada proceso, todos pueden ser creados ahorita
        //posiblesNew.add(1);
        procesos.add(1);
        for(int i = 1; i <= numProcesos; i++){
            posiblesNew.add(i);
        }

        String[] tiposInstruccion = {"new", "use", "delete", "kill"};
        int punteros = 0;
        
        int killsAntes = 20*numProcesos/100;
        
        int killsDespues = numProcesos - killsAntes;
        
        System.out.println(killsAntes);
        
        int killsHechas =0;
        
        for (int i = 0; i < numOperaciones - killsDespues; i++) {
            String tipo = tiposInstruccion[random.nextInt(tiposInstruccion.length)];
            //operacionesString.append(tipo).append("(").append(proceso);
            boolean anoto = false;
            if (tipo.equals("new") && !posiblesNew.isEmpty()) { //Si es new y aun no se ha hecho kill de todos los procesos
                int proceso;
                if(procesos.size() <= numProcesos){
                    proceso = procesos.size();
                    procesos.add(procesos.size()+1);
                    posiblesNew.add(proceso);
                }else{
                    proceso = posiblesNew.get(random.nextInt(posiblesNew.size()));
                } //Elijo uno de los posibles procesos
                /*if(posiblesNew.size() < numProcesos && random.nextInt(2)==1){
                    proceso = procesos.get(posiblesNew.size()-1);
                    proceso++;
                    posiblesNew.add(proceso);
                }*/
                operacionesString.append(tipo).append("(").append(proceso); //Lo anota
                anoto = true;
                int tamano = random.nextInt(0,40); //Elige un tamaño random
                operacionesString.append(",").append(tamano); //Se mete el tamaño
                punteros++; //Eso genera un puntero posible de usar
                posiblesUse.put(punteros,proceso); //Se mete a los posibles Use y lo asocia con el proceso que lo creo
                if(!posiblesKill.contains(proceso)){
                    posiblesKill.add(proceso);
                }
            }
            else if(tipo.equals("use") && !posiblesUse.isEmpty()){ //Si hay punteros creados y va a usar uno
                ArrayList<Integer> punterosActuales = new ArrayList<>(posiblesUse.keySet()); //Obtiene los posibles punteros
                int puntero = punterosActuales.get(random.nextInt(punterosActuales.size())); // Elige un puntero random a usar
                operacionesString.append(tipo).append("(").append(puntero); // Lo anota
                anoto = true;
            }
            else if(tipo.equals("delete") && !posiblesUse.isEmpty()){ //Si hay punteros creados y va a borrar uno
                ArrayList<Integer> punterosActuales = new ArrayList<>(posiblesUse.keySet()); //Obtiene los posibles punteros
                int puntero = punterosActuales.get(random.nextInt(punterosActuales.size())); // Elige un ptr a borrar
                operacionesString.append(tipo).append("(").append(puntero); // Lo anota
                posiblesUse.remove(puntero);
                anoto = true;
            }
            else if(tipo.equals("kill") && 
                    ! posiblesKill.isEmpty() && killsHechas < killsAntes){
                int proceso = posiblesKill.remove(random.nextInt(posiblesKill.size())); //Elige un proceso random a eliminar
                for(int x = 0; x < posiblesNew.size(); x++){
                    if(posiblesNew.get(x)== proceso){
                        posiblesNew.remove(x);
                    }
                }
                
                operacionesString.append(tipo).append("(").append(proceso); //Lo anota
                Iterator<Map.Entry<Integer, Integer>> iter = posiblesUse.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iter.next();
                    if (entry.getValue().equals(proceso)) {
                        iter.remove(); // Elimina el elemento si el valor coincide
                    }
                }
                anoto = true;
                killsHechas++;
            }
            if(anoto){
                operacionesString.append(")\n");
            }else{
                //System.out.println("No entra a nada");
                i--;
            }
        }
        if(killsHechas == 0){
            for (int i = 0; i < killsAntes; i++) {
                int proceso = posiblesKill.remove(random.nextInt(posiblesKill.size())); //Elige un proceso random a eliminar
                for(int x = 0; x < posiblesNew.size(); x++){
                    if(posiblesNew.get(x)== proceso){
                        posiblesNew.remove(x);
                    }
                }

                operacionesString.append("kill").append("(").append(proceso); //Lo anota
                Iterator<Map.Entry<Integer, Integer>> iter = posiblesUse.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iter.next();
                    if (entry.getValue().equals(proceso)) {
                        iter.remove(); // Elimina el elemento si el valor coincide
                    }
                }
                killsHechas++;
                operacionesString.append(")\n");
            }
        }
        while(!posiblesKill.isEmpty()){
            int proceso = posiblesKill.remove(random.nextInt(posiblesKill.size())); //Elige un proceso random a eliminar
            killsHechas++;
            operacionesString.append("kill").append("(").append(proceso); //Lo anota
            operacionesString.append(")\n");
        }
        // Ahora puedes imprimir o usar la cadena generada

        return operacionesString.toString();
    }
    
    public void guardarArchivo(String nombreArchivo, String contenido) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido);
        }
    }
}
