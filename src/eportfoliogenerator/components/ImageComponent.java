package eportfoliogenerator.components;

import java.io.Serializable;

/**
 * Created by Nauman on 11/21/2015.
 */
public class ImageComponent implements Serializable
{
    String imagePath;
    String imageName;

    String caption;
    String width;
    String height;

    String floatAttribute;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public String getFloatAttribute() {
        return floatAttribute;
    }

    public void setFloatAttribute(String floatAttribute) {
        this.floatAttribute = floatAttribute;
    }
}
