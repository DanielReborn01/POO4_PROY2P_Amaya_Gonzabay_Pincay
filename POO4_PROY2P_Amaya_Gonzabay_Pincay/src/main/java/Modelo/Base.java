/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utilidades.Utilidades;
import espol.poo4_proy2p_amaya_gonzabay_pincay.App;
import java.util.ArrayList;

/**
 *
 * Clase para la abstraccion de un objeto de tipo base
 * escoger la base de un helado
 * 
 * @author wal26
 */
public class Base {
    
    private String sabor;
    private double precio;

    public Base(String sabor, double precio) {
        this.sabor = sabor;
        this.precio = precio;
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
    
    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return sabor + "," + precio;
    }
    
    
}
