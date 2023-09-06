/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author wal26
 */
public class Sabor implements Serializable, Comparable<Sabor> {
    private String nombre;
    private double precio;

    public Sabor(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public static Sabor FindSabor(ArrayList<Sabor> sabores, String nombre){
        for (Sabor sab : sabores) {
            if(sab.getNombre().equals(nombre)){
                return sab;
            }
        }
        return null;
    }
    
    
    /**
     * Obtiene los sabores de los arhcivos y devuelve una estructura de datos
     * con estos objetos
     * @param ruta Path donde se encuentran los sabores
     * @return un ArrayList con los elementos
     */
    public static ArrayList<Sabor> sabores(String ruta){
        ArrayList<Sabor> sabores = new ArrayList<>();
        ArrayList<String[]> datSabores = Utilidades.LeerValidando(ruta, false);
        for (String[] dataSabor : datSabores) {
            sabores.add(new Sabor(dataSabor[0], Double.parseDouble(dataSabor[1])));
        }
        
        return sabores;
    }

    /**
     * Obtiene el nombre del sabor
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre 
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /**
     * Obtiene el precio
     * @return 
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio
     * @param precio 
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    /**
     * Metodo toString
     * @return 
     */
    @Override
    public String toString() {
        return nombre + " - " + precio;
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
        final Sabor other = (Sabor) obj;
        return nombre.equals(other.getNombre());
    }

    @Override
    public int compareTo(Sabor o) {
        return this.nombre.compareTo(o.getNombre());
    }
    
    
    
}
