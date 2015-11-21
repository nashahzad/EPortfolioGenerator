package eportfoliogenerator.components;

import java.util.ArrayList;

/**
 * Created by Nauman on 11/21/2015.
 */
public class SlideShowComponent
{
    String slideShowTitle;
    ArrayList<ImageComponent> imageSlides = new ArrayList<ImageComponent>();

    public String getSlideShowTitle() {
        return slideShowTitle;
    }

    public void setSlideShowTitle(String slideShowTitle) {
        this.slideShowTitle = slideShowTitle;
    }

    public ArrayList<ImageComponent> getImageSlides() {
        return imageSlides;
    }

    public void setImageSlides(ArrayList<ImageComponent> imageSlides) {
        this.imageSlides = imageSlides;
    }
}
