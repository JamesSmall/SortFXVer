/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lostwolfgames.Samples.Graphics.Desktop.FX;

import Lostwolfgames.Samples.Graphics.RenderObject;
import Lostwolfgames.Samples.SortAPI.BiDirectionalBubbleSort;
import Lostwolfgames.Samples.SortAPI.BogoSort;
import Lostwolfgames.Samples.SortAPI.BubbleSort;
import Lostwolfgames.Samples.SortAPI.CountingSort;
import Lostwolfgames.Samples.SortAPI.InsertionSort;
import Lostwolfgames.Samples.SortAPI.SelectionSort;
import Lostwolfgames.Samples.SortAPI.Shuffler;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Navnik
 */
public class MainPanel extends Application{
    private Stage stage;
    private int size =100;
    private int wait = 100;
    private DrawingPanel canvas;
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        ExecutorService executor = Executors.newFixedThreadPool(1);
        
        HBox options = new HBox();
        options.setMinHeight(25);
        options.setPrefHeight(25);
        //options.setPrefWidth(100000);
        
        Label waitLBL = new Label();
        waitLBL.setText("Wait:");
        waitLBL.setTextFill(Color.WHITE);
        TextField waitInput = new TextField();
        waitInput.setText(""+this.wait);
        waitInput.textProperty().addListener((e)->{
            try{
                int num = Integer.parseInt(waitInput.getText());
                if(0 <= num && num <= 500){
                    this.wait = num;
                    waitInput.setStyle("");
                    return;
                }
            }
            catch(Exception ex){
                
            }
            waitInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        });
        
        
        Label sizeLBL = new Label();
        sizeLBL.setText("Size:");
        sizeLBL.setTextFill(Color.WHITE);
        TextField sizeInput = new TextField();
        sizeInput.setText(""+this.size);
        sizeInput.textProperty().addListener((e)->{
            try{
                int num = Integer.parseInt(sizeInput.getText());
                if(5 <= num && num <= 5000){
                    this.size = num;
                    sizeInput.setStyle("");
                    return;
                }
            }
            catch(Exception ex){
                
            }
            sizeInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        });
        options.getChildren().addAll(Arrays.asList(waitLBL,waitInput,sizeLBL,sizeInput));
        HBox buttons = new HBox();
        
        getButton("Bubble Sort", ()->{
            canvas.setSort(new BubbleSort(generateRenderList(size)),this.wait);
        },buttons);
        getButton("BI Directional Bubble Sort", ()->{
            canvas.setSort(new BiDirectionalBubbleSort(generateRenderList(size)),this.wait);
        },buttons);
        getButton("InsertionSort",()->{
            canvas.setSort(new InsertionSort(generateRenderList(size)),this.wait);
        },buttons);
        getButton("Selection Sort", ()->{
            canvas.setSort(new SelectionSort(generateRenderList(size)),this.wait);
        },buttons);
        
        getButton("Counting Sort", ()->{
            canvas.setSort(new CountingSort(generateRenderList(size)),this.wait);
        },buttons);
        
        getButton("Bogo Sort", ()->{
            canvas.setSort(new BogoSort(generateRenderList(size)),this.wait);
        },buttons);
        
        
        
        Group g = new Group();
        Scene s = new Scene(g,500,500,Color.BLACK);
        stage.setScene(s);
        
        stage.show();
        stage.setTitle("Sorting Algorithms");
        
        VBox v = new VBox();
        v.setPrefHeight(1000000);
        v.getChildren().addAll(Arrays.asList(canvas = new DrawingPanel(),options,buttons));
        g.getChildren().add(v);
        canvas.setHeight(stage.getHeight()-90);
        canvas.setWidth(stage.getWidth()-15);
        buttons.setPrefHeight(25);
        stage.heightProperty().addListener((e)->{canvas.setHeight(stage.getHeight()-90);});
        stage.widthProperty().addListener((e)->{canvas.setWidth(stage.getWidth()-15);options.setPrefWidth(stage.getWidth());buttons.setPrefWidth(stage.getWidth());});
        this.canvas.setSort(new BubbleSort(generateRenderList(size)),this.wait);
        
        
        stage.setOnCloseRequest(e->{System.exit(0);});
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                canvas.update();
                executor.submit(()->{
                    try {
                        Thread.sleep(canvas.getWaitTime());
                        Platform.runLater(this);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        });
    }
    private Button getButton(String text,Runnable onclick,HBox box){
        final Button b = new Button();
        b.setTextFill(Color.BLACK);
        b.setText(text);
        b.addEventHandler(MouseEvent.MOUSE_RELEASED, (e)->{onclick.run();});
        b.setPrefWidth(0);
        b.setMinWidth(100);
        
        box.widthProperty().addListener((e)->{
            b.setPrefWidth(box.getWidth()/box.getChildren().size());
            b.setMaxWidth(box.getWidth()/box.getChildren().size());
        });
        box.getChildren().add(b);
        b.setMinHeight(25);
        b.setPrefHeight(25);
        return b;
    }
    public static RenderObject[] generateRenderList(int size){
        RenderObject[] obj = new RenderObject[size];
        for(int i = 0; i < size;i++){
            obj[i] = new RenderObject();
            obj[i].setValue(i);
            obj[i].setCode(RenderObject.UNMARKED);
        }
        Shuffler.fullRandomShuffle(obj);
        return obj;
    }
}
