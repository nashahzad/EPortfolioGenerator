package eportfoliogenerator.dialog;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.SlideShowComponent;
import eportfoliogenerator.model.Page;
import eportfoliogenerator.slideshowhelpers.Slide;
import eportfoliogenerator.slideshowhelpers.SlideShowModel;
import eportfoliogenerator.slideshowhelpers.SlideView;
import eportfoliogenerator.view.PageView;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.border.Border;
import java.util.ArrayList;

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

        slideVBox.getChildren().add(slideShowTitleButton);
        scrollPane = new ScrollPane(slideVBox);

        //Set up left hand side stuff
        slideButtonVBox = new VBox();

        setUpButtons();
        slideButtonVBox.getChildren().addAll(addSlideButton, removeSlideButton, moveUpSlideButton, moveDownSlideButton);

        //Set up bottom bar of screen
        confirmCancelHBox = new HBox();
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");
        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);


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
            SlideView slideView = new SlideView(slideShowModel, slide);
            slideVBox.getChildren().add(slideView);
        });
    }

    //To Reload slide view VBox
    public void reloadSlideShow(SlideShowModel model){
        slideVBox.getChildren().clear();
        for(Slide slide: model.getSlides()){
            SlideView slideView = new SlideView(model, slide);
        }
    }

}
