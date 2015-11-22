package eportfoliogenerator.slideshowhelpers;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.controller.ImageSelectionController;
import eportfoliogenerator.dialog.DialogSlideShowComponent;
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
    //Stage
    DialogSlideShowComponent dialogSlideShowComponent;

    //Slide Associated with each Slide
    Slide slide;
    SlideShowModel slideShowModel;

    //ImageView for image
    ImageView imageSelectionView;
    ImageSelectionController imageSelectionController;

    //Caption stuff
    VBox attributesVBox;

    Label captionLabel = new Label("Caption:");
    TextField captionTextField;

    Label widthLabel = new Label("Width:");
    TextField widthTextField;

    Label heightLabel = new Label("Height:");
    TextField heightTextField;

    public SlideView(SlideShowModel slideShowModel, Slide slide, DialogSlideShowComponent dialogSlideShowComponent){
        this.slide = slide;
        this.slideShowModel = slideShowModel;
        this.dialogSlideShowComponent = dialogSlideShowComponent;

        //For Captions, width, height
        attributesVBox = new VBox();

        captionTextField = new TextField(slide.getCaption());
        captionTextField.textProperty().addListener((a, b, newText) -> {
            slide.setCaption(newText);
        });
        widthTextField = new TextField(slide.getWidth());
        widthTextField.textProperty().addListener((a, b, newText) -> {
            slide.setWidth(newText);
        });
        heightTextField = new TextField(slide.getHeight());
        heightTextField.textProperty().addListener((a, b, newText) -> {
            slide.setHeight(newText);
        });

        attributesVBox.getChildren().addAll(captionLabel, captionTextField, widthLabel, widthTextField, heightLabel, heightTextField);

        this.setOnMouseClicked(event -> {
            slideShowModel.setSelectedSlide(this.slide);
            dialogSlideShowComponent.reloadSlideShow(slideShowModel);
            dialogSlideShowComponent.updateButtons();
        });

        if(this.slide == slideShowModel.getSelectedSlide()){
            this.setStyle("-fx-background-color: orange;");
        }
        else{
            this.setStyle("-fx-background-color: #85CDE6");
        }

        //Place to Display image and select and image
        imageSelectionView = new ImageView();
        updateSlideImage();
        imageSelectionController = new ImageSelectionController();
        imageSelectionView.setOnMousePressed(event -> {
            imageSelectionController.processSelectImage(slide, this);
            dialogSlideShowComponent.reloadSlideShow(slideShowModel);
        });

        this.getChildren().addAll(imageSelectionView, attributesVBox);
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
