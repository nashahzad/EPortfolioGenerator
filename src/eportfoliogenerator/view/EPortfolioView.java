package eportfoliogenerator.view;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.file.EPortfolioFileManager;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;
import eportfoliogenerator.web.GenerateDirectories;
import eportfoliogenerator.web.HTMLGenerator;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    int webCounter = 0;

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
        model = new EPortfolioModel();

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
        saveAsEPortfolioButton = setUpButton(StartUpConstants.ICON_SAVE_AS_EPORTFOLIO, "Save EPortfolio As", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, false);
        exportEPortfolioButton = setUpButton(StartUpConstants.ICON_EXPORT_EPORTFOLIO, "Export EPortfolio", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, true);
        exitEPortfolioButton = setUpButton(StartUpConstants.ICON_EXIT_EPORTFOLIO, "Exit EPortfolio", StartUpConstants.CSS_FILE_TOOLBAR_BUTTON, false);

        fileToolBarHBox.getChildren().addAll(newEPortfolioButton, loadEPortfolioButton, saveEPortfolioButton, saveAsEPortfolioButton, exportEPortfolioButton, exitEPortfolioButton);
    }

    /**
     * Setting up the site toolbar
     * Set up the buttons, icons, tooltips and the boxes they will be in
     */
    private void setUpSiteToolbar()
    {
        siteToolBarVBox = new VBox();
        siteToolBarVBox.getStyleClass().add(StartUpConstants.CSS_SITE_TOOLBAR);

        addPageButton = setUpButton(StartUpConstants.ICON_ADD_PAGE, "Add Page", StartUpConstants.CSS_SITE_TOOLBAR_BUTTON, false);
        removePageButton = setUpButton(StartUpConstants.ICON_REMOVE_PAGE, "Remove Page", StartUpConstants.CSS_SITE_TOOLBAR_BUTTON, true);
        selectPageButton = setUpButton(StartUpConstants.ICON_SELECT_PAGE, "Select Page", StartUpConstants.CSS_SITE_TOOLBAR_BUTTON, false);

        siteToolBarVBox.getChildren().addAll(addPageButton, removePageButton, selectPageButton);
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
        //ePortfolioBorderPane.setLeft(siteToolBarVBox);
        ePortfolioBorderPane.setBottom(webToolBarHBox);

        ePortfolioBorderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);

        //Set up the default workspace upon initial launch of the application
//        Page page = new Page();
//        page.setPageTitle("Page" + counter);
//        counter++;
//        model.getPages().add(page);
//        model.setSelectedPage(page);
//        pageView = new PageView(page, model, this);
//        pageViewScrollPane = new ScrollPane(pageView);
//        pageViewScrollPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
//        pageViewScrollPane.setFitToHeight(true);
//        pageViewScrollPane.setFitToWidth(true);
//        ePortfolioBorderPane.setCenter(pageViewScrollPane);


        primaryScene = new Scene(ePortfolioBorderPane);
        primaryScene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    private void eventHandlers(){

        newEPortfolioButton.setOnAction(event -> {
            try{
                //if not already saved, prompt user to save, if not hit cancel go through with new session
                if(fileManager.promptToSave(model, saveEPortfolioButton, 0)){
                    model.reset();
                    Page page = new Page();
                    page.setPageTitle("Page" + counter);
                    counter++;
                    model.getPages().add(page);
                    model.setSelectedPage(page);
                    pageView = new PageView(page, model, this);
                    pageViewScrollPane = new ScrollPane(pageView);
                    pageViewScrollPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
                    pageViewScrollPane.setFitToHeight(true);
                    pageViewScrollPane.setFitToWidth(true);
                    ePortfolioBorderPane.setCenter(pageViewScrollPane);
                    ePortfolioBorderPane.setLeft(siteToolBarVBox);
                    primaryStage.setTitle("");
                    updateToolbarControls(false, model);
                }
            }catch (Exception ex) {}
        });

        saveEPortfolioButton.setOnAction(event -> {
            try {
                fileManager.handleSaveEPortfolio(model);
            } catch (Exception ex) {
            }
        });

        saveAsEPortfolioButton.setOnAction(event -> {
            try{
                fileManager.handleSaveAsEPortfolio(model);
            }catch(Exception ex) {}
        });

        loadEPortfolioButton.setOnAction(event -> {
            try {
                if(fileManager.promptToSave(model, saveEPortfolioButton, 0)) {
                    model = fileManager.handleLoadEPortfolio(this.model);
                    ePortfolioBorderPane.setLeft(siteToolBarVBox);
                }
            } catch (Exception ex) {
                System.out.println("Problem with reading serializiable object.");
            }
        });

        addPageButton.setOnAction(event -> {
            Page page = new Page();
            page.setPageTitle("Page" + counter);
            counter++;
            model.getPages().add(page);
            model.setSelectedPage(page);
            pageView = new PageView(page, model, this);
            pageViewScrollPane = new ScrollPane(pageView);
            pageViewScrollPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
            pageViewScrollPane.setFitToHeight(true);
            pageViewScrollPane.setFitToWidth(true);
            ePortfolioBorderPane.setCenter(pageViewScrollPane);
            updateToolbarControls(false);
        });

        selectPageButton.setOnAction(event -> {
            List<String> choices = new ArrayList<>();
            for(Page page: model.getPages()){
                if(!page.getPageTitle().equalsIgnoreCase(model.getSelectedPage().getPageTitle()))
                    choices.add(page.getPageTitle());
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>(model.getSelectedPage().getPageTitle(), choices);
            dialog.setTitle("Select Page Box");
            dialog.setContentText("Choose a page:");
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
            if (result.isPresent()){
                Page page = model.getSpecificPage(result.get());
                if(page == null){}
                else{
                    model.setSelectedPage(page);
                    pageView = new PageView(page, model, this);
                    pageView.reloadPageView();
                    pageViewScrollPane = new ScrollPane(pageView);
                    pageViewScrollPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
                    pageViewScrollPane.setFitToHeight(true);
                    pageViewScrollPane.setFitToWidth(true);
                    ePortfolioBorderPane.setCenter(pageViewScrollPane);
                }
            }
        });

        removePageButton.setOnAction(event -> {
            for(int i = 0; i < model.getPages().size(); i++){
                if(model.getPages().get(i) == model.getSelectedPage()){
                    model.getPages().remove(i);
                    if(model.getPages().size() > 0){
                        model.setSelectedPage(model.getPages().get(0));
                        pageView = new PageView(model.getSelectedPage(), model, this);
                        pageView.reloadPageView();
                        pageViewScrollPane = new ScrollPane(pageView);
                        pageViewScrollPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
                        pageViewScrollPane.setFitToHeight(true);
                        pageViewScrollPane.setFitToWidth(true);
                        ePortfolioBorderPane.setCenter(pageViewScrollPane);
                        updateToolbarControls(false, model);
                    }
                    else{
                        ePortfolioBorderPane.setCenter(null);
                        removePageButton.setDisable(true);
                        updateToolbarControls(false, model);
                    }
                }
            }
        });

        exitEPortfolioButton.setOnAction(event -> {
            try{
            if(fileManager.promptToSave(model, saveEPortfolioButton, -1)){
                System.exit(0);
            }} catch (Exception ex) {}
        });

        webPageViewButton.setOnAction(event -> {
            GenerateDirectories htmlGenerator = new GenerateDirectories(model);
            htmlGenerator.createDirectories();
            try{
                htmlGenerator.copyImageFiles();
            }catch(IOException ex){
                System.out.println("Exception thrown in copying image files.");
            }

            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load("http://www.google.com");
            BorderPane borderPane = new BorderPane();

            borderPane.setCenter(browser);
            borderPane.setBottom(webToolBarHBox);

            webScene = new Scene(borderPane);
            webScene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);

            if(webCounter % 2 == 0){
                primaryStage.setScene(webScene);
            }
            else{
                ePortfolioBorderPane.setBottom(webToolBarHBox);
                primaryStage.setScene(primaryScene);
            }

            webCounter++;
        });
    }

    /**
     * Updates the enabled/disabled status of all toolbar
     * buttons.
     */
    public void updateToolbarControls(boolean saved){
        saveEPortfolioButton.setDisable(saved);

        if(model.getPages().size() > 0){
            removePageButton.setDisable(false);
            webPageViewButton.setDisable(false);
        }
        else{
            removePageButton.setDisable(true);
            webPageViewButton.setDisable(true);
        }
        if(model.getPages().size() > 1){
            selectPageButton.setDisable(false);
        }
        else{
            selectPageButton.setDisable(true);
        }
    }

    /**
     * Updates the enabled/disabled status of all toolbar
     * buttons.
     */
    public void updateToolbarControls(boolean saved, EPortfolioModel model){
        saveEPortfolioButton.setDisable(saved);

        if(model.getPages().size() > 0){
            removePageButton.setDisable(false);
            webPageViewButton.setDisable(false);
        }
        else{
            removePageButton.setDisable(true);
            webPageViewButton.setDisable(true);
        }
        if(model.getPages().size() > 1){
            selectPageButton.setDisable(false);
        }
        else{
            selectPageButton.setDisable(true);
        }
    }

    //Method for just updating the buttons for saving
    public void updateSaveButtons(){
        saveEPortfolioButton.setDisable(false);
    }

    //Method to update the PageView with starting page
    public void updatePageView(EPortfolioModel model)
    {
        if(model.getPages().size() > 0)
        {
            pageView = new PageView(model.getPages().get(0), model, this);
            model.setSelectedPage(model.getPages().get(0));
            pageViewScrollPane = new ScrollPane(pageView);
            ePortfolioBorderPane.setCenter(pageView);
        }

        primaryStage.setTitle(model.getePortfolioTitle());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ScrollPane getPageViewScrollPane() {
        return pageViewScrollPane;
    }

    public void setPageViewScrollPane(ScrollPane pageViewScrollPane) {
        this.pageViewScrollPane = pageViewScrollPane;
    }

    public BorderPane getePortfolioBorderPane() {
        return ePortfolioBorderPane;
    }

    public void setePortfolioBorderPane(BorderPane ePortfolioBorderPane) {
        this.ePortfolioBorderPane = ePortfolioBorderPane;
    }

    public PageView getPageView() {
        return pageView;
    }

    public void setPageView(PageView pageView) {
        this.pageView = pageView;
    }
}
