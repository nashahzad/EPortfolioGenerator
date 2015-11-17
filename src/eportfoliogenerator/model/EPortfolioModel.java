package eportfoliogenerator.model;

import eportfoliogenerator.view.EPortfolioView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 * Created by Nauman on 11/14/2015.
 */
public class EPortfolioModel implements Serializable
{
    EPortfolioView ui;

    String ePortfolioTitle = "";
    String studentName = "";

    ObservableList<Page> pages;
    Page selectedPage;

    public EPortfolioModel(EPortfolioView ui)
    {
        this.ui = ui;
        pages = FXCollections.observableArrayList();
        reset();
    }

    /**
     * Resets the EPortfolio to have no pages and a default blank title.
     */
    public void reset()
    {
        pages.clear();
        ePortfolioTitle = "";
        selectedPage = null;
    }



    public String getePortfolioTitle() {
        return ePortfolioTitle;
    }

    public void setePortfolioTitle(String ePortfolioTitle) {
        this.ePortfolioTitle = ePortfolioTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public ObservableList<Page> getPages() {
        return pages;
    }

    public void setPages(ObservableList<Page> pages) {
        this.pages = pages;
    }

    public Page getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(Page selectedPage) {
        this.selectedPage = selectedPage;
    }

    public Page getSpecificPage(String pageTitle){
        for(Page page: pages){
            if(page.getPageTitle().equalsIgnoreCase(pageTitle))
                return page;
        }

        return null;
    }
}
