/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.Helado;
import Modelo.IncompleteFieldsException;
import Modelo.TipoPago;
import Utilidades.Utilidades;
import Utilidades.popupWindows;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button btnCancelar;
    
    @FXML
    private RadioButton rbEfectivo;
    
    @FXML
    private RadioButton rbTarjeta;
    
    @FXML
    private VBox rootPago;
    
    @FXML
    private VBox contDin;
    
    @FXML 
    private Button btnConfirmar;
    
    private TextField txtNombre, txtNumero, txtCvv;
    private DatePicker dpFecha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        //Colocando los datos de pago
        updateTextEfectivo();
        
        
        //Colocando los controles
        rbEfectivo.addEventHandler(ActionEvent.ACTION, e->{
            showSection(e);
        });
        
        rbTarjeta.addEventHandler(ActionEvent.ACTION, e->{
            showSection(e);
        });
        
        btnConfirmar.addEventHandler(ActionEvent.ACTION, e->{
            popupWindows.makeWindowDialog((s)->{
                try {
                    pagarPedido();
                } catch (IncompleteFieldsException ex) {
                    popupWindows.makeWindowEmer(ex.getMessage());
                }
                s.close();
            }, "Esta seguro de confirmar el pago");
            
        });
        btnCancelar.addEventHandler(ActionEvent.ACTION, (e)->{
            //Eliminar pedido
            popupWindows.makeWindowDialog((s)->{
                BienvenidaController.stagePedidos.close();
                int indice = Helado.getIndiceSave(App.pathData + "pedidos.txt", App.heladoPedido);
                Utilidades.EliminarLinea(App.pathData + "pedidos.txt", (indice + 1));
                //Asi mismo se elimina el archivo 
                App.heladoPedido = new Helado();
                App.heladoPedido.setUsuario(App.userLogin);
                
                //Elimina el pedidodo el archivo
                BienvenidaController.stagePedidos.close();
                s.close();
            }, "Esta seguro que desea cancelar el pedido :c");
        });
    } 
    
    /**
     * Actuliza los datos del pedido cuando se seleccion efectivo
     */
    private void updateTextEfectivo(){
        txtValorPagar.setText(App.heladoPedido.getPrecio()+"");
        double preciores = 0.0;
        if(!txtAdicionalTarjeta.getText().isEmpty()){
            preciores = Double.parseDouble(txtAdicionalTarjeta.getText());
            
        }
        txtAdicionalTarjeta.setText("0.0");
        txtIva.setText((App.heladoPedido.getPrecio()*0.12) + "");
        double precioTotal = App.heladoPedido.getPrecio() + App.heladoPedido.getPrecio()*0.12 - preciores;
        txtTotal.setText(precioTotal + "");
    }
    
    /**
     * Muestras los datos para llenar los campos de la tarjeta
     * @param e 
     */
    private void showSection(ActionEvent e){
        //Se limpia el contenedor
        contDin.getChildren().clear();
        contDin.setPadding(new Insets(10, 0, 0, 20));
        RadioButton rbSel = (RadioButton) e.getSource();
        if(rbSel.equals(rbEfectivo)){
            Label mensajeL = new Label("Puede acercarse a ventanilla para poder pagar su pedido");
            mensajeL.setStyle("-fx-font-size: 18; -fx-font-weight: bold");
            contDin.getChildren().add(mensajeL);
            updateTextEfectivo();
            
        }else if(rbSel.equals(rbTarjeta)){
            txtAdicionalTarjeta.setText(0.69 + "");
            double precio = Double.parseDouble(txtTotal.getText()) + 0.69;
            txtTotal.setText(precio + "");
            BienvenidaController.stagePedidos.setHeight(500);
            Label lblMensa = new Label("Inserte los datos de su tarjeta");
            GridPane griPane = new GridPane();
            
            griPane.setVgap(5);
            ColumnConstraints c1 = new ColumnConstraints();
            c1.setPercentWidth(30);
            griPane.getColumnConstraints().add(c1);
            Label lblNombre = new Label("Nombre ");
            GridPane.setRowIndex(lblNombre, 0);
            GridPane.setColumnIndex(lblMensa, 0);
            
            this.txtNombre = new TextField();
            txtNombre.setPrefWidth(200);
            GridPane.setRowIndex(txtNombre, 0);
            GridPane.setColumnIndex(txtNombre, 1);
            
            Label lblNumero = new Label("Numero ");
            GridPane.setRowIndex(lblNumero, 1);
            GridPane.setColumnIndex(lblNumero, 0);
            
            this.txtNumero = new TextField();
            txtNumero.setPrefWidth(200);
            GridPane.setRowIndex(txtNumero, 1);
            GridPane.setColumnIndex(txtNumero, 1);
            
            Label lblFecha = new Label("Fecha de caducidad ");
            GridPane.setRowIndex(lblFecha, 2);
            GridPane.setColumnIndex(lblFecha, 0);
            
            this.dpFecha = new DatePicker();
            dpFecha.setPrefWidth(200);
            GridPane.setRowIndex(dpFecha, 2);
            GridPane.setColumnIndex(dpFecha, 1);
            
            Label lblCvv = new Label("CVV ");
            GridPane.setRowIndex(lblCvv, 3);
            GridPane.setColumnIndex(lblCvv, 0);
            
            this.txtCvv = new TextField();
            txtCvv.setPrefWidth(200);
            GridPane.setRowIndex(txtCvv, 3);
            GridPane.setColumnIndex(txtCvv, 1);
            
            griPane.getChildren().addAll(lblNombre, txtNombre, lblNumero, txtNumero,
                    lblFecha, dpFecha, lblCvv, txtCvv);
            
            contDin.getChildren().add(griPane);
            
        }
        
        
    }
    
    
    /**
     * Metodo pagarPedido
     * @throws IncompleteFieldsException 
     */
    private void pagarPedido() throws IncompleteFieldsException{
        if(pagoGroup.getSelectedToggle() == null){
            throw new IncompleteFieldsException("No selecciono un metodo de pago");
        }
        
        
        
        if(((RadioButton)pagoGroup.getSelectedToggle()).equals(rbEfectivo)){
            App.heladoPedido.generarTransaccion(TipoPago.E);
            
        }else if(((RadioButton)pagoGroup.getSelectedToggle()).equals(rbTarjeta)){
            //Validando datos de la trajeta
            String nombreTa = txtNombre.getText();
            String numeroTa = txtNumero.getText();
            String fecha = null;
            if(dpFecha.getValue() != null){
                fecha = dpFecha.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
            }
            String cvv = txtCvv.getText();
            if(nombreTa == null || numeroTa == null || fecha == null || cvv == null){
                throw new IncompleteFieldsException("No se llenaron todos los campos solicitados");
                
            }else if(nombreTa.isEmpty() || numeroTa.isEmpty() || cvv.isEmpty() || fecha.isEmpty()){
                throw new IncompleteFieldsException("No se llenaron todos los campos solicitados");
            }
            
            //Pasan las validaciones
            App.heladoPedido.generarTransaccion(TipoPago.T);
        }
        
        //Mostramos la escena final
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/final" + ".fxml"));
        Parent rootNew = null;
        try {
            rootNew = fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        double ancho = BienvenidaController.stagePedidos.getScene().getWidth();
        double alto = BienvenidaController.stagePedidos.getScene().getHeight();
        
        BienvenidaController.stagePedidos.setScene(new Scene(rootNew, ancho,alto));
    }
}
