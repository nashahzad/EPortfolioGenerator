package eportfoliogenerator.model;

/**
 * Created by Nauman on 11/14/2015.
 */
public class Page
{
    String pageTitle;

    String bannerImagePath;
    String bannerImageName;

    String footer;

    String pageFont;

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
}
