/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utilidades.Utilidades;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author wal26
 */
public class Topping {
    private String nombre;
    private double precio;
    
    public static Topping FindTopping(ArrayList<Topping> toppings, String nombre){
        for (Topping top : toppings) {
            if(top.getNombre().equals(nombre)){
                return top;
            }
        }
        return null;
    }
    
    public static ArrayList<Topping> toppings(String ruta){
        ArrayList<Topping> sabores = new ArrayList<>();
        ArrayList<String[]> datToppings = Utilidades.LeerValidando(ruta, false);
        for (String[] dataTopping : datToppings) {
            sabores.add(new Topping(dataTopping[0], Double.parseDouble(dataTopping[1])));
        }
        
        return sabores;
    }

    public Topping(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - " + precio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

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
