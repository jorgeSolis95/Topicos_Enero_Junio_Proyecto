package com.example.views;

import com.example.funcionLoteria.Card;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Loteria extends Stage {
    private Scene scene;
    private VBox v_box_container, v_box_board, v_box_card;
    private HBox h_box_title, h_box_buttons, h_box_timer, h_box2, h_box3;
    private Button btn_prev, btn_next, btn_play;
    private Label lbl_time;
    private GridPane gdp_board, gdp_card;
    private ImageView image_view;
    private Font font;
    private Text title;
    private Timeline time_line;
    private final Timer timer = new Timer();

    /**
     * Define el número actual de la plantilla en uso.
     **/
    private int current_board = 0;

    /**
     * Define el número actual de la carta mostrada en el mazo.
     **/
    private int current_card_image = 0;

    /**
     * Proporciona los datos de la carta actual que se muestra en el mazo.
     **/
    private Card current_card_data;

    /**
     * Indica si el juego está activo o ya ha comenzado, por defecto está inactivo.
     **/
    private boolean is_active = false;

    private final Integer START_TIME = 3;
    private final IntegerProperty time_seconds = new SimpleIntegerProperty(START_TIME);

    private final Card[] CARDS = LoteriaImages.getRandomCards();

    public Loteria() {
        createUI();
        this.setTitle("Lotería Mexicana");
        this.setMaximized(true);
        this.setScene(scene);
        this.show();
    }

    private void createUI() {
        // Título.
        font = Font.loadFont("file:src/main/resources/fonts/Lobster-Regular.ttf", 64);
        title = new Text("Juego Lotería Mexicana");
        title.setFont(font);
        title.setFill(Color.rgb(208, 71, 100));

        // HBox contenedor del título.
        h_box_title = new HBox();
        h_box_title.setAlignment(Pos.CENTER);
        h_box_title.setPadding(new Insets(15, 0, 15, 0));
        h_box_title.getChildren().add(title);

        // Botón regresar a la plantilla anterior.
        btn_prev = new Button("Atrás");
        btn_prev.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                prevBoard();
            }
        });
        btn_prev.setPrefWidth(100);
        // Botón ir a la plantilla siguiente.
        btn_next = new Button("Siguiente");
        btn_next.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nextBoard();
            }
        });
        btn_next.setPrefWidth(100);

        // Marcador de tiempo para cambiar de carta.
        lbl_time = new Label();
        lbl_time.textProperty().bind(time_seconds.asString());

        // Contenedor del contador de tiempo (label_time).
        h_box_timer = new HBox();
        h_box_timer.setAlignment(Pos.CENTER);
        h_box_timer.getChildren().add(lbl_time);

        // Contenedor de botones 'siguiente' y 'atrás'.
        h_box_buttons = new HBox();
        h_box_buttons.setSpacing(5);
        h_box_buttons.setAlignment(Pos.CENTER);
        h_box_buttons.getChildren().addAll(btn_prev, btn_next);

        // GridPane para las plantillas.
        gdp_board = new GridPane();
        gdp_board.setPadding(new Insets(15, 15, 15, 15));
        gdp_board.setHgap(15);
        gdp_board.setVgap(15);

        // Contenedor de las plantillas y los botones 'siguiente' y 'atrás'.
        v_box_board = new VBox();
        v_box_board.getChildren().addAll(h_box_buttons, gdp_board);

        // GridPane para las cartas.
        gdp_card = new GridPane();
        gdp_card.setPadding(new Insets(15, 15, 15, 15));
        gdp_card.setHgap(15);
        gdp_card.setVgap(15);

        // Contenedor del mazo y del timer para cambiar de carta.
        v_box_card = new VBox();
        v_box_card.getChildren().addAll(h_box_timer, gdp_card);

        // Muestra la primera plantilla (0).
        renderBoard();

        // Muestra la primera carta del mazo (0).
        renderCard();

        // Contenedor de las plantillas, cartas y sus controladores.
        h_box2 = new HBox();
        h_box2.setAlignment(Pos.CENTER);
        h_box2.getChildren().addAll(v_box_board, v_box_card);

        // Botón jugar.
        // Empieza a sacar las cartas del mazo y deshabilita el botón cuando este es presionado. Así mismo,
        // deshabilita los botones 'Atrás' y 'Siguiente', esto para evitar poder cambiar de plantilla durante el
        // transcurso del juego.
        btn_play = new Button("Jugar");
        btn_play.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                is_active = true;
                btn_play.setDisable(true);
                h_box_buttons.setDisable(true);

                changeCardTimer();
            }
        });
        btn_play.setPrefWidth(250);

        // Contenedor del botón jugar.
        h_box3 = new HBox();
        h_box3.setSpacing(5);
        h_box3.setAlignment(Pos.CENTER);
        h_box3.getChildren().addAll(btn_play);

        // Layout principal.
        // Contiene el título, plantillas, cartas y sus respectivos botones.
        v_box_container = new VBox();
        v_box_container.getStyleClass().add("bg-3");
        v_box_container.getChildren().addAll(h_box_title, h_box2, h_box3);

        // Ventana principal.
        scene = new Scene(v_box_container, 800, 600);
        // Hoja de estilos CSS.
        scene.getStylesheets().add(String.valueOf(this.getClass().getResource("/style.css")));
    }

    /**
     * Muestra en pantalla la plantilla del número actual con la ayuda de un GridPane.
     *
     * Las plantillas se obtienen de la clase LoteriaImages previamente ya definidas las plantillas con su
     * respectivo objeto carta (Card), el cual servirá para poder comparar cartas posteriormente.
     *
     * Dentro de la clase Card se guardan datos como la posición donde se encuentra ubicada la carta en la plantilla,
     * así como el índice en el que este fue agregado a la plantilla.
     *
     * Al momento de seleccionar una carta de la plantilla se lanzará el método para comparar las cartas actuales.
     **/
    private void renderBoard() {
        // row y col establecen la posición de una carta dentro de la plantilla.
        // counter se encarga de iterar el número de cartas añadidas a la plantilla.
        int row = 0, col = 0, counter = 0;

        gdp_board.getChildren().clear();

        for (Card card : LoteriaImages.BOARDS[current_board]) {
            // Al llegar a la cuarta fila se pasa a la siguiente columna desde la fila 0 nuevamente.
            if (row == 4) {
                col++;
                row = 0;
            }

            // Se establecen los datos de la carta dentro de la plantilla.
            card.setIndexCardAdded(counter);
            card.setCardCoords(col, row);

            image_view = new ImageView(card.getImage());
            image_view.setFitWidth(70);
            image_view.setFitHeight(120);
            image_view.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (is_active) handleCardClicked(card);
                }
            });

            gdp_board.add(image_view, col, row);

            // Por cada iteración las filas aumentan hasta llegar a 4.
            row++;
            // El contador de cartas aumenta.
            counter++;
        }
    }

    /**
     * Llama al método validateCards y, en caso de que la igualdad sea verdadera, reescribe el GridPane solamente en la
     * posición donde se encuentra la carta seleccionada, inhabilitando a la misma. Posteriormente verifica si la
     * plantilla actual cuenta con todas sus cartas deshabilitadas, lo cual quiere decir que la plantilla ya ha ganado
     * y el juego ha terminado, mostrando así un mensaje de victoria.
     *
     * En caso contrario, si la carta seleccionada no es igual a la carta mostrada en el mazo, solamente mostrará un
     * mensaje por pantalla.
     *
     * @param card_clicked objeto Card con la información de la carta que fue seleccionada.
     **/
    private void handleCardClicked(Card card_clicked) {
        if (validateCards(card_clicked)) {
            LoteriaImages.disableSelectedCard(card_clicked, current_board);

            image_view = new ImageView(LoteriaImages.getDisableCard().getImage());
            image_view.setFitWidth(70);
            image_view.setFitHeight(120);

            gdp_board.add(image_view, card_clicked.getAxisX(), card_clicked.getAxisY());

            if (checkIfUserWon()) {
                timer.cancel();
                time_line.stop();
                is_active = false;
                btn_play.setText("Juego terminado");
                showWinnerMessage();
            }
        } else {
            System.out.println("Card not match.");
            timer.cancel();
            time_line.stop();
            is_active = false;
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Perdio el juego");
            alert.setContentText("Gracias por participar en la Loteria");
            alert.show();
            btn_play.setText("Juego terminado");
        }
    }

    private void prevBoard() {
        current_board--;

        if (current_board < 0) current_board = 4;

        renderBoard();
    }


    private void nextBoard() {
        current_board++;

        if (current_board > 4) current_board = 0;

        renderBoard();
    }

    private void renderCard() {
        image_view = new ImageView((CARDS[current_card_image].getImage()));
        image_view.setFitWidth(320);
        image_view.setFitHeight(525);
        gdp_card.add(image_view, 0, 0);

        current_card_data = CARDS[current_card_image];
    }


    private void changeCard() {
        current_card_image++;

        renderCard();
    }

    private Boolean validateCards(Card board_card) {
        return board_card.equals(current_card_data);
    }

    private void startCountDownTimer() {
        if (time_line != null) time_line.stop();

        time_seconds.set(START_TIME);

        time_line = new Timeline();
        time_line.getKeyFrames().add(
                new KeyFrame(Duration.seconds(START_TIME + 1),
                        new KeyValue(time_seconds, 0))
        );
        time_line.playFromStart();
    }


    private void changeCardTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (current_card_image < CARDS.length - 1) {
                    Platform.runLater(() -> {
                        changeCard();

                        startCountDownTimer();
                    });
                } else {
                    Platform.runLater(() -> {
                        timer.cancel();
                        is_active = false;
                        btn_play.setText("Juego terminado");
                        showGameOverMessage();
                    });
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 3000);
    }


    private boolean checkIfUserWon() {
        int disabled_cards = 0;

        for (Card card : LoteriaImages.BOARDS[current_board]) {
            if (card.getStatusCard()) disabled_cards++;
        }

        return disabled_cards == LoteriaImages.BOARDS[0].length;
    }

    private void showWinnerMessage() {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Gano el juego");
        alert.setContentText("Felicidades Gano la Loteria");
        alert.show();
    }


    private void showGameOverMessage() {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Perdio el juego");
        alert.setContentText("Gracias por participar en la Loteria");
        alert.show();
    }
}
