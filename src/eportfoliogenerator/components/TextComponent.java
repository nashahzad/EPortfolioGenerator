package eportfoliogenerator.components;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/20/2015.
 */
public class TextComponent implements Serializable
{
    String paragraphOrHeader;
    ArrayList<String> listText = new ArrayList<String>();
    String textType;
    String textFont;
    String textSize;
    ArrayList<HyperLinkComponent> hyperLinks = new ArrayList<HyperLinkComponent>();

    public String getParagraphOrHeader() {
        return paragraphOrHeader;
    }

    public void setParagraphOrHeader(String paragraphOrHeader) {
        this.paragraphOrHeader = paragraphOrHeader;
    }

    public ArrayList<String> getListText() {
        return listText;
    }

    public void setListText(ArrayList<String> listText) {
        this.listText = listText;
    }

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public String getTextFont() {
        return textFont;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

    public String getTextSize() {
        return textSize;
    }

    public void setTextSize(String textSize) {
        this.textSize = textSize;
    }

    public ArrayList<HyperLinkComponent> getHyperLinks() {
        return hyperLinks;
    }

    public void setHyperLinks(ArrayList<HyperLinkComponent> hyperLinks) {
        this.hyperLinks = hyperLinks;
    }
}
