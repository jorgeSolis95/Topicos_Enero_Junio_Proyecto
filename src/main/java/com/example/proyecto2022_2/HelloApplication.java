package com.example.proyecto2022_2;

import com.example.views.Loteria;
import com.example.views.Parseador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private VBox vBox;
    private MenuBar mnbMenu;
    private Menu menCompetemcia1,menuCompetencia2;
    private MenuItem mitLoteria,mitParseador;

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        mnbMenu=new MenuBar();
        menCompetemcia1=new Menu("Competencia 1");
        mitLoteria=new MenuItem("Loteria");
        mitLoteria.setOnAction(event -> {Eventos(1);});
        mitParseador=new MenuItem("Codigo Morse");
        mitParseador.setOnAction(event -> {Eventos(2);});
        menCompetemcia1.getItems().addAll(mitLoteria,mitParseador);

        menuCompetencia2=new Menu("Competencia 2");
        mnbMenu.getMenus().addAll(menCompetemcia1,menuCompetencia2);

        vBox=new VBox();
        vBox.getChildren().addAll(mnbMenu);

        Scene scene = new Scene(vBox, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    private void Eventos(int i) {
        switch (i){
            case 1:new Loteria();break;
            case 2:new Parseador();break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}