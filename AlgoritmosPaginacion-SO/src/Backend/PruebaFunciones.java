/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Backend;

import java.io.IOException;
import java.util.ArrayList;

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
        String content = adminOperaciones.generarOperaciones(5,25);
        adminOperaciones.guardarArchivo("./archivos_instrucciones/contenido.txt", content);
        



    }
    
}
