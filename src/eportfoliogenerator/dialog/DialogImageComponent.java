package eportfoliogenerator.dialog;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.error.ErrorHandler;
import eportfoliogenerator.model.Page;
import eportfoliogenerator.view.EPortfolioView;
import eportfoliogenerator.view.PageView;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/21/2015.
 */
public class DialogImageComponent extends Stage
{
    EPortfolioView ui;

    //Current PageView we are working with
    PageView pageView;
    ImageComponent imageComponent;

    //For Editing ImageComponent
    ImageComponent imageComponentConstruct;

    //Scene and other GUI Components needed
    Scene scene;
    ScrollPane scrollPane;
    VBox imageVBox;
    HBox imageHBox;

    HBox imageAttributesHBox;
    Label imageWidthLabel = new Label("Width:");
    TextField imageWidthTextField;
    Label imageHeightLabel = new Label("Height:");
    TextField imageHeightTextField;

    HBox floatHBox;
    Label floatLabel = new Label("Float:");
    ToggleGroup floatToggleGroup;
    RadioButton floatLeftRadioButton;
    RadioButton floatNeitherRadioButton;
    RadioButton floatRightRadioButton;

    HBox imageCaptionHBox;
    Label imageCaptionLabel = new Label("Caption: ");
    TextField imageCaptionTextField;

    HBox confirmCancelHBox;
    Button confirmButton;
    Button cancelButton;

    ImageView imageView;


    public DialogImageComponent(PageView pageView){
        this.pageView = pageView;
        Image image = new Image("file:./images/icons/eportfolio.gif");
        this.getIcons().add(image);
    }

    public void createImageComponent(Page page, ArrayList<RadioButton> imageComponentsList, EPortfolioView ui){
        this.ui = ui;

        this.setTitle("Create Image Component");
        imageVBox = new VBox();
        imageComponent = new ImageComponent();

        imageHBox = new HBox();
        Image defaultImage = new Image("file:" + StartUpConstants.DEFAULT_IMAGE);
        imageView = new ImageView(defaultImage);
        imageHBox.getChildren().add(imageView);

        imageView.setOnMousePressed(event -> {
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
                imageComponent.setImagePath(path);
                imageComponent.setImageName(fileName);
                updateSlideImage();

            } else {
                // @todo provide error message for no files selected
                ErrorHandler.errorPopUp("The program was unable to find the image or an image was not selected!");
            }
        });

        //Set up area to input width and height of image
        imageAttributesHBox = new HBox();

        imageWidthTextField = new TextField();
        imageHeightTextField = new TextField();

        imageAttributesHBox.getChildren().addAll(imageWidthLabel, imageWidthTextField, imageHeightLabel, imageHeightTextField);

        //Set up Area to take in float attribute
        floatHBox = new HBox();
        floatToggleGroup = new ToggleGroup();

        floatLeftRadioButton = new RadioButton("Left");
        floatNeitherRadioButton = new RadioButton("Neither");
        floatRightRadioButton = new RadioButton("Right");
        floatLeftRadioButton.setToggleGroup(floatToggleGroup);
        floatNeitherRadioButton.setToggleGroup(floatToggleGroup);
        floatRightRadioButton.setToggleGroup(floatToggleGroup);

        floatHBox.getChildren().addAll(floatLabel, floatLeftRadioButton, floatNeitherRadioButton, floatRightRadioButton);

        //Set up area to enter Caption
        imageCaptionHBox = new HBox();
        imageCaptionTextField = new TextField();
        imageCaptionHBox.getChildren().addAll(imageCaptionLabel, imageCaptionTextField);

        //Set up Confirm and Cancel Buttons
        confirmCancelHBox = new HBox();
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");

        confirmButton.setOnAction(event -> {
            imageComponent.setCaption(imageCaptionTextField.getText());
            boolean flagWidth = isNumeric(imageWidthTextField.getText());
            boolean flagHeight = isNumeric(imageHeightTextField.getText());
            if(flagWidth && flagHeight){
                imageComponent.setWidth(imageWidthTextField.getText());
                imageComponent.setHeight(imageHeightTextField.getText());

                if(floatLeftRadioButton.isSelected()){
                    imageComponent.setFloatAttribute("Left");
                }
                else if(floatRightRadioButton.isSelected()){
                    imageComponent.setFloatAttribute("Right");
                }
                else
                    imageComponent.setFloatAttribute("Neither");

                imageComponentsList.add(new RadioButton(imageComponent.getImageName()));
                page.getAllComponents().add(imageComponent);
                pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            }
            else{
                ErrorHandler.errorPopUp("Incorrect user input, either the width or height was not a number, or is less than or equal to zero!!");
            }
        });

        cancelButton.setOnAction(event -> {
            this.close();
        });

        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);

        imageHBox.setAlignment(Pos.TOP_CENTER);
        confirmCancelHBox.setAlignment(Pos.BOTTOM_CENTER);
        floatHBox.setAlignment(Pos.CENTER);
        imageVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        imageAttributesHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        floatHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageCaptionHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        confirmCancelHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageVBox.getChildren().addAll(imageHBox, imageAttributesHBox, floatHBox, imageCaptionHBox);


        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(.5 * bounds.getMinX());
        this.setY(.5 * bounds.getMinY());
        this.setWidth(.5 * bounds.getWidth());
        this.setHeight(.5 * bounds.getHeight());

        scrollPane = new ScrollPane(imageVBox);
        scrollPane.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(confirmCancelHBox);
        borderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
        scene = new Scene(borderPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    public void editImageComponent(ImageComponent imageComponentToEdit, EPortfolioView ui){
        this.ui = ui;
        this.setTitle("Create Image Component");
        imageVBox = new VBox();
        imageComponent = imageComponentToEdit;

        imageComponentConstruct = new ImageComponent();
        imageComponentConstruct.setImageName(imageComponentToEdit.getImageName());
        imageComponentConstruct.setImagePath(imageComponentToEdit.getImagePath());
        imageComponentConstruct.setCaption(imageComponentToEdit.getCaption());
        imageComponentConstruct.setFloatAttribute(imageComponentToEdit.getFloatAttribute());
        imageComponentConstruct.setWidth(imageComponentToEdit.getWidth());
        imageComponentConstruct.setHeight(imageComponentToEdit.getHeight());

        imageHBox = new HBox();
        try {
            Image defaultImage = new Image(new File(imageComponentToEdit.getImagePath() + imageComponentToEdit.getImageName()).toURI().toURL().toExternalForm());
            imageView = new ImageView(defaultImage);
            // AND RESIZE IT
            double scaledWidth = 200;
            double perc = scaledWidth / defaultImage.getWidth();
            double scaledHeight = defaultImage.getHeight() * perc;
            imageView.setFitWidth(scaledWidth);
            imageView.setFitHeight(scaledHeight);
        }catch (Exception ex){
            System.out.println("Exception was thrown in the editImageComponent method, in the DialogImageComponent Class.");
        }

        imageHBox.getChildren().add(imageView);

        imageView.setOnMousePressed(event -> {
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
                imageComponent.setImagePath(path);
                imageComponent.setImageName(fileName);
                updateSlideImage();

            } else {
                // @todo provide error message for no files selected
                ErrorHandler.errorPopUp("The program was unable to find the image or an image was not selected!");
            }
        });

        //Set up area to input width and height of image
        imageAttributesHBox = new HBox();

        imageWidthTextField = new TextField(imageComponentToEdit.getWidth());
        imageHeightTextField = new TextField(imageComponentToEdit.getHeight());

        imageAttributesHBox.getChildren().addAll(imageWidthLabel, imageWidthTextField, imageHeightLabel, imageHeightTextField);

        //Set up Area to take in float attribute
        floatHBox = new HBox();
        floatToggleGroup = new ToggleGroup();

        floatLeftRadioButton = new RadioButton("Left");
        floatNeitherRadioButton = new RadioButton("Neither");
        floatRightRadioButton = new RadioButton("Right");
        floatLeftRadioButton.setToggleGroup(floatToggleGroup);
        floatNeitherRadioButton.setToggleGroup(floatToggleGroup);
        floatRightRadioButton.setToggleGroup(floatToggleGroup);

        if(imageComponentToEdit.getFloatAttribute().equalsIgnoreCase("Left"))
            floatLeftRadioButton.setSelected(true);
        else if(imageComponentToEdit.getFloatAttribute().equalsIgnoreCase("Right"))
            floatRightRadioButton.setSelected(true);
        else
            floatNeitherRadioButton.setSelected(true);

        floatHBox.getChildren().addAll(floatLabel, floatLeftRadioButton, floatNeitherRadioButton, floatRightRadioButton);

        //Set up area to enter Caption
        imageCaptionHBox = new HBox();
        imageCaptionTextField = new TextField(imageComponentToEdit.getCaption());
        imageCaptionHBox.getChildren().addAll(imageCaptionLabel, imageCaptionTextField);

        //Set up Confirm and Cancel Buttons
        confirmCancelHBox = new HBox();
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");

        confirmButton.setOnAction(event -> {
            imageComponent.setCaption(imageCaptionTextField.getText());
            boolean flagWidth = isNumeric(imageWidthTextField.getText());
            boolean flagHeight = isNumeric(imageHeightTextField.getText());
            if(flagWidth && flagHeight){
                imageComponent.setWidth(imageWidthTextField.getText());
                imageComponent.setHeight(imageHeightTextField.getText());

                if(floatLeftRadioButton.isSelected()){
                    imageComponent.setFloatAttribute("Left");
                }
                else if(floatRightRadioButton.isSelected()){
                    imageComponent.setFloatAttribute("Right");
                }
                else
                    imageComponent.setFloatAttribute("Neither");

                pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            }
            else{
                ErrorHandler.errorPopUp("Incorrect user input, either the width or height was not a number, or was less than equal to zero!!");
            }
        });

        cancelButton.setOnAction(event -> {
            this.close();
        });

        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);

        imageHBox.setAlignment(Pos.TOP_CENTER);
        confirmCancelHBox.setAlignment(Pos.BOTTOM_CENTER);
        floatHBox.setAlignment(Pos.CENTER);
        imageVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        imageAttributesHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        floatHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageCaptionHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        confirmCancelHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageVBox.getChildren().addAll(imageHBox, imageAttributesHBox, floatHBox, imageCaptionHBox);


        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(.5 * bounds.getMinX());
        this.setY(.5 * bounds.getMinY());
        this.setWidth(.5 * bounds.getWidth());
        this.setHeight(.5 * bounds.getHeight());

        scrollPane = new ScrollPane(imageVBox);
        scrollPane.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(confirmCancelHBox);
        borderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
        scene = new Scene(borderPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }



    public void updateSlideImage()
    {
        String imagePath = imageComponent.getImagePath() + "/" + imageComponent.getImageName();
        File file = new File(imagePath);
        try{
            //Get and set the banner image
            URL fileURL = file.toURI().toURL();
            Image bannerImage = new Image(fileURL.toExternalForm());
            imageView.setImage(bannerImage);

            // AND RESIZE IT
            double scaledWidth = 200;
            double perc = scaledWidth / bannerImage.getWidth();
            double scaledHeight = bannerImage.getHeight() * perc;
            imageView.setFitWidth(scaledWidth);
            imageView.setFitHeight(scaledHeight);
        }catch (Exception e) {
            // @todo - use Error handler to respond to missing image
            ErrorHandler.errorPopUp("The program was unable to find the image!");
            imagePath = "file:images/icons/DefaultStartSlide.png";
            file = new File(imagePath);
            try{
                URL fileURL = file.toURI().toURL();
                Image slideImage = new Image(fileURL.toExternalForm());
                imageView.setImage(slideImage);

                // AND RESIZE IT
                double scaledWidth = 200;
                double perc = scaledWidth / slideImage.getWidth();
                double scaledHeight = slideImage.getHeight() * perc;
                imageView.setFitWidth(scaledWidth);
                imageView.setFitHeight(scaledHeight);
            } catch(Exception e2){}
        }
    }

    private boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
            if(d <= 0)
                return false;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }


}
