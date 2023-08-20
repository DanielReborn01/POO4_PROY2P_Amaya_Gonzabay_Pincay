package espol.poo4_proy2p_amaya_gonzabay_pincay;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public final String pathImage = "src/main/resources/Imagenes/" ;
    
    public static Format tam = Format.MEDIANO;
    
    @Override
    public void start(Stage stage) throws IOException {
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

    public static void main(String[] args) {
        launch();
    }

}