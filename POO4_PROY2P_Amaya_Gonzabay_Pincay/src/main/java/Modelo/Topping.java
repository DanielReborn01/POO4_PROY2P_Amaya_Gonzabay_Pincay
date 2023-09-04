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
public class Topping implements Serializable{
    private String nombre;
    private double precio;
    
    /**
     * Constructor de los toppings
     * @param toppings
     * @param nombre
     * @return 
     */
    public static Topping FindTopping(ArrayList<Topping> toppings, String nombre){
        for (Topping top : toppings) {
            if(top.getNombre().equals(nombre)){
                return top;
            }
        }
        return null;
    }
    
    
    /**
     * Obtiene los objetos topping de un archivo
     * @param ruta
     * @return 
     */
    public static ArrayList<Topping> toppings(String ruta){
        ArrayList<Topping> sabores = new ArrayList<>();
        ArrayList<String[]> datToppings = Utilidades.LeerValidando(ruta, false);
        for (String[] dataTopping : datToppings) {
            sabores.add(new Topping(dataTopping[0], Double.parseDouble(dataTopping[1])));
        }
        
        return sabores;
    }

    //Constructor del topping
    public Topping(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del topping
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtinee el precio del topping
     * @return 
     */
    public double getPrecio() {
        return precio;
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
        final Topping other = (Topping) obj;
        return this.nombre.equals(other.nombre);
    }
    
    
}
