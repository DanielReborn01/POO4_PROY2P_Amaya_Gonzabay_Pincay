/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.IncompleteStageException;
import Modelo.Sabor;
import Modelo.Topping;
import Modelo.windowDialog;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class PedidoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ListView LVpedido;
    
    @FXML
    private Label lblPrecioPedido;
    
    @FXML
    private Button btnEliminar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblPrecioPedido.setText("Valor a pagar: " + App.heladoPedido.getPrecio());
        LVpedido.minHeight(300);
        LVpedido.minWidth(250);
        updateList();
        
        btnEliminar.addEventHandler(ActionEvent.ACTION, e->{
            if(LVpedido.getSelectionModel().getSelectedItem() == null) return;
            makeWindowDialog((s)->{
                try {
                    RemoveElemento(s);
                } catch (IncompleteStageException ex) {
                    System.out.println(ex);
                }
            }, "Esta seguro que desea eliminar el elemento");
        });
    }

    /**
     * Es una ventana de dialog que al confirmar ejecuta una accion
     * esta accion sera dependiendo de la implemtnacion del metodo start
     * de la interfaz windowDialog
     * @param dialog objeto que implemente la interfaz
     */
    private void makeWindowDialog(windowDialog dialog, String msg){
        Stage stageDialog = new Stage();
        VBox contParent = new VBox();
        contParent.setAlignment(Pos.CENTER);
        contParent.getStyleClass().add("mainFxmlClass");
        contParent.getStylesheets().add("styles/pedidos.css");
        
        Label lbMensaje = new Label(msg);
        lbMensaje.getStyleClass().add("lblMensajeDialog");
        
        HBox vonBotones = new HBox();
        vonBotones.setMinHeight(125);
        vonBotones.setSpacing(20);
        vonBotones.setAlignment(Pos.CENTER);
        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");
        
        btnCancelar.setOnAction(e->{
            stageDialog.close();
        });
        
        btnAceptar.setOnAction(e->{
            dialog.start(stageDialog);
        });
        
        vonBotones.getChildren().addAll(btnAceptar, btnCancelar);
        contParent.getChildren().addAll(lbMensaje, vonBotones);
        
        Scene scene = new Scene(contParent, 350, 250);
        stageDialog.setScene(scene);
        stageDialog.setResizable(false);
        stageDialog.initModality(Modality.APPLICATION_MODAL);
        stageDialog.show();
        
    }
    
    private void RemoveElemento(Stage stage) throws IncompleteStageException{
        String selTipo = ((String)LVpedido.getSelectionModel().getSelectedItem()).split(": ")[0];
        String selOpc = ((String)LVpedido.getSelectionModel().getSelectedItem()).split(": ")[1];
        

        switch (selTipo) {
            case "Base":
                throw new IncompleteStageException("Lo siento no se puede eliminar una base");
            case "Sabor":
                
                if(!(App.heladoPedido.getSabor1() != null && App.heladoPedido.getSabor2() != null)){
                    throw new IncompleteStageException("Lo siento como minimo debe haber un sabor");
                }
                
                Sabor sabor = Sabor.FindSabor(App.sabores, selOpc);
               
                
                if(App.heladoPedido.getSabor1().equals(sabor)){
                    App.heladoPedido.setSabor1(null);
                }else if(App.heladoPedido.getSabor2().equals(sabor)){
                    App.heladoPedido.setSabor2(null);
                }
                break;
            case "Topping":
                Topping topping = Topping.FindTopping(App.toppings, selOpc);
                App.heladoPedido.removeTopping(topping);
                break;
            default:
                break;
        }
        
        updateList();
        stage.close();
        
    }
    
    private void updateList(){
        LVpedido.getItems().clear();
        String baseNombre = App.heladoPedido.getBase().getSabor();
        String saborNombre1 = null;
        if(App.heladoPedido.getSabor1() != null){
            saborNombre1 = App.heladoPedido.getSabor1().getNombre();
        }
        String saborNombre2 = null;
        if(App.heladoPedido.getSabor2() != null){
            saborNombre2 = App.heladoPedido.getSabor2().getNombre();
        }
        ArrayList<Topping> toppings = App.heladoPedido.getToppings();
        
        //Mostrando en el listView
        LVpedido.getItems().add("Base: " + baseNombre);
        if(saborNombre1 != null){
            LVpedido.getItems().add("Sabor: " + saborNombre1);
        }
        if(saborNombre2 != null){
            LVpedido.getItems().add("Sabor: " + saborNombre2);
        }
        
        for (Topping topping : toppings) {
            LVpedido.getItems().add("Topping: " + topping.getNombre());
        }
        
        lblPrecioPedido.setText("Valor a pagar: " + App.heladoPedido.getPrecio());
    }
    
}
