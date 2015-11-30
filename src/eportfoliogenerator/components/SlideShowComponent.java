package eportfoliogenerator.components;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/21/2015.
 */
public class SlideShowComponent extends Component implements Serializable
{
    String slideShowTitle;
    ArrayList<ImageComponent> imageSlides = new ArrayList<ImageComponent>();

    public SlideShowComponent(){
        identity = "SlideShowComponent";
    }

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
