/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Backend;

import Modelo.Instruccion;
import Modelo.MemoryManagementUnit;
import Modelo.Pagina;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author acost
 */
public class PruebaFunciones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        AdminOperaciones adminOperaciones = new AdminOperaciones();
        /*String operacionesString = adminOperaciones.cargarArchivoOperaciones("./archivos_instrucciones/instrucciones.txt");
        ArrayList<Instruccion> instrucciones = adminOperaciones.stringToOperaciones(operacionesString);
        for(Instruccion ins : instrucciones){
            System.out.println("\n tipo: " + ins.tipoInstruccion + " Parametros: " + ins.parametros);
        }*/
        String operacionesString = adminOperaciones.generarOperaciones(5,25,1);
        
        ArrayList<Instruccion> instrucciones = adminOperaciones.stringToOperaciones(operacionesString);
        System.out.println(instrucciones);
        Computadora compu = new Computadora();
        compu.secondChance(instrucciones);
        MemoryManagementUnit mmu = compu.getMmu();
        HashMap<String, ArrayList<Pagina>> map = mmu.getMapa();
        for (int i = 0; i < map.size(); i++) {
            System.out.println(map.get(String.valueOf(i)));
        }
        
        //adminOperaciones.guardarArchivo("./archivos_instrucciones/contenido.txt", operacionesString);
        



    }
    
}
