package eportfoliogenerator.slideshowhelpers;

import java.util.ArrayList;

/**
 * Created by Nauman on 11/21/2015.
 */
public class SlideShowModel
{
    String title;
    ArrayList<Slide> slides = new ArrayList<Slide>();
    Slide selectedSlide;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Slide> getSlides() {
        return slides;
    }

    public void setSlides(ArrayList<Slide> slides) {
        this.slides = slides;
    }

    public Slide getSelectedSlide() {
        return selectedSlide;
    }

    public void setSelectedSlide(Slide selectedSlide) {
        this.selectedSlide = selectedSlide;
    }
}
