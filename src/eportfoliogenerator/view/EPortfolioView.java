package eportfoliogenerator.view;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Nauman on 11/14/2015.
 */
public class EPortfolioView
{
    //Main Window and UI will be here
    Stage primaryStage;
    Scene primaryScene;
    //Scene to switch with for page editor and web editor workspace
    Scene webScene;

    //BorderPane for different toolbars and workspaces
    BorderPane ePortfolioBorderPane;
    ScrollPane pageViewScrollPane;

    //Toolbar Boxes
    HBox fileToolBarHBox;
    VBox siteToolBarVBox;
    HBox webToolBarHBox;
}
