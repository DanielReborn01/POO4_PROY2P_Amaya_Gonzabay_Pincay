package espol.poo4_proy2p_amaya_gonzabay_pincay;

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
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stagePrincipal;
    
    public final String pathImage = "src/main/resources/Imagenes/" ; 
    public final String pathData = "src/main/resources/Data/";
    
    public static Format tam = Format.MEDIANO;
    public static ArrayList<Usuario> usuarios = new ArrayList<>();

    @Override
    public void init() throws Exception {
        super.init();
        
        //Se cargaran todos los datos
        ArrayList<String[]> dataUsuario = Utilidades.LeerValidando(pathData + "usuarios.txt", false);
        for (String[] dataUser : dataUsuario) {
            usuarios.add(new Usuario(dataUser[0], dataUser[1], dataUser[2]));
        }
    }
    
    
   
   
    @Override
    public void start(Stage stage) throws IOException {
        stagePrincipal = stage; //Se copia la direccion de memoria
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/login" + ".fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root, 750, 480);
        
        //Ponemos a la escucha para el Design Responsive
        scene.widthProperty().addListener((o) -> {
           
           if(scene.getWidth() > 500 && scene.getWidth() < 1024 && !tam.equals(Format.MEDIANO)){
               //Se coloca la vista del tamano mediano
               System.out.println("Tamaño mediano");
               tam = Format.MEDIANO;
           }else if(scene.getWidth() < 500 && !tam.equals(Format.PEQUEÑO)){
               System.out.println("Tamaño pequeño");
            
               tam = Format.PEQUEÑO;
           }else if(scene.getWidth() > 1024 && !tam.equals(Format.FULL)){
               System.out.println("Tamaño grande");
               tam = Format.FULL;
           }
        });
        
        stage.setScene(scene);
        stage.setMinWidth(375);
        stage.setMinHeight(450);
        stage.getIcons().add(new Image(new FileInputStream(pathImage+"logo.png")));
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