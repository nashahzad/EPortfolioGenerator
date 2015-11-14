package eportfoliogenerator.view;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.file.EPortfolioFileManager;
import eportfoliogenerator.model.EPortfolioModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Nauman on 11/14/2015.
 */
public class EPortfolioView
{
    //Main Window and UI will be here
    Stage primaryStage;
    Scene primaryScene;
    //Scene to switch with for page editor and web editor workspace
    Scene webScene;

    //BorderPane for different toolbars and workspaces
    BorderPane ePortfolioBorderPane;
    ScrollPane pageViewScrollPane;

    //Toolbar Boxes
    HBox fileToolBarHBox;
    VBox siteToolBarVBox;
    HBox webToolBarHBox;

    //File tool bar buttons
    Button newEPortfolioButton;
    Button loadEPortfolioButton;
    Button saveEPortfolioButton;
    Button saveAsEPortfolioButton;
    Button exportEPortfolioButton;
    Button exitEPortfolioButton;

    //Site tool bar buttons
    Button addPageButton;
    Button removePageButton;
    Button selectPageButton;

    //Page editor and web editor button
    Button webPageViewButton;

    //Model which only one instance of will exist and be used to store and load up pages from
    EPortfolioModel model;

    //Object to handle loading and saving of the EPortfolio
    EPortfolioFileManager fileManager;


    //Instantiate the main view with an already declared fileManager, set up the data model, and fileManager
    public EPortfolioView(EPortfolioFileManager fileManager)
    {
        //Create the fileManager instance and pass in this EPortfolioView so as to be able to easily access the model later on for saving/loading
        this.fileManager = fileManager;
        this.fileManager.setUi(this);

        //Data mangement model
        model = new EPortfolioModel(this);

    }

    public void startUI(Stage primaryStage)
    {
        this.primaryStage = primaryStage;

        setUpFileToolbar();
    }

    private void setUpFileToolbar()
    {
        fileToolBarHBox = new HBox();

        //Method helper
        setUpButton(newEPortfolioButton, StartUpConstants.ICON_NEW_EPORTFOLIO, "New EPortfolio", false);
        setUpButton(loadEPortfolioButton, StartUpConstants.ICON_LOAD_EPORTFOLIO, "Load EPortfolio", false);
        setUpButton(saveEPortfolioButton, StartUpConstants.ICON_SAVE_EPORTFOLIO, "Save EPortfolio", true);
        setUpButton(saveAsEPortfolioButton, StartUpConstants.ICON_SAVE_AS_EPORTFOLIO, "Save EPortfolio As", true);

    }

    private void setUpButton(Button buttonToEdit, String iconPath, String toolTip, Boolean disabled)
    {
        buttonToEdit = new Button();
        buttonToEdit.setDisable(disabled);

        Image buttonImage = new Image("file:" + iconPath);
        buttonToEdit.setGraphic(new ImageView(buttonImage));

        buttonToEdit.setTooltip(new Tooltip(toolTip));
    }


}
