/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.IncompleteStageException;
import Modelo.Sabor;
import Utilidades.popupWindows;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class SaboresHeladoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Label lblPagarSabores;
    
    @FXML 
    private ComboBox cbSabor1;
    
    @FXML 
    private ComboBox cbSabor2;
    
    @FXML
    private Button btnSaboresContinuar; 
    
    private ArrayList<Sabor> sabores = App.sabores; //Copia de la direccion de memoria
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println(App.heladoPedido);
        //Colocamos el precio actual del pedido
        lblPagarSabores.setText("Valor a pagar: " + Double.toString(App.heladoPedido.getPrecio()));
        
        cbSabor2.getItems().add("Ninguno");
        //Agregamos elementos al comboBox
        for (Sabor sabor : sabores) {
            cbSabor1.getItems().add(sabor);
            cbSabor2.getItems().add(sabor);
        }
        
        cbSabor1.addEventHandler(ActionEvent.ACTION, e->{
            choiceSabor(cbSabor1);
        });
        cbSabor2.addEventHandler(ActionEvent.ACTION, e->{
            choiceSabor(cbSabor2);
        });
        
        btnSaboresContinuar.addEventHandler(ActionEvent.ACTION, e ->{
            try {
                nextPage();
            } catch (IncompleteStageException ex) {
                popupWindows.makeWindowEmer("Porfavor, debe seleccionar al menos un sabor");
            } catch (IOException ex) {
                popupWindows.makeWindowEmer("Ocurrio un error en el sistema");
            }
        });
        
    }    
    
    /**
     * Escoge el sabor seleccionado
     * @param combo 
     */
    private void choiceSabor(ComboBox combo){
        
        String sabor = combo.getSelectionModel().getSelectedItem().toString().split(" - ")[0];
        Sabor sab = Sabor.FindSabor(sabores, sabor);
        if(combo == cbSabor1){
            App.heladoPedido.setSabor1(sab);
        }else{
            if(sab != null){
                App.heladoPedido.setSabor2(sab);
            }else{
                App.heladoPedido.setSabor2(null);
            }
        }
        
        lblPagarSabores.setText("Valor a pagar: " + App.heladoPedido.getPrecio());

        
    }
    
    
    /**
     * Pasa la escena sigueitne
     * @throws IncompleteStageException
     * @throws IOException 
     */
    private void nextPage() throws IncompleteStageException, IOException{
        //Valida que se haya escogido el comboBox
        System.out.println(cbSabor1.getValue());
        if(cbSabor1.getValue() == null){
            throw new IncompleteStageException("Ocurrio un error");
        }
        
        //Muestra la escena de los toppings
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/topping" + ".fxml"));
        Parent rootNew = fxmlLoader.load();
        
        
        double ancho = BienvenidaController.stagePedidos.getScene().getWidth();
        double alto = BienvenidaController.stagePedidos.getScene().getHeight();
        
        BienvenidaController.stagePedidos.setScene(new Scene(rootNew, ancho,alto));
        
    }
}
