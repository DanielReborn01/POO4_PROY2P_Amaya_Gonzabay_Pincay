/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Modelo.windowDialog;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author wal26
 */
public class popupWindows {
     public static void makeWindowDialog(windowDialog dialog, String msg){
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
     
     public static void makeWindowEmer(String msg){
        Stage stageDialog = new Stage();
        stageDialog.setTitle("ERROR");
        VBox contParent = new VBox();
        contParent.setAlignment(Pos.CENTER);
        contParent.getStyleClass().add("mainFxmlClass");
        contParent.getStylesheets().add("styles/pedidos.css");
        
        Label lbMensaje = new Label(msg);
        lbMensaje.getStyleClass().add("lblMensajeDialog");
        
        contParent.getChildren().add(lbMensaje);
        Scene scene = new Scene(contParent, 350, 150);
        stageDialog.setScene(scene);
        stageDialog.setResizable(false);
        stageDialog.initModality(Modality.APPLICATION_MODAL);
        stageDialog.show();
        
    }
}
