package eportfoliogenerator.web;

import eportfoliogenerator.components.*;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;
import javafx.scene.image.Image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Nauman on 11/28/2015.
 */
public class HTMLGenerator
{
    //DATA MODEL WORKING WITH
    EPortfolioModel model;

    //Some strings
    String SITES_PATH = "./src/eportfoliogenerator/sites/";

    String URL = new String();

    int textIndex = 0;
    int imageIndex = 0;
    int slideIndex = 0;
    int videoIndex = 0;


    public HTMLGenerator(EPortfolioModel model){
        this.model = model;
    }

    public void generateHTML(){
        for(Page page: model.getPages()){
            if(page.getLayout() == 1){
                HTMLLayoutOne(page);
            }
            else if(page.getLayout() == 2){
                HTMLLayoutTwo(page);
            }
            else if(page.getLayout() == 3){
                HTMLLayoutThree(page);
            }
            else if(page.getLayout() == 4){
                HTMLLayoutFour(page);
            }
//            else if(page.getLayout() == 5){
//                HTMLLayoutFive(page);
//            }
        }
    }

    //ADD AND ARRANGE COMPONENTS IN REGARDS TO FIRST LAYOUT SCHEME/TEMPLATE FROM HW#5
    private void HTMLLayoutOne(Page page){
        String text = "<!DOCTYPE html>\n";
        text+="<html>\n";
        text+="<head>\n";
        text+="<script src=\"js/" + page.getPageTitle() + "JS.js\"></script>\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + page.getPageTitle() + "CSS.css\">\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "LayoutOne" + "CSS.css\">\n";
        text = addColorSchemeSheet(text, page);
        text = addFontSheet(text, page);
        text = addGoogleFonts(text);
        text+= "</head>\n";
        text+="<body>\n";
        text = addNavBar(text, page);
        text = addBanner(text, page);
        text+="<div class=\"content\">\n";
        text = addAllComponents(text, page);
        text = addFooter(text, page);
        text += "</div>\n\n";
        text += "</body>\n";
        text += "</html>\n";

        BufferedWriter output;
        try{
            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
            if(page == model.getSelectedPage()){
                URL = file.toURI().toURL().toString();
            }

            file.createNewFile();
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        }catch(Exception ex){
            System.out.println("Error in HTMLLayoutOne method in HTMLGenerator Class");
        }
    }

    private void HTMLLayoutTwo(Page page){
        String text = "<!DOCTYPE html>\n";
        text+="<html>\n";
        text+="<head>\n";
        text+="<script src=\"js/" + page.getPageTitle() + "JS.js\"></script>\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + page.getPageTitle() + "CSS.css\">\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "LayoutTwo" + "CSS.css\">\n";
        text = addColorSchemeSheet(text, page);
        text = addFontSheet(text, page);
        text = addGoogleFonts(text);
        text+= "</head>\n";
        text+="<body>\n";
        text = addBanner(text, page);
        text = addNavBar(text, page);
        text+="<div class=\"content\">\n";
        text = addAllComponents(text, page);
        text = addFooter(text, page);
        text += "</div>\n\n";
        text += "</body>\n";
        text += "</html>\n";

        BufferedWriter output;
        try{
            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
            if(page == model.getSelectedPage()){
                URL = file.toURI().toURL().toString();
            }

            file.createNewFile();
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        }catch(Exception ex){
            System.out.println("Error in HTMLLayoutTwo method in HTMLGenerator Class");
        }
    }

    private void HTMLLayoutThree(Page page){
        String text = "<!DOCTYPE html>\n";
        text+="<html>\n";
        text+="<head>\n";
        text+="<script src=\"js/" + page.getPageTitle() + "JS.js\"></script>\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + page.getPageTitle() + "CSS.css\">\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "LayoutThree" + "CSS.css\">\n";
        text = addColorSchemeSheet(text, page);
        text = addFontSheet(text, page);
        text = addGoogleFonts(text);
        text+="</head>\n";
        text+="<body>\n";
        text = addBanner(text, page);
        text = addNavBarBR(text, page);
        text+="<div class=\"content\">\n";
        text = addAllComponents(text, page);
        text = addFooter(text, page);
        text += "</div>\n\n";
        text += "</body>\n";
        text += "</html>\n";

        BufferedWriter output;
        try{
            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
            if(page == model.getSelectedPage()){
                URL = file.toURI().toURL().toString();
            }

            file.createNewFile();
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        }catch(Exception ex){
            System.out.println("Error in HTMLLayoutThree method in HTMLGenerator Class");
        }
    }

    private void HTMLLayoutFour(Page page){
        String text = "<!DOCTYPE html>\n";
        text+="<html>\n";
        text+="<head>\n";
        text+="<script src=\"js/" + page.getPageTitle() + "JS.js\"></script>\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + page.getPageTitle() + "CSS.css\">\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "LayoutFour" + "CSS.css\">\n";
        text = addColorSchemeSheet(text, page);
        text = addFontSheet(text, page);
        text = addGoogleFonts(text);
        text+="</head>\n";
        text+="<body>\n";
        text = addBanner4(text, page);
        text = addNavBar(text, page);
        text+="<div class=\"content\">\n";
        text = addAllComponents(text, page);
        text = addFooter(text, page);
        text += "</div>\n\n";
        text += "</body>\n";
        text += "</html>\n";

        BufferedWriter output;
        try{
            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
            if(page == model.getSelectedPage()){
                URL = file.toURI().toURL().toString();
            }
            file.createNewFile();
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        }catch(Exception ex){
            System.out.println("Error in HTMLLayoutFour method in HTMLGenerator Class");
        }
    }

//    private void HTMLLayoutFive(Page page){
//        String text = "<!DOCTYPE html>\n";
//        text+="<html>\n";
//        text+="<head>\n";
//        text+="<script src=\"js/" + page.getPageTitle() + "JS.js\"></script>\n";
//        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + page.getPageTitle() + "CSS.css\">\n";
//        text = addGoogleFonts(text);
//        text+="</head>\n";
//        text+="<body>\n";
//        text = addBanner(text, page);
//        text = addNavBarBR(text, page);
//        text+="<div class=\"content\">\n";
//        text = addVideoComponents(text, page);
//        text = addTextComponents(text, page);
//        text = addImageComponents(text, page);
//        text = addSlideShowComponents(text, page);
//        text = addFooter(text, page);
//        text += "</div>\n\n";
//        text += "</body>\n";
//        text += "</html>\n";
//
//        BufferedWriter output;
//        try{
//            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
//            file.createNewFile();
//            output = new BufferedWriter(new FileWriter(file));
//            output.write(text);
//            output.close();
//        }catch(Exception ex){
//            System.out.println("Error in HTMLLayoutFive method in HTMLGenerator Class");
//        }
//    }


    //HELPER METHODS TO ADD IN FONTS, NAV BAR AND COMPONENTS
    private String addColorSchemeSheet(String text, Page page){
        if(page.getColor() == 1)
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "ColorOne" + "CSS.css\">\n";
        if(page.getColor() == 2)
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "ColorTwo" + "CSS.css\">\n";
        if(page.getColor() == 3)
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "ColorThree" + "CSS.css\">\n";
        if(page.getColor() == 4)
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "ColorFour" + "CSS.css\">\n";
        if(page.getColor() == 5)
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "ColorFive" + "CSS.css\">\n";

        return text;
    }

    private String addFontSheet(String text, Page page){
        if(page.getPageFont().equalsIgnoreCase("Slabo"))
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "FontOne" + "CSS.css\">\n";
        if(page.getPageFont().equalsIgnoreCase("Sans Pro"))
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "FontTwo" + "CSS.css\">\n";
        if(page.getPageFont().equalsIgnoreCase("Serif"))
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "FontThree" + "CSS.css\">\n";
        if(page.getPageFont().equalsIgnoreCase("Hind"))
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "FontFour" + "CSS.css\">\n";
        if(page.getPageFont().equalsIgnoreCase("Cantarell"))
            text += text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + "FontFive" + "CSS.css\">\n";

        return text;
    }


    private String addGoogleFonts(String text){
        text += "<link href=\'https://fonts.googleapis.com/css?family=Slabo+27px\' rel=\'stylesheet\' type=\'text/css\'>\n";
        text += "<link href=\'https://fonts.googleapis.com/css?family=Source+Sans+Pro\' rel=\'stylesheet\' type=\'text/css\'>\n";
        text += "<link href=\'https://fonts.googleapis.com/css?family=PT+Serif\' rel=\'stylesheet\' type=\'text/css\'>\n";
        text += "<link href=\'https://fonts.googleapis.com/css?family=Hind\' rel=\'stylesheet\' type=\'text/css\'>\n";
        text += "<link href=\'https://fonts.googleapis.com/css?family=Cantarell\' rel=\'stylesheet\' type=\'text/css\'>\n";
        return text;
    }

    private String addNavBar(String text, Page currentPage){
        text+="<div class=\"navigation_bar\">\n";
            for(Page page: model.getPages()){
                if(page.getPageTitle().equalsIgnoreCase(currentPage.getPageTitle())){
                    text += "<a href=\"" + page.getPageTitle() + "HTML.html\" class=\"navigation_link\"><i><b> " + page.getPageTitle() + " </b></i></a>\n";
                }
                else {
                    text += "<a href=\"" + page.getPageTitle() + "HTML.html\" class=\"navigation_link\"> " + page.getPageTitle() + " </a>\n";
                }
            }
        text+="</div>\n";
        return text;
    }

    //ADDS BR TAG AFTER EACH PAGE LINK FOR LAYOUT THAT HAS NAV BAR ON LEFT SIDE OF PAGE
    private String addNavBarBR(String text, Page currentPage){
        text+="<div class=\"navigation_bar\">\n";
        for(Page page: model.getPages()){
            if(page.getPageTitle().equalsIgnoreCase(currentPage.getPageTitle())){
                text += "<a href=\"" + page.getPageTitle() + "HTML.html\" class=\"navigation_link\"><i><b> " + page.getPageTitle() + " </b></i></a><br>\n";
            }
            else {
                text += "<a href=\"" + page.getPageTitle() + "HTML.html\" class=\"navigation_link\"> " + page.getPageTitle() + " </a><br>\n";
            }
        }
        text+="</div>\n";
        return text;
    }

    private String addBanner(String text, Page page){
        text+="<div class=\"banner\">\n";

        if(page.getBannerImageName().equalsIgnoreCase("DefaultStartSlide.png")){}
        else{
           text+="<img src=\"img/" + page.getBannerImageName() + "\" class=\"banner_image\">\n";
        }
        text+="<h1> " + model.getStudentName() + " </h1>\n";
        text+="</div>\n";
        return text;
    }

    private String addBanner4(String text, Page page){
        text+="<div class=\"banner\">\n";

        if(page.getBannerImageName().equalsIgnoreCase("DefaultStartSlide.png")){}
        else{
            text+="<h1>" +  model.getStudentName() + "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<img src=\"img/" + page.getBannerImageName() + "\" class=\"banner_image\">\n";
        }
        text+=" </h1>\n";
        text+="</div>\n";
        return text;
    }

    private String addFooter(String text, Page page){
        if(page.getFooter() == "" || page.getFooter() == null){}
        else{
            text += "<footer class=\"footer\">\n";
            text += " <p> " + page.getFooter() + " </p>\n";
            text += "</footer>";
        }
        return text;
    }

    //DYNAMICALLY ADD IN ANY AND ALL IMAGE COMPONENTS ONTO THE PAGE

    private String addAllComponents(String text, Page page){
        for(Component component: page.getAllComponents()){
            if(component.getIdentity().equalsIgnoreCase("TextComponent")){
                TextComponent textComponent = (TextComponent)component;
                if(textComponent.getTextType().equalsIgnoreCase("header"))
                    text = addHeader(text, textComponent);
                if(textComponent.getTextType().equalsIgnoreCase("paragraph"))
                    text = addParagraph(text, textComponent);
                if(textComponent.getTextType().equalsIgnoreCase("List"))
                    text = addList(text, textComponent);
            }

            if(component.getIdentity().equalsIgnoreCase("ImageComponent")){
                ImageComponent imageComponent = (ImageComponent)component;
                text = addImageComponent(text, imageComponent);
            }

            if(component.getIdentity().equalsIgnoreCase("SlideShowComponent")){
                SlideShowComponent slideShowComponent = (SlideShowComponent) component;
                text = addSlideShowComponent(text, slideShowComponent);
            }

            if(component.getIdentity().equalsIgnoreCase("VideoComponent")){
                VideoComponent videoComponent = (VideoComponent) component;
                text = addVideoComponent(text, videoComponent);
            }
        }

        return text;
    }

    //HELPER METHODS FOR ADDING EACH TYPE OF COMPONENT
    private String addHeader(String text, TextComponent textComponent){
        text+="<h3 class=\"t" + textIndex + "\"> " + textComponent.getParagraphOrHeader() + " </h3>\n\n";
        textIndex++;
        return text;
    }

    private String addParagraph(String text, TextComponent textComponent){
        text += "<p class=\"t" + textIndex + "\">&nbsp&nbsp&nbsp&nbsp\n";
        if(textComponent.getHyperLinks().size() == 0){
            text += textComponent.getParagraphOrHeader() + "\n";
        }
        else{
            StringBuilder paragraph = new StringBuilder(textComponent.getParagraphOrHeader());
            int offset = 0;
            textComponent.sortHyperLinks();
            for(HyperLinkComponent hyperLinkComponent: textComponent.getHyperLinks()){
                paragraph.insert((hyperLinkComponent.getStart() + offset), "<a href=\"" + hyperLinkComponent.getUrl() + "\">");
                offset += ("<a href=\"" + hyperLinkComponent.getUrl() + "\">").length();

                paragraph.insert((hyperLinkComponent.getEnd() + offset), "</a>");
                offset += ("</a>").length();
            }
            text += paragraph.toString() + "\n";
        }

        text += "</p>\n\n";

        textIndex++;
        return text;
    }

    private String addList(String text, TextComponent textComponent){
        text += "<ul class=\"t" + textIndex + "\">\n";
        for(String string: textComponent.getListText()){
            text += "<li> " + string + " </li>\n";
        }
        text += "</ul>\n\n";

        textIndex++;
        return text;
    }

    private String addImageComponent(String text, ImageComponent imageComponent){

        text+= "<h2 class=\"i" + imageIndex + "\">\n";
        text += "<img src=\"img/" + imageComponent.getImageName() + "\" class=\"i" + imageIndex + "\">\n";
        text += imageComponent.getCaption() + " </h2><br>\n\n";

        imageIndex++;
        return text;
    }

    private String addSlideShowComponent(String text, SlideShowComponent slideShowComponent){
        text += "<h2> " + slideShowComponent.getSlideShowTitle() + " </h2>\n";
        text += "<img src=\"img/" + slideShowComponent.getImageSlides().get(0).getImageName() + "\" class=\"s" + slideIndex + "\" id=\"s" + slideIndex + "\"" +
                "height=\"" + slideShowComponent.getImageSlides().get(0).getHeight() +"\" width=\"" + slideShowComponent.getImageSlides().get(0).getWidth() + "\">\n\n";
        text += "<p class =\"sc" + slideIndex + "\" id=\"sc" + slideIndex + "\"> " + slideShowComponent.getImageSlides().get(0).getCaption() + "</p>\n\n";

        text += "<button type=\"button\" onclick=\"previouss" + slideIndex +"()\"> <img src=\"img/Previous.png\"></button>\n";
        text += "<button type=\"button\" onclick=\"plays" + slideIndex + "()\" id=\"Play/Pauses" + slideIndex + "\"> <img src=\"img/Play.png\"></button>\n";
        text += "<button type=\"button\" onclick=\"nexts" + slideIndex +"()\"> <img src=\"img/Next.png\"></button>\n\n";


        slideIndex++;
        return text;
    }

    private String addVideoComponent(String text, VideoComponent videoComponent){

        text += "<h2> " + videoComponent.getCaption() + " </h2>\n";
        text += "<video class=\"v" + videoIndex + "\" controls>\n";
        text += "<source src=\"img/" + videoComponent.getVideoName() + "\">\n";
        text += "</video>\n";

        videoIndex++;
        return text;
    }

    public String getURL() {
        return URL;
    }
}
