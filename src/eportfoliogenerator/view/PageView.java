package eportfoliogenerator.view;

import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/14/2015.
 */
public class PageView extends VBox
{
    //Data model singleton
    EPortfolioModel model;

    //Current working page
    Page page;

    //HBoxes for layout and color
    HBox layoutHBox;
    HBox colorHBox;

    //Labels and radio buttons for layout and color
    Label layoutLabel = new Label("Layout: ");
    Label colorLabel = new Label("Color: ");

    ToggleGroup layoutToggleGroup;
    RadioButton layoutOneRadioButton;
    RadioButton layoutTwoRadioButton;
    RadioButton layoutThreeRadioButton;
    RadioButton layoutFourRadioButton;
    RadioButton layoutFiveRadoButton;

    ToggleGroup colorToggleGroup;
    RadioButton colorOneRadioButton;
    RadioButton colorTwoRadioButton;
    RadioButton colorThreeRadioButton;
    RadioButton colorFourRadioButton;
    RadioButton colorFiveRadioButton;

    //Label and ImageView for Banner
    Label bannerLabel = new Label("Optional Banner Image: ");
    ImageView bannerImageView;

    //Label and Button for Footer, Student Name, and Page Title
    Label footerLabel = new Label("Footer: ");
    Button footerButton;

    Label studentNameLabel = new Label("Student Name: ");
    Button studentNameButton;

    Label pageTitleLabel = new Label("Page Title: ");
    Button pageTitleButton;

    //Page Font label and buttons
    Label pageFontLabel = new Label("Page Font: ");
    Button pageFontOneButton;
    Button pageFontTwoButton;
    Button pageFontThreeButton;
    Button pageFontFourButton;
    Button pageFontFiveButton;

    //Objects and Lists for handling Components
    Label textComponentLabel = new Label("Text Components");
    Label imageComponentsLabel = new Label("Image Components");
    Label slideShowComponentsLabel = new Label("SlideShow Components");
    Label videoComponentsLabel = new Label("Video Components");

    HBox addEditComponentsHBox;
    VBox addComponentsVBox;
    Button addTextComponentButton;
    Button addImageComponentButton;
    Button addSlideShowComponentButton;
    Button addVideoComponentButton;
    Button addTextHyperLinkButton;

    VBox editComponentsVBox;
    Button editTextComponentButton;
    Button editImageComponentButton;
    Button editSlideShowComponentButton;
    Button editVideoComponentButton;
    Button editTextHyperLinkButton;

    ArrayList<RadioButton> textComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> imageComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> slideShowComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> videoComponentsList = new ArrayList<RadioButton>();


   public void updateSlideImage()
   {
       String imagePath = page.getBannerImagePath() + "/" + page.getBannerImageName();
       File file = new File(imagePath);
       try{
           //Get and set the banner image
           URL fileURL = file.toURI().toURL();
           Image bannerImage = new Image(fileURL.toExternalForm());
           bannerImageView.setImage(bannerImage);

           // AND RESIZE IT
           double scaledWidth = 200;
           double perc = scaledWidth / bannerImage.getWidth();
           double scaledHeight = bannerImage.getHeight() * perc;
           bannerImageView.setFitWidth(scaledWidth);
           bannerImageView.setFitHeight(scaledHeight);
       }catch (Exception e) {
           // @todo - use Error handler to respond to missing image
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Warning!");
           alert.setHeaderText("");
           alert.setContentText("The program was unable to find the image!");

           alert.showAndWait();
           imagePath = "file:images/icons/DefaultStartSlide.png";
           file = new File(imagePath);
           try{
               URL fileURL = file.toURI().toURL();
               Image slideImage = new Image(fileURL.toExternalForm());
               bannerImageView.setImage(slideImage);

               // AND RESIZE IT
               double scaledWidth = 200;
               double perc = scaledWidth / slideImage.getWidth();
               double scaledHeight = slideImage.getHeight() * perc;
               bannerImageView.setFitWidth(scaledWidth);
               bannerImageView.setFitHeight(scaledHeight);
           } catch(Exception e2){}
       }
   }
}
