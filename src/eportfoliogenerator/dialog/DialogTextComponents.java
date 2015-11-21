package eportfoliogenerator.dialog;

import eportfoliogenerator.components.TextComponent;
import eportfoliogenerator.model.Page;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by Nauman on 11/20/2015.
 */
public class DialogTextComponents extends Stage
{
    Scene scene;
    ScrollPane scrollPane;

    //Components for getting paragraph input
    VBox paragraphVBox;

    HBox paragraphHBox;
    Label paragraphInputLabel = new Label("Input paragraph:");
    TextField paragraphTextField;

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

    public void promptForType(Page page)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Select Type of Text Component");
        alert.setContentText("Choose what kind of text component you want:");

        ButtonType buttonTypeOne = new ButtonType("Header");
        ButtonType buttonTypeTwo = new ButtonType("Paragraph");
        ButtonType buttonTypeThree = new ButtonType("List");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One"
        } else if (result.get() == buttonTypeTwo) {
            // ... user chose "Paragraph"
            getParagraph(page);
        } else if (result.get() == buttonTypeThree) {
            // ... user chose "Three"
        } else {

        }

    }

    private void getParagraph(Page page)
    {
        paragraphVBox = new VBox();

        paragraphHBox = new HBox();

        paragraphHBox.getChildren().add(paragraphInputLabel);

        paragraphTextField = new TextField();
        paragraphHBox.getChildren().add(paragraphTextField);

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
        confirmParagraphButton = new Button("Confirm");
        cancelParagraphButton = new Button("Cancel");
        paragraphButtonsHBox.getChildren().add(confirmParagraphButton);
        paragraphButtonsHBox.getChildren().add(cancelParagraphButton);

        confirmParagraphButton.setOnAction(event -> {
            TextComponent textComponent = new TextComponent();
            textComponent.setParagraphOrHeader(paragraphTextField.getText());
            textComponent.setTextType("paragraph");
            textComponent.setTextFont(getTextComponentFont());
            textComponent.setTextSize(paragraphSizeTextField.getText());
            page.getTextComponents().add(textComponent);
            this.close();
        });

        cancelParagraphButton.setOnAction(event -> {
            this.close();
        });

        paragraphVBox.getChildren().add(paragraphHBox);
        paragraphVBox.getChildren().add(pageFontHBox);
        paragraphVBox.getChildren().add(paragraphButtonsHBox);
        scrollPane = new ScrollPane(paragraphVBox);
        scene = new Scene(scrollPane);
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
