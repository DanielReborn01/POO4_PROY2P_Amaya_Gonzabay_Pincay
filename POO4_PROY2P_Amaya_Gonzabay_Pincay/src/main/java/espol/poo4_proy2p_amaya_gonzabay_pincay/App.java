package espol.poo4_proy2p_amaya_gonzabay_pincay;

import Modelo.Base;
import Modelo.Helado;
import Modelo.Pickup;
import Modelo.Sabor;
import Modelo.Topping;
import Modelo.Usuario;
import Utilidades.Utilidades;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.print.Collation;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stagePrincipal;
    
    public static String pathImage = "Imagenes/" ; 
    public static String pathData = "Data/";
    public static Helado heladoPedido = new Helado();
    
    public static Format tam = Format.MEDIANO;
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Base> bases = new ArrayList<>();
    public static ArrayList<Sabor> sabores = new ArrayList<>();
    public static ArrayList<Pickup> pickups = new ArrayList<>();
    public static ArrayList<Topping> toppings = new ArrayList<>();
    public static Usuario userLogin = null;
    public static boolean close = false;
    @Override
    public void init() throws Exception  {
        super.init();
        
        //Se cargaran todos los datos
        usuarios = Usuario.usuarios(pathData+"usuarios.txt");
        bases = Base.Bases(pathData+"bases.txt");
        Collections.sort(bases);
        sabores = Sabor.sabores(pathData + "sabores.txt");
        Collections.sort(sabores);
        toppings = Topping.toppings(pathData + "toppings.txt");
        pickups = Pickup.pickup(pathData + "locales.txt");
    }
    
    
   
   
    @Override
    public void start(Stage stage) throws IOException {
        stagePrincipal = stage; //Se copia la direccion de memoria
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login" + ".fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root, 750, 480);
        
        stage.setScene(scene);
        stage.setMinWidth(375);
        stage.setMinHeight(450);
        stage.getIcons().add(new Image(new FileInputStream(pathImage+"logo.png")));
        
        stage.setOnCloseRequest(e -> {
            close = true;
            Platform.exit();
        });
        
        stage.show();
    }
    
    /**
     * Cuando se requiera cambiar la scene del stage principal
     * 
     * @param root
     */
    public static void changeScene(Parent root){
        double ancho = stagePrincipal.getScene().getWidth();
        double alto = stagePrincipal.getScene().getHeight();
        
        stagePrincipal.setScene(new Scene(root, ancho,alto));
        
    }
    

    public static void main(String[] args) {
        launch();
        
    }

}