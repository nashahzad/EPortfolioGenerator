package eportfoliogenerator.dialog;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.TextComponent;
import eportfoliogenerator.model.Page;
import eportfoliogenerator.view.PageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Nauman on 11/20/2015.
 */
public class DialogTextComponents extends Stage
{
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

    public DialogTextComponents(PageView pageView){
        this.pageView = pageView;
        Image image = new Image("file:./images/icons/eportfolio.gif");
        this.getIcons().add(image);
    }

    public void promptForType(Page page, ArrayList<RadioButton> textComponentsList)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Select Type of Text Component");
        alert.setContentText("Choose what kind of text component you want:");

        ButtonType buttonTypeOne = new ButtonType("Header");
        ButtonType buttonTypeTwo = new ButtonType("Paragraph");
        ButtonType buttonTypeThree = new ButtonType("List");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

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

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One"
            getHeader(page, textComponentsList);
        } else if (result.get() == buttonTypeTwo) {
            // ... user chose "Paragraph"
            getParagraph(page, textComponentsList);
        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
        } else {

        }

    }

    private void getHeader(Page page, ArrayList<RadioButton> textComponentsList){
        paragraphVBox = new VBox();

        paragraphHBox = new HBox();

        paragraphHBox.getChildren().add(headerInputLabel);

        headerTextField = new TextField();
        headerTextField.setPrefWidth(800);
        paragraphHBox.getChildren().add(headerTextField);

        //Page Fonts
        pageFontHBox = new HBox();
        pageFontToggleGroup = new ToggleGroup();

        pageFontOneRadioButton = new RadioButton("Slabo");
        pageFontTwoRadioButton = new RadioButton("Sans Pro");
        pageFontThreeRadioButton = new RadioButton("Serif");
        pageFontFourRadioButton = new RadioButton("Hind");
        pageFontFiveRadioButton = new RadioButton("Cantarrell");

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
            textComponent.setTextType("header");
            textComponent.setTextFont(getTextComponentFont());
            textComponent.setTextSize(paragraphSizeTextField.getText());
            page.getTextComponents().add(textComponent);

            textComponentsList.add(new RadioButton(headerTextField.getText()));
            this.pageView.reloadPageView();
            this.close();
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

    private void getParagraph(Page page, ArrayList<RadioButton> textComponentsList)
    {
        paragraphVBox = new VBox();

        paragraphHBox = new HBox();

        paragraphHBox.getChildren().add(paragraphInputLabel);

        paragraphTextArea = new TextArea();
        paragraphHBox.getChildren().add(paragraphTextArea);

        //Page Fonts
        pageFontHBox = new HBox();
        pageFontToggleGroup = new ToggleGroup();

        pageFontOneRadioButton = new RadioButton("Slabo");
        pageFontTwoRadioButton = new RadioButton("Sans Pro");
        pageFontThreeRadioButton = new RadioButton("Serif");
        pageFontFourRadioButton = new RadioButton("Hind");
        pageFontFiveRadioButton = new RadioButton("Cantarrell");

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
            textComponent.setTextType("paragraph");
            textComponent.setTextFont(getTextComponentFont());
            textComponent.setTextSize(paragraphSizeTextField.getText());
            page.getTextComponents().add(textComponent);

            textComponentsList.add(new RadioButton(paragraphTextArea.getText()));
            this.pageView.reloadPageView();
            this.close();
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



}
