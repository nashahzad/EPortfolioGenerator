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

    public void moveUpSlide(){
        Slide thisSlide = this.getSelectedSlide();
        if(slides.size() == 0 || slides.size() == 1)
        {}
        else{
            int thisSlideIndex = 0;
            for(int i = 0; i < slides.size(); i++)
            {
                if(slides.get(i) == thisSlide){
                    thisSlideIndex = i;
                    break;
                }
            }
            if(thisSlideIndex == 0){}
            else{
                Slide slideHolder = slides.get(thisSlideIndex-1);
                slides.set(thisSlideIndex-1, thisSlide);
                slides.set(thisSlideIndex, slideHolder);
            }
        }
    }

    public void moveDownSlide(){
        Slide thisSlide = this.getSelectedSlide();
        if(slides.size() == 0 || slides.size() == 1)
        {}
        else{
            int thisSlideIndex = 0;
            for(int i = 0; i < slides.size(); i++)
            {
                if(slides.get(i) == thisSlide){
                    thisSlideIndex = i;
                    break;
                }
            }
            if(thisSlideIndex == (slides.size()-1)){}
            else{
                Slide slideHolder = slides.get(thisSlideIndex+1);
                slides.set(thisSlideIndex+1, thisSlide);
                slides.set(thisSlideIndex, slideHolder);
            }
        }
    }

}
