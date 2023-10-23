
package Vista;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Backend.AdminOperaciones;
import Backend.Computadora;
import Modelo.Instruccion;
import Modelo.MemoryManagementUnit;
import Modelo.Pagina;
import Vista.Configuracion;
import Vista.Simulacion;
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
        System.out.println("Hola");
        configuracion.setVisible(true);
        AdminOperaciones adminOperaciones = new AdminOperaciones();
        String operacionesString = adminOperaciones.generarOperaciones(10,200);
        ArrayList<Instruccion> instrucciones = adminOperaciones.stringToOperaciones(operacionesString);
        computadora.secondChance(instrucciones);
    }
}
