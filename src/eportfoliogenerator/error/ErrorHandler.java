package eportfoliogenerator.error;

import eportfoliogenerator.StartUpConstants;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Nauman on 11/29/2015.
 */
public class ErrorHandler
{
    public static void errorPopUp(String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(text);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:./images/icons/eportfolio.gif"));

        DialogPane alertDialogPane = alert.getDialogPane();

        alertDialogPane.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        alertDialogPane.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);

        //CSS to buttons added after alert does its getButtonTypes method
        ButtonBar buttonBar = (ButtonBar)alert.getDialogPane().lookup(".button-bar");
        buttonBar.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        buttonBar.getButtons().forEach(b -> b.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS));

        //Content text
        alertDialogPane.lookup(".content.label").getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        alert.showAndWait();
    }
}
