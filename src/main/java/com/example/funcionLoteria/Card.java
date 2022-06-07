package com.example.funcionLoteria;

import com.example.images.Images;
import javafx.scene.image.Image;

import java.util.Objects;

public class Card {
    private String card_name;
    private String image_name;
    private int id;


    private boolean is_disabled = false;


    private int axis_x;
    private int axis_y;

    private int index_card_added;


    public Card(String card_name, String image_name, int id) {
        setAll(card_name, image_name, id);
    }

    public void setAll(String card_name, String image_name, int id) {
        setCardName(card_name);
        setImageName(image_name);
        setId(id);
    }

    public String getCardName() {
        return card_name;
    }

    public void setCardName(String card_name) {
        this.card_name = card_name;
    }

    public String getImageName() {
        return image_name;
    }

    public void setImageName(String image_name) {
        this.image_name = image_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Image getImage() {
        return Images.getImage(this.image_name);
    }

    public void setCardCoords(int axis_x, int axis_y) {
        setAxisX(axis_x);
        setAxisY(axis_y);
    }

    public int getAxisX() {
        return axis_x;
    }

    public void setAxisX(int axis_x) {
        this.axis_x = axis_x;
    }

    public int getAxisY() {
        return axis_y;
    }

    public void setAxisY(int axis_y) {
        this.axis_y = axis_y;
    }

    public int getIndexCardAdded() {
        return index_card_added;
    }

    public void setIndexCardAdded(int index_card_added) {
        this.index_card_added = index_card_added;
    }

    public boolean getStatusCard() {
        return is_disabled;
    }


    public void disableCard() {
        this.is_disabled = true;
    }

    @Override
    public String toString() {
        return "Carta #" + id + ": " + card_name + " (" + image_name + ").";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card that = (Card) o;
        return
                Objects.equals(card_name, that.card_name) &&
                        Objects.equals(image_name, that.image_name) &&
                        Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_name, image_name, getId());
    }
}
