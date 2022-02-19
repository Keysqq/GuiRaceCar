package com.example.guiracecar;
//Dev:Justin Fredericks
//Date: 2/19/2022
//Script: Gui Car Movement
//Class: 171
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();

        //used horizontal box to hold the buttons in place and next to each other
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        Button btnStart = new Button("Start");
        Button btnPause = new Button("Pause");

        hbox.getChildren().addAll(btnPause, btnStart);

        //border pane to display/place the two other pans
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hbox);
        borderPane.setAlignment(hbox, Pos.CENTER);

        ImageView imageView = new ImageView("rabbit.PNG");
        pane.getChildren().add(imageView);

        double speed = 10000;
        //used PT in order to create a track or path for the image to run on
        PathTransition pt = new PathTransition(Duration.millis(speed),
                new Line(100,250,1000,250),
                imageView);

        pt.setCycleCount(Animation.INDEFINITE);
        //the pt constructor requires a speed/DURATION attribute, but unable to insert rate as that attribute
        //so below later in code i had to "reset the speed/rate"
        //unaware of how i could have set rate in the constructor
        pt.setRate(5);

        btnStart.setOnAction((ActionEvent e) ->{pt.play();});
        btnPause.setOnAction((ActionEvent e) -> {pt.pause();});

        Scene scene = new Scene(borderPane, 1000, 600);
        stage.setTitle("Race Car Animation");

        //needed to add (.addEventFilter(KeyEvent.KEY_PRESSED,)
        //in order to have this event trigger
        //still unaware of why this was needed or the workings behind it
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
        //  scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case DOWN: pt.setRate(pt.getRate()-1);break;
                case UP: pt.setRate(pt.getRate()+1);break;
            }
        });

        stage.setScene(scene);
        stage.show();
     // scene.requestFocus();

    }

    public static void main(String[] args) {
        launch();
    }
}