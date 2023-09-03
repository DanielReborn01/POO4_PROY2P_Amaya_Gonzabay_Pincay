/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author danie
 */
public class PagoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtValorPagar;
    
    @FXML
    private TextField txtAdicionalTarjeta;
    
    @FXML
    private TextField txtIva;
    
    @FXML
    private TextField txtTotal;
    
    @FXML
    private ToggleGroup pagoGroup;
    
    @FXML
    private RadioButton rbEfectivo;
    
    @FXML
    private RadioButton rbTarjeta;
    
    @FXML
    private VBox rootPago;
    
    @FXML
    private VBox contDin;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        //Colocando los datos de pago
        txtValorPagar.setText(App.heladoPedido.getPrecio()+"");
        txtAdicionalTarjeta.setText("0.0");
        txtIva.setText((App.heladoPedido.getPrecio()*0.12) + "");
        double precioTotal = App.heladoPedido.getPrecio() + App.heladoPedido.getPrecio()*0.12;
        txtTotal.setText(precioTotal + "");
        
        
        //Colocando los controles
        rbEfectivo.addEventHandler(ActionEvent.ACTION, e->{
            showSection(e);
        });
        
        rbTarjeta.addEventHandler(ActionEvent.ACTION, e->{
            showSection(e);
        });
        
    }    
    
    private void showSection(ActionEvent e){
        //Se limpia el contenedor
        contDin.getChildren().clear();
        contDin.setPadding(new Insets(10, 0, 0, 0));
        RadioButton rbSel = (RadioButton) e.getSource();
        if(rbSel.equals(rbEfectivo)){
            Label mensajeL = new Label("Puede acercarse a ventanilla para poder pagar su pedido");
            mensajeL.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
            contDin.getChildren().add(mensajeL);
            
        }else if(rbSel.equals(rbTarjeta)){
            BienvenidaController.stagePedidos.setHeight(500);
            Label lblMensa = new Label("Inserte los datos de su tarjeta");
            GridPane griPane = new GridPane();
            ColumnConstraints c1 = new ColumnConstraints();
            ColumnConstraints c2 = new ColumnConstraints();
            
            c1.setPercentWidth(30);
            c2.setPercentWidth(70);
            griPane.getColumnConstraints().addAll(c1, c2);
            griPane.setHgap(5);
            griPane.setVgap(5);
            
            Label lblNombre = new Label("Nombre ");
            GridPane.setRowIndex(lblNombre, 0);
            GridPane.setColumnIndex(lblMensa, 0);
            
            TextField txtNombre = new TextField();
            GridPane.setRowIndex(txtNombre, 0);
            GridPane.setColumnIndex(txtNombre, 1);
            
            Label lblNumero = new Label("Numero ");
            GridPane.setRowIndex(lblNumero, 1);
            GridPane.setColumnIndex(lblNumero, 0);
            
            TextField txtNumero = new TextField();
            GridPane.setRowIndex(txtNumero, 1);
            GridPane.setColumnIndex(txtNumero, 1);
            
            Label lblFecha = new Label("Fecha de caducidad ");
            GridPane.setRowIndex(lblFecha, 2);
            GridPane.setColumnIndex(lblFecha, 0);
            
            DatePicker dpFecha = new DatePicker();
            GridPane.setRowIndex(dpFecha, 2);
            GridPane.setColumnIndex(dpFecha, 1);
            
            Label lblCvv = new Label("CVV ");
            GridPane.setRowIndex(lblCvv, 3);
            GridPane.setColumnIndex(lblCvv, 0);
            
            TextField txtCvv = new TextField();
            GridPane.setRowIndex(txtCvv, 3);
            GridPane.setColumnIndex(txtCvv, 1);
            
            griPane.getChildren().addAll(lblNombre, txtNombre, lblNumero, txtNumero,
                    lblFecha, dpFecha, lblCvv, txtCvv);
            
            contDin.getChildren().add(griPane);
            
        }
        
        
    }
}
