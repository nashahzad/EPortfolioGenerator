package eportfoliogenerator.view;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.file.EPortfolioFileManager;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
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

    //Object to handle visual view of Page Editor workspace
    PageView pageView;

    //Counter for giving random starting page titles
    int counter = 0;


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

        setUpSiteToolbar();

        setUpWebToolbar();

        //NEED TO WORK ON, EVENT HANDLERS FOR MAIN BUTTONS BESIDES PAGE EDITOR WORKSPACE BUTTONS
        eventHandlers();

        launchWindow();
    }

    /**
     * Setting up the file toolbar
     * Set up the buttons, icons, tooltips and the boxes they will be in
     */
    private void setUpFileToolbar()
    {
        fileToolBarHBox = new HBox();
        fileToolBarHBox.getStyleClass().add(StartUpConstants.CSS_FILE_TOOLBAR);

        //Method helper
        newEPortfolioButton = setUpButton(StartUpConstants.ICON_NEW_EPORTFOLIO, "New EPortfolio", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, false);
        loadEPortfolioButton = setUpButton(StartUpConstants.ICON_LOAD_EPORTFOLIO, "Load EPortfolio", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, false);
        saveEPortfolioButton = setUpButton(StartUpConstants.ICON_SAVE_EPORTFOLIO, "Save EPortfolio", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, true);
        saveAsEPortfolioButton = setUpButton(StartUpConstants.ICON_SAVE_AS_EPORTFOLIO, "Save EPortfolio As", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, true);
        exportEPortfolioButton = setUpButton(StartUpConstants.ICON_EXPORT_EPORTFOLIO, "Export EPortfolio", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, true);
        exitEPortfolioButton = setUpButton(StartUpConstants.ICON_EXIT_EPORTFOLIO, "Exit EPortfolio", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, false);

        fileToolBarHBox.getChildren().add(newEPortfolioButton);
        fileToolBarHBox.getChildren().add(loadEPortfolioButton);
        fileToolBarHBox.getChildren().add(saveEPortfolioButton);
        fileToolBarHBox.getChildren().add(saveAsEPortfolioButton);
        fileToolBarHBox.getChildren().add(exportEPortfolioButton);
        fileToolBarHBox.getChildren().add(exitEPortfolioButton);
    }

    /**
     * Setting up the site toolbar
     * Set up the buttons, icons, tooltips and the boxes they will be in
     */
    private void setUpSiteToolbar()
    {
        siteToolBarVBox = new VBox();
        siteToolBarVBox.getStyleClass().add(StartUpConstants.CSS_SITE_TOOLBAR);

        addPageButton = setUpButton(StartUpConstants.ICON_ADD_PAGE, "Add Page", StartUpConstants.CSS_SITE_TOOLBAR_BUTTON, true);
        removePageButton = setUpButton(StartUpConstants.ICON_REMOVE_PAGE, "Remove Page", StartUpConstants.CSS_SITE_TOOLBAR_BUTTON, true);
        selectPageButton = setUpButton(StartUpConstants.ICON_SELECT_PAGE, "Select Page", StartUpConstants.CSS_SITE_TOOLBAR_BUTTON, true);

        siteToolBarVBox.getChildren().add(addPageButton);
        siteToolBarVBox.getChildren().add(removePageButton);
        siteToolBarVBox.getChildren().add(selectPageButton);
    }

    /**
     * Setting up the web toolbar
     * Set up the buttons, icons, tooltips and the boxes they will be in
     */
    private void setUpWebToolbar()
    {
        webToolBarHBox = new HBox();
        webToolBarHBox.getStyleClass().add(StartUpConstants.CSS_FILE_TOOLBAR);

        webPageViewButton = setUpButton(StartUpConstants.ICON_VIEW, "Page-Editor/Web View", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, true);

        webToolBarHBox.getChildren().add(webPageViewButton);
        webToolBarHBox.setAlignment(Pos.BOTTOM_CENTER);
    }

    private Button setUpButton(String iconPath, String toolTip, String css, Boolean disabled)
    {
        Button buttonToEdit = new Button();
        buttonToEdit.setDisable(disabled);

        Image buttonImage = new Image("file:" + iconPath);
        buttonToEdit.setGraphic(new ImageView(buttonImage));

        buttonToEdit.setTooltip(new Tooltip(toolTip));

        buttonToEdit.getStyleClass().add(css);

        return buttonToEdit;
    }

    private void launchWindow()
    {
        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        //Set up the layout of the various toolbars on the main window with a BorderPane
        ePortfolioBorderPane = new BorderPane();
        ePortfolioBorderPane.setTop(fileToolBarHBox);
        ePortfolioBorderPane.setLeft(siteToolBarVBox);
        ePortfolioBorderPane.setBottom(webToolBarHBox);

        ePortfolioBorderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);

        //Set up the default workspace upon initial launch of the application
        Page page = new Page();
        page.setPageTitle("Page" + counter);
        counter++;
        model.getPages().add(page);
        pageView = new PageView(page, model);
        pageViewScrollPane = new ScrollPane(pageView);
        pageViewScrollPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
        pageViewScrollPane.setFitToHeight(true);
        pageViewScrollPane.setFitToWidth(true);
        ePortfolioBorderPane.setCenter(pageViewScrollPane);


        primaryScene = new Scene(ePortfolioBorderPane);
        primaryScene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    private void eventHandlers(){

    }


}
