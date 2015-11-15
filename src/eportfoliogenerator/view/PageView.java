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
    HBox pageFontHBox;
    Label pageFontLabel = new Label("Page Font: ");
    ToggleGroup pageFontToggleGroup;
    RadioButton pageFontOneRadioButton;
    RadioButton pageFontTwoRadioButton;
    RadioButton pageFontThreeRadioButton;
    RadioButton pageFontFourRadioButton;
    RadioButton pageFontFiveRadioButton;

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

    VBox textComponentsVBox;
    VBox imageComponentsVBox;
    VBox slideShowComponentsVBox;
    VBox videoComponentsVBox;

    ArrayList<RadioButton> textComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> imageComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> slideShowComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> videoComponentsList = new ArrayList<RadioButton>();

    public PageView(Page page)
    {
        //Keep for the page later
        this.page = page;

        //Make sure to display proper Banner image or default image.
        bannerImageView = new ImageView();
        updateSlideImage();

        setUpLayout();
        setUpColor();

    }

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

    //Method to Set up the layout HBox and the event handlers for Radio buttons there
    private void setUpLayout()
    {
        layoutHBox = new HBox();

        layoutToggleGroup = new ToggleGroup();

        layoutOneRadioButton = new RadioButton("1");
        layoutOneRadioButton.setOnAction(event -> {
           this.page.setLayout(1);
        });

        layoutTwoRadioButton = new RadioButton("2");
        layoutTwoRadioButton.setOnAction(event -> {
            this.page.setLayout(2);
        });

        layoutThreeRadioButton = new RadioButton("3");
        layoutThreeRadioButton.setOnAction(event -> {
            this.page.setLayout(3);
        });

        layoutFourRadioButton = new RadioButton("4");
        layoutFourRadioButton.setOnAction(event -> {
            this.page.setLayout(4);
        });

        layoutFiveRadoButton = new RadioButton("5");
        layoutFiveRadoButton.setOnAction(event -> {
            this.page.setLayout(5);
        });

        //Add all radio buttons to same Toggle Group so that only one can be toggled on at a time
        layoutOneRadioButton.setToggleGroup(layoutToggleGroup);
        layoutTwoRadioButton.setToggleGroup(layoutToggleGroup);
        layoutThreeRadioButton.setToggleGroup(layoutToggleGroup);
        layoutFourRadioButton.setToggleGroup(layoutToggleGroup);
        layoutFiveRadoButton.setToggleGroup(layoutToggleGroup);

        //Setting up the starting toggled button
        if(page.getLayout() == 0){
            layoutOneRadioButton.setSelected(true);
        }
        else if(page.getLayout() == 1){
            layoutOneRadioButton.setSelected(true);
        }
        else if(page.getLayout() == 2){
            layoutTwoRadioButton.setSelected(true);
        }
        else if(page.getLayout() == 3){
            layoutThreeRadioButton.setSelected(true);
        }
        else if(page.getLayout() == 4){
            layoutFourRadioButton.setSelected(true);
        }
        else{
            layoutFiveRadoButton.setSelected(true);
        }

        //Add Buttons to the layout HBox
        layoutHBox.getChildren().add(layoutLabel);
        layoutHBox.getChildren().add(layoutOneRadioButton);
        layoutHBox.getChildren().add(layoutTwoRadioButton);
        layoutHBox.getChildren().add(layoutThreeRadioButton);
        layoutHBox.getChildren().add(layoutFourRadioButton);
        layoutHBox.getChildren().add(layoutFiveRadoButton);
    }

    private void setUpColor()
    {
        colorHBox = new HBox();

        colorToggleGroup = new ToggleGroup();

        colorOneRadioButton = new RadioButton("1");
        colorOneRadioButton.setOnAction(event -> {
            this.page.setColor(1);
        });

        colorTwoRadioButton = new RadioButton("2");
        colorTwoRadioButton.setOnAction(event -> {
            this.page.setColor(2);
        });

        colorThreeRadioButton = new RadioButton("3");
        colorThreeRadioButton.setOnAction(event -> {
            this.page.setColor(3);
        });

        colorFourRadioButton = new RadioButton("4");
        colorFourRadioButton.setOnAction(event -> {
            this.page.setColor(4);
        });

        colorFiveRadioButton = new RadioButton("5");
        colorFiveRadioButton.setOnAction(event -> {
            this.page.setColor(5);
        });

        //Add all radio buttons to same Toggle Group so that only one can be toggled on at a time
        colorOneRadioButton.setToggleGroup(colorToggleGroup);
        colorTwoRadioButton.setToggleGroup(colorToggleGroup);
        colorThreeRadioButton.setToggleGroup(colorToggleGroup);
        colorFourRadioButton.setToggleGroup(colorToggleGroup);
        colorFiveRadioButton.setToggleGroup(colorToggleGroup);

        //Setting up the starting toggled button
        if(page.getColor() == 0){
            colorOneRadioButton.setSelected(true);
        }
        else if(page.getColor() == 1){
            colorOneRadioButton.setSelected(true);
        }
        else if(page.getColor() == 2){
            colorTwoRadioButton.setSelected(true);
        }
        else if(page.getColor() == 3){
            colorThreeRadioButton.setSelected(true);
        }
        else if(page.getColor() == 4){
            colorFourRadioButton.setSelected(true);
        }
        else{
            colorFiveRadioButton.setSelected(true);
        }

        //Add Buttons to the layout HBox
        colorHBox.getChildren().add(colorLabel);
        colorHBox.getChildren().add(colorOneRadioButton);
        colorHBox.getChildren().add(colorTwoRadioButton);
        colorHBox.getChildren().add(colorThreeRadioButton);
        colorHBox.getChildren().add(colorFourRadioButton);
        colorHBox.getChildren().add(colorFiveRadioButton);
    }

}
