/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.Usuario;
import Utilidades.Utilidades;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Walter G
 */
public class LoginController implements Initializable {
    
    @FXML
    private Button btnLogin;
    
    @FXML
    private TextField txUser;
    
    @FXML
    private TextField txPassw;
    
    @FXML
    private Label lbMessage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Se capturara el evento del boton
        btnLogin.addEventHandler(ActionEvent.ACTION, (t) -> {
            
            String correo = txUser.getText();
            String password = txPassw.getText();
            login(correo, password);
            //App.changeScene(new Scene(new VBox(new Label("Hola"))));
        });
        
        
    } 
    
    /**
     * Encargado de poder iniciar sesion
     * @param correo Correo del Usuario
     * @param password Constraseña del usuario
     */
    private void login(String correo, String password){
        Usuario user = new Usuario(correo, password);
        int indice = App.usuarios.indexOf(user); 
        if(indice == -1){
            Utilidades.animateStyle(txUser, "txt-error", 2000);
            lbMessage.setText("No se pudo encontrar el usuario");
            return;
        }
        if(!App.usuarios.get(indice).getPassword().equals(password)){
            Utilidades.animateStyle(txPassw, "txt-error", 2000);
            Utilidades.animateStyle(txUser, "txt-error", 2000);
            lbMessage.setText("No se pudo validar sus credenciales");
            return;
        }
        
        //Esta seccion entrara solo el usuario loguiado
        //Se puede simular la ventana de carga para mas realismo 
        //ya que el efecto sera inmediato
        lbMessage.setText("");
        
        //Agregando el usuasrio al pedido
        App.heladoPedido.setUsuario(App.usuarios.get(indice));
        App.userLogin = App.usuarios.get(indice);
        //Mostrando la ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/bienvenida" + ".fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
            App.changeScene(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        makeNewWindow();
        
    }
    
    private void makeNewWindow(){
        Stage stagePedidos = new Stage();
        Scene scena = new Scene(new HBox(),350,250);
        stagePedidos.setScene(scena);
        stagePedidos.show();
    }
    
        
        
}
