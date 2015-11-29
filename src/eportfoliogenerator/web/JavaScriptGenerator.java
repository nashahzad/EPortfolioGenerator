package eportfoliogenerator.web;

import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.SlideShowComponent;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;
import eportfoliogenerator.slideshowhelpers.Slide;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Nauman on 11/28/2015.
 */
public class JavaScriptGenerator
{
    //DATA MODEL WORKING WITH
    EPortfolioModel model;

    //Some strings
    String SITES_PATH = "./src/eportfoliogenerator/sites/";

    public JavaScriptGenerator(EPortfolioModel model){
        this.model = model;
    }

    public void generateJavaScriptSlideShow(){
        for(Page page: model.getPages()){
            String text = new String();
            for(SlideShowComponent slideShowComponent: page.getSlideShowComponents()){
                int index = page.getSlideShowComponents().indexOf(slideShowComponent);
                text+="var sc" + index + "= new Array();\n";

                    for(ImageComponent imageComponent: slideShowComponent.getImageSlides()){
                        int i = slideShowComponent.getImageSlides().indexOf(imageComponent);
                        text+="sc" + index + "[" + i + "] =\"" + imageComponent.getCaption() + "\";\n";
                    }

                text+="var s" + index + "= new Array();\n";
                for(ImageComponent imageComponent: slideShowComponent.getImageSlides()){
                    int i = slideShowComponent.getImageSlides().indexOf(imageComponent);
                    text+="s" + index + "[" + i + "] =\"img/" + imageComponent.getImageName() + "\";\n";
                }

                text+="var heights" + index + "= new Array();\n";
                for(ImageComponent imageComponent: slideShowComponent.getImageSlides()){
                    int i = slideShowComponent.getImageSlides().indexOf(imageComponent);
                    text+="heights" + index + "[" + i + "] =\"" + imageComponent.getHeight() + "\";\n";
                }

                text+="var widths" + index + "= new Array();\n";
                for(ImageComponent imageComponent: slideShowComponent.getImageSlides()){
                    int i = slideShowComponent.getImageSlides().indexOf(imageComponent);
                    text+="widths" + index + "[" + i + "] =\"" + imageComponent.getWidth() + "\";\n";
                }

                text+="var pointers" + index + "= 0;\n";
                text+="var timers" + index + ";\n\n";

                //PREVIOUS SLIDE
                text+="function previouss" + index + "()\n{\n";
                text+="if(pointers" + index + " == 0){}\n";
                text+="else{\n";
                text+="pointers" + index + "--;\n";
                text+="document.getElementById(\"s" + index + "\").src = s" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"sc" + index + "\").innerHTML = sc" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"s" + index + "\").height = heights" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"s" + index + "\").width = widths" + index + "[pointers" + index + "];\n";
                text+="}\n}\n\n";

                //NEXT SLIDE
                text+="function nexts" + index + "()\n{\n";
                text+="if(pointers" + index + " == (sc" + index + ".length - 1)){}\n";
                text+="else{\n";
                text+="pointers" + index + "++;\n";
                text+="document.getElementById(\"s" + index + "\").src = s" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"sc" + index + "\").innerHTML = sc" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"s" + index + "\").height = heights" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"s" + index + "\").width = widths" + index + "[pointers" + index + "];\n";
                text+="}\n}\n\n";

                //PLAY SLIDESHOW BUTTON
                text+="function plays" + index +"()\n{\n";
                text+="document.getElementById(\"Play/Pauses" + index + "\").onclick = pauses" + index + ";\n";
                text+="document.getElementById(\"Play/Pauses" + index + "\").innerHTML = \"<img src=\\\"img/Pause.png\\\">\";\n";
                text+="timers" + index + " = setInterval(nextplays" + index + ", 1000);\n}\n\n";

                //PAUSE SLIDESHOW BUTTON
                text+="function pauses" + index + "()\n{\n";
                text+="window.clearInterval(timers" + index + ");\n";
                text+="document.getElementById(\"Play/Pauses" + index + "\").onclick = plays" + index + ";\n";
                text+="document.getElementById(\"Play/Pauses" + index + "\").innerHTML = \"<img src=\\\"img/Play.png\\\">\";\n}\n\n";

                //FUNCTION TO HANDLY PLAYING OF SLIDESHOW
                text+="function nextplays" + index + "()\n{\n";
                text+="if(pointers" + index + " == (sc" + index + ".length - 1)){\n";
                text+="pointers" + index + " = 0;\n";
                text+="document.getElementById(\"s" + index + "\").height = heights" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"s" + index + "\").width = widths" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"s" + index + "\").src = s" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"sc" + index + "\").innerHTML = sc" + index + "[pointers" + index + "];\n}\n";
                text+="else{\n";
                text+="pointers" + index + "++;\n";
                text+="document.getElementById(\"s" + index + "\").height = heights" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"s" + index + "\").width = widths" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"s" + index + "\").src = s" + index + "[pointers" + index + "];\n";
                text+="document.getElementById(\"sc" + index + "\").innerHTML = sc" + index + "[pointers" + index + "];\n}\n}\n\n";
            }

            BufferedWriter output;
            try{
                File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/js/" + page.getPageTitle() + "JS.js");
                file.createNewFile();
                output = new BufferedWriter(new FileWriter(file));
                output.write(text);
                output.close();
            }catch(Exception ex){
                System.out.println("Error in generateJavaScript method in JavaScriptGenerator Class");
            }
        }
    }

}
