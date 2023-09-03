/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utilidades.Utilidades;
import java.util.ArrayList;

/**
 *
 * @author wal26
 */
public class Pickup {
    private double coordenadaX;
    private double coordenadaY;
    private String nombre;
    private String horario;

    public Pickup(double coordenadaX, double coordenadaY, String nombre, String horario) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.nombre = nombre;
        this.horario = horario;
    }
    
    public static ArrayList<Pickup> pickup(String ruta){
        ArrayList<Pickup> pickups = new ArrayList<>();
        ArrayList<String[]> datPickup = Utilidades.LeerValidando(ruta, false);
        for (String[] dataPick : datPickup) {
            pickups.add(new Pickup(Double.parseDouble(dataPick[0]), Double.parseDouble(dataPick[1])*-1, dataPick[2],
                dataPick[3]));
        }
        
        return pickups;
    }

    public double getCoordenadaX() {
        return coordenadaX;
    }

    public double getCoordenadaY() {
        return coordenadaY;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }
    
    
}
