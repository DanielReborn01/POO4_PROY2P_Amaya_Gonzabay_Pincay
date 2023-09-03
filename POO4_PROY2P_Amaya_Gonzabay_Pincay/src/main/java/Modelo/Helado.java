/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utilidades.Utilidades;
import espol.poo4_proy2p_amaya_gonzabay_pincay.App;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author wal26
 */
public class Helado implements Serializable, Pagable{
    private Base base;
    private Sabor sabor1;
    private Sabor sabor2;
    private ArrayList<Topping> toppings;
    private Usuario usuario;
    private double precio;
    private int id;
    

    public Helado() {
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            id = number.nextInt(999999);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Algo fallo con el metodo de generacion de numeros");
        }
        precio = 0;
        toppings = new ArrayList<>();
        
    }

    public int getId() {
        return id;
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

    public void savePedido() throws IOException{
        //Se guarda en el archivo pedido
        
        BufferedWriter bff = new BufferedWriter(new FileWriter(App.pathData + "pedidos.txt",true));
        bff.write(this+"\n");
        bff.flush();
        
        //Guardamos el objeto
        ObjectOutputStream ob = new ObjectOutputStream(new FileOutputStream(App.pathData + "pedido" + this.id + ".bin", true));
        ob.writeObject(this);
        ob.flush();
    }

    @Override
    public String toString() {
        return id+";"+usuario.getNombres()+";"+precio;
    }
    
    
    

    public double getPrecio() {
        return precio;
    }

    @Override
    public void generarTransaccion(TipoPago tipoPago) {
        //Se guardara la informacion del pago
        SecureRandom number;
        int idPago = 0;
        try {
            number = SecureRandom.getInstance("SHA1PRNG");
            idPago = number.nextInt(99999);

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        double aumento = 0;
        if(tipoPago.equals(TipoPago.T)){
            aumento = 0.69;
        }
        //Precio con iva y con el aumento de la trajeta si  ese es el caso
        double precioTotal = this.precio + aumento + this.precio*0.12;
        LocalDate fechaActual = LocalDate.now();
        String dataSave = idPago+","+this.id+","+this.usuario.getNombres()+","+precioTotal+
                ","+fechaActual+","+tipoPago.toString();
        
        //Lo escribimos en el archivo 
        Utilidades.EscribirArchivo(App.pathData + "pagos.txt", dataSave);
    }
    
    
    
}
