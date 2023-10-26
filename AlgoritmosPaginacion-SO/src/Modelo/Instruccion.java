/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author megui
 */
public class Instruccion {
    /**
     * Enumeración que define los tipos de instrucciones posibles.
     */
    private TipoInstruccion tipoInstruccion;
    
    /**
     * Lista de parámetros asociados a la instrucción.
     */
    private ArrayList<String> parametros;

    public TipoInstruccion getTipoInstruccion() {
        return tipoInstruccion;
    }

    public void setTipoInstruccion(TipoInstruccion tipoInstruccion) {
        this.tipoInstruccion = tipoInstruccion;
    }

    public ArrayList<String> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<String> parametros) {
        this.parametros = parametros;
    }
    
     /**
     * Constructor de la clase Instruccion.
     *
     * @param tipo   El tipo de instrucción.
     * @param params La lista de parámetros asociados a la instrucción.
     */
    public Instruccion(TipoInstruccion tipo, ArrayList<String> params){
        this.tipoInstruccion = tipo;
        this.parametros = params;
    }
    
    
    public boolean esReferenciaAPagina(Pagina page,HashMap<String, ArrayList<Pagina>> mapa){
        if(tipoInstruccion!=TipoInstruccion.USE)
            return false;
        
        ArrayList<Pagina> paginasAsociadas = mapa.get(parametros.get(0));
        if(paginasAsociadas == null) return false;
      
        return paginasAsociadas.contains(page);
   
    }
}