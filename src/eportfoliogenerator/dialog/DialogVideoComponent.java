package eportfoliogenerator.dialog;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.VideoComponent;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/22/2015.
 */
public class DialogVideoComponent extends Stage
{
    EPortfolioView ui;

    //Current PageView we are working with
    PageView pageView;

    //Media/Video stuff
//    MediaView mediaView;
//    MediaPlayer mediaPlayer;
    Button pickVideoButton;
    VideoComponent videoComponent;

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

    //For Editing a Video Component
    String pathOriginal;
    String nameOriginal;



    public DialogVideoComponent(PageView pageView){
        this.pageView = pageView;
        Image image = new Image("file:./images/icons/eportfolio.gif");
        this.getIcons().add(image);
    }

    public void createVideoComponent(Page page, ArrayList<RadioButton> videoComponentsList, EPortfolioView ui){
        this.ui = ui;

        this.setTitle("Create Video Component");
        imageVBox = new VBox();
        videoComponent = new VideoComponent();

        imageHBox = new HBox();
        pickVideoButton = new Button(videoComponent.getVideoName());
        pickVideoButton.prefWidth(700);
        pickVideoButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
//        Media defaultImage = new Media("file:" + StartUpConstants.DEFAULT_VIDEO);
//        System.out.println(defaultImage.durationProperty().toString());
//        mediaPlayer = new MediaPlayer(defaultImage);
//        mediaView = new MediaView(mediaPlayer);
        imageHBox.getChildren().add(pickVideoButton);

        pickVideoButton.setOnAction(event -> {
            FileChooser imageFileChooser = new FileChooser();

            // SET THE STARTING DIRECTORY
            imageFileChooser.setInitialDirectory(new File(StartUpConstants.IMAGE_ICONS_FILE_PATH));

            // LET'S ONLY SEE IMAGE FILES
            FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
            imageFileChooser.getExtensionFilters().addAll(mp4Filter);

            // LET'S OPEN THE FILE CHOOSER
            File file = imageFileChooser.showOpenDialog(null);
            if (file != null) {
                String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
                String fileName = file.getName();
                videoComponent.setVideoPath(path);
                videoComponent.setVideoName(fileName);
                pickVideoButton.setText(videoComponent.getVideoName());
                //updateSlideImage();

            } else {
                // @todo provide error message for no files selected
                ErrorHandler.errorPopUp("The program was unable to find the video or a video was not selected!");
            }
        });

        //Set up area to input width and height of image
        imageAttributesHBox = new HBox();

        imageWidthTextField = new TextField();
        imageHeightTextField = new TextField();

        imageAttributesHBox.getChildren().addAll(imageWidthLabel, imageWidthTextField, imageHeightLabel, imageHeightTextField);

//        //Set up Area to take in float attribute
//        floatHBox = new HBox();
//        floatToggleGroup = new ToggleGroup();
//
//        floatLeftRadioButton = new RadioButton("Left");
//        floatNeitherRadioButton = new RadioButton("Neither");
//        floatRightRadioButton = new RadioButton("Right");
//        floatLeftRadioButton.setToggleGroup(floatToggleGroup);
//        floatNeitherRadioButton.setToggleGroup(floatToggleGroup);
//        floatRightRadioButton.setToggleGroup(floatToggleGroup);
//
//        floatHBox.getChildren().addAll(floatLabel, floatLeftRadioButton, floatNeitherRadioButton, floatRightRadioButton);

        //Set up area to enter Caption
        imageCaptionHBox = new HBox();
        imageCaptionTextField = new TextField();
        imageCaptionHBox.getChildren().addAll(imageCaptionLabel, imageCaptionTextField);

        //Set up Confirm and Cancel Buttons
        confirmCancelHBox = new HBox();
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");

        confirmButton.setOnAction(event -> {
            videoComponent.setCaption(imageCaptionTextField.getText());
            boolean flagWidth = isNumeric(imageWidthTextField.getText());
            boolean flagHeight = isNumeric(imageHeightTextField.getText());
            if (flagWidth && flagHeight) {
                videoComponent.setWidth(imageWidthTextField.getText());
                videoComponent.setHeight(imageHeightTextField.getText());

//                if(floatLeftRadioButton.isSelected()){
//                    imageComponent.setFloatAttribute("Left");
//                }
//                if(floatRightRadioButton.isSelected()){
//                    imageComponent.setFloatAttribute("Right");
//                }

                videoComponentsList.add(new RadioButton(videoComponent.getVideoName()));
                page.getAllComponents().add(videoComponent);
                pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            } else {
                ErrorHandler.errorPopUp("Incorrect user input, either the width or height was not a number, or a negative number or 0 was used!!");
            }
        });

        cancelButton.setOnAction(event -> {
            this.close();
        });

        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);

        imageHBox.setAlignment(Pos.TOP_CENTER);
        confirmCancelHBox.setAlignment(Pos.BOTTOM_CENTER);
//        floatHBox.setAlignment(Pos.CENTER);
        imageVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        imageAttributesHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
//        floatHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageCaptionHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        confirmCancelHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageVBox.getChildren().addAll(imageHBox, imageAttributesHBox, imageCaptionHBox);


        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(.25 * bounds.getMinX());
        this.setY(.5 * bounds.getMinY());
        this.setWidth(.5 * bounds.getWidth());
        this.setHeight(.25 * bounds.getHeight());

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

    public void editVideoComponent(VideoComponent videoComponentToEdit, EPortfolioView ui){
        this.ui = ui;

        this.setTitle("Edit Video Component");
        imageVBox = new VBox();
        videoComponent = videoComponentToEdit;
        pathOriginal = videoComponentToEdit.getVideoPath();
        nameOriginal = videoComponentToEdit.getVideoName();

        imageHBox = new HBox();
        pickVideoButton = new Button(videoComponent.getVideoName());
        pickVideoButton.prefWidth(700);
        pickVideoButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
//        Media defaultImage = new Media("file:" + StartUpConstants.DEFAULT_VIDEO);
//        System.out.println(defaultImage.durationProperty().toString());
//        mediaPlayer = new MediaPlayer(defaultImage);
//        mediaView = new MediaView(mediaPlayer);
        imageHBox.getChildren().add(pickVideoButton);

        pickVideoButton.setOnAction(event -> {
            FileChooser imageFileChooser = new FileChooser();

            // SET THE STARTING DIRECTORY
            imageFileChooser.setInitialDirectory(new File(StartUpConstants.IMAGE_ICONS_FILE_PATH));

            // LET'S ONLY SEE IMAGE FILES
            FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
            imageFileChooser.getExtensionFilters().addAll(mp4Filter);

            // LET'S OPEN THE FILE CHOOSER
            File file = imageFileChooser.showOpenDialog(null);
            if (file != null) {
                String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
                String fileName = file.getName();
                videoComponent.setVideoPath(path);
                videoComponent.setVideoName(fileName);
                pickVideoButton.setText(videoComponent.getVideoName());
                //updateSlideImage();

            } else {
                // @todo provide error message for no files selected
                ErrorHandler.errorPopUp("The program was unable to find the video or a video was not selected!");
            }
        });

        //Set up area to input width and height of image
        imageAttributesHBox = new HBox();

        imageWidthTextField = new TextField(videoComponentToEdit.getWidth());
        imageHeightTextField = new TextField(videoComponentToEdit.getHeight());

        imageAttributesHBox.getChildren().addAll(imageWidthLabel, imageWidthTextField, imageHeightLabel, imageHeightTextField);

//        //Set up Area to take in float attribute
//        floatHBox = new HBox();
//        floatToggleGroup = new ToggleGroup();
//
//        floatLeftRadioButton = new RadioButton("Left");
//        floatNeitherRadioButton = new RadioButton("Neither");
//        floatRightRadioButton = new RadioButton("Right");
//        floatLeftRadioButton.setToggleGroup(floatToggleGroup);
//        floatNeitherRadioButton.setToggleGroup(floatToggleGroup);
//        floatRightRadioButton.setToggleGroup(floatToggleGroup);
//
//        floatHBox.getChildren().addAll(floatLabel, floatLeftRadioButton, floatNeitherRadioButton, floatRightRadioButton);

        //Set up area to enter Caption
        imageCaptionHBox = new HBox();
        imageCaptionTextField = new TextField(videoComponentToEdit.getCaption());
        imageCaptionHBox.getChildren().addAll(imageCaptionLabel, imageCaptionTextField);

        //Set up Confirm and Cancel Buttons
        confirmCancelHBox = new HBox();
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");

        confirmButton.setOnAction(event -> {
            videoComponent.setCaption(imageCaptionTextField.getText());
            boolean flagWidth = isNumeric(imageWidthTextField.getText());
            boolean flagHeight = isNumeric(imageHeightTextField.getText());
            if (flagWidth && flagHeight) {
                videoComponent.setWidth(imageWidthTextField.getText());
                videoComponent.setHeight(imageHeightTextField.getText());

//                if(floatLeftRadioButton.isSelected()){
//                    imageComponent.setFloatAttribute("Left");
//                }
//                if(floatRightRadioButton.isSelected()){
//                    imageComponent.setFloatAttribute("Right");
//                }
                pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect user input, either the width or height was not a number, or a negative number or 0 was used!!");

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

                alert.showAndWait();
            }
        });

        cancelButton.setOnAction(event -> {
            videoComponent.setVideoName(nameOriginal);
            videoComponent.setVideoPath(pathOriginal);
            this.close();
        });

        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);

        imageHBox.setAlignment(Pos.TOP_CENTER);
        confirmCancelHBox.setAlignment(Pos.BOTTOM_CENTER);
//        floatHBox.setAlignment(Pos.CENTER);
        imageVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        imageAttributesHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
//        floatHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageCaptionHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        confirmCancelHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        imageVBox.getChildren().addAll(imageHBox, imageAttributesHBox, imageCaptionHBox);


        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(.25 * bounds.getMinX());
        this.setY(.5 * bounds.getMinY());
        this.setWidth(.5 * bounds.getWidth());
        this.setHeight(.25 * bounds.getHeight());

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



//    public void updateSlideImage()
//    {
//        String imagePath = videoComponent.getVideoPath() + "/" + videoComponent.getVideoName();
//        File file = new File(imagePath);
//        try{
//            //Get and set the banner image
//            URL fileURL = file.toURI().toURL();
//            Media bannerImage = new Media(fileURL.toExternalForm());
//            mediaPlayer = new MediaPlayer(bannerImage);
//            mediaView.setMediaPlayer(mediaPlayer);
//
//            // AND RESIZE IT
//            double scaledWidth = 200;
//            double perc = scaledWidth / bannerImage.getWidth();
//            double scaledHeight = bannerImage.getHeight() * perc;
//            mediaView.setFitWidth(scaledWidth);
//            mediaView.setFitHeight(scaledHeight);
//        }catch (Exception e) {
//            // @todo - use Error handler to respond to missing image
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Warning!");
//            alert.setHeaderText("");
//            alert.setContentText("The program was unable to find the image!");
//
//            alert.showAndWait();
//            imagePath = "file:images/icons/vid.mp4";
//            file = new File(imagePath);
//            try{
//                URL fileURL = file.toURI().toURL();
//                Media slideImage = new Media(fileURL.toExternalForm());
//                mediaPlayer = new MediaPlayer(slideImage);
//                mediaView.setMediaPlayer(mediaPlayer);
//
//                // AND RESIZE IT
//                double scaledWidth = 200;
//                double perc = scaledWidth / slideImage.getWidth();
//                double scaledHeight = slideImage.getHeight() * perc;
//                mediaView.setFitWidth(scaledWidth);
//                mediaView.setFitHeight(scaledHeight);
//            } catch(Exception e2){}
//        }
//    }

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
