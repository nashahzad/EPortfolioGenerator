package eportfoliogenerator.dialog;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.SlideShowComponent;
import eportfoliogenerator.model.Page;
import eportfoliogenerator.slideshowhelpers.Slide;
import eportfoliogenerator.slideshowhelpers.SlideShowModel;
import eportfoliogenerator.slideshowhelpers.SlideView;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Nauman on 11/21/2015.
 */
public class DialogSlideShowComponent extends Stage
{

    PageView pageView;

    //Data model
    SlideShowModel slideShowModel;

    //Some main GUI stuff and data model to use
    Scene scene;
    BorderPane borderPane;

    //Center of Screen
    VBox slideVBox;
    Button slideShowTitleButton;
    ScrollPane scrollPane;

    //Left Hand side
    VBox slideButtonVBox;
    Button addSlideButton;
    Button removeSlideButton;
    Button moveUpSlideButton;
    Button moveDownSlideButton;

    //Bottom of window
    HBox confirmCancelHBox;
    Button confirmButton;
    Button cancelButton;

    public DialogSlideShowComponent(PageView pageView)
    {
        this.pageView = pageView;
        Image image = new Image("file:./images/icons/eportfolio.gif");
        this.getIcons().add(image);
    }

    public void addSlideShow(Page page, ArrayList<RadioButton> slideShowComponentsList){
        slideShowModel = new SlideShowModel();

        //Set up center stuff
        slideVBox = new VBox();
        slideShowTitleButton = new Button();
        slideShowTitleButton.setPrefWidth(400);
        slideShowTitleButton.setStyle("-fx-background-color: #F2C53D");
        slideVBox.setAlignment(Pos.TOP_CENTER);

        slideVBox.getChildren().add(slideShowTitleButton);
        scrollPane = new ScrollPane(slideVBox);

        //Set up left hand side stuff
        slideButtonVBox = new VBox();
        slideButtonVBox.setAlignment(Pos.CENTER_LEFT);

        setUpButtons();
        slideButtonVBox.getChildren().addAll(addSlideButton, removeSlideButton, moveUpSlideButton, moveDownSlideButton);

        //Set up bottom bar of screen
        confirmCancelHBox = new HBox();
        confirmCancelHBox.setAlignment(Pos.CENTER_LEFT);
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");
        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);

        //Confirm and Cancel Button handlers
        confirmButton.setOnAction(event -> {
            SlideShowComponent slideShowComponent = new SlideShowComponent();
            slideShowComponent.setSlideShowTitle(slideShowModel.getTitle());
            boolean flag = false;
            if(slideShowModel.getSlides().size() > 0){
                for(Slide slide: slideShowModel.getSlides()){
                    ImageComponent imageComponent = new ImageComponent();
                    imageComponent.setImagePath(slide.getImagePath());
                    imageComponent.setImageName(slide.getImageFileName());

                    boolean flagWidth = isNumeric(slide.getWidth());
                    boolean flagHeight = isNumeric(slide.getHeight());

                    if(flagHeight && flagWidth){
                        imageComponent.setWidth(slide.getWidth());
                        imageComponent.setHeight(slide.getHeight());
                    }
                    //One of the width or height text fields did not have a number inputted into them
                    else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect user input, width or height text fields on one of your slides is not a number!");
                        alert.showAndWait();
                        flag = true;
                        break;
                    }
                    slideShowComponent.getImageSlides().add(imageComponent);
                }
            }

            if(flag){}
            else {
                page.getSlideShowComponents().add(slideShowComponent);
                slideShowComponentsList.add(new RadioButton(slideShowComponent.getSlideShowTitle()));
                pageView.reloadPageView();
                this.close();
            }
        });

        cancelButton.setOnAction(event -> {
            this.close();
        });


        //Put it all into a borderpane
        borderPane = new BorderPane();
        borderPane.setLeft(slideButtonVBox);
        borderPane.setCenter(slideVBox);
        borderPane.setBottom(confirmCancelHBox);

        buttonHandlers();

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(bounds.getMinX());
        this.setY(bounds.getMinY());
        this.setWidth(bounds.getWidth());
        this.setHeight(bounds.getHeight());

        scene = new Scene(borderPane);
        this.setScene(scene);
        this.show();
    }

    private void setUpButtons()
    {
        Image image = new Image("file:" + StartUpConstants.ICON_ADD_PAGE);
        addSlideButton = new Button();
        addSlideButton.setGraphic(new ImageView(image));
        addSlideButton.setTooltip(new Tooltip("Add Slide"));

        image = new Image("file:" + StartUpConstants.ICON_REMOVE_PAGE);
        removeSlideButton = new Button();
        removeSlideButton.setGraphic(new ImageView(image));
        removeSlideButton.setTooltip(new Tooltip("Remove Slide"));

        image = new Image("file:" + StartUpConstants.ICON_MOVE_UP);
        moveUpSlideButton = new Button();
        moveUpSlideButton.setGraphic(new ImageView(image));
        moveUpSlideButton.setTooltip(new Tooltip("Move Slide Up"));

        image = new Image("file:" + StartUpConstants.ICON_MOVE_DOWN);
        moveDownSlideButton = new Button();
        moveDownSlideButton.setGraphic(new ImageView(image));
        moveDownSlideButton.setTooltip(new Tooltip("Move Slide Down"));
    }

    private void buttonHandlers()
    {
        addSlideButton.setOnAction(event -> {
            Slide slide = new Slide();
            slideShowModel.getSlides().add(slide);
            SlideView slideView = new SlideView(slideShowModel, slide, this);
            slideVBox.getChildren().add(slideView);
            updateButtons();
        });

        removeSlideButton.setOnAction(event -> {
            slideShowModel.getSlides().remove(slideShowModel.getSelectedSlide());
            slideShowModel.setSelectedSlide(null);
            reloadSlideShow(slideShowModel);
            updateButtons();
        });

        moveUpSlideButton.setOnAction(event -> {
            slideShowModel.moveUpSlide();
            reloadSlideShow(slideShowModel);
        });

        moveDownSlideButton.setOnAction(event -> {
            slideShowModel.moveDownSlide();
            reloadSlideShow(slideShowModel);
        });

        slideShowTitleButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog(slideShowModel.getTitle());
            dialog.setTitle("SlideShow Title");
            dialog.setContentText("Please input a SlideShow title:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                slideShowModel.setTitle(result.get());
                slideShowTitleButton.setText(result.get());
            }
        });
    }

    //To Reload slide view VBox
    public void reloadSlideShow(SlideShowModel model){
        slideVBox.getChildren().clear();
        slideVBox.getChildren().add(slideShowTitleButton);
        for(Slide slide: model.getSlides()){
            SlideView slideView = new SlideView(model, slide, this);
            slideVBox.getChildren().add(slideView);
        }
    }

    public void updateButtons(){
        if(slideShowModel.getSlides().size() > 0){
            removeSlideButton.setDisable(false);
        }
        if(slideShowModel.getSlides().size() < 1){
            moveUpSlideButton.setDisable(true);
            moveDownSlideButton.setDisable(true);
        }

        if(slideShowModel.getSlides().size() > 1) {
            if (slideShowModel.getSelectedSlide() == slideShowModel.getSlides().get(slideShowModel.getSlides().size() - 1)) {
                moveUpSlideButton.setDisable(false);
                moveDownSlideButton.setDisable(true);
            } else if (slideShowModel.getSelectedSlide() == slideShowModel.getSlides().get(0)) {
                moveUpSlideButton.setDisable(true);
                moveDownSlideButton.setDisable(false);
            } else {
                moveUpSlideButton.setDisable(false);
                moveDownSlideButton.setDisable(false);
            }
        }

        else if(slideShowModel.getSlides().size() == 1){
            removeSlideButton.setDisable(false);
            moveUpSlideButton.setDisable(true);
            moveDownSlideButton.setDisable(true);
        }

        else{
            removeSlideButton.setDisable(true);
            moveUpSlideButton.setDisable(true);
            moveDownSlideButton.setDisable(true);
        }
    }

    private boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

}
