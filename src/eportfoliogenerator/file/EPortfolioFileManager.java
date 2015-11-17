package eportfoliogenerator.file;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.view.EPortfolioView;
import javafx.scene.control.TextInputDialog;

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
    public void handleSaveEPortfolio( EPortfolioModel model) throws IOException
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
