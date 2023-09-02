/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.Base;
import Modelo.IncompleteStageException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class BaseHeladoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ArrayList<Base> bases = new ArrayList<>();
    private VBox selected;
    
    @FXML
    private HBox hbBases;
    
    @FXML
    private Label lblPrecio;
    
    @FXML
    private Button btnNextBase;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Se presentan las bases de los helados en funcion del archivo
        bases = App.bases; //Guardamos la direccion de memoria de la estructura
        try {
            //add los sabores
            addSabores();
        } catch (FileNotFoundException ex) {
            System.out.println("Ocurrio un error en la lectura del archivo");
        }
        
        //Ponemos a la escucha al boton
        
        btnNextBase.addEventHandler(ActionEvent.ACTION, (e)->{
            try {
                nextScene();
            } catch (IncompleteStageException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    
    private void addSabores() throws FileNotFoundException{
        HBox contenedor = new HBox();
        contenedor.getStyleClass().add("cont-padre");
        contenedor.setPadding(new Insets(5, 20, 5, 20));
        contenedor.setSpacing(30.0);

        //Por cada base en el 
        for (Base base : bases) {
            VBox contInt = new VBox();
            contInt.getStyleClass().add("cont-children");
            contInt.setPrefWidth(200);
            contInt.setAlignment(Pos.CENTER);
            contInt.setSpacing(10.0);
            
            
            Label sabor = new Label(base.getSabor());
            Label precio = new Label(Double.toString(base.getPrecio()));
            ImageView imageView = new ImageView();
            
            
            FileInputStream file = new FileInputStream(App.pathImage + base.getSabor() + ".png");
            Image image = new Image(file,150,125,true,true);
            imageView.setImage(image);
            
            
            //add elementos
            contInt.getChildren().add(imageView);
            contInt.getChildren().add(sabor);
            contInt.getChildren().add(precio);
            
            contInt.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
                SelBase(contInt, selected);
            });
            
            contenedor.getChildren().add(contInt);
        }
        
        hbBases.getChildren().add(contenedor);
    }
    
    /**
     * Selecciona la base e ira formando el pedido del Helado
     * @param selected 
     */
    private void SelBase(VBox selected, VBox lastSelected){
        if(lastSelected != null){
            lastSelected.getStyleClass().remove("Selected");
        }
        selected.getStyleClass().add("Selected");
        this.selected = selected;
        
        String sabor = ((Label) selected.getChildren().get(1)).getText();
        Label precio = (Label) selected.getChildren().get(2);
        double precioBase = Double.parseDouble(precio.getText());
        
        Base base = Base.FindBase(bases, sabor);
        App.heladoPedido.setBase(base);
        lblPrecio.setText("Valor a pagar: " + Double.toString(precioBase));
    }
    
    private void nextScene() throws IncompleteStageException, IOException{
        if(App.heladoPedido.getBase() == null){
            throw new IncompleteStageException("No se selecciono una base");
        }
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/saboresHelado" + ".fxml"));
        Parent rootNew = fxmlLoader.load();
        
        
        double ancho = BienvenidaController.stagePedidos.getScene().getWidth();
        double alto = BienvenidaController.stagePedidos.getScene().getHeight();
        
        BienvenidaController.stagePedidos.setScene(new Scene(rootNew, ancho,alto));
    }
   
}
