package eportfoliogenerator.model;

import eportfoliogenerator.StartUpConstants;
import eportfoliogenerator.components.ImageComponent;
import eportfoliogenerator.components.SlideShowComponent;
import eportfoliogenerator.components.TextComponent;
import eportfoliogenerator.components.VideoComponent;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/14/2015.
 */
public class Page
{
    String pageTitle = "";

    String bannerImagePath = StartUpConstants.IMAGE_ICONS_FILE_PATH;
    String bannerImageName = "DefaultStartSlide.png";

    String footer = "";

    String pageFont = "Slabo";

    int layout, color;

    //Components aspect of page
    ArrayList<TextComponent> textComponents = new ArrayList<TextComponent>();
    ArrayList<ImageComponent> imageComponents = new ArrayList<ImageComponent>();
    ArrayList<SlideShowComponent> slideShowComponents = new ArrayList<SlideShowComponent>();
    ArrayList<VideoComponent> videoComponents = new ArrayList<VideoComponent>();

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getBannerImagePath() {
        return bannerImagePath;
    }

    public void setBannerImagePath(String bannerImagePath) {
        this.bannerImagePath = bannerImagePath;
    }

    public String getBannerImageName() {
        return bannerImageName;
    }

    public void setBannerImageName(String bannerImageName) {
        this.bannerImageName = bannerImageName;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getPageFont() { return pageFont; }

    public void setPageFont(String pageFont) { this.pageFont = pageFont; }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ArrayList<TextComponent> getTextComponents() {
        return textComponents;
    }

    public void setTextComponents(ArrayList<TextComponent> textComponents) {
        this.textComponents = textComponents;
    }

    public ArrayList<ImageComponent> getImageComponents() {
        return imageComponents;
    }

    public void setImageComponents(ArrayList<ImageComponent> imageComponents) {
        this.imageComponents = imageComponents;
    }

    public ArrayList<SlideShowComponent> getSlideShowComponents() {
        return slideShowComponents;
    }

    public void setSlideShowComponents(ArrayList<SlideShowComponent> slideShowComponents) {
        this.slideShowComponents = slideShowComponents;
    }

    public ArrayList<VideoComponent> getVideoComponents() {
        return videoComponents;
    }

    public void setVideoComponents(ArrayList<VideoComponent> videoComponents) {
        this.videoComponents = videoComponents;
    }
}
