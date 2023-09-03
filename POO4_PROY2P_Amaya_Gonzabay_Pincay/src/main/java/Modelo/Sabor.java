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
public class Sabor implements Serializable {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
        final Sabor other = (Sabor) obj;
        return nombre.equals(other.getNombre());
    }
    
    
    
}
