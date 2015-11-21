package eportfoliogenerator.dialog;

import eportfoliogenerator.model.Page;
import eportfoliogenerator.view.PageView;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Nauman on 11/21/2015.
 */
public class DialogImageComponent extends Stage
{
    //Current PageView we are working with
    PageView pageView;

    //Scene and other GUI Components needed
    VBox imageVBox;
    HBox imageHBox;

    Label imageWidthLabel = new Label("Width:");
    TextField imageWidthTextField;
    Label imageHeightLabel = new Label("Height:");
    TextField imageHeightTextField;
    ImageView imageView;


    public DialogImageComponent(PageView pageView){
        this.pageView = pageView;
    }

    public void createImageComponent(Page page, ArrayList<RadioButton> imageComponentsList){
        imageVBox = new VBox();

    }


}
