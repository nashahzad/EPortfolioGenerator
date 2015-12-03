package eportfoliogenerator.web;

import eportfoliogenerator.components.Component;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.TextComponent;
import eportfoliogenerator.components.VideoComponent;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Nauman on 11/28/2015.
 */
public class CSSGenerator
{
    //DATA MODEL WORKING WITH
    EPortfolioModel model;

    //Some strings
    String SITES_PATH = "./src/eportfoliogenerator/sites/";

    int textIndex = 0;
    int imageIndex = 0;
    int videoIndex = 0;

    public CSSGenerator(EPortfolioModel model){
        this.model = model;
    }

    public void createCSS(){
        for(Page page: model.getPages()) {
            String text = new String();
            text = addAllComponentsCSS(text, page);

            BufferedWriter output;
            try{
                File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/css/" + page.getPageTitle() + "CSS.css");
                file.createNewFile();
                output = new BufferedWriter(new FileWriter(file));
                output.write(text);
                output.close();
            }catch(Exception ex){
                System.out.println("Error in CSS file generation method in CSSGenerator Class");
            }
        }

    }

    private String addAllComponentsCSS(String text, Page page){
        for(Component component: page.getAllComponents()){

            if(component.getIdentity().equalsIgnoreCase("TextComponent")){
                TextComponent textComponent = (TextComponent) component;
                text = addTextComponentCSS(text, textComponent);
            }

            else if(component.getIdentity().equalsIgnoreCase("ImageComponent")){
                ImageComponent imageComponent = (ImageComponent) component;
                text = addImageComponentCSS(text, imageComponent);
            }

            else if(component.getIdentity().equalsIgnoreCase("VideoComponent")){
                VideoComponent videoComponent = (VideoComponent) component;
                text = addVideoComponentCSS(text, videoComponent);
            }

        }

        return text;
    }

    private String addTextComponentCSS(String text, TextComponent textComponent){
        text += ".t" + textIndex + "{\n";
        if(textComponent.getTextFont() == null){}
        else {
            if (textComponent.getTextFont().equalsIgnoreCase("Slabo"))
                text += "font-family: \'Slabo 27px\', serif;\n";
            if (textComponent.getTextFont().equalsIgnoreCase("Sans Pro"))
                text += "font-family: \'Source Sans Pro\', sans-serif;\n";
            if (textComponent.getTextFont().equalsIgnoreCase("Serif"))
                text += "font-family: \'PT Serif\', serif;\n";
            if (textComponent.getTextFont().equalsIgnoreCase("Hind"))
                text += "font-family: \'Hind\', sans-serif;\n";
            if (textComponent.getTextFont().equalsIgnoreCase("Cantarell"))
                text += "font-family: \'Cantarell\', sans-serif;\n";
        }

        if(textComponent.getTextSize().equalsIgnoreCase("") || textComponent.getTextSize() == null){}
//            text += "font-size: 16px;\n";
        else
            text += "font-size: " + textComponent.getTextSize() + "px;\n";

        text += "}\n\n";

        textIndex++;
        return text;
    }

    private String addImageComponentCSS(String text, ImageComponent imageComponent){
        text += ".i" + imageIndex + "{\n";

        text += "min-width: " + imageComponent.getWidth() + "px;\n" + "max-width: " + imageComponent.getWidth() + "px;\n";
        text += "min-height: " + imageComponent.getHeight() + "px;\n" + "max-height: " + imageComponent.getHeight() + "px;\n";

        if(imageComponent.getFloatAttribute().equalsIgnoreCase("Neither"))
            text += "float: none;\n";
        else
            text += "float: " + imageComponent.getFloatAttribute() + ";\n";

        text += "}\n\n";
        imageIndex++;
        return text;
    }

    private String addVideoComponentCSS(String text, VideoComponent videoComponent){
        text += ".v" + videoIndex + "{\n";

        text += "min-width: " + videoComponent.getWidth() + "px;\n" + "max-width: " + videoComponent.getWidth() + "px;\n";
        text += "min-height: " + videoComponent.getHeight() + "px;\n" + "max-height: " + videoComponent.getHeight() + "px;\n";

        text += "}\n\n";
        videoIndex++;
        return text;
    }
}
