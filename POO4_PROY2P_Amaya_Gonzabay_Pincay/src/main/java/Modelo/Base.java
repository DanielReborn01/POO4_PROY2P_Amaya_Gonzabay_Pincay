/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utilidades.Utilidades;
import espol.poo4_proy2p_amaya_gonzabay_pincay.App;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * Clase para la abstraccion de un objeto de tipo base
 * escoger la base de un helado
 * 
 * @author wal26
 */
public class Base implements Serializable{
    
    private String sabor;
    private double precio;
    
    /**
     * Constructor de la clase base
     * @param sabor Nombre del sabor de la base
     * @param precio Precio del sabor
     */

    public Base(String sabor, double precio) {
        this.sabor = sabor;
        this.precio = precio;
    }

    
    /**
     * Encuentra una base dependiendo del nombre del sabor
     * @param bases Lista de bases a buscar
     * @param sabor Nombre del sabor de la base
     * @return La base que se encontro, si no tiene devuelve null
     */
    public static Base FindBase(ArrayList<Base> bases, String sabor){
        for (Base base : bases) {
            if(base.getSabor().equals(sabor)){
                return base;
            }
        }
        return null;
    }
    
    /**
     * Obtiene las bases de los arhcivos y devuelve una estructura de datos
     * con estos objetos
     * @param ruta Path donde se encuentran los helados
     * @return un ArrayList con los elementos
     */
    public static ArrayList<Base> Bases(String ruta){
        ArrayList<Base> bases = new ArrayList<>();
        ArrayList<String[]> datBase = Utilidades.LeerValidando(ruta, false);
        for (String[] dataBa : datBase) {
            bases.add(new Base(dataBa[0], Double.parseDouble(dataBa[1])));
        }
        
        return bases;
    }
    
    /**
     * Obtiene el sabor de la base
     * @return Nombre del Sabor
     */
    public String getSabor() {
        return sabor;
    }

    /**
     * Establece el sabor de la base
     * @param sabor El nombre del sabor de la base
     */
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    /**
     * Obtiene el precio de la base
     * @return Precio de la base
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la base
     * @param precio 
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    /**
     * Metodo sobreescrito toSrting
     * @return 
     */
    @Override
    public String toString() {
        return sabor + "," + precio;
    }


    /**
     * Metodo equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Base other = (Base) obj;
        return sabor.equals(other.getSabor());
    }
    
    
}
