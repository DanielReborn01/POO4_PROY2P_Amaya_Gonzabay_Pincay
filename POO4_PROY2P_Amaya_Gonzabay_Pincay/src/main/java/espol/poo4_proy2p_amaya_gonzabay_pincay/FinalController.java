/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.Helado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class FinalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label lbPedido;
    
    @FXML
    private Label lbEspera;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbPedido.setText("Tu pedido es el " + App.heladoPedido.getId() + ". Te llamaremos cuando esté listo");
        
        Thread hilo = new Thread(()->{
            for(int i=5; i>=0; i--){
                try {
                    setText(i+"");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            //Limpiamos los datos del pedido
            App.heladoPedido = new Helado();
            App.heladoPedido.setUsuario(App.userLogin);
            Platform.runLater(()->{
                BienvenidaController.stagePedidos.close();
            });
            
        });
        hilo.start();
 
        
    }    
    /**
     * Muestra en el label
     * @param contador 
     */
    private void setText(String contador){
        Platform.runLater(()->{
            lbEspera.setText("Esta ventana se cerrará en "+contador+" segundos");
        });
        
    }
}
