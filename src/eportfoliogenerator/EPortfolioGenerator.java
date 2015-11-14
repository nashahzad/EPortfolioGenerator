/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliogenerator;

import eportfoliogenerator.file.EPortfolioFileManager;
import eportfoliogenerator.view.EPortfolioView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Nauman
 */
public class EPortfolioGenerator extends Application {
    //Will do reading and writing
    EPortfolioFileManager fileManager = new EPortfolioFileManager();

    EPortfolioView ui = new EPortfolioView(fileManager);


    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("file:images/icons/MainIcon.png");
        primaryStage.getIcons().add(image);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
