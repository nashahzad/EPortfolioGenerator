package eportfoliogenerator.slideshowhelpers;

import eportfoliogenerator.StartUpConstants;

/**
 * Created by Nauman on 11/21/2015.
 */
public class Slide
{
    String imageFileName = "DefaultStartSlide.png";
    String imagePath = "./images/icons";
    String caption;

    String width;
    String height;

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setImage(String initPath, String initFileName) {
        imagePath = initPath;
        imageFileName = initFileName;
    }
}
