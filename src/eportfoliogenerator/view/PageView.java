package eportfoliogenerator.view;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.SlideShowComponent;
import eportfoliogenerator.components.TextComponent;
import eportfoliogenerator.components.VideoComponent;
import eportfoliogenerator.controller.ImageSelectionController;
import eportfoliogenerator.dialog.*;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Nauman on 11/14/2015.
 */
public class PageView extends VBox
{
    //Data model singleton and the Main GUI
    EPortfolioModel model;
    EPortfolioView ui;

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
    HBox bannerHBox;
    Label bannerLabel = new Label("Optional Banner Image: ");
    ImageView bannerImageView;

    //Label and Button for Footer, Student Name, and Page Title
    HBox basicInfoHBox;
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

    HBox addComponentsHBox;
    Label addComponentsLabel = new Label("Add Components");
    Button addTextComponentButton;
    Button addImageComponentButton;
    Button addSlideShowComponentButton;
    Button addVideoComponentButton;
    Button addTextHyperLinkButton;

    HBox editComponentsHBox;
    Label editComponentLabel = new Label("Edit Components");
    Button editTextComponentButton;
    Button editImageComponentButton;
    Button editSlideShowComponentButton;
    Button editVideoComponentButton;
    Button editTextHyperLinkButton;
    Button removeComponentButton;

    HBox displayComponentsHBox;
    VBox textComponentsVBox;
    VBox imageComponentsVBox;
    VBox slideShowComponentsVBox;
    VBox videoComponentsVBox;

    ToggleGroup componentsToggleGroup;
    ArrayList<RadioButton> textComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> imageComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> slideShowComponentsList = new ArrayList<RadioButton>();
    ArrayList<RadioButton> videoComponentsList = new ArrayList<RadioButton>();

    public PageView(Page page, EPortfolioModel model, EPortfolioView ui)
    {
        //Keep for the page later and model for later
        this.page = page;
        this.model = model;
        this.ui = ui;

        //Make sure to display proper Banner image or default image.
        bannerImageView = new ImageView();
        updateSlideImage();

        //Helper methods for setting up all the radio buttons and toggle groups
        setUpLayout();
        setUpColor();
        setUpPageFont();

        setUpBasicInfoGetters();

        //Add to PageView the basic components
        this.getChildren().add(layoutHBox);
        this.getChildren().add(colorHBox);
        this.getChildren().add(pageFontHBox);
        this.getChildren().add(basicInfoHBox);

        //Banner Image selection set up
        bannerHBox = new HBox();
        bannerHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        bannerLabel.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        bannerImageView.setOnMousePressed(event -> {
            ImageSelectionController imageSelectionController = new ImageSelectionController();
            imageSelectionController.processSelectImage(this.page, this);
            ui.updateSaveButtons();
            System.out.println(page.getBannerImagePath() + page.getBannerImageName());
        });
        bannerHBox.getChildren().add(bannerLabel);
        bannerHBox.getChildren().add(bannerImageView);
        bannerHBox.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(bannerHBox);

        //Now to set up the area for where ADDING/EDITING COMPONENTS will take place
        setUpComponentsView();

        //Attach event handlers for when buttons are clicked on
        setUpComponentEventHandlers();
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
        layoutHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);

        layoutToggleGroup = new ToggleGroup();

        layoutOneRadioButton = new RadioButton("1");
        layoutOneRadioButton.setOnAction(event -> {
            if(this.page.getLayout() != 1) { ui.updateSaveButtons(); }
           this.page.setLayout(1);
        });

        layoutTwoRadioButton = new RadioButton("2");
        layoutTwoRadioButton.setOnAction(event -> {
            if(this.page.getLayout() != 2) { ui.updateSaveButtons(); }
            this.page.setLayout(2);
        });

        layoutThreeRadioButton = new RadioButton("3");
        layoutThreeRadioButton.setOnAction(event -> {
            if(this.page.getLayout() != 3) { ui.updateSaveButtons(); }
            this.page.setLayout(3);
        });

        layoutFourRadioButton = new RadioButton("4");
        layoutFourRadioButton.setOnAction(event -> {
            if(this.page.getLayout() != 4) { ui.updateSaveButtons(); }
            this.page.setLayout(4);
        });

        layoutFiveRadoButton = new RadioButton("5");
        layoutFiveRadoButton.setOnAction(event -> {
            if (this.page.getLayout() != 5) {
                ui.updateSaveButtons();
            }
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

        //Attach CSS class to GUI components
        layoutLabel.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        layoutOneRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        layoutTwoRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        layoutThreeRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        layoutFourRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        layoutFiveRadoButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);

        //Add Buttons to the layout HBox
        layoutHBox.getChildren().add(layoutLabel);
        layoutHBox.getChildren().add(layoutOneRadioButton);
        layoutHBox.getChildren().add(layoutTwoRadioButton);
        layoutHBox.getChildren().add(layoutThreeRadioButton);
        layoutHBox.getChildren().add(layoutFourRadioButton);
        layoutHBox.getChildren().add(layoutFiveRadoButton);

        layoutHBox.setAlignment(Pos.TOP_CENTER);
    }

    private void setUpColor()
    {
        colorHBox = new HBox(7);
        colorHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);

        colorToggleGroup = new ToggleGroup();

        colorOneRadioButton = new RadioButton("Green");
        colorOneRadioButton.setOnAction(event -> {
            if(this.page.getColor() != 1) { ui.updateSaveButtons(); }
            this.page.setColor(1);
        });

        colorTwoRadioButton = new RadioButton("Hot Red");
        colorTwoRadioButton.setOnAction(event -> {
            if(this.page.getColor() != 2) { ui.updateSaveButtons(); }
            this.page.setColor(2);
        });

        colorThreeRadioButton = new RadioButton("Cool Blue");
        colorThreeRadioButton.setOnAction(event -> {
            if(this.page.getColor() != 3) { ui.updateSaveButtons(); }
            this.page.setColor(3);
        });

        colorFourRadioButton = new RadioButton("Yellow");
        colorFourRadioButton.setOnAction(event -> {
            if (this.page.getColor() != 4) {
                ui.updateSaveButtons();
            }
            this.page.setColor(4);
        });

        colorFiveRadioButton = new RadioButton("Purple");
        colorFiveRadioButton.setOnAction(event -> {
            if (this.page.getColor() != 5) {
                ui.updateSaveButtons();
            }
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

        //Attach CSS style class to GUI components
        colorLabel.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        colorOneRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_GREEN);
        colorTwoRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_HOT_RED);
        colorThreeRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_COOL_BLUE);
        colorFourRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_YELLOW);
        colorFiveRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_PURPLE);

        //Add Buttons to the layout HBox
        colorHBox.getChildren().add(colorLabel);
        colorHBox.getChildren().add(colorOneRadioButton);
        colorHBox.getChildren().add(colorTwoRadioButton);
        colorHBox.getChildren().add(colorThreeRadioButton);
        colorHBox.getChildren().add(colorFourRadioButton);
        colorHBox.getChildren().add(colorFiveRadioButton);

        colorHBox.setAlignment(Pos.TOP_CENTER);
    }

    private void setUpPageFont()
    {
        pageFontHBox = new HBox();
        pageFontHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);

        pageFontToggleGroup = new ToggleGroup();

        pageFontOneRadioButton = new RadioButton("Slabo");
        pageFontOneRadioButton.setOnAction(event -> {
            if(!this.page.getPageFont().equalsIgnoreCase("Slabo")) { ui.updateSaveButtons(); }
            this.page.setPageFont("Slabo");
        });

        pageFontTwoRadioButton = new RadioButton("Sans Pro");
        pageFontTwoRadioButton.setOnAction(event -> {
            if(!this.page.getPageFont().equalsIgnoreCase("Sans Pro")) { ui.updateSaveButtons(); }
            this.page.setPageFont("Sans Pro");
        });

        pageFontThreeRadioButton = new RadioButton("Serif");
        pageFontThreeRadioButton.setOnAction(event -> {
            if(!this.page.getPageFont().equalsIgnoreCase("Serif")) { ui.updateSaveButtons(); }
            this.page.setPageFont("Serif");
        });

        pageFontFourRadioButton = new RadioButton("Hind");
        pageFontFourRadioButton.setOnAction(event -> {
            if (!this.page.getPageFont().equalsIgnoreCase("Hind")) {
                ui.updateSaveButtons();
            }
            this.page.setPageFont("Hind");
        });

        pageFontFiveRadioButton = new RadioButton("Cantarell");
        pageFontFiveRadioButton.setOnAction(event -> {
            if (!this.page.getPageFont().equalsIgnoreCase("Cantarell")) {
                ui.updateSaveButtons();
            }
            this.page.setPageFont("Canatarell");
        });

        //Add all radio buttons to same Toggle Group so that only one can be toggled on at a time
        pageFontOneRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontTwoRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontThreeRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFourRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFiveRadioButton.setToggleGroup(pageFontToggleGroup);

        //Setting up the starting toggled button
        if(page.getPageFont() == null || page.getPageFont().equalsIgnoreCase("")){
            pageFontOneRadioButton.setSelected(true);
        }
        else if(page.getPageFont().equalsIgnoreCase("Slabo")){
            pageFontOneRadioButton.setSelected(true);
        }
        else if(page.getPageFont().equalsIgnoreCase("Sans Pro")){
            pageFontTwoRadioButton.setSelected(true);
        }
        else if(page.getPageFont().equalsIgnoreCase("Serif")){
            pageFontThreeRadioButton.setSelected(true);
        }
        else if(page.getPageFont().equalsIgnoreCase("Hind")){
             pageFontFourRadioButton.setSelected(true);
        }
        else{
            pageFontFiveRadioButton.setSelected(true);
        }

        //Attach CSS style Class to GUI components
        pageFontLabel.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        pageFontOneRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_SLABO);
        pageFontTwoRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_SANS_PRO);
        pageFontThreeRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_SERIF);
        pageFontFourRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_HIND);
        pageFontFiveRadioButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS_CANTARELL);

        //Add Buttons to the layout HBox
        pageFontHBox.getChildren().add(pageFontLabel);
        pageFontHBox.getChildren().add(pageFontOneRadioButton);
        pageFontHBox.getChildren().add(pageFontTwoRadioButton);
        pageFontHBox.getChildren().add(pageFontThreeRadioButton);
        pageFontHBox.getChildren().add(pageFontFourRadioButton);
        pageFontHBox.getChildren().add(pageFontFiveRadioButton);

        pageFontHBox.setAlignment(Pos.TOP_CENTER);
    }

    private void setUpBasicInfoGetters()
    {
        basicInfoHBox = new HBox(10);
        basicInfoHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);

        //Set up the part for retrieving and setting the Page title, and CSS
        pageTitleLabel.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        basicInfoHBox.getChildren().add(pageTitleLabel);

        pageTitleButton = new Button();
        pageTitleButton.setText(page.getPageTitle());
        pageTitleButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog(page.getPageTitle());
            dialog.setTitle("Page Title Input");
            dialog.setContentText("Please enter a page title:");
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
            if (result.isPresent()) {
                page.setPageTitle(result.get());
                pageTitleButton.setText(result.get());
                ui.updateSaveButtons();
            }
        });

        pageTitleButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        basicInfoHBox.getChildren().add(pageTitleButton);

        //Set up the part for retrieving and setting Student Name, and CSS
        studentNameLabel.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        basicInfoHBox.getChildren().add(studentNameLabel);

        studentNameButton = new Button();
        studentNameButton.setText(model.getStudentName());
        studentNameButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog(model.getStudentName());
            dialog.setTitle("Student Name Input");
            dialog.setContentText("Please the Student's Name:");
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
            if (result.isPresent()) {
                model.setStudentName(result.get());
                studentNameButton.setText(result.get());
                ui.updateSaveButtons();
            }
        });

        studentNameButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        basicInfoHBox.getChildren().add(studentNameButton);

        //Set up part for retrieving and setting Footer for the page
        footerLabel.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        basicInfoHBox.getChildren().add(footerLabel);

        footerButton = new Button();
        Image image = new Image("file:" + StartUpConstants.ICON_FOOTER);
        footerButton.setGraphic(new ImageView(image));
        footerButton.setTooltip(new Tooltip("Enter Footer Here"));
        footerButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog(page.getFooter());
            dialog.setTitle("Footer Input");
            dialog.setContentText("Enter or modify the footer:");
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
            if (result.isPresent()) {
                page.setFooter(result.get());
                ui.updateSaveButtons();
            }
        });

        footerButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        basicInfoHBox.getChildren().add(footerButton);

        basicInfoHBox.setAlignment(Pos.TOP_CENTER);
    }

    private void setUpComponentsView()
    {

        //Add Components VBOX
        addComponentsHBox = new HBox();
        addComponentsHBox.setAlignment(Pos.TOP_CENTER);
        addTextComponentButton = new Button();
        addImageComponentButton = new Button();
        addSlideShowComponentButton = new Button();
        addVideoComponentButton = new Button();
        addTextHyperLinkButton = new Button();

        addComponentsHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        addComponentsLabel.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        addTextComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        addImageComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        addSlideShowComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        addVideoComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        addTextHyperLinkButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);

        //Add it all into the HBox and then into this PageView
        addComponentsHBox.getChildren().addAll(addComponentsLabel, addTextComponentButton, addImageComponentButton, addSlideShowComponentButton, addVideoComponentButton, addTextHyperLinkButton);
        this.getChildren().add(addComponentsHBox);

        //Edit Components VBOX
        editComponentsHBox = new HBox();
        editComponentsHBox.setAlignment(Pos.TOP_CENTER);
        editTextComponentButton = new Button();
        editImageComponentButton = new Button();
        editSlideShowComponentButton = new Button ();
        editVideoComponentButton = new Button();
        editTextHyperLinkButton = new Button();
        removeComponentButton = new Button();

        editComponentsHBox.getStyleClass().add(StartUpConstants.CSS_EDIT_COMPONENTS);
        editComponentLabel.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        editTextComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        editImageComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        editSlideShowComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        editVideoComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        editTextHyperLinkButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        removeComponentButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);

        //Add it all into the HBox and then this PageView
        editComponentsHBox.getChildren().addAll(editComponentLabel, editTextComponentButton, editImageComponentButton, editSlideShowComponentButton,
                editVideoComponentButton, editTextHyperLinkButton, removeComponentButton);
        this.getChildren().add(editComponentsHBox);

        //Now for area that will DISPLAY CREATED COMPONENTS
        displayComponentsHBox = new HBox();
        displayComponentsHBox.getStyleClass().add(StartUpConstants.CSS_COMPONENTS_HBOX);

        textComponentsVBox = new VBox();
        textComponentsVBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        textComponentsVBox.getChildren().add(textComponentLabel);
        displayComponentsHBox.getChildren().add(textComponentsVBox);

        imageComponentsVBox = new VBox();
        imageComponentsVBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageComponentsVBox.getChildren().add(imageComponentsLabel);
        displayComponentsHBox.getChildren().add(imageComponentsVBox);

        slideShowComponentsVBox = new VBox();
        slideShowComponentsVBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        slideShowComponentsVBox.getChildren().add(slideShowComponentsLabel);
        displayComponentsHBox.getChildren().add(slideShowComponentsVBox);

        videoComponentsVBox = new VBox();
        videoComponentsVBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        videoComponentsVBox.getChildren().add(videoComponentsLabel);
        displayComponentsHBox.getChildren().add(videoComponentsVBox);

        //Add in COMPONENTS DISPLAY AREA into this PageView VBOX
        displayComponentsHBox.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(displayComponentsHBox);

        //Initialize the COMPONENTS TOGGLE GROUP
        componentsToggleGroup = new ToggleGroup();

        //Attach graphics/images to buttons
        attachGraphics();

        //Reload any existing components
        reloadPageView();
    }

    private void attachGraphics(){
        Image image = new Image("file:" + StartUpConstants.ICON_COMPONENT_TEXT);
        addTextComponentButton.setGraphic(new ImageView(image));
        addTextComponentButton.setTooltip(new Tooltip("Add Text Component"));
        editTextComponentButton.setGraphic(new ImageView(image));
        editTextComponentButton.setTooltip(new Tooltip("Edit Text Component"));

        image = new Image("file:" + StartUpConstants.ICON_COMPONENT_IMAGE);
        addImageComponentButton.setGraphic(new ImageView(image));
        addImageComponentButton.setTooltip(new Tooltip("Add Image Component"));
        editImageComponentButton.setGraphic(new ImageView(image));
        editImageComponentButton.setTooltip(new Tooltip("Edit Image Component"));

        image = new Image("file:" + StartUpConstants.ICON_COMPONENT_SLIDESHOW);
        addSlideShowComponentButton.setGraphic(new ImageView(image));
        addSlideShowComponentButton.setTooltip(new Tooltip("Add SlideShow Component"));
        editSlideShowComponentButton.setGraphic(new ImageView(image));
        editSlideShowComponentButton.setTooltip(new Tooltip("Edit SlideShow Component"));

        image = new Image("file:" + StartUpConstants.ICON_COMPONENT_VIDEO);
        addVideoComponentButton.setGraphic(new ImageView(image));
        addVideoComponentButton.setTooltip(new Tooltip("Add Video Component"));
        editVideoComponentButton.setGraphic(new ImageView(image));
        editVideoComponentButton.setTooltip(new Tooltip("Edit Video Component"));

        image = new Image("file:" + StartUpConstants.ICON_COMPONENT_HYPERLINK);
        addTextHyperLinkButton.setGraphic(new ImageView(image));
        addTextHyperLinkButton.setTooltip(new Tooltip("Add Text-Hyperlink Component"));
        editTextHyperLinkButton.setGraphic(new ImageView(image));
        editTextHyperLinkButton.setTooltip(new Tooltip("Edit Text-Hyperlink Component"));

        image = new Image("file:" + StartUpConstants.ICON_REMOVE_COMPONENT);
        removeComponentButton.setGraphic(new ImageView(image));
        removeComponentButton.setTooltip(new Tooltip("Remove Component"));
    }

    private void setUpComponentEventHandlers()
    {
        addTextComponentButton.setOnAction(event -> {
            DialogTextComponents dialogTextComponents = new DialogTextComponents(this);
            dialogTextComponents.promptForType(page, textComponentsList);
        });

        addImageComponentButton.setOnAction(event -> {
            DialogImageComponent dialogImageComponent = new DialogImageComponent(this);
            dialogImageComponent.createImageComponent(page, imageComponentsList);
        });

        addSlideShowComponentButton.setOnAction(event -> {
            DialogSlideShowComponent dialogSlideShowComponent = new DialogSlideShowComponent(this);
            dialogSlideShowComponent.addSlideShow(page, slideShowComponentsList);
        });

        addVideoComponentButton.setOnAction(event -> {
            DialogVideoComponent dialogVideoComponent = new DialogVideoComponent(this);
            dialogVideoComponent.createVideoComponent(page, videoComponentsList);
        });

        addTextHyperLinkButton.setOnAction(event -> {
            if(textComponentsList.size() > 0){
                int index = 0;
                boolean flag = false;
                for(RadioButton radioButton: textComponentsList){
                    if(radioButton.isSelected()){
                        String string = radioButton.getText();
                        index = Character.getNumericValue(string.charAt(string.length()-1));
                        break;
                    }
                }
                TextComponent textComponent = page.getTextComponents().get(index);
                if(textComponent.getTextType().equalsIgnoreCase("paragraph"))
                    flag = true;

                if(flag){
                    DialogTextHyperLinkComponent dialogTextHyperLinkComponent = new DialogTextHyperLinkComponent(this);
                    dialogTextHyperLinkComponent.createHyperlinks(textComponent);
                }

                else{

                }
            }
        });

        //NOW FOR EDIT COMPONENT BUTTONS STARTING WITH REMOVE COMPONENT BUTTON HANDLER

        removeComponentButton.setOnAction(event -> {
            boolean flag = true;
            int index = 0;

            if(flag){
                if(textComponentsList.size() > 0){
                    for(RadioButton radioButton: textComponentsList){
                        if(radioButton.isSelected()){
                            flag = false;
                            textComponentsList.remove(radioButton);
                            break;
                        }
                        index++;
                    }

                    if(flag){}
                    else{
                        page.getTextComponents().remove(index);
                        reloadPageView();
                        ui.updateSaveButtons();
                    }
                }
            }

            if(flag){
                if(imageComponentsList.size() > 0){
                    index = 0;
                    for(RadioButton radioButton: imageComponentsList){
                        if(radioButton.isSelected()){
                            flag = false;
                            imageComponentsList.remove(radioButton);
                            break;
                        }
                        index++;
                    }

                    if(flag){}
                    else{
                        page.getImageComponents().remove(index);
                        reloadPageView();
                        ui.updateSaveButtons();
                    }
                }
            }

            if(slideShowComponentsList.size() > 0){
                index = 0;
                for(RadioButton radioButton: slideShowComponentsList){
                    if(radioButton.isSelected()){
                        flag = false;
                        slideShowComponentsList.remove(radioButton);
                        break;
                    }
                    index++;
                }

                if(flag){}
                else{
                    page.getSlideShowComponents().remove(index);
                    reloadPageView();
                    ui.updateSaveButtons();
                }
            }

            if(videoComponentsList.size() > 0){
                index = 0;
                for(RadioButton radioButton: videoComponentsList){
                    if(radioButton.isSelected()){
                        flag = false;
                        videoComponentsList.remove(radioButton);
                        break;
                    }
                    index++;
                }

                if(flag){}
                else{
                    page.getVideoComponents().remove(index);
                    reloadPageView();
                    ui.updateSaveButtons();
                }
            }
        });

        editTextComponentButton.setOnAction(event -> {
            if(textComponentsList.size() > 0){
                int index = 0;
                for(RadioButton radioButton: textComponentsList){
                    if(radioButton.isSelected()){
                        break;
                    }
                    index++;
                }
                TextComponent textComponentToEdit = page.getTextComponents().get(index);
                DialogTextComponents dialogTextComponents = new DialogTextComponents(this);
                //IF TEXT COMPONENT IS A HEADER RUN THIS BLOCK
                if(textComponentToEdit.getTextType().equalsIgnoreCase("Header")){
                    dialogTextComponents.editHeader(textComponentToEdit);
                }
                //IF TEXT COMPONENT IS A PARAGRAPH RUN THIS BLOCK
                if(textComponentToEdit.getTextType().equalsIgnoreCase("Paragraph")){
                    dialogTextComponents.editParagraph(textComponentToEdit);
                }
                //IF TEXT COMPONENT IS A LIST RUN THIS BLOCK
                if(textComponentToEdit.getTextType().equalsIgnoreCase("List")){
                    dialogTextComponents.editList(textComponentToEdit);
                }
            }
        });

        editImageComponentButton.setOnAction(event -> {
            if(imageComponentsList.size() > 0) {
                int index = 0;
                for (RadioButton radioButton : imageComponentsList) {
                    if (radioButton.isSelected()) {
                        break;
                    }
                    index++;
                }
                ImageComponent imageComponentToEdit = page.getImageComponents().get(index);
                DialogImageComponent dialogImageComponent = new DialogImageComponent(this);
                dialogImageComponent.editImageComponent(imageComponentToEdit);
            }
        });

        editSlideShowComponentButton.setOnAction(event -> {
            if(slideShowComponentsList.size() > 0) {
                int index = 0;
                for (RadioButton radioButton : slideShowComponentsList) {
                    if (radioButton.isSelected()) {
                        break;
                    }
                    index++;
                }
                SlideShowComponent slideShowComponentToEdit = page.getSlideShowComponents().get(index);
                DialogSlideShowComponent dialogSlideShowComponent = new DialogSlideShowComponent(this);
                dialogSlideShowComponent.editSlideShowComponent(slideShowComponentToEdit);
            }
        });

        editVideoComponentButton.setOnAction(event -> {
            if(videoComponentsList.size() > 0) {
                int index = 0;
                for (RadioButton radioButton : videoComponentsList) {
                    if (radioButton.isSelected()) {
                        break;
                    }
                    index++;
                }
                VideoComponent videoComponentToEdit = page.getVideoComponents().get(index);
                DialogVideoComponent dialogVideoComponent = new DialogVideoComponent(this);
                dialogVideoComponent.editVideoComponent(videoComponentToEdit);
            }
        });

        editTextHyperLinkButton.setOnAction(event -> {
            if(textComponentsList.size() > 0) {
                int index = 0;
                for (RadioButton radioButton : textComponentsList) {
                    if (radioButton.isSelected()) {
                        break;
                    }
                    index++;
                }
                TextComponent textComponentToEdit = page.getTextComponents().get(index);
                DialogTextHyperLinkComponent dialogTextHyperLinkComponent = new DialogTextHyperLinkComponent(this);
//                //IF TEXT COMPONENT IS A HEADER RUN THIS BLOCK
//                if(textComponentToEdit.getTextType().equalsIgnoreCase("Header")){
//                    dialogTextComponents.editHeader(textComponentToEdit);
//                }
                //IF TEXT COMPONENT IS A PARAGRAPH RUN THIS BLOCK
                if (textComponentToEdit.getTextType().equalsIgnoreCase("Paragraph")) {
                    dialogTextHyperLinkComponent.editTextHyperlinkComponent(textComponentToEdit);
                }
//                //IF TEXT COMPONENT IS A LIST RUN THIS BLOCK
//                if(textComponentToEdit.getTextType().equalsIgnoreCase("List")){
//                    dialogTextComponents.editList(textComponentToEdit);
//                }
            }
        });

    }

    public void reloadPageView(){
        this.getChildren().clear();

        reloadComponentsRadioButtons();

        this.getChildren().add(layoutHBox);
        this.getChildren().add(colorHBox);
        this.getChildren().add(pageFontHBox);
        this.getChildren().add(basicInfoHBox);

        this.getChildren().add(bannerHBox);

        this.getChildren().add(addComponentsHBox);
        this.getChildren().add(editComponentsHBox);
        this.getChildren().add(displayComponentsHBox);
    }

    private void reloadComponentsRadioButtons()
    {
        //Block of code to refresh and update area to display text components on a page
        if(page.getTextComponents().size() > 0){
            textComponentsVBox.getChildren().clear();
            textComponentsVBox.getChildren().add(textComponentLabel);
            textComponentsList.clear();
            int index = 0;
            for(TextComponent textComponent: page.getTextComponents()) {
                RadioButton radioButton = new RadioButton(textComponent.getTextType() + " " + index);
                radioButton.setToggleGroup(componentsToggleGroup);
                textComponentsList.add(radioButton);
                textComponentsVBox.getChildren().add(radioButton);
                index++;
            }
        }

        else{
            textComponentsVBox.getChildren().clear();
            textComponentsVBox.getChildren().add(textComponentLabel);
        }

        //Block of code to refresh and update area on display for image components
        if(page.getImageComponents().size() > 0){
            imageComponentsVBox.getChildren().clear();
            imageComponentsVBox.getChildren().add(imageComponentsLabel);
            imageComponentsList.clear();
            for(ImageComponent imageComponent: page.getImageComponents()) {
                RadioButton radioButton = new RadioButton(imageComponent.getImageName());
                radioButton.setToggleGroup(componentsToggleGroup);
                imageComponentsList.add(radioButton);
                imageComponentsVBox.getChildren().add(radioButton);
            }
        }

        else{
            imageComponentsVBox.getChildren().clear();
            imageComponentsVBox.getChildren().add(imageComponentsLabel);
        }

        //Block of code to refresh and update the area on display for slideshowcomponents
        if(page.getSlideShowComponents().size() > 0){
            slideShowComponentsVBox.getChildren().clear();
            slideShowComponentsVBox.getChildren().add(slideShowComponentsLabel);
            slideShowComponentsList.clear();
            for(SlideShowComponent slideShowComponent: page.getSlideShowComponents()) {
                RadioButton radioButton = new RadioButton(slideShowComponent.getSlideShowTitle());
                radioButton.setToggleGroup(componentsToggleGroup);
                slideShowComponentsList.add(radioButton);
                slideShowComponentsVBox.getChildren().add(radioButton);
            }
        }

        else{
            slideShowComponentsVBox.getChildren().clear();
            slideShowComponentsVBox.getChildren().add(slideShowComponentsLabel);
        }

        //Block of code to refresh and update the area on display for Video Components
        if(page.getVideoComponents().size() > 0){
            videoComponentsVBox.getChildren().clear();
            videoComponentsVBox.getChildren().add(videoComponentsLabel);
            videoComponentsList.clear();
            for(VideoComponent videoComponent: page.getVideoComponents()) {
                RadioButton radioButton = new RadioButton(videoComponent.getVideoName());
                radioButton.setToggleGroup(componentsToggleGroup);
                videoComponentsList.add(radioButton);
                videoComponentsVBox.getChildren().add(radioButton);
            }
        }

        else{
            videoComponentsVBox.getChildren().clear();
            videoComponentsVBox.getChildren().add(videoComponentsLabel);
        }

    }

}
