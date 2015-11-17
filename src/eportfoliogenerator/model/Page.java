package eportfoliogenerator.model;

import eportfoliogenerator.StartUpConstants;

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
}
