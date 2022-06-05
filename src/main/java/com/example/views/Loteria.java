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
import java.util.ArrayList;
import java.util.Collections;

public class Loteria extends Stage implements EventHandler {

    private VBox vBox;
    private HBox hBox1,hBox2;
    private Button btnSiguiente,btnAtras,btnJugar;
    private Label lblTiempo;
    private GridPane gdpPlantilla;
    private Image imgCarta;
    private Scene escena;
    private File file;
    int  cont=1;

    //private String[] arImaLoteria={"1gallo.jpg","2Diablo.jpg","3Dama.jpg","4Catrin.jpg","5Paraguas.jpg","6Sirena.jpg"
    //,"7Escalera.jpg","8Botella.jpg","9Barril.jpg","10Arbol.jpg","11Melon.jpg","12Valiente.jpg","13Gorro.jpg","14Muerte.jpg"
    //,"15Pera.jpg","16Bandera.jpg"};
    ArrayList<String> arImaLoteria=new ArrayList<>();
    ArrayList<String> arImaLoteria2=new ArrayList<>();
    ArrayList<String> arImaLoteria3=new ArrayList<>();
    ArrayList<String> arImaLoteria4=new ArrayList<>();
    ArrayList<String> arImaLoteria5=new ArrayList<>();

    private String[] arImagenes={"39Nopal.png","botella.jpeg","catrin.jpeg","chavorruco.jpeg","concha.jpeg","graduada.jpeg","luchador.jpeg","maceta.jpeg"};
    private Button[][] arBtnPlantilla=new Button[4][4];
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
        LlenadoArray();
        btnAtras=new Button("Atras");
        btnAtras.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cont--;
                System.out.println(cont);
                    switch (cont){
                        case 0:
                            cont=5;
                            CrearPlantilla5();
                            break;
                        case 1:
                            CrearPlantilla();
                            break;
                        case 2:
                            CrearPlantilla2();
                            break;
                        case 3:
                            CrearPlantilla3();
                            break;
                        case 4:
                            CrearPlantilla4();
                            break;
                        case 5:
                            CrearPlantilla5();
                            break;
                        case 6:
                            cont=1;
                            CrearPlantilla();
                            break;
                    }
            }
        });
        btnAtras.setPrefWidth(100);

        btnSiguiente=new Button("Siguiente");
        btnSiguiente.setPrefWidth(100);
        btnSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cont++;
                switch (cont) {
                    case 0 -> {
                        cont = 5;
                        CrearPlantilla5();
                    }
                    case 1 -> CrearPlantilla();
                    case 2 -> CrearPlantilla2();
                    case 3 -> CrearPlantilla3();
                    case 4 -> CrearPlantilla4();
                    case 5 -> CrearPlantilla5();
                    case 6 -> {
                        cont = 1;
                        CrearPlantilla();
                    }
                }
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


        escena=new Scene(vBox, 650,630);
    }

    private void LlenadoArray() {
        arImaLoteria.add("1gallo.jpg");
        arImaLoteria.add("2Diablo.jpg");
        arImaLoteria.add("3Dama.jpg");
        arImaLoteria.add("4Catrin.jpg");
        arImaLoteria.add("5Paraguas.jpg");
        arImaLoteria.add("6Sirena.jpg");
        arImaLoteria.add("7Escalera.jpg");
        arImaLoteria.add("8Botella.jpg");
        arImaLoteria.add("9Barril.jpg");
        arImaLoteria.add("10Arbol.jpg");
        arImaLoteria.add("11Melon.jpg");
        arImaLoteria.add("12Valiente.jpg");
        arImaLoteria.add("13Gorro.jpg");
        arImaLoteria.add("14Muerte.jpg");
        arImaLoteria.add("15Pera.jpg");
        arImaLoteria.add("16Bandera.jpg");
        arImaLoteria.add("17Bandolon.jpg");
        arImaLoteria.add("18Violoncello.jpg");
        arImaLoteria.add("19Garza.jpg");
        arImaLoteria.add("20Pajaro.jpg");
        arImaLoteria.add("21Mano.jpg");
        arImaLoteria.add("22Bota.jpg");
        arImaLoteria.add("23Luna.jpg");
        arImaLoteria.add("24Cotorro.jpg");
        arImaLoteria.add("25Borracho.jpg");
        arImaLoteria.add("26Negrito.jpg");
        arImaLoteria.add("27Corazon.jpg");
        arImaLoteria.add("28Sandia.jpg");
        arImaLoteria.add("29Tambor.jpg");
        arImaLoteria.add("30Camaron.jpg");
        arImaLoteria.add("31Jaras.jpg");
        arImaLoteria.add("32Musico.jpg");
        arImaLoteria.add("33Araña.jpg");
        arImaLoteria.add("34Soldado.jpg");
        arImaLoteria.add("35Estrella.jpg");
        arImaLoteria.add("36Cazo.jpg");
        arImaLoteria.add("37Mundo.jpg");
        arImaLoteria.add("38Apache.jpg");
        arImaLoteria.add("39Nopal.png");
        arImaLoteria.add("40Alacran.jpg");
        arImaLoteria.add("41Rosa.jpg");
        arImaLoteria.add("42Calavera.jpg");
        arImaLoteria.add("43Campana.jpg");
        arImaLoteria.add("44Cantarito.jpg");
        arImaLoteria.add("45Venado.jpg");
        arImaLoteria.add("46Sol.jpg");
        arImaLoteria.add("47Corona.jpg");
        arImaLoteria.add("48Chalupa.jpg");
        arImaLoteria.add("49Pino.jpg");
        arImaLoteria.add("50Pescado.jpg");
        arImaLoteria.add("51Palma.jpg");
        arImaLoteria.add("52Maceta.jpg");
        arImaLoteria.add("53Arpa.jpg");
        arImaLoteria.add("54Rana.jpg");
        arImaLoteria2.addAll(arImaLoteria);
        arImaLoteria3.addAll(arImaLoteria);
        arImaLoteria4.addAll(arImaLoteria);
        arImaLoteria5.addAll(arImaLoteria);
        Collections.shuffle(arImaLoteria);
        Collections.shuffle(arImaLoteria2);
        Collections.shuffle(arImaLoteria3);
        Collections.shuffle(arImaLoteria4);
        Collections.shuffle(arImaLoteria5);
    }

    private void CrearPlantilla() {
        System.out.println(arImaLoteria2);
        System.out.println(arImaLoteria3);
        System.out.println(arImaLoteria4);
        System.out.println(arImaLoteria5);
        int con=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                arBtnPlantilla[j][i]=new Button();
                file = new File("src/main/resources/images/" + arImaLoteria.get(con));
                imgCartP= new Image(file.toURI().toString());
                imv= new ImageView(imgCartP);
                imv.setFitWidth(70);
                imv.setFitHeight(120);
                arBtnPlantilla[j][i].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[j][i],i,j);
                con ++;
            }
        }
    }
    private void CrearPlantilla2() {
        System.out.println(arImaLoteria);
        System.out.println(arImaLoteria2);
        System.out.println(arImaLoteria3);
        System.out.println(arImaLoteria4);
        System.out.println(arImaLoteria5);
        int con=0;
        for (int i=0;i<arBtnPlantilla.length;i++){
            for (int j = 0; j<arBtnPlantilla[i].length; j++){
                file = new File("src/main/resources/images/" + arImaLoteria2.get(con));
                imgCartP= new Image(file.toURI().toString());
                imv= new ImageView(imgCartP);
                imv.setFitWidth(70);
                imv.setFitHeight(120);
                arBtnPlantilla[j][i].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[j][i],i,j);
                con ++;
            }
        }
        System.out.println(arBtnPlantilla);
    }
    private void CrearPlantilla3() {
        System.out.println(arImaLoteria);
        System.out.println(arImaLoteria2);
        System.out.println(arImaLoteria3);
        System.out.println(arImaLoteria4);
        System.out.println(arImaLoteria5);
        int con=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                arBtnPlantilla[j][i]=new Button();
                file = new File("src/main/resources/images/" + arImaLoteria3.get(con));
                imgCartP= new Image(file.toURI().toString());
                imv= new ImageView(imgCartP);
                imv.setFitWidth(70);
                imv.setFitHeight(120);
                arBtnPlantilla[j][i].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[j][i],i,j);

                con ++;
            }
        }
    }
    private void CrearPlantilla4() {
        System.out.println(arImaLoteria);
        System.out.println(arImaLoteria2);
        System.out.println(arImaLoteria3);
        System.out.println(arImaLoteria4);
        System.out.println(arImaLoteria5);
        int con=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                arBtnPlantilla[j][i]=new Button();
                file = new File("src/main/resources/images/" + arImaLoteria4.get(con));
                imgCartP= new Image(file.toURI().toString());
                imv= new ImageView(imgCartP);
                imv.setFitWidth(70);
                imv.setFitHeight(120);
                arBtnPlantilla[j][i].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[j][i],i,j);

                con ++;
            }
        }
    }
    private void CrearPlantilla5() {
        System.out.println(arImaLoteria);
        System.out.println(arImaLoteria2);
        System.out.println(arImaLoteria3);
        System.out.println(arImaLoteria4);
        System.out.println(arImaLoteria5);
        int con=0;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                arBtnPlantilla[j][i]=new Button();
                file = new File("src/main/resources/images/" + arImaLoteria5.get(con));
                imgCartP= new Image(file.toURI().toString());
                imv= new ImageView(imgCartP);
                imv.setFitWidth(70);
                imv.setFitHeight(120);
                arBtnPlantilla[j][i].setGraphic(imv);
                gdpPlantilla.add(arBtnPlantilla[j][i],i,j);

                con ++;
            }
        }
    }


    @Override
    public void handle(Event event) {
        System.out.println("Mi primer evento covifest :");
    }
}
