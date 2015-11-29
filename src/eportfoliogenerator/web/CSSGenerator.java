package eportfoliogenerator.web;

import eportfoliogenerator.model.EPortfolioModel;
import eportfoliogenerator.model.Page;

/**
 * Created by Nauman on 11/28/2015.
 */
public class CSSGenerator
{
    //DATA MODEL WORKING WITH
    EPortfolioModel model;

    //Some strings
    String SITES_PATH = "./src/eportfoliogenerator/sites/";


    public CSSGenerator(EPortfolioModel model){
        this.model = model;
    }

    private void CSSColorOne(Page page){
        String text = new String();

        text += "body {\n";
        text += "background: #e6f0a3;\n";
        text += "background: -moz-linear-gradient(top, #e6f0a3 0%, #d2e638 50%, #c3d825 51%, #dbf043 100%);\n";
        text += "background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#e6f0a3), color-stop(50%,#d2e638), color-stop(51%,#c3d825), color-stop(100%,#dbf043));\n";
        text += "background: -webkit-linear-gradient(top,  #e6f0a3 0%,#d2e638 50%,#c3d825 51%,#dbf043 100%);\n";
        text += "background: -o-linear-gradient(top,  #e6f0a3 0%,#d2e638 50%,#c3d825 51%,#dbf043 100%);";
        text += "background: -ms-linear-gradient(top,  #e6f0a3 0%,#d2e638 50%,#c3d825 51%,#dbf043 100%);\n";
        text += "background: linear-gradient(to bottom,  #e6f0a3 0%,#d2e638 50%,#c3d825 51%,#dbf043 100%);\n";
        text += "filter: progid:DXImageTransform.Microsoft.gradient( startColorstr=\'#e6f0a3\', endColorstr=\'#dbf043\',GradientType=0);\n}\n";
    }
}
