package eportfoliogenerator.view;

import eportfoliogenerator.model.EPortfolioModel;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Nauman on 11/14/2015.
 */
public class PageView extends VBox
{
    //Data model singleton
    EPortfolioModel model;

    //HBoxes for layout and color
    HBox layoutHBox;
    HBox colorHBox;

    //Labels and radio buttons for layout and color
    Label layoutLabel = new Label("Layout: ");
    Label colorLabel = new Label("Color: ");

    ToggleGroup layoutToggleGroup;
    RadioButton layoutOneRadioButton;
    RadioButton layoutTwoRadioButton;
    RadioButton layoutThreeRadioButton;
    RadioButton layoutFourRadioButton;
    RadioButton layoutFiveRadoButton;

    ToggleGroup colorToggleGroup;
    RadioButton colorOneRadioButton;
    RadioButton colorTwoRadioButton;
    RadioButton colorThreeRadioButton;
    RadioButton colorFourRadioButton;
    RadioButton colorFiveRadioButton;



}
