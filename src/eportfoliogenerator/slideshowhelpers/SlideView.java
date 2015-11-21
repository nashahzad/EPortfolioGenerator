package eportfoliogenerator.slideshowhelpers;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.ImageComponent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;

/**
 * Created by Nauman on 11/21/2015.
 */
public class SlideView extends HBox
{
    //Slide Associated with each Slide
    Slide slide;

    //ImageView for image
    ImageView imageSelectionView;

    //Caption stuff
    VBox captionVBox;
    Label captionLabel;
    TextField captionTextField;

    public SlideView(){
        slide = new Slide();

        Image image = new Image("file:" + StartUpConstants.DEFAULT_IMAGE);
        imageSelectionView = new ImageView(image);



    }

    /**
     * This function gets the image for the slide and uses it to
     * update the image displayed.
     */
    public void updateSlideImage() {
        String imagePath = slide.getImagePath() + "/" + slide.getImageFileName();
        File file = new File(imagePath);
        try {
            // GET AND SET THE IMAGE
            URL fileURL = file.toURI().toURL();
            Image slideImage = new Image(fileURL.toExternalForm());
            imageSelectionView.setImage(slideImage);

            // AND RESIZE IT
            double scaledWidth = 200;
            double perc = scaledWidth / slideImage.getWidth();
            double scaledHeight = slideImage.getHeight() * perc;
            imageSelectionView.setFitWidth(scaledWidth);
            imageSelectionView.setFitHeight(scaledHeight);
        } catch (Exception e) {
            // @todo - use Error handler to respond to missing image
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("");
            alert.setContentText("The program was unable to find the image!");

            alert.showAndWait();
            imagePath = "file:images/slide_show_images/DefaultStartSlide.png";
            file = new File(imagePath);
            try{
                URL fileURL = file.toURI().toURL();
                Image slideImage = new Image(fileURL.toExternalForm());
                imageSelectionView.setImage(slideImage);

                // AND RESIZE IT
                double scaledWidth = 200;
                double perc = scaledWidth / slideImage.getWidth();
                double scaledHeight = slideImage.getHeight() * perc;
                imageSelectionView.setFitWidth(scaledWidth);
                imageSelectionView.setFitHeight(scaledHeight);
            } catch(Exception e2){}
        }
    }
}
