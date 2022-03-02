package com.example.views;

import com.example.events.EventoLoteria;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Loteria extends Stage implements EventHandler {

    private VBox vBox;
    private HBox hBox1,hBox2;
    private Button btnSiguiente,btnAtras,btnJugar;
    private Label lblTiempo;
    private GridPane gdpPlantilla;
    private Image imgCarta;
    private Scene escena;
    private File file;

    private String[] arImagenes={"barril.jpeg","botella.jpeg","catrin.jepg","chavorruco.jpeg","concha.jpeg"};
    private Button[][] arBtnPlantilla=new Button[4][2];
    private Image imgCartP;
    private ImageView imv;


    public Loteria(){
        CrearUI();
        this.setTitle("Escena");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        //area de seleccion de plantilla
        btnAtras=new Button("Atras");
        btnAtras.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventoLoteria());
        btnAtras.setPrefWidth(100);
        btnSiguiente=new Button("Siguiente");
        btnSiguiente.setPrefWidth(100);
        btnSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Evento desde un objeto anonimo");
            }
        });
        lblTiempo=new Label("00:00");
        hBox1=new HBox();
        hBox1.getChildren().addAll(btnAtras,btnSiguiente,lblTiempo);

        gdpPlantilla=new GridPane();
        CrearPlantilla();
        hBox2=new HBox();
        hBox2.getChildren().addAll(gdpPlantilla);

        btnJugar=new Button("Jugar");
        btnJugar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);
        btnJugar.setPrefWidth(250);

        vBox=new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(hBox1,hBox2,btnJugar);


        escena=new Scene(vBox, 250,630);

    }

    private void CrearPlantilla() {
        for (int i=0;i<2;i++){
            for (int j=0;j<4;j++){
                arBtnPlantilla[j][i]=new Button();
                file = new File("src/main/java/com/example/images/" + arImagenes[3]);
                imgCartP= new Image(file.toURI().toString());
                imv= new ImageView(imgCartP);
                imv.setFitWidth(70);
                imv.setFitHeight(120);
                arBtnPlantilla[j][i].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[j][i],i,j);
            }
        }
    }

    @Override
    public void handle(Event event) {
        System.out.println("Mi primer evento covifest :");
    }
}
