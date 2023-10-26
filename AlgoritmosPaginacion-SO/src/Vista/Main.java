
package Vista;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Backend.AdminOperaciones;
import Backend.Computadora;
import Backend.TipoAlgoritmo;
import Modelo.Estadisticas;
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
    public static Computadora computadora = new Computadora();    
    public static Simulacion simulacion = new Simulacion();
    public static Configuracion configuracion = new Configuracion();
    public static Estadisticas estadisticasAlg = new Estadisticas();
    public static Estadisticas estadisticasOPT = new Estadisticas();


    
    public static void main(String[] args) {
        configuracion.setVisible(true);
    }
}
