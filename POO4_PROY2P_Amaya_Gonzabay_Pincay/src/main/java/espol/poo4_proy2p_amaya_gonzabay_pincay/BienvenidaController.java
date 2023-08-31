/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class BienvenidaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static Stage stagePedidos;
    
    @FXML
    private Button btnLocales;

    @FXML
    private Button btnPedidos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Colocando eventos a los botones
        btnLocales.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("abre la ventanas de los locales");
            }
        });

        btnPedidos.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    makeWindowPedidos();
                } catch (IOException ex) {
                    System.out.println("Ocurrio un error");
                }
            }
        });

    }

    /**
     * Se encarara de crear la ventana donde se podra realizar el pedido
     */
    private void makeWindowPedidos() throws IOException {
        stagePedidos = new Stage();
        Parent rootPedidos = FXMLLoader.load(App.class.getResource("/fxml/baseHelado" + ".fxml"));
        
        Scene scene = new Scene(rootPedidos);
        stagePedidos.setScene(scene);
        stagePedidos.show();
        
    }

}
