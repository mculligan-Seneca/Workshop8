/*
Stduent: Mitchell Culligan
Workshop 8
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 27th, 2020
 */
package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
public class Controller implements Initializable {
    public  static final int  BOY_ACCESS=1;
    public  static final int GIRL_ACCESS=3;
    private static final String PATH ="./babynamesranking2001to2010/babynamesranking%d.txt";
    @FXML
    private BorderPane root;
    private TextField year;
    private TextField name;
    private ChoiceBox<String> gender;
    private Label mssge;

    public Controller() {
        this.root = new BorderPane();
        this.year = new TextField();
        this.name = new TextField();
        this.gender = new ChoiceBox<>();
        this.mssge = new Label();
    /*    for(int i=START_YEAR;i<=END_YEAR;i++)
            this.year.getItems().add(i);

     */
        this.gender.getItems().addAll("Boy", "Girl");

    }

    private boolean validate(){
        boolean valid =false;

        if(this.year.getText().matches("^[0-9]{4}$")) {
            if(this.gender.getValue()!=null) {
                if (this.name.getText().matches("[A-Za-z]+"))
                    valid=true;
                 else
                    Message.showMessage("Invalid Name", Alert.AlertType.ERROR);
            }
            else
                Message.showMessage("No Gender selected", Alert.AlertType.ERROR);
        }
        else
            Message.showMessage("Invalid Year", Alert.AlertType.ERROR);
        return valid;
    }
    private int getNameIndex() {
        int index = -1;
        boolean found=false;
        int access;
        String line;
        String[] tokens;
        File file = new File(String.format(PATH, Integer.valueOf(this.year.getText())));
        if(file.exists()) {
            if (this.gender.getValue() == "Girl")
                access = GIRL_ACCESS;
            else access = BOY_ACCESS;
            try (RandomAccessFile randf = new RandomAccessFile(file, "r")) {
                while (!found && (line = randf.readLine()) != null) {
                    tokens = line.trim().replaceAll("\\p{Space}{2,}", " ").split("\\p{Space}");
                    if (tokens[access].equalsIgnoreCase(this.name.getText())) {
                        index = Integer.valueOf(tokens[0]);
                        found = true;
                    }


                }
            } catch (IOException ioe) {
                StringWriter errMsge = new StringWriter();
                ioe.printStackTrace(new PrintWriter(errMsge));
                Message.showMessage(errMsge.toString(), Alert.AlertType.ERROR);

            }
        }
        else {
            Message.showMessage("No records for year!", Alert.AlertType.ERROR);
            System.out.println("File nor exist");
        }
        return index;
    }

    public HBox addLabel(Label lbl,Node node){
        HBox hb = new HBox(10);
        lbl.setLabelFor(node);
        hb.getChildren().addAll(lbl,node);
        return hb;
    }

    public void initialize(URL url, ResourceBundle rb){
        VBox vb = new VBox(15);
        HBox hb= new HBox(10);
        Button submit= new Button("Submit"), exit = new Button("Exit");
        this.year.setPrefColumnCount(4);
        vb.getChildren().add(this.addLabel(new Label("Enter the year:"),this.year));
        vb.getChildren().add(this.addLabel(new Label("Enter the Gender:"),this.gender));
        vb.getChildren().add(this.addLabel(new Label("Enter the name:"),this.name));
        BorderPane.setMargin(vb, new Insets(8,8,8,8));
        this.root.setCenter(vb);

        submit.setOnMouseClicked((e)->{
            int index;
            if(this.validate()) {
                if ((index = this.getNameIndex()) != -1) {
                    this.mssge.setText(String.format("%s name %s is ranked #%d in the year %d",
                            this.gender.getValue(), this.name.getText(), index, Integer.valueOf(this.year.getText())));
                } else
                    this.mssge.setText("Couldn't find Name");
                this.displayMssge();

            }
        });

        exit.setOnMouseClicked((e)->{
            ((Node)e.getSource()).getScene().getWindow().hide();
        });

        this.setButton(submit);
        this.setButton(exit);
        vb = new VBox(10);
        //vb.getChildren().add(this.mssge);
        hb.getChildren().addAll(submit,exit);
        vb.getChildren().add(hb);

        this.root.setBottom(vb);


    }

    public void displayMssge(){
        Stage window = new Stage();
        Scene scene;
        BorderPane bp = new BorderPane();
        Button close = new Button("close");
        close.setOnMouseClicked((e)->{
            ((Node)e.getSource()).getScene().getWindow().hide();
        });
        this.setButton(close);

        BorderPane.setMargin(this.mssge,new Insets(10,10,10,10));
        BorderPane.setMargin(close, new Insets(8,8,8,8));
        BorderPane.setAlignment(close, Pos.BOTTOM_CENTER);
        bp.setCenter(this.mssge);
        bp.setBottom(close);
        scene = new Scene(bp, 350,100);
        window.setScene(scene);
        window.show();

    }

    public void setButton(Button b){
        b.setPrefHeight(20);
        b.setPrefWidth(100);
    }
}
