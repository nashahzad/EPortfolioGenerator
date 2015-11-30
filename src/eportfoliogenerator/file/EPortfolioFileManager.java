package eportfoliogenerator.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.view.EPortfolioView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.json.*;
import java.io.*;
import java.util.Optional;

/**
 * Created by Nauman on 11/14/2015.
 */
public class EPortfolioFileManager
{
    EPortfolioView ui;

    public void setUi(EPortfolioView ui) {
        this.ui = ui;
    }

    //Method to handle saving of the EPortfolioModel to a JSON file
    public void handleSaveEPortfolio(EPortfolioModel model) throws IOException
    {
        if(model.getePortfolioTitle().equalsIgnoreCase("") || model.getePortfolioTitle() == null){
            promptForEPortfolioTitle(model);
        }
        if(model.getePortfolioTitle().equalsIgnoreCase("") || model.getePortfolioTitle() == null){}

        else {

//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            String json = gson.toJson(model);
//            FileWriter writer = new FileWriter("./data/" + model.getePortfolioTitle() + ".json");
//            writer.write(json);
//            writer.close();


        FileOutputStream file = new FileOutputStream("./data/" + model.getePortfolioTitle() + ".json");
        ObjectOutputStream fout = new ObjectOutputStream(file);
        fout.writeObject(model);
        fout.close();

            ui.updateToolbarControls(true);

//        //Build file path for the JSON
//        String jsonFilePath = StartUpConstants.JSON_SAVE + "/" + model.getePortfolioTitle() + ".json";
//
//        OutputStream os = new FileOutputStream(jsonFilePath);
//        JsonWriter jsonWriter = Json.createWriter(os);
//
//        //Create and Save JSON
        }

    }

    public void handleSaveAsEPortfolio(EPortfolioModel model) throws IOException
    {
        promptForEPortfolioTitle(model);
        handleSaveEPortfolio(model);
    }

    public EPortfolioModel handleLoadEPortfolio(EPortfolioModel model) throws Exception
    {
        FileChooser modelFileChooser = new FileChooser();

        // SET THE STARTING DIRECTORY
        modelFileChooser.setInitialDirectory(new File("./data/"));

        // LET'S ONLY SEE IMAGE FILES
        FileChooser.ExtensionFilter objFilter = new FileChooser.ExtensionFilter("JSON files (&.json)", "*.JSON");

        modelFileChooser.getExtensionFilters().addAll(objFilter);

        // LET'S OPEN THE FILE CHOOSER
        File file = modelFileChooser.showOpenDialog(null);
        if (file != null) {
            String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
            String fileName = file.getName();

//            Gson gson = new Gson();
//            BufferedReader br = new BufferedReader(new FileReader(path + fileName));
//            model = gson.fromJson(br, EPortfolioModel.class);
            FileInputStream fileInputStream = new FileInputStream(path + fileName);
            ObjectInputStream fin = new ObjectInputStream(fileInputStream);
            model = (EPortfolioModel) fin.readObject();
            fin.close();
            ui.updatePageView(model);
            ui.updateToolbarControls(true, model);
            return model;
        }
        else {
            // @todo provide error message for no files selected
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("");
            alert.setContentText("The program was unable to find the EPortfolio or nothing was selected!");
            alert.showAndWait();
            return model;
        }

    }

    public boolean promptToSave(EPortfolioModel model, Button saveButton, int i) throws Exception
    {
        if(saveButton.isDisable()){
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Prompt to Save");
            alert.setContentText("Would you like to save?");

            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeTwo = new ButtonType("No");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

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


            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                // ... user chose "One"
                handleSaveEPortfolio(model);
                if(i == -1)
                    System.exit(0);
                return true;
            } else if (result.get() == buttonTypeTwo) {
                // ... user chose "Two"
                return true;
            } else {
                // ... user chose CANCEL or closed the dialog
                return false;
            }
        }
    }

    public void promptForEPortfolioTitle(EPortfolioModel model)
    {
        TextInputDialog dialog = new TextInputDialog(model.getePortfolioTitle());
        dialog.setTitle("EPortfolio Title");
        dialog.setContentText("Please enter a title for your EPortfolio:");
        dialog.getDialogPane().setPrefWidth(600);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:./images/icons/eportfolio.gif"));
        stage.setScene(new Scene(new ScrollPane(dialog.getDialogPane())));

        DialogPane alertDialogPane = dialog.getDialogPane();

        alertDialogPane.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        alertDialogPane.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);

        //CSS to buttons added after alert does its getButtonTypes method
        ButtonBar buttonBar = (ButtonBar) dialog.getDialogPane().lookup(".button-bar");
        buttonBar.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        buttonBar.getButtons().forEach(b -> b.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS));

        //Content text
        alertDialogPane.lookup(".content.label").getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            model.setePortfolioTitle(result.get());
            ui.getPrimaryStage().setTitle(result.get());
        }
    }

}
