package eportfoliogenerator.dialog;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.TextComponent;
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
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Nauman on 11/20/2015.
 */
public class DialogTextComponents extends Stage
{
    EPortfolioView ui;

    //New Text Component to construct for editing purposes
    TextComponent textComponentConstruct = new TextComponent();

    //PageView we are working with
    PageView pageView;

    Scene scene;
    ScrollPane scrollPane;

    //Components for getting paragraph input
    VBox paragraphVBox;

    HBox paragraphHBox;
    Label paragraphInputLabel = new Label("Input paragraph:");
    TextArea paragraphTextArea;

    HBox paragraphSizeHBox;
    Label paragraphSizeLabel = new Label("Input font size:");
    TextField paragraphSizeTextField;

    HBox paragraphButtonsHBox;
    Button confirmParagraphButton;
    Button cancelParagraphButton;

    //Page Font label and buttons
    HBox pageFontHBox;
    Label pageFontLabel = new Label("Page Font: ");
    ToggleGroup pageFontToggleGroup;
    RadioButton pageFontOneRadioButton;
    RadioButton pageFontTwoRadioButton;
    RadioButton pageFontThreeRadioButton;
    RadioButton pageFontFourRadioButton;
    RadioButton pageFontFiveRadioButton;

    //Stuff for Header
    Label headerInputLabel = new Label ("Input header");
    TextField headerTextField;

    //For List
    ToggleGroup listToggleGroup;
    Button addTextButton;
    Button removeTextButton;
    Button moveTextUpButton;
    Button moveTextDownButton;
    BorderPane listBorderPane;
    VBox listButtonsVBox;
    VBox listTextComponents;
    ArrayList<RadioButton> listRadioButtons = new ArrayList<RadioButton>();
    Button listFontSizeButton;
    Button listFontStyleButton;
    TextComponent listTextComponent;

    public DialogTextComponents(PageView pageView){
        this.pageView = pageView;
        Image image = new Image("file:./images/icons/eportfolio.gif");
        this.getIcons().add(image);
    }

//    public void promptForType(Page page, ArrayList<RadioButton> textComponentsList, EPortfolioView ui)
//    {
//        this.ui = ui;
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Select Type of Text Component");
//        alert.setContentText("Choose what kind of text component you want:");
//
//        ButtonType buttonTypeOne = new ButtonType("Header");
//        ButtonType buttonTypeTwo = new ButtonType("Paragraph");
//        ButtonType buttonTypeThree = new ButtonType("List");
//        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
//
//        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image("file:./images/icons/eportfolio.gif"));
//
//        DialogPane alertDialogPane = alert.getDialogPane();
//
//        alertDialogPane.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
//        alertDialogPane.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
//
//        //CSS to buttons added after alert does its getButtonTypes method
//        ButtonBar buttonBar = (ButtonBar)alert.getDialogPane().lookup(".button-bar");
//        buttonBar.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
//        buttonBar.getButtons().forEach(b -> b.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS));
//
//        //Content text
//        alertDialogPane.lookup(".content.label").getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == buttonTypeOne){
//            // ... user chose "Header"
//            getHeader(page, textComponentsList);
//        } else if (result.get() == buttonTypeTwo) {
//            // ... user chose "Paragraph"
//            getParagraph(page, textComponentsList);
//        } else if (result.get() == buttonTypeThree) {
//            // ... user chose "List"
//            getList(page, textComponentsList);
//        } else {
//
//        }
//
//    }

    public void getHeader(Page page, ArrayList<RadioButton> textComponentsList, EPortfolioView ui){
        this.ui = ui;
        this.setTitle("Header Dialog");
        paragraphVBox = new VBox();

        paragraphHBox = new HBox();

        paragraphHBox.getChildren().add(headerInputLabel);

        headerTextField = new TextField();
        headerTextField.setPrefWidth(800);
        paragraphHBox.getChildren().add(headerTextField);

        //Page Fonts
        pageFontHBox = new HBox(8);
        pageFontToggleGroup = new ToggleGroup();

        pageFontOneRadioButton = new RadioButton("Slabo");
        pageFontTwoRadioButton = new RadioButton("Sans Pro");
        pageFontThreeRadioButton = new RadioButton("Serif");
        pageFontFourRadioButton = new RadioButton("Hind");
        pageFontFiveRadioButton = new RadioButton("Cantarell");

        //Add all radio buttons to same Toggle Group so that only one can be toggled on at a time
        pageFontOneRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontOneRadioButton.setSelected(true); //Start out with default one on toggle
        pageFontTwoRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontThreeRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFourRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFiveRadioButton.setToggleGroup(pageFontToggleGroup);

        //Add page font buttons into HBOX
        pageFontHBox.getChildren().add(pageFontLabel);
        pageFontHBox.getChildren().addAll(pageFontOneRadioButton, pageFontTwoRadioButton, pageFontThreeRadioButton, pageFontFourRadioButton, pageFontFiveRadioButton);

        //Paragraph font size buttons
        paragraphSizeHBox = new HBox();
        paragraphSizeHBox.getChildren().add(paragraphSizeLabel);

        paragraphSizeTextField = new TextField("12");
        paragraphSizeHBox.getChildren().add(paragraphSizeTextField);

        paragraphButtonsHBox = new HBox();
        paragraphButtonsHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        paragraphButtonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmParagraphButton = new Button("Confirm");
        cancelParagraphButton = new Button("Cancel");
        paragraphButtonsHBox.getChildren().addAll(confirmParagraphButton, cancelParagraphButton);

        confirmParagraphButton.setOnAction(event -> {
            TextComponent textComponent = new TextComponent();
            textComponent.setParagraphOrHeader(headerTextField.getText());
            textComponent.setTextType("Header");
            textComponent.setTextFont(getTextComponentFont());
            if(isNumeric(paragraphSizeTextField.getText())) {
                textComponent.setTextSize(paragraphSizeTextField.getText());
                page.getAllComponents().add(textComponent);

//                RadioButton radioButton = new RadioButton();
//
//                textComponentsList.add(new RadioButton("header"));
                this.pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            }
            else{
                ErrorHandler.errorPopUp("Incorrect user input for font size");
            }
        });

        cancelParagraphButton.setOnAction(event -> {
            this.close();
        });

        paragraphVBox.getChildren().add(paragraphHBox);
        paragraphVBox.getChildren().add(pageFontHBox);
        paragraphVBox.getChildren().add(paragraphSizeHBox);
        paragraphVBox.getChildren().add(paragraphButtonsHBox);
        paragraphVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        scrollPane = new ScrollPane(paragraphVBox);
        scene = new Scene(scrollPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    //Method for editing header text component
    public void editHeader(TextComponent textComponentToEdit, EPortfolioView ui){
        this.ui = ui;

        this.setTitle("Header Dialog");
        paragraphVBox = new VBox();

        paragraphHBox = new HBox();

        paragraphHBox.getChildren().add(headerInputLabel);

        headerTextField = new TextField(textComponentToEdit.getParagraphOrHeader());
        headerTextField.setPrefWidth(800);
        paragraphHBox.getChildren().add(headerTextField);

        //Page Fonts
        pageFontHBox = new HBox(8);
        pageFontToggleGroup = new ToggleGroup();

        pageFontOneRadioButton = new RadioButton("Slabo");
        pageFontTwoRadioButton = new RadioButton("Sans Pro");
        pageFontThreeRadioButton = new RadioButton("Serif");
        pageFontFourRadioButton = new RadioButton("Hind");
        pageFontFiveRadioButton = new RadioButton("Cantarell");

        //Add all radio buttons to same Toggle Group so that only one can be toggled on at a time
        pageFontOneRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontTwoRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontThreeRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFourRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFiveRadioButton.setToggleGroup(pageFontToggleGroup);

        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Slabo"))
            pageFontOneRadioButton.setSelected(true);
        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Sans Pro"))
            pageFontTwoRadioButton.setSelected(true);
        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Serif"))
            pageFontThreeRadioButton.setSelected(true);
        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Hind"))
            pageFontFourRadioButton.setSelected(true);
        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Cantarell"))
            pageFontFiveRadioButton.setSelected(true);

        //Add page font buttons into HBOX
        pageFontHBox.getChildren().add(pageFontLabel);
        pageFontHBox.getChildren().addAll(pageFontOneRadioButton, pageFontTwoRadioButton, pageFontThreeRadioButton, pageFontFourRadioButton, pageFontFiveRadioButton);

        //Paragraph font size buttons
        paragraphSizeHBox = new HBox();
        paragraphSizeHBox.getChildren().add(paragraphSizeLabel);

        paragraphSizeTextField = new TextField(textComponentToEdit.getTextSize());
        paragraphSizeHBox.getChildren().add(paragraphSizeTextField);

        paragraphButtonsHBox = new HBox();
        paragraphButtonsHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        paragraphButtonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmParagraphButton = new Button("Confirm");
        cancelParagraphButton = new Button("Cancel");
        paragraphButtonsHBox.getChildren().addAll(confirmParagraphButton, cancelParagraphButton);

        confirmParagraphButton.setOnAction(event -> {
            if(isNumeric(paragraphSizeTextField.getText())) {
                textComponentToEdit.setParagraphOrHeader(headerTextField.getText());
                textComponentToEdit.setTextType("Header");
                textComponentToEdit.setTextFont(getTextComponentFont());
                textComponentToEdit.setTextSize(paragraphSizeTextField.getText());

                this.pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            }
            else{
                ErrorHandler.errorPopUp("Incorrect user input for font size");
            }
        });

        cancelParagraphButton.setOnAction(event -> {
            this.close();
        });

        paragraphVBox.getChildren().add(paragraphHBox);
        paragraphVBox.getChildren().add(pageFontHBox);
        paragraphVBox.getChildren().add(paragraphSizeHBox);
        paragraphVBox.getChildren().add(paragraphButtonsHBox);
        paragraphVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        scrollPane = new ScrollPane(paragraphVBox);
        scene = new Scene(scrollPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    public void getParagraph(Page page, ArrayList<RadioButton> textComponentsList, EPortfolioView ui)
    {
        this.ui = ui;
        this.setTitle("Paragraph Dialog");
        paragraphVBox = new VBox();

        paragraphHBox = new HBox();

        paragraphHBox.getChildren().add(paragraphInputLabel);

        paragraphTextArea = new TextArea();
        paragraphHBox.getChildren().add(paragraphTextArea);

        //Page Fonts
        pageFontHBox = new HBox(8);
        pageFontToggleGroup = new ToggleGroup();

        pageFontOneRadioButton = new RadioButton("Slabo");
        pageFontTwoRadioButton = new RadioButton("Sans Pro");
        pageFontThreeRadioButton = new RadioButton("Serif");
        pageFontFourRadioButton = new RadioButton("Hind");
        pageFontFiveRadioButton = new RadioButton("Cantarell");

        //Add all radio buttons to same Toggle Group so that only one can be toggled on at a time
        pageFontOneRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontOneRadioButton.setSelected(true); //Start out with default one on toggle
        pageFontTwoRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontThreeRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFourRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFiveRadioButton.setToggleGroup(pageFontToggleGroup);

        //Add page font buttons into HBOX
        pageFontHBox.getChildren().add(pageFontLabel);
        pageFontHBox.getChildren().addAll(pageFontOneRadioButton, pageFontTwoRadioButton, pageFontThreeRadioButton, pageFontFourRadioButton, pageFontFiveRadioButton);

        //Paragraph font size buttons
        paragraphSizeHBox = new HBox();
        paragraphSizeHBox.getChildren().add(paragraphSizeLabel);

        paragraphSizeTextField = new TextField("12");
        paragraphSizeHBox.getChildren().add(paragraphSizeTextField);

        paragraphButtonsHBox = new HBox();
        paragraphButtonsHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        paragraphButtonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmParagraphButton = new Button("Confirm");
        cancelParagraphButton = new Button("Cancel");
        paragraphButtonsHBox.getChildren().addAll(confirmParagraphButton, cancelParagraphButton);

        confirmParagraphButton.setOnAction(event -> {
            TextComponent textComponent = new TextComponent();
            textComponent.setParagraphOrHeader(paragraphTextArea.getText());
            textComponent.setTextType("Paragraph");
            textComponent.setTextFont(getTextComponentFont());
            if(isNumeric(paragraphSizeTextField.getText())) {
                textComponent.setTextSize(paragraphSizeTextField.getText());
                page.getAllComponents().add(textComponent);

//                textComponentsList.add(new RadioButton("paragraph"));
                this.pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            }
            else{
                ErrorHandler.errorPopUp("Incorrect user input for font size");
            }
        });

        cancelParagraphButton.setOnAction(event -> {
            this.close();
        });

        paragraphVBox.getChildren().add(paragraphHBox);
        paragraphVBox.getChildren().add(pageFontHBox);
        paragraphVBox.getChildren().add(paragraphSizeHBox);
        paragraphVBox.getChildren().add(paragraphButtonsHBox);
        paragraphVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        scrollPane = new ScrollPane(paragraphVBox);
        scene = new Scene(scrollPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    public void editParagraph(TextComponent textComponentToEdit, EPortfolioView ui){
        this.ui = ui;

        this.setTitle("Paragraph Dialog");
        paragraphVBox = new VBox();

        paragraphHBox = new HBox();

        paragraphHBox.getChildren().add(paragraphInputLabel);

        paragraphTextArea = new TextArea(textComponentToEdit.getParagraphOrHeader());
        paragraphHBox.getChildren().add(paragraphTextArea);

        //Page Fonts
        pageFontHBox = new HBox(8);
        pageFontToggleGroup = new ToggleGroup();

        pageFontOneRadioButton = new RadioButton("Slabo");
        pageFontTwoRadioButton = new RadioButton("Sans Pro");
        pageFontThreeRadioButton = new RadioButton("Serif");
        pageFontFourRadioButton = new RadioButton("Hind");
        pageFontFiveRadioButton = new RadioButton("Cantarell");

        //Add all radio buttons to same Toggle Group so that only one can be toggled on at a time
        pageFontOneRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontTwoRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontThreeRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFourRadioButton.setToggleGroup(pageFontToggleGroup);
        pageFontFiveRadioButton.setToggleGroup(pageFontToggleGroup);

        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Slabo"))
            pageFontOneRadioButton.setSelected(true);
        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Sans Pro"))
            pageFontTwoRadioButton.setSelected(true);
        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Serif"))
            pageFontThreeRadioButton.setSelected(true);
        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Hind"))
            pageFontFourRadioButton.setSelected(true);
        if(textComponentToEdit.getTextFont().equalsIgnoreCase("Cantarell"))
            pageFontFiveRadioButton.setSelected(true);

        //Add page font buttons into HBOX
        pageFontHBox.getChildren().add(pageFontLabel);
        pageFontHBox.getChildren().addAll(pageFontOneRadioButton, pageFontTwoRadioButton, pageFontThreeRadioButton, pageFontFourRadioButton, pageFontFiveRadioButton);

        //Paragraph font size buttons
        paragraphSizeHBox = new HBox();
        paragraphSizeHBox.getChildren().add(paragraphSizeLabel);

        paragraphSizeTextField = new TextField(textComponentToEdit.getTextSize());
        paragraphSizeHBox.getChildren().add(paragraphSizeTextField);

        paragraphButtonsHBox = new HBox();
        paragraphButtonsHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        paragraphButtonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmParagraphButton = new Button("Confirm");
        cancelParagraphButton = new Button("Cancel");
        paragraphButtonsHBox.getChildren().addAll(confirmParagraphButton, cancelParagraphButton);

        confirmParagraphButton.setOnAction(event -> {
            if(isNumeric(paragraphSizeTextField.getText())) {
                textComponentToEdit.setParagraphOrHeader(paragraphTextArea.getText());
                textComponentToEdit.setTextType("Paragraph");
                textComponentToEdit.setTextFont(getTextComponentFont());
                textComponentToEdit.setTextSize(paragraphSizeTextField.getText());

                this.pageView.reloadPageView();
                ui.updateSaveButtons();
                this.close();
            }
            else{
                ErrorHandler.errorPopUp("Incorrect user input for font size");
            }
        });

        cancelParagraphButton.setOnAction(event -> {
            this.close();
        });

        paragraphVBox.getChildren().add(paragraphHBox);
        paragraphVBox.getChildren().add(pageFontHBox);
        paragraphVBox.getChildren().add(paragraphSizeHBox);
        paragraphVBox.getChildren().add(paragraphButtonsHBox);
        paragraphVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        scrollPane = new ScrollPane(paragraphVBox);
        scene = new Scene(scrollPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    public void getList(Page page, ArrayList<RadioButton> textComponentsList, EPortfolioView ui){
        this.ui = ui;
        this.setTitle("List Dialog");
        listBorderPane = new BorderPane();
        listTextComponent = new TextComponent();

        //Left hand side
        listButtonsVBox = new VBox();
        listButtonsVBox.setAlignment(Pos.CENTER_LEFT);
        listButtonsVBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        addTextButton = new Button();
        removeTextButton = new Button();
        moveTextUpButton = new Button();
        moveTextDownButton = new Button();

        //Add and Remove Buttons
        Image image = new Image("file:" + StartUpConstants.ICON_ADD_PAGE);
        addTextButton.setGraphic(new ImageView(image));
        image = new Image("file:" + StartUpConstants.ICON_REMOVE_PAGE);
        removeTextButton.setGraphic(new ImageView(image));
        //Move up and down buttons
        image = new Image("file:" + StartUpConstants.ICON_MOVE_UP);
        moveTextUpButton.setGraphic(new ImageView(image));
        image = new Image("file:" + StartUpConstants.ICON_MOVE_DOWN);
        moveTextDownButton.setGraphic(new ImageView(image));
        listButtonsVBox.getChildren().addAll(addTextButton, removeTextButton, moveTextUpButton, moveTextDownButton);

        //Stuff to go on right hand side
        paragraphVBox = new VBox();
        paragraphVBox.setAlignment(Pos.CENTER_RIGHT);
        paragraphVBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);

        listFontSizeButton = new Button();
        listFontStyleButton = new Button();

        image = new Image("file:" + StartUpConstants.ICON_FONT_SIZE);
        listFontSizeButton.setGraphic(new ImageView(image));
        image = new Image("file:" + StartUpConstants.ICON_FONT_STYLE);
        listFontStyleButton.setGraphic(new ImageView(image));

        paragraphVBox.getChildren().addAll(listFontSizeButton, listFontStyleButton);


        //Confirm and cancel buttons
        paragraphButtonsHBox = new HBox();
        paragraphButtonsHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        paragraphButtonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmParagraphButton = new Button("Confirm");
        cancelParagraphButton = new Button("Cancel");
        confirmParagraphButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        cancelParagraphButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        paragraphButtonsHBox.getChildren().addAll(confirmParagraphButton, cancelParagraphButton);

        //CONFIRM BUTTON HANDLER
        confirmParagraphButton.setOnAction(event -> {
            for (RadioButton radioButton : listRadioButtons) {
                listTextComponent.getListText().add(radioButton.getText());
            }
            page.getAllComponents().add(listTextComponent);
            listTextComponent.setTextType("List");
//            textComponentsList.add(new RadioButton("List"));
            pageView.reloadPageView();
            ui.updateSaveButtons();
            this.close();
        });

        cancelParagraphButton.setOnAction(event -> {
            this.close();
        });

        listTextComponents = new VBox();
        listTextComponents.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        listToggleGroup = new ToggleGroup();

        listBorderPane.setLeft(listButtonsVBox);
        listBorderPane.setRight(paragraphVBox);
        listBorderPane.setBottom(paragraphButtonsHBox);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(.5 * bounds.getMinX());
        this.setY(.5 * bounds.getMinY());
        this.setWidth(.5 * bounds.getWidth());
        this.setHeight(.5 * bounds.getHeight());

        listButtonHandlers();

        scrollPane = new ScrollPane(listTextComponents);
        scrollPane.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        listBorderPane.setCenter(scrollPane);
        listBorderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
        scene = new Scene(listBorderPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();

    }

    public void editList(TextComponent textComponentToEdit, EPortfolioView ui){
        this.ui = ui;

        this.setTitle("List Dialog");
        listBorderPane = new BorderPane();
        listTextComponent = textComponentToEdit;


        //Left hand side
        listButtonsVBox = new VBox();
        listButtonsVBox.setAlignment(Pos.CENTER_LEFT);
        listButtonsVBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        addTextButton = new Button();
        removeTextButton = new Button();
        moveTextUpButton = new Button();
        moveTextDownButton = new Button();

        //Add and Remove Buttons
        Image image = new Image("file:" + StartUpConstants.ICON_ADD_PAGE);
        addTextButton.setGraphic(new ImageView(image));
        image = new Image("file:" + StartUpConstants.ICON_REMOVE_PAGE);
        removeTextButton.setGraphic(new ImageView(image));
        //Move up and down buttons
        image = new Image("file:" + StartUpConstants.ICON_MOVE_UP);
        moveTextUpButton.setGraphic(new ImageView(image));
        image = new Image("file:" + StartUpConstants.ICON_MOVE_DOWN);
        moveTextDownButton.setGraphic(new ImageView(image));
        listButtonsVBox.getChildren().addAll(addTextButton, removeTextButton, moveTextUpButton, moveTextDownButton);

        //Stuff to go on right hand side
        paragraphVBox = new VBox();
        paragraphVBox.setAlignment(Pos.CENTER_RIGHT);
        paragraphVBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);

        listFontSizeButton = new Button();
        listFontStyleButton = new Button();

        image = new Image("file:" + StartUpConstants.ICON_FONT_SIZE);
        listFontSizeButton.setGraphic(new ImageView(image));
        image = new Image("file:" + StartUpConstants.ICON_FONT_STYLE);
        listFontStyleButton.setGraphic(new ImageView(image));

        paragraphVBox.getChildren().addAll(listFontSizeButton, listFontStyleButton);


        //Confirm and cancel buttons
        paragraphButtonsHBox = new HBox();
        paragraphButtonsHBox.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS);
        paragraphButtonsHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmParagraphButton = new Button("Confirm");
        cancelParagraphButton = new Button("Cancel");
        confirmParagraphButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        cancelParagraphButton.getStyleClass().add(StartUpConstants.CSS_ADD_COMPONENTS_BUTTONS);
        paragraphButtonsHBox.getChildren().addAll(confirmParagraphButton, cancelParagraphButton);

        //CONFIRM BUTTON HANDLER
        confirmParagraphButton.setOnAction(event -> {
            listTextComponent.getListText().clear();
            for(RadioButton radioButton: listRadioButtons){
                listTextComponent.getListText().add(radioButton.getText());
            }
            pageView.reloadPageView();
            ui.updateSaveButtons();
            this.close();
        });

        cancelParagraphButton.setOnAction(event -> {
            this.close();
        });

        listTextComponents = new VBox();
        listTextComponents.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        listToggleGroup = new ToggleGroup();

        listBorderPane.setLeft(listButtonsVBox);
        listBorderPane.setRight(paragraphVBox);
        listBorderPane.setBottom(paragraphButtonsHBox);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(.5 * bounds.getMinX());
        this.setY(.5 * bounds.getMinY());
        this.setWidth(.5 * bounds.getWidth());
        this.setHeight(.5 * bounds.getHeight());

        listButtonHandlers();

        scrollPane = new ScrollPane(listTextComponents);
        scrollPane.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        listBorderPane.setCenter(scrollPane);
        listBorderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);

        textComponentConstruct = new TextComponent();
        textComponentConstruct.setTextFont(textComponentToEdit.getTextFont());
        textComponentConstruct.setTextSize(textComponentToEdit.getTextSize());
        textComponentConstruct.setTextType(textComponentToEdit.getTextType());


        if(listTextComponent.getListText().size() > 0){
            for(String string: listTextComponent.getListText()){
                RadioButton radioButton = new RadioButton(string);
                radioButton.setToggleGroup(listToggleGroup);
                listRadioButtons.add(radioButton);
                textComponentConstruct.getListText().add(string);
                listTextComponents.getChildren().add(radioButton);
            }
        }

        scene = new Scene(listBorderPane);
        scene.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    private void listButtonHandlers(){
        addTextButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("List Item Input");
            dialog.setContentText("Input a list item:");
            //dialog.setResizable(true);
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
                RadioButton radioButton = new RadioButton(result.get());
                radioButton.setToggleGroup(listToggleGroup);
                listRadioButtons.add(radioButton);
                listTextComponents.getChildren().add(radioButton);
            }
        });

        removeTextButton.setOnAction(event -> {
            boolean flag = false;
            for (RadioButton radioButton : listRadioButtons) {
                if (radioButton.isSelected() == true) {
                    listRadioButtons.remove(radioButton);
                    flag = true;
                    break;
                }
            }

            if (flag) {
                listTextComponents.getChildren().clear();
                for (RadioButton radioButton : listRadioButtons) {
                    listTextComponents.getChildren().add(radioButton);
                }
            }
        });

        moveTextUpButton.setOnAction(event -> {
            int index = 0;
            for(RadioButton radioButton: listRadioButtons){
                if(radioButton.isSelected()){
                    if(index == 0){}
                    else{
                        Collections.swap(listRadioButtons, index, index - 1);
                        listTextComponents.getChildren().clear();
                        for(RadioButton radioButton1: listRadioButtons)
                            listTextComponents.getChildren().add(radioButton1);
                        break;
                    }
                }
                index++;
            }
        });

        moveTextDownButton.setOnAction(event -> {
            int index = 0;
            for(RadioButton radioButton: listRadioButtons){
                if(radioButton.isSelected()){
                    if(index == listRadioButtons.size()-1){}
                    else{
                        Collections.swap(listRadioButtons, index, index+1);
                        listTextComponents.getChildren().clear();
                        for(RadioButton radioButton1: listRadioButtons)
                            listTextComponents.getChildren().add(radioButton1);
                        break;
                    }
                }
                index++;
            }
        });

        listFontSizeButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog(listTextComponent.getTextSize());
            dialog.setHeaderText("Input a font size");
            dialog.setContentText("Please input your desired font size:");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefWidth(550);

            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:./images/icons/eportfolio.gif"));
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
               if(isNumeric(result.get())){
                    listTextComponent.setTextSize(result.get());
               }
                else{
                   ErrorHandler.errorPopUp("Incorrect user input for font size");
               }
            }
        });

        listFontStyleButton.setOnAction(event -> {
            List<String> choices = new ArrayList<>();
            choices.add("Slabo");
            choices.add("Sans Pro");
            choices.add("Serif");
            choices.add("Hind");
            choices.add("Cantarell");

            ChoiceDialog<String> dialog = new ChoiceDialog<>("Slabo", choices);
            dialog.setTitle("Font Style/Family");
            dialog.setContentText("Choose your desired font style/family:");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefWidth(550);

            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:./images/icons/eportfolio.gif"));
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
                listTextComponent.setTextFont(result.get());
            }
        });
    }

    private String getTextComponentFont()
    {
        if(pageFontOneRadioButton.isSelected() == true)
            return "Slabo";
        else if(pageFontTwoRadioButton.isSelected() == true)
            return "Sans Pro";
        else if(pageFontThreeRadioButton.isSelected() == true)
            return "Serif";
        else if(pageFontFourRadioButton.isSelected() == true)
            return "Hind";
        else
            return "Cantarell";
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
