package eportfoliogenerator.web;

import eportfoliogenerator.components.Component;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.SlideShowComponent;
import eportfoliogenerator.components.VideoComponent;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/28/2015.
 */
public class GenerateDirectories
{
    //DATA MODEL TO USE
    EPortfolioModel model;

    //Some strings
    String SITES_PATH = "./src/eportfoliogenerator/sites/";
    String IMAGES_PATH = "./images/icons/";

    public GenerateDirectories(EPortfolioModel model){
        this.model = model;
    }

    public void createDirectories(){
        Path path = Paths.get(SITES_PATH + model.getePortfolioTitle());
        if(Files.exists(path)){
            deleteFile(new File(SITES_PATH + model.getePortfolioTitle()));
        }


        File directory = new File(SITES_PATH + model.getePortfolioTitle());
        directory.mkdir();

        directory = new File(SITES_PATH + model.getePortfolioTitle() + "/css");
        directory.mkdir();

        directory = new File(SITES_PATH + model.getePortfolioTitle() + "/js");
        directory.mkdir();

        directory = new File(SITES_PATH + model.getePortfolioTitle() + "/img");
        directory.mkdir();
    }

    public void copyImageFiles() throws IOException{
        Path FROM;
        Path TO;
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        //Loop through each page in model
        for(Page page: model.getPages()){
            //Loop through each existing image component
            for(Component component: page.getAllComponents()){
                if(component instanceof ImageComponent){
                    FROM = Paths.get(((ImageComponent) component).getImagePath() + ((ImageComponent) component).getImageName());
                    TO = Paths.get(SITES_PATH + "/img/" + ((ImageComponent) component).getImageName());
                    java.nio.file.Files.copy(FROM, TO, options);
                }

                if(component instanceof SlideShowComponent){
                    for(ImageComponent imageComponent: ((SlideShowComponent) component).getImageSlides()){
                        FROM = Paths.get(imageComponent.getImagePath() + imageComponent.getImageName());
                        TO = Paths.get(SITES_PATH + "/img/" + imageComponent.getImageName());
                        java.nio.file.Files.copy(FROM, TO, options);
                    }
                }
            }

            if(!page.getBannerImageName().equalsIgnoreCase("DefaultStartSlide.png") && page.getBannerImageName() != null && !page.getBannerImageName().equalsIgnoreCase("")){
                FROM = Paths.get(page.getBannerImagePath() + page.getBannerImageName());
                TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/" + page.getBannerImageName());
                java.nio.file.Files.copy(FROM, TO, options);
            }
        }

        //MOVE OVER BUTTON ICONS AS WELL
        FROM = Paths.get(IMAGES_PATH + "Play.png");
        TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/Play.png");
        java.nio.file.Files.copy(FROM, TO, options);

        FROM = Paths.get(IMAGES_PATH + "Pause.png");
        TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/Pause.png");
        java.nio.file.Files.copy(FROM, TO, options);

        FROM = Paths.get(IMAGES_PATH + "Next.png");
        TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/Next.png");
        java.nio.file.Files.copy(FROM, TO, options);

        FROM = Paths.get(IMAGES_PATH + "Previous.png");
        TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/Previous.png");
        java.nio.file.Files.copy(FROM, TO, options);
    }

    //HELPER METHOD TO RECURSIVELY DELETE EVERY FILE INSIDE OF A DIRECTORY AND THE DIRECTORY ITSELF
    private void deleteFile(File element) {
        if (element.isDirectory()) {
            for (File sub : element.listFiles()) {
                deleteFile(sub);
            }
        }
        element.delete();
    }
}
