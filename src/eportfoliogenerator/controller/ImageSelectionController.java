package eportfoliogenerator.controller;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.model.Page;
import eportfoliogenerator.view.PageView;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Nauman on 11/15/2015.
 */
public class ImageSelectionController implements Serializable
{
    public void processSelectImage(Page pageToEdit, PageView view) {
        FileChooser imageFileChooser = new FileChooser();

        // SET THE STARTING DIRECTORY
        imageFileChooser.setInitialDirectory(new File(StartUpConstants.IMAGE_ICONS_FILE_PATH));

        // LET'S ONLY SEE IMAGE FILES
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
        imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);

        // LET'S OPEN THE FILE CHOOSER
        File file = imageFileChooser.showOpenDialog(null);
        if (file != null) {
            String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
            String fileName = file.getName();
            pageToEdit.setBannerImagePath(path);
            pageToEdit.setBannerImageName(fileName);
            view.updateSlideImage();
        }
        else {
            // @todo provide error message for no files selected
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("");
            alert.setContentText("The program was unable to find the image or an image was not selected!");

            alert.showAndWait();
        }
    }
}
