package eportfoliogenerator.dialog;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.SlideShowComponent;
import eportfoliogenerator.error.ErrorHandler;
import eportfoliogenerator.model.Page;
import eportfoliogenerator.slideshowhelpers.Slide;
import eportfoliogenerator.slideshowhelpers.SlideShowModel;
import eportfoliogenerator.slideshowhelpers.SlideView;
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

    EPortfolioView ui;
    PageView pageView;

    //Data model
    SlideShowModel slideShowModel;
    SlideShowComponent slideShowComponent;

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

    public void addSlideShow(Page page, ArrayList<RadioButton> slideShowComponentsList, EPortfolioView ui){
        this.ui = ui;

        slideShowModel = new SlideShowModel();

        //Set up center stuff
        slideVBox = new VBox();
        slideShowTitleButton = new Button();
        slideShowTitleButton.setPrefWidth(400);
        slideShowTitleButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        slideVBox.setAlignment(Pos.TOP_CENTER);
        scrollPane = new ScrollPane(slideVBox);

        //Set up left hand side stuff
        slideButtonVBox = new VBox(7);
        slideButtonVBox.setAlignment(Pos.CENTER_LEFT);

        setUpButtons();
        slideButtonVBox.getChildren().addAll(addSlideButton, removeSlideButton, moveUpSlideButton, moveDownSlideButton);

        //Set up bottom bar of screen
        confirmCancelHBox = new HBox(7);
        confirmCancelHBox.getStyleClass().add(StartUpConstants.CSS_EDIT_COMPONENTS);
        confirmCancelHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmButton = new Button("Confirm");
        confirmButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
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
                    imageComponent.setCaption(slide.getCaption());

                    boolean flagWidth = isNumeric(slide.getWidth());
                    boolean flagHeight = isNumeric(slide.getHeight());

                    if(flagHeight && flagWidth){
                        imageComponent.setWidth(slide.getWidth());
                        imageComponent.setHeight(slide.getHeight());
                    }
                    //One of the width or height text fields did not have a number inputted into them
                    else{
                        ErrorHandler.errorPopUp("Incorrect user input, width or height text fields on one of your slides is not a number, or is less than equal to zero!");
                        flag = true;
                        break;
                    }
                    slideShowComponent.getImageSlides().add(imageComponent);
                }
            }

            if(flag){}
            else {
                page.getAllComponents().add(slideShowComponent);
                slideShowComponentsList.add(new RadioButton(slideShowComponent.getSlideShowTitle()));
                pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            }
        });

        cancelButton.setOnAction(event -> {
            this.close();
        });


        //Scroll pane for slides
        scrollPane = new ScrollPane(slideVBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        //Put it all into a borderpane
        borderPane = new BorderPane();
        borderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
        borderPane.setLeft(slideButtonVBox);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(confirmCancelHBox);
        borderPane.setTop(slideShowTitleButton);
        borderPane.setAlignment(slideShowTitleButton, Pos.TOP_CENTER);

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
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    public void editSlideShowComponent(SlideShowComponent slideShowComponentToEdit, EPortfolioView ui){
        this.ui = ui;

        slideShowModel = new SlideShowModel();
        slideShowComponent = slideShowComponentToEdit;

        slideShowModel.setTitle(slideShowComponentToEdit.getSlideShowTitle());
        for(ImageComponent imageComponent: slideShowComponentToEdit.getImageSlides()){
            Slide slide = new Slide();
            slide.setCaption(imageComponent.getCaption());
            slide.setHeight(imageComponent.getHeight());
            slide.setWidth(imageComponent.getWidth());
            slide.setImagePath(imageComponent.getImagePath());
            slide.setImageFileName(imageComponent.getImageName());

            slideShowModel.getSlides().add(slide);
        }

        //Set up center stuff
        slideVBox = new VBox();
        slideShowTitleButton = new Button(slideShowComponentToEdit.getSlideShowTitle());
        slideShowTitleButton.setPrefWidth(400);
        slideShowTitleButton.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
        slideVBox.setAlignment(Pos.TOP_CENTER);
        scrollPane = new ScrollPane(slideVBox);

        //Set up left hand side stuff
        slideButtonVBox = new VBox(7);
        slideButtonVBox.setAlignment(Pos.CENTER_LEFT);

        setUpButtons();
        slideButtonVBox.getChildren().addAll(addSlideButton, removeSlideButton, moveUpSlideButton, moveDownSlideButton);

        //Set up bottom bar of screen
        confirmCancelHBox = new HBox(7);
        confirmCancelHBox.getStyleClass().add(StartUpConstants.CSS_EDIT_COMPONENTS);
        confirmCancelHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmButton = new Button("Confirm");
        confirmButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);

        //Confirm and Cancel Button handlers
        confirmButton.setOnAction(event -> {
            boolean flag = false;
            ArrayList<ImageComponent> imageComponents = new ArrayList<ImageComponent>();
            if(slideShowModel.getSlides().size() > 0){
                for(Slide slide: slideShowModel.getSlides()){
                    ImageComponent imageComponent = new ImageComponent();
                    imageComponent.setImagePath(slide.getImagePath());
                    imageComponent.setImageName(slide.getImageFileName());
                    imageComponent.setCaption(slide.getCaption());

                    boolean flagWidth = isNumeric(slide.getWidth());
                    boolean flagHeight = isNumeric(slide.getHeight());

                    if(flagHeight && flagWidth){
                        imageComponent.setWidth(slide.getWidth());
                        imageComponent.setHeight(slide.getHeight());
                    }
                    //One of the width or height text fields did not have a number inputted into them
                    else{
                        ErrorHandler.errorPopUp("Incorrect user input, width or height text fields on one of your slides is not a number, or is less than equal to zero!");
                        flag = true;
                        break;
                    }
                    imageComponents.add(imageComponent);
                }
            }

            if(flag){}
            else {
                slideShowComponent.setSlideShowTitle(slideShowModel.getTitle());
                slideShowComponent.setImageSlides(imageComponents);
                pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            }
        });

        cancelButton.setOnAction(event -> {
            this.close();
        });


        //Scroll pane for slides
        scrollPane = new ScrollPane(slideVBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        //Put it all into a borderpane
        borderPane = new BorderPane();
        borderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
        borderPane.setLeft(slideButtonVBox);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(confirmCancelHBox);
        borderPane.setTop(slideShowTitleButton);
        borderPane.setAlignment(slideShowTitleButton, Pos.TOP_CENTER);

        buttonHandlers();

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(bounds.getMinX());
        this.setY(bounds.getMinY());
        this.setWidth(bounds.getWidth());
        this.setHeight(bounds.getHeight());

        //Reload
        reloadSlideShow(slideShowModel);

        scene = new Scene(borderPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
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
            slideView.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
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
            dialog.getDialogPane().setPrefWidth(800);

            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:./images/icons/eportfolio.gif"));
            stage.setResizable(true);
            stage.setScene(new Scene(new ScrollPane(dialog.getDialogPane())));

            DialogPane alertDialogPane = dialog.getDialogPane();

            alertDialogPane.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
            alertDialogPane.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);

            //CSS to buttons added after alert does its getButtonTypes method
            ButtonBar buttonBar = (ButtonBar)dialog.getDialogPane().lookup(".button-bar");
            buttonBar.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
            buttonBar.getButtons().forEach(b -> b.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS));

            //Content text
            alertDialogPane.lookup(".content.label").getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);

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
            slideView.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
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

        if(slideShowModel.getSelectedSlide() == null){
            moveUpSlideButton.setDisable(true);
            moveDownSlideButton.setDisable(true);
            removeSlideButton.setDisable(true);
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
