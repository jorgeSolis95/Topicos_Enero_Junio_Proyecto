package com.example.views;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parseador extends Stage implements EventHandler<KeyEvent> {
    private VBox vBox;
    private ToolBar tlbMenu;
    private TextArea txtEntrada,txtSalida;
    private FileChooser flcArchivo;
    private Button btnAbrir,btn_convertir;
    private Scene escena;
    private Image imgAbrir;
    private ImageView imvAbrir;
    private File file;

    private final Map<String, String> alfabeto=new HashMap<>();
    Properties properties=new Properties();

    public Parseador(){
        CrearUI();
        cargarPropiedades();
        this.setTitle("Traductor de código Morse");
        this.setScene(escena);
        this.show();
    }

    private void cargarPropiedades() {
        try {
            File proFile1=new File("src/main/resources/alfabeto.properties");
            properties.load(new FileReader(proFile1));
        }catch (IOException e){
            e.printStackTrace();
        }
        for (String palabra: properties.stringPropertyNames() ) {
            alfabeto.put(palabra,properties.getProperty(palabra));
        }
    }

    private void CrearUI() {
        vBox=new VBox();
        tlbMenu=new ToolBar();
        file = new File("src/main/resources/images/3890931_communication_letter_memo_message_note_icon.png");
        imgAbrir=new Image(file.toURI().toString());
        imvAbrir=new ImageView(imgAbrir);
        imvAbrir.setFitHeight(25);
        imvAbrir.setFitWidth(25);
        btnAbrir=new Button();
        btnAbrir.setGraphic(imvAbrir);
        btnAbrir.setOnAction(event -> {
            //bloque de codigo
            flcArchivo=new FileChooser();
            flcArchivo.setTitle("Buscar archivo....");
            flcArchivo.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files","*.txt"));
            File archivo=flcArchivo.showOpenDialog(this);
            if (archivo!=null){
                txtEntrada.clear();
                txtEntrada.appendText(leerArchivo(archivo));
            }

        });
        tlbMenu.getItems().addAll(btnAbrir);
        //....
        txtEntrada=new TextArea();
        txtEntrada.setPromptText("Introduce el texto a parsear");
        txtEntrada.setOnKeyPressed(this);
        txtSalida=new TextArea();
        txtSalida.setEditable(false);
        btn_convertir=new Button("Convertir");
        btn_convertir.setPrefWidth(600);
        btn_convertir.setOnAction(event -> morseText());
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(tlbMenu,txtEntrada,txtSalida,btn_convertir);
        escena=new Scene(vBox,500,300);
    }
    private ArrayList<String> convertirMorse(){
        ArrayList<String>palabrasMorse=new ArrayList<>();
        char[] palabras=txtEntrada.getText().toCharArray();
        for (char palabra:palabras) {
            palabrasMorse.add(alfabetoMorse(palabra) + "");
        }
        return palabrasMorse;
    }

    private String alfabetoMorse(char palabra) {
        String palabra_morse=alfabeto.get(String.valueOf(palabra).toUpperCase());
        return palabra_morse !=null ? palabra_morse : "";
    }

    private void escribirMorse(List<String> palabrasMorse){
        for (String palabraMorse : palabrasMorse) {
            txtSalida.appendText(palabraMorse);
        }
    }

    private void morseText() {
        txtSalida.clear();
        escribirMorse(convertirMorse());
    }


    private String leerArchivo(File archivo) {
        String contenido=null;
        try {
            contenido=new Scanner(archivo).useDelimiter("//z").next();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return contenido;
    }

    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCode().toString());
        Platform.runLater(this::morseText);
    }
}
