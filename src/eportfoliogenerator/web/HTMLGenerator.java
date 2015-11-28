package eportfoliogenerator.web;

import eportfoliogenerator.model.EPortfolioModel;

import java.io.File;

/**
 * Created by Nauman on 11/28/2015.
 */
public class HTMLGenerator
{
    //DATA MODEL WORKING WITH
    EPortfolioModel model;

    //Some strings
    String SITES_PATH = "./src/eportfoliogenerator/sites/";


    public HTMLGenerator(EPortfolioModel model){
        this.model = model;
    }
}
