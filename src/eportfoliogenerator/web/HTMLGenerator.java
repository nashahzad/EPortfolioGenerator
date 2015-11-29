package eportfoliogenerator.web;

import eportfoliogenerator.components.*;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;

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
        }
    }

    //ADD AND ARRANGE COMPONENTS IN REGARDS TO FIRST LAYOUT SCHEME/TEMPLATE FROM HW#5
    private void HTMLLayoutOne(Page page){
        String text = "<!DOCTYPE html>\n";
        text+="<html>\n";
        text+="<head>\n";
        text+="<script src=\"js/" + page.getPageTitle() + "JS.js\"></script>\n";
        text+="<link rel=\"stylesheet\" type=\"text/css\" href=\"css/" + page.getPageTitle() + "CSS.css\">\n";
        text = addGoogleFonts(text);
        text+="</head>\n";
        text+="<body>\n";
        text = addNavBar(text, page);
        text = addBanner(text, page);
        text+="<div class=\"content\">\n";
        text = addImageComponents(text, page);
        text = addVideoComponents(text, page);
        text = addTextComponents(text, page);
        text = addSlideShowComponents(text, page);
        text = addFooter(text, page);
        text += "</div>\n\n";
        text += "</body>\n";
        text += "</html>\n";

        BufferedWriter output;
        try{
            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
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
        text = addGoogleFonts(text);
        text+="</head>\n";
        text+="<body>\n";
        text = addBanner(text, page);
        text = addNavBar(text, page);
        text+="<div class=\"content\">\n";
        text = addImageComponents(text, page);
        text = addVideoComponents(text, page);
        text = addTextComponents(text, page);
        text = addSlideShowComponents(text, page);
        text = addFooter(text, page);
        text += "</div>\n\n";
        text += "</body>\n";
        text += "</html>\n";

        BufferedWriter output;
        try{
            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
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
        text = addGoogleFonts(text);
        text+="</head>\n";
        text+="<body>\n";
        text = addBanner(text, page);
        text = addNavBarBR(text, page);
        text+="<div class=\"content\">\n";
        text = addImageComponents(text, page);
        text = addVideoComponents(text, page);
        text = addTextComponents(text, page);
        text = addSlideShowComponents(text, page);
        text = addFooter(text, page);
        text += "</div>\n\n";
        text += "</body>\n";
        text += "</html>\n";

        BufferedWriter output;
        try{
            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
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
        text = addGoogleFonts(text);
        text+="</head>\n";
        text+="<body>\n";
        text = addBanner(text, page);
        text = addNavBarBR(text, page);
        text+="<div class=\"content\">\n";
        text = addSlideShowComponents(text, page);
        text = addTextComponents(text, page);
        text = addImageComponents(text, page);
        text = addVideoComponents(text, page);
        text = addFooter(text, page);
        text += "</div>\n\n";
        text += "</body>\n";
        text += "</html>\n";

        BufferedWriter output;
        try{
            File file = new File("./src/eportfoliogenerator/sites/" + model.getePortfolioTitle() + "/" + page.getPageTitle() + "HTML.html");
            file.createNewFile();
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
            output.close();
        }catch(Exception ex){
            System.out.println("Error in HTMLLayoutFour method in HTMLGenerator Class");
        }
    }


    //HELPER METHODS TO ADD IN FONTS, NAV BAR AND COMPONENTS
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
    private String addImageComponents(String text, Page page){
        text +=  "<p>\n";
        for(ImageComponent imageComponent: page.getImageComponents()){
            text += "<h2> " + imageComponent.getCaption() + " </h2>\n";
            text += "<img src=\"img/" + imageComponent.getImageName() + "\" class=\"i" + page.getImageComponents().indexOf(imageComponent) + "\">\n";
        }
        text+= "</p>\n";
        return text;
    }

    private String addVideoComponents(String text, Page page){
        text += "<p>\n";
        for(VideoComponent videoComponent: page.getVideoComponents()){
            text += "<h2> " + videoComponent.getCaption() + " </h2>\n";
            text += "<video class=\"v" + page.getVideoComponents().indexOf(videoComponent) + "\" controls>\n";
            text += "<source src=\"img/" + videoComponent.getVideoName() + "\">\n";
            text += "</video>\n";
        }
        text += "</p>\n";
        return text;
    }

    private String addTextComponents(String text, Page page){
        for(TextComponent textComponent: page.getTextComponents()){
            //FOR IF TEXT COMPONENT IS A PARAGRAPH
            if(textComponent.getTextType().equalsIgnoreCase("paragraph")){
                text+="<p class=\"t" + page.getTextComponents().indexOf(textComponent) + "\">\n";
                if(textComponent.getHyperLinks().size() == 0){
                    text += textComponent.getParagraphOrHeader();
                }
                //FOR PARAGRAPHS THAT HAVE IN-LINE HYPERLINKS IN THEM
                else{
                    textComponent.sortHyperLinks();
                    //LOOP TO ADD IN STARTING AND ENDING TAGS USING STRINGBUILDER
                    StringBuilder stringBuilder = new StringBuilder(textComponent.getParagraphOrHeader());
                    int offset = 0;
                    for(HyperLinkComponent hyperLinkComponent: textComponent.getHyperLinks()){
                        stringBuilder.insert(hyperLinkComponent.getStart() + offset, "<a href=\"" + hyperLinkComponent.getUrl() + "\">");
                        offset += ("<a href=\"" + hyperLinkComponent.getUrl() + "\">").length();
                        stringBuilder.insert(hyperLinkComponent.getEnd() + offset, "</a>");
                        offset += ("</a>").length();
                    }
                    text += stringBuilder.toString() + "\n";
                }
                text+="</p>\n";
            }
            //IF TEXT COMPONENT IS A HEADER
            if(textComponent.getTextType().equalsIgnoreCase("header")){
                text += "<h3 class=\"t" + page.getTextComponents().indexOf(textComponent) + "\">\n";
                text += textComponent.getParagraphOrHeader() + "\n";
                text += "</h3>\n";
            }
            //IF TEXT COMPONENT IS A LIST
            if(textComponent.getTextType().equalsIgnoreCase("list")){
                text += "<ul class=\"t" + page.getTextComponents().indexOf(textComponent) + "\">\n";
                for(String string: textComponent.getListText()){
                    text += "<li> " + string + "</li>\n";
                }
                text += "</ul>\n";
            }
        }

        return text;
    }

    private String addSlideShowComponents(String text, Page page){
        for(SlideShowComponent slideShowComponent: page.getSlideShowComponents()){
            int index = page.getSlideShowComponents().indexOf(slideShowComponent);
            text += "<h2> " + slideShowComponent.getSlideShowTitle() + " </h2>\n";
            text += "<img src=\"img/" + slideShowComponent.getImageSlides().get(0).getImageName() + "\" class=\"s" + index + "\" id=\"s" + index + "\">\n\n";
            text += "<p class =\"sc" + index + "\" id=\"sc" + index + "\"> " + slideShowComponent.getImageSlides().get(0).getCaption() + "</p>\n\n";

            text += "<button type=\"button\" onclick=\"previouss" + index +"()\"> <img src=\"img/Previous.png\"></button>\n";
            text += "<button type=\"button\" onclick=\"plays" + index + "()\" id=\"Play/Pauses" + index + "\"> <img src=\"img/Play.png\"></button>\n";
            text += "<button type=\"button\" onclick=\"nexts" + index +"()\"> <img src=\"img/Next.png\"></button>\n\n";
        }
        return text;
    }

}
