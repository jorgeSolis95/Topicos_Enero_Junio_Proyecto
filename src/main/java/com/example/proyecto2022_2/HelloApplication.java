package com.example.proyecto2022_2;

import com.example.events.EventoLoteria;
import com.example.views.ClientesBD;
import com.example.views.Loteria;
import com.example.views.Parseador;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application implements EventHandler {
    private VBox vBox;
    private MenuBar mnbMenu;
    private Menu menCompetemcia1,menuCompetencia2;
    private MenuItem mitLoteria,mitParseador,mitClientes;

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        mnbMenu=new MenuBar();
        menCompetemcia1=new Menu("Competencia 1");
        mitLoteria=new MenuItem("Loteria");
        mitLoteria.setOnAction(event -> {Eventos(1);});
        mitParseador=new MenuItem("Codigo Morse");
        mitParseador.setOnAction(event -> {Eventos(2);});
        mitClientes=new MenuItem("Taqueria");
        mitClientes.setOnAction(event -> {Eventos(3);});
        menCompetemcia1.getItems().addAll(mitLoteria,mitParseador,mitClientes);


        menuCompetencia2=new Menu("Competencia 2");
        mnbMenu.getMenus().addAll(menCompetemcia1,menuCompetencia2);

        vBox=new VBox();
        vBox.getChildren().addAll(mnbMenu);

        Scene scene = new Scene(vBox, 320, 240);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.addEventHandler(WindowEvent.WINDOW_SHOWING,this);
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alerta=new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Bienvenidos :) ");
                alerta.setHeaderText("Mensaje del sistema");
                alerta.setContentText("Manejo de eventos de la ventana usando dialogos");
                alerta.showAndWait();
            }
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();


    }

    private void Eventos(int i) {
        switch (i){
            case 1:new Loteria();break;
            case 2:new Parseador();break;
            case 3:new ClientesBD();break;
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void handle(Event event) {
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Bienvenidos :) ");
        alerta.setHeaderText("Mensaje del sistema");
        alerta.setContentText("Manejo de eventos de la ventana usando dialogos");
        alerta.showAndWait();
    }
}