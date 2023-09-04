/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.IOException;

/**
 *  Excepcion sobre falta de campos
 * @author wal26
 */
public class IncompleteFieldsException extends IOException{

    /**
     * Contructor de la excpecion
     * @param message Mensaje a mostrar
     */
    public IncompleteFieldsException(String message) {
        super(message);
    }
    
}
