/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author wal26
 */
public class Helado {
    private Base base;
    private Sabor sabor1;
    private Sabor sabor2;
    private ArrayList<Topping> toppings;
    private Usuario usuario;
    private double precio;

    public Helado() {
        precio = 0;
        toppings = new ArrayList<>();
        
    }
    
    
    
    public Base getBase() {
        
        return base;
    }

    public void setBase(Base base) {
        if(this.base != null){
            precio -= this.base.getPrecio();
        }
        
        precio += base.getPrecio();
        this.base = base;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sabor getSabor1() {
        return sabor1;
    }

    public void setSabor1(Sabor sabor1) {
        if(this.sabor1 != null){
            precio -= this.sabor1.getPrecio();
        }
        
        if(sabor1 != null){
            precio += sabor1.getPrecio();
        }
        
        this.sabor1 = sabor1;
    }
    
    public Sabor getSabor2() {
        return sabor2;
    }

    public void setSabor2(Sabor sabor2) {
        if(this.sabor2 != null){
            precio -= this.sabor2.getPrecio();
        }
        if(sabor2 != null){
            
            precio += sabor2.getPrecio();
        }
        
        this.sabor2 = sabor2;
    }
    
    public void addTopping(Topping topping){
        precio += topping.getPrecio();
        toppings.add(topping);
    }
    
    public void removeTopping(Topping topping){
        precio -= topping.getPrecio();
        toppings.remove(topping);
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    
    @Override
    public String toString() {
        return "Helado{" + "base=" + base + ", sabor1=" + sabor1 + ", sabor2=" + sabor2 + ", usuario=" + usuario + ", precio=" + precio + '}';
    }
    

    public double getPrecio() {
        return precio;
    }
    
    
    
}
