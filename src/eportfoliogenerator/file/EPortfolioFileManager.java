package eportfoliogenerator.file;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.view.EPortfolioView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

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

        FileOutputStream file = new FileOutputStream(model.getePortfolioTitle() + ".obj");
        ObjectOutputStream fout = new ObjectOutputStream(file);
        fout.writeObject(model);
        fout.close();

//        //Build file path for the JSON
//        String jsonFilePath = StartUpConstants.JSON_SAVE + "/" + model.getePortfolioTitle() + ".json";
//
//        OutputStream os = new FileOutputStream(jsonFilePath);
//        JsonWriter jsonWriter = Json.createWriter(os);
//
//        //Create and Save JSON

    }

    public void handleLoadEPortfolio(EPortfolioModel model) throws Exception
    {
        FileChooser modelFileChooser = new FileChooser();

        // SET THE STARTING DIRECTORY
        modelFileChooser.setInitialDirectory(new File("./"));

        // LET'S ONLY SEE IMAGE FILES
        FileChooser.ExtensionFilter objFilter = new FileChooser.ExtensionFilter("OBJ files (&.obj)", "*.OBJ");

        modelFileChooser.getExtensionFilters().addAll(objFilter);

        // LET'S OPEN THE FILE CHOOSER
        File file = modelFileChooser.showOpenDialog(null);
        if (file != null) {
            String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
            String fileName = file.getName();

            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream fin = new ObjectInputStream(fileInputStream);
            model = (EPortfolioModel) fin.readObject();
            fin.close();
            ui.updatePageView();
        }
        else {
            // @todo provide error message for no files selected
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("");
            alert.setContentText("The program was unable to find the EPortfolio or nothing was selected!");

            alert.showAndWait();
        }

    }

    public void promptForEPortfolioTitle(EPortfolioModel model)
    {
        TextInputDialog dialog = new TextInputDialog(model.getePortfolioTitle());
        dialog.setTitle("EPortfolio Title");
        dialog.setContentText("Please enter a title for your EPortfolio:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            model.setePortfolioTitle(result.get());
            ui.getPrimaryStage().setTitle(result.get());
        }
    }

}
