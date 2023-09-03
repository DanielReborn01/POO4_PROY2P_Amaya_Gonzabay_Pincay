/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Utilidades.Utilidades;
import static espol.poo4_proy2p_amaya_gonzabay_pincay.App.pathData;
import static espol.poo4_proy2p_amaya_gonzabay_pincay.App.usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *  Clase la cual hace una representacion abstracta del usuario
 * 
 * @author Walter G
 */
public class Usuario implements Serializable{
    private String nombres;
    private String correo;
    private String password;

    /**
     * Constructor para crear objetos de tipo usuario, el cual contiene 
     * toda la informacion
     * 
     * @param nombres Nombre del usuario
     * @param correo Correo del usuario
     * @param password Contraseña del usuario
     */
    public Usuario(String nombres, String correo, String password) {
        this.nombres = nombres;
        this.correo = correo;
        this.password = password;
    }

    
    /**
     * Contructor de usuario que sirve para poder verificar si la contraseña
     * es correcta o no
     * 
     * @param correo
     * @param password 
     */
    public Usuario(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }
    
    
    /**
     * Metodo que permite obtener los datos del archivo usuario
     * @param ruta Path donde se encuentran los datos
     * @return un ArrayList con los objetos usuarios
     */
    public static ArrayList<Usuario> usuarios(String ruta){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<String[]> dataUsuario = Utilidades.LeerValidando(ruta, false);
        for (String[] dataUser : dataUsuario) {
            usuarios.add(new Usuario(dataUser[0], dataUser[1], dataUser[2]));
        }
        return usuarios;
    }
    

    /**
     * Obtiene los nombres del usuario
     * @return Los nombres del usuario
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del usuario
     * 
     * @param nombres Nombres del usuario
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el correo del usuario
     * @return Correo del Usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo del Usuario
     * @param correo Correo del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del Usuario
     * @return 
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Establece la contrasña del usuario
     * @param password Contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
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
        final Usuario other = (Usuario) obj;
        return this.correo.equals(other.correo);
    }

    @Override
    public String toString() {
        return nombres + "," + correo + "," + password;
    }
    
    
    
    
    
}
