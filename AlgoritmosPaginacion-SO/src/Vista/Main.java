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
 *public void showPages(){
        MemoryManagementUnit mmu = Main.computadora.getMmu();
        HashMap<String, ArrayList<Pagina>> map = mmu.getMapa();
        ArrayList<Pagina> paginas = new ArrayList();
        ArrayList<Integer> ids = new ArrayList();
        for (int i = 0; i < map.size(); i++) {
            if(map.get(String.valueOf(i))!= null){
                paginas.addAll(map.get(String.valueOf(i)));
                for(int j = 0; j < map.get(String.valueOf(i)).size(); j++){
                    ids.add(i);
                }
            }
            
        }
        System.out.println("YAY6");
        DefaultTableModel model = (DefaultTableModel) algorithmTable.getModel();
        System.out.println("YAY7");
        for(int i = 0; i < paginas.size(); i++){
            Pagina page = paginas.get(i);
            model.addRow(new Object[]{page.id, String.valueOf(ids.get(i)),page.direccionFisica, page.direccionVirtual, page.marking});
            System.out.println("Nice");
        }
    }
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
