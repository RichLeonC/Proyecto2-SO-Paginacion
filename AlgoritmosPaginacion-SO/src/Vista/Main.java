/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Backend.AdminOperaciones;
import Backend.Computadora;
import Modelo.Instruccion;
import Modelo.MemoryManagementUnit;
import Modelo.Pagina;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author richa
 */
public class Main {
    static Computadora computadora = new Computadora();    
    static Simulacion simulacion = new Simulacion();
    static Configuracion configuracion = new Configuracion();


    
    public static void main(String[] args) {
        configuracion.setVisible(true);
        AdminOperaciones adminOperaciones = new AdminOperaciones();
        String operacionesString = adminOperaciones.generarOperaciones(5,25);
        ArrayList<Instruccion> instrucciones = adminOperaciones.stringToOperaciones(operacionesString);
        computadora.secondChance(instrucciones);
        
       
    }
}
