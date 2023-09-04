/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import java.io.IOException;

/**
 * Excepcion sobre falta escoger elementos
 * @author wal26
 */
public class IncompleteStageException extends IOException{

    /**
     * Contructor de la excepcion
     * @param message Mensaje a mostrar
     */
    public IncompleteStageException(String message) {
        super(message);
    }
    
}
