package eportfoliogenerator.dialog;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.HyperLinkComponent;
import eportfoliogenerator.components.TextComponent;
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
import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Nauman on 11/22/2015.
 */
public class DialogTextHyperLinkComponent extends Stage
{
    EPortfolioView ui;

    //PageView and TextComponent to edit
    PageView pageView;
    TextComponent textComponent;

    //Border Pane to Arrange GUI COMPONENTS ON SCREEN
    BorderPane borderPane;
    ScrollPane scrollPane;

    //Left Hand Side
    VBox addRemoveVBox;
    Button addHyperlinkButton;
    Button removeHyperlinkButton;

    //Top Side
    HBox paragraphHBox;
    TextArea paragraphTextArea;

    //Center
    VBox linksVBox;
    ToggleGroup linksToggleGroup;
    ArrayList<RadioButton> linksRadioButtons = new ArrayList<RadioButton>();
    ArrayList<HyperLinkComponent> hyperLinkComponents = new ArrayList<HyperLinkComponent>();

    //Bottom Side
    HBox confirmCancelHBox;
    Button confirmButton;
    Button cancelButton;

    //Scene
    Scene scene;

    public DialogTextHyperLinkComponent(PageView pageView){
        this.pageView = pageView;
        Image image = new Image("file:./images/icons/eportfolio.gif");
        this.getIcons().add(image);
    }

    public void createHyperlinks(TextComponent textComponent, EPortfolioView ui){
        this.ui = ui;
        this.textComponent = textComponent;

        //Left Hand side
        addRemoveVBox = new VBox(7);
        addRemoveVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        addRemoveVBox.setAlignment(Pos.CENTER_LEFT);
        addHyperlinkButton = new Button();
        removeHyperlinkButton = new Button();

        addRemoveVBox.getChildren().addAll(addHyperlinkButton, removeHyperlinkButton);

        //Top Side
        paragraphHBox = new HBox();
        paragraphHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        paragraphHBox.setAlignment(Pos.TOP_CENTER);
        paragraphTextArea = new TextArea(textComponent.getParagraphOrHeader() + " Range from 0-" + (textComponent.getParagraphOrHeader().length()-1));
        paragraphTextArea.setEditable(false);
        paragraphHBox.getChildren().add(paragraphTextArea);

        //Center
        linksVBox = new VBox();
        linksVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        linksToggleGroup = new ToggleGroup();
        scrollPane = new ScrollPane(linksVBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        //Bottom Side
        confirmCancelHBox = new HBox(7);
        confirmCancelHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        confirmCancelHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");
        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);

        //CONFIRM AND CANCEL BUTTON HANDLERS
        confirmButton.setOnAction(event -> {
            for(HyperLinkComponent hyperLinkComponent: hyperLinkComponents){
                textComponent.getHyperLinks().add(hyperLinkComponent);
            }
            ui.updateSaveButtons();
            this.close();
        });

        cancelButton.setOnAction(event -> {
            this.close();
        });

        //Slap into borderpane and scene
        borderPane = new BorderPane();
        borderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
        borderPane.setTop(paragraphHBox);
        borderPane.setLeft(addRemoveVBox);
        borderPane.setCenter(linksVBox);
        borderPane.setBottom(confirmCancelHBox);

        //Set up Button images and handlers
        setUpButtonImages();
        setUpButtonHandlers();

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(.5 * bounds.getMinX());
        this.setY(.5 * bounds.getMinY());
        this.setWidth(.5 * bounds.getWidth());
        this.setHeight(.5 * bounds.getHeight());

        scene = new Scene(borderPane);
        scene.getStylesheets().addAll(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    public void editTextHyperlinkComponent(TextComponent textComponentToEdit, EPortfolioView ui){
        this.textComponent = textComponentToEdit;
        this.ui = ui;

        //Left Hand side
        addRemoveVBox = new VBox(7);
        addRemoveVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        addRemoveVBox.setAlignment(Pos.CENTER_LEFT);
        addHyperlinkButton = new Button();
        removeHyperlinkButton = new Button();

        addRemoveVBox.getChildren().addAll(addHyperlinkButton, removeHyperlinkButton);

        //Top Side
        paragraphHBox = new HBox();
        paragraphHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        paragraphHBox.setAlignment(Pos.TOP_CENTER);
        paragraphTextArea = new TextArea(textComponent.getParagraphOrHeader() + " Range from 0-" + (textComponent.getParagraphOrHeader().length()-1));
        paragraphTextArea.setEditable(false);
        paragraphHBox.getChildren().add(paragraphTextArea);

        //Center
        linksVBox = new VBox();
        linksVBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        linksToggleGroup = new ToggleGroup();
        scrollPane = new ScrollPane(linksVBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        //Bottom Side
        confirmCancelHBox = new HBox(7);
        confirmCancelHBox.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);
        confirmCancelHBox.setAlignment(Pos.BOTTOM_CENTER);
        confirmButton = new Button("Confirm");
        cancelButton = new Button("Cancel");
        confirmCancelHBox.getChildren().addAll(confirmButton, cancelButton);

        //CONFIRM AND CANCEL BUTTON HANDLERS
        confirmButton.setOnAction(event -> {
            textComponent.getHyperLinks().clear();
            for(HyperLinkComponent hyperLinkComponent: hyperLinkComponents){
                textComponent.getHyperLinks().add(hyperLinkComponent);
            }
            ui.updateSaveButtons();
            this.close();
        });

        cancelButton.setOnAction(event -> {
            this.close();
        });

        //Slap into borderpane and scene
        borderPane = new BorderPane();
        borderPane.getStyleClass().add(StartUpConstants.CSS_BORDER_PANE);
        borderPane.setTop(paragraphHBox);
        borderPane.setLeft(addRemoveVBox);
        borderPane.setCenter(linksVBox);
        borderPane.setBottom(confirmCancelHBox);

        //Set up Button images and handlers
        setUpButtonImages();
        setUpButtonHandlers();

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        this.setX(.5 * bounds.getMinX());
        this.setY(.5 * bounds.getMinY());
        this.setWidth(.5 * bounds.getWidth());
        this.setHeight(.5 * bounds.getHeight());

        reloadLinksRadioButtonsEdit();

        scene = new Scene(borderPane);
        scene.getStylesheets().addAll(StartUpConstants.STYLE_SHEET_UI);
        this.setScene(scene);
        this.show();
    }

    private void setUpButtonImages(){
        Image image = new Image("file:" + StartUpConstants.ICON_ADD_PAGE);
        addHyperlinkButton.setGraphic(new ImageView(image));
        addHyperlinkButton.setTooltip(new Tooltip("Add Hyperlink"));

        image = new Image("file:" + StartUpConstants.ICON_REMOVE_PAGE);
        removeHyperlinkButton.setGraphic(new ImageView(image));
        removeHyperlinkButton.setTooltip(new Tooltip("Remove Selected Hyperlink"));
    }

    private void setUpButtonHandlers(){
        addHyperlinkButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Hyperlink input");
            dialog.setContentText("Please input a valid URL/Hyperlink:");
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
            if (result.isPresent()) {
                HyperLinkComponent hyperLinkComponent = new HyperLinkComponent();
                hyperLinkComponent.setUrl(result.get());

                //Dialog inside a dialog, INCEPTION
                TextInputDialog dialogInception = new TextInputDialog();
                dialogInception.setTitle("Index Range for URL");
                dialogInception.setContentText("Please enter a valid range for your URL (Ex. 0-" + (textComponent.getParagraphOrHeader().length() - 1) + ") Include dash and no spaces:");
                dialogInception.getDialogPane().setPrefWidth(600);

                Stage stageCeption = (Stage) dialogInception.getDialogPane().getScene().getWindow();
                stageCeption.getIcons().add(new Image("file:./images/icons/eportfolio.gif"));
                stageCeption.setScene(new Scene(new ScrollPane(dialogInception.getDialogPane())));

                DialogPane alertDialogPaneCeption = dialogInception.getDialogPane();

                alertDialogPaneCeption.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
                alertDialogPaneCeption.getStyleClass().add(StartUpConstants.CSS_LAYOUT_HBOX);

                //CSS to buttons added after alert does its getButtonTypes method
                ButtonBar buttonBarCeption = (ButtonBar) dialogInception.getDialogPane().lookup(".button-bar");
                buttonBarCeption.getStylesheets().add(StartUpConstants.STYLE_SHEET_UI);
                buttonBarCeption.getButtons().forEach(b -> b.getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS));

                //Content text
                alertDialogPaneCeption.lookup(".content.label").getStyleClass().add(StartUpConstants.CSS_LAYOUT_BUTTONS);

                // Traditional way to get the response value.
                Optional<String> resultInception = dialogInception.showAndWait();
                if (resultInception.isPresent()) {
                    String[] arrayString = resultInception.get().split("-");
                    hyperLinkComponent.setStart(Integer.parseInt(arrayString[0]));
                    hyperLinkComponent.setEnd(Integer.parseInt(arrayString[1]));

                    hyperLinkComponents.add(hyperLinkComponent);
                    RadioButton radioButton = new RadioButton(hyperLinkComponent.getUrl() + " " + hyperLinkComponent.getStart() + "-" + hyperLinkComponent.getEnd());
                    radioButton.setToggleGroup(linksToggleGroup);
                    linksRadioButtons.add(radioButton);
                    reloadLinksRadioButtons();
                }
            }
        });

        removeHyperlinkButton.setOnAction(event -> {
            int index = 0;
            for (RadioButton radioButton : linksRadioButtons) {
                if (radioButton.isSelected()) {
                    linksRadioButtons.remove(radioButton);
                    break;
                }
                index++;
            }
            hyperLinkComponents.remove(index);
            reloadLinksRadioButtons();
        });
    }

    private void reloadLinksRadioButtons(){
        linksVBox.getChildren().clear();
        linksRadioButtons.clear();
        for(HyperLinkComponent hyperLinkComponent: hyperLinkComponents){
            RadioButton radioButton = new RadioButton(hyperLinkComponent.getUrl() + " " + hyperLinkComponent.getStart() + "-" + hyperLinkComponent.getEnd());
            radioButton.setToggleGroup(linksToggleGroup);
            linksRadioButtons.add(radioButton);
            linksVBox.getChildren().add(radioButton);
        }
    }

    private void reloadLinksRadioButtonsEdit(){
        linksVBox.getChildren().clear();
        for(HyperLinkComponent hyperLinkComponent: textComponent.getHyperLinks()){
            hyperLinkComponents.add(hyperLinkComponent);
            RadioButton radioButton = new RadioButton(hyperLinkComponent.getUrl() + " " + hyperLinkComponent.getStart() + "-" + hyperLinkComponent.getEnd());
            radioButton.setToggleGroup(linksToggleGroup);
            linksRadioButtons.add(radioButton);
            linksVBox.getChildren().add(radioButton);
        }
    }

}
