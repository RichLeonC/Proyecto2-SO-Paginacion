/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Backend;

/**
 * Enumeración que define los tipos de instrucciones posibles en el contexto de una Unidad de Gestión de Memoria (MMU).
 * @author Viviana Acosta
 */
public enum TipoInstruccion {
     /**
     * Instrucción para asignar nueva memoria (NEW).
     */
    NEW,
    
    /**
     * Instrucción para utilizar memoria existente (USE).
     */
    USE,
    
    /**
     * Instrucción para liberar memoria (DELETE).
     */
    DELETE,
    
    /**
     * Instrucción para liberar memoria de manera definitiva (KILL).
     */
    KILL
}
