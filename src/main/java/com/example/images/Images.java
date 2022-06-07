package com.example.images;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Images {
    public static Image getImage(String url){
        FileInputStream image=null;
        try{
            String url_image="src/main/resources/images/";
            image=new FileInputStream(url_image+url);
        } catch (FileNotFoundException e) {
            System.err.println("No se abrio la imagen");
            System.err.println(e.getMessage());
            //System.exit(0);
        }

        return new Image(image);
    }
}
