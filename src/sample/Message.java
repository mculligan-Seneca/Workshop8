/*
Stduent: Mitchell Culligan
Workshop 8
id: 1616293170
email: mculligan@myseneca.ca
Professor: Mahboob Ali
Date: March 27th, 2020
 */
package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Message {

    public static void showMessage(String message, Alert.AlertType type){
        Alert alert = new Alert(type,message, ButtonType.CLOSE);
        alert.showAndWait()
                .filter(response-> response==ButtonType.CLOSE)
                .ifPresent(response -> alert.close());

    }
}
