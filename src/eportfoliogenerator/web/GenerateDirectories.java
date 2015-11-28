package eportfoliogenerator.web;

import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.SlideShowComponent;
import eportfoliogenerator.components.VideoComponent;
import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/28/2015.
 */
public class GenerateDirectories
{
    //DATA MODEL TO USE
    EPortfolioModel model;

    //Use this to prevent duplicate images from being added to same directory
    ArrayList<String> imageNames = new ArrayList<String>();

    //Some strings
    String SITES_PATH = "./src/eportfoliogenerator/sites/";
    String IMAGES_PATH = "./images/icons/";

    public GenerateDirectories(EPortfolioModel model){
        this.model = model;
    }

    public void createDirectories(){
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
        //Loop through each page in model
        for(Page page: model.getPages()){
            //Loop through each existing image component
            for(ImageComponent imageComponent: page.getImageComponents()){
                    FROM = Paths.get(imageComponent.getImagePath() + imageComponent.getImageName());
                    TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/" + imageComponent.getImageName());
                    CopyOption[] options = new CopyOption[]{
                            StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.COPY_ATTRIBUTES
                    };
                    java.nio.file.Files.copy(FROM, TO, options);
            }

            for(SlideShowComponent slideShowComponent: page.getSlideShowComponents()){
                for(ImageComponent imageComponent: slideShowComponent.getImageSlides()){
                    FROM = Paths.get(imageComponent.getImagePath() + imageComponent.getImageName());
                    TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/" + imageComponent.getImageName());
                    CopyOption[] options = new CopyOption[]{
                            StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.COPY_ATTRIBUTES
                    };
                    java.nio.file.Files.copy(FROM, TO, options);
                }
            }

            for(VideoComponent videoComponent: page.getVideoComponents()){
                FROM = Paths.get(videoComponent.getVideoPath() + videoComponent.getVideoName());
                TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/" + videoComponent.getVideoName());
                CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                };
                java.nio.file.Files.copy(FROM, TO, options);
            }
        }

        //MOVE OVER BUTTON ICONS AS WELL
        FROM = Paths.get(IMAGES_PATH + "Play.png");
        TO = Paths.get(SITES_PATH + model.getePortfolioTitle() + "/img/Play.png");
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
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
}
