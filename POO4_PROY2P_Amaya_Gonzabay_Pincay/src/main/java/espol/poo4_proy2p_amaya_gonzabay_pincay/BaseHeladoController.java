/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.Base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    @FXML
    private HBox hbBases;
    
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
            
            contenedor.getChildren().add(contInt);
        }
        
        hbBases.getChildren().add(contenedor);
    }
}
