/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.Topping;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class ToppingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ArrayList<Topping> toppings = App.toppings; //Obtenemos una copia
    
    @FXML
    private Label lblPagarTopping;
    
    @FXML
    private VBox vboxToppings;
    
    @FXML
    private Button btnNextToppings;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Se actualiza el label del precio
        lblPagarTopping.setText("Valor a pagar: " + App.heladoPedido.getPrecio());
        
        //Agregamos los toppings 
        
        VBox contChBox = new VBox();
        contChBox.setSpacing(20);
        contChBox.setPadding(new Insets(20, 20, 10, 20));
        for (Topping topping : toppings) {
            CheckBox ckbTopp = new CheckBox(topping.toString());
            
            ckbTopp.addEventHandler(ActionEvent.ACTION, e ->{
                CheckBox checkSel = (CheckBox) e.getSource();
                choiceTopping(checkSel);
            });
            
            contChBox.getChildren().add(ckbTopp);
        }
        
        vboxToppings.getChildren().add(contChBox);
        
        
        //Agregando el evento al boton
        btnNextToppings.addEventHandler(ActionEvent.ACTION, e ->{
            try {
                changeScene();
            } catch (IOException ex) {
                System.out.println("Hubo un error al abrir la ventana");
            }
        });
    }    
    
    
    /**
     * Escogen el topping
     * @param checkTopping 
     */
    private void choiceTopping(CheckBox checkTopping){
        
        //Obtener el topping 
       
        Topping topping = Topping.FindTopping(toppings, checkTopping.getText().split(" - ")[0]);
        
        
        if(checkTopping.isSelected()){
            App.heladoPedido.addTopping(topping);
        }else if (!checkTopping.isSelected()) {
            App.heladoPedido.removeTopping(topping);
        }
        
        lblPagarTopping.setText("Valor a pagar: " + App.heladoPedido.getPrecio());
       
    }
    
    /**
     * Cambia la escena
     * @throws IOException 
     */
    private void changeScene() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/pedido" + ".fxml"));
        Parent rootNew = fxmlLoader.load();
        
        double ancho = BienvenidaController.stagePedidos.getScene().getWidth();
        double alto = BienvenidaController.stagePedidos.getScene().getHeight();
        
        BienvenidaController.stagePedidos.setScene(new Scene(rootNew, ancho,alto));
    }
}
