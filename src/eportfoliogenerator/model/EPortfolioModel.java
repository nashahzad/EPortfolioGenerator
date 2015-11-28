package eportfoliogenerator.model;

import eportfoliogenerator.view.EPortfolioView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/14/2015.
 */
public class EPortfolioModel
{
    String ePortfolioTitle = "";
    String studentName = "";

    ArrayList<Page> pages;
    Page selectedPage;

    public EPortfolioModel()
    {
        pages = new ArrayList<Page>();
        //pages = FXCollections.observableArrayList();
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

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
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
