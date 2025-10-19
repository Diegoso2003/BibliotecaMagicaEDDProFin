/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecamagica.controllers;

import com.mycompany.bibliotecamagica.backend.LectorArchivo;
import com.mycompany.bibliotecamagica.backend.exception.ArchivoException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rafael-cayax
 */
public class CargaArchivoController implements Initializable {

    @FXML private Group bibliotecas;
    @FXML private Group conexiones;
    @FXML private Group libros;
    @FXML private HBox archivoBibliotecas;
    @FXML private HBox archivoConexiones;
    @FXML private HBox archivoLibros;
    @FXML private TextArea log;
    private final File[] archivos = new File[3];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reiniciar(libros);
        reiniciar(conexiones);
        reiniciar(bibliotecas);
    }

    private void reiniciar(Group group) {
        group.getChildren().clear();
        SVGPath path1 = new SVGPath();
        path1.setContent("M8.5 6a.5.5 0 0 0-1 0v1.5H6a.5.5 0 0 0 0 1h1.5V10a.5.5 0 0 0 1 0V8.5H10a.5.5 0 0 0 0-1H8.5z");
        path1.setFill(Color.WHITE);

        SVGPath path2 = new SVGPath();
        path2.setContent("M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2zm10-1H4a1 1 0 0 0-1 1v12a1 1 0 "
                + "0 0 1 1h8a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1");
        path2.setFill(Color.WHITE);

        group.getChildren().addAll(path1, path2);
    }

    private void agregado(Group group) {
        group.getChildren().clear();
        SVGPath path1 = new SVGPath();
        path1.setContent("M14 4.5V14a2 2 0 0 1-2 2h-1v-1h1a1 1 0 0 0 1-1V4.5h-2A1.5 1.5 0 0 1 9.5 3V1H4a1 "
                + "1 0 0 0-1 1v9H2V2a2 2 0 0 1 2-2h5.5zM3.517 14.841a1.13 1.13 0 0 0 .401.823q.195.162.478.252.284.091.665.091.507 "
                + "0 .859-.158.354-.158.539-.44.187-.284.187-.656 0-.336-.134-.56a1 1 0 0 0-.375-.357 2 2 0 0 0-.566-.21l-.621-.144a1 "
                + "1 0 0 1-.404-.176.37.37 0 0 1-.144-.299q0-.234.185-.384.188-.152.512-.152.214 0 .37.068a.6.6 0 0 1 .246.181.56.56 0 "
                + "0 1 .12.258h.75a1.1 1.1 0 0 0-.2-.566 1.2 1.2 0 0 0-.5-.41 1.8 1.8 0 0 0-.78-.152q-.439 0-.776.15-.337.149-.527.421-."
                + "19.273-.19.639 0 .302.122.524.124.223.352.367.228.143.539.213l.618.144q.31.073.463.193a.39.39 0 0 1 .152.326.5.5 0 0 "
                + "1-.085.29.56.56 0 0 1-.255.193q-.167.07-.413.07-.175 0-.32-.04a.8.8 0 0 1-.248-.115.58.58 0 0 1-.255-.384zM.806 "
                + "13.693q0-.373.102-.633a.87.87 0 0 1 .302-.399.8.8 0 0 1 .475-.137q.225 0 .398.097a.7.7 0 0 1 .272.26.85.85 0 0 1 "
                + ".12.381h.765v-.072a1.33 1.33 0 0 0-.466-.964 1.4 1.4 0 0 0-.489-.272 1.8 1.8 0 0 0-.606-.097q-.534 0-.911.223-.375."
                + "222-.572.632-.195.41-.196.979v.498q0 .568.193.976.197.407.572.626.375.217.914.217.439 0 .785-.164t.55-.454a1.27 1.27 "
                + "0 0 0 .226-.674v-.076h-.764a.8.8 0 0 1-.118.363.7.7 0 0 1-.272.25.9.9 0 0 1-.401.087.85.85 0 0 1-.478-.132.83.83 0 0 "
                + "1-.299-.392 1.7 1.7 0 0 1-.102-.627zm8.239 2.238h-.953l-1.338-3.999h.917l.896 3.138h.038l.888-3.138h.879z");
        path1.setFill(Color.WHITE);
        path1.setFillRule(FillRule.EVEN_ODD);
        group.getChildren().addAll(path1);
    }

    @FXML
    private void cargarArchivoBiblioteca() {
        guardarArchivo(0, bibliotecas, archivoBibliotecas);
    }

    @FXML
    private void cargarArchivoConexion() {
        guardarArchivo(1, conexiones, archivoConexiones);
    }

    @FXML
    private void cargarArchivoLibro() {
        guardarArchivo(2, libros, archivoLibros);
    }

    private void guardarArchivo(int pos, Group group, HBox hbox) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"),
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        Stage stage = (Stage) bibliotecas.getScene().getWindow();
        archivos[pos] = fileChooser.showOpenDialog(stage);
        if(archivos[pos] != null){
            agregado(group);
            mostrarInfo(hbox, pos, group);
        }
    }
    
    private void mostrarInfo(HBox hbox, int pos, Group group){
        hbox.getChildren().clear();
        Button b = new Button();
        b.getStyleClass().add("fab-button");
        SVGPath path1 = new SVGPath();
        path1.setContent("M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16");
        path1.setFill(Color.WHITE);

        SVGPath path2 = new SVGPath();
        path2.setContent("M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 "
                + "8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708");
        path2.setFill(Color.WHITE);
        Group g = new Group(path1, path2);
        b.setGraphic(g);
        b.setOnAction(e -> {
            eliminar(hbox, pos, group);
        });
        
        Label label = new Label(archivos[pos].getName());
        hbox.getChildren().addAll(b, label);
    }
    
    private void eliminar(HBox hbox, int pos, Group group){
        reiniciar(group);
        hbox.getChildren().clear();
        archivos[pos] = null;
    }
    
    @FXML
    private void analizarArchivos(){
        Alert alerta = null;
        var lector = new LectorArchivo(archivos);
        log.clear();
        try {
            lector.leer();
            if(lector.isHayError()) {
                log.setText(lector.getLog());
            } else {
                alerta = new Alert(AlertType.INFORMATION);
                alerta.setTitle("Exito");
                alerta.setContentText("informacion añadida correctamente");
            }
        } catch (ArchivoException ex) {
            alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setContentText(ex.getMessage());
        }
        if(lector.isHayError()) return;
        Stage stage = (Stage) log.getScene().getWindow();
        alerta.initOwner(stage);
        alerta.initModality(Modality.WINDOW_MODAL);
        alerta.showAndWait();
    }
}
