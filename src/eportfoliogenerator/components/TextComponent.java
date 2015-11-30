package eportfoliogenerator.components;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nauman on 11/20/2015.
 */
public class TextComponent extends Component implements Serializable
{
    String paragraphOrHeader;
    ArrayList<String> listText = new ArrayList<String>();
    String textType;
    String textFont;
    String textSize;
    ArrayList<HyperLinkComponent> hyperLinks = new ArrayList<HyperLinkComponent>();

    public TextComponent(){
        identity = "TextComponent";
    }

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

    public void sortHyperLinks(){
        boolean flag = true;
        int tempStart;
        int tempEnd;
        String tempURL;
        while(flag){
            flag = false;
            for(int i = 0; i < hyperLinks.size()-1; i++){
                if(hyperLinks.get(i).getStart()  > hyperLinks.get(i+1).getStart()){
                    //SWAP START AND END NUMBERS ALONG WITH URL
                    tempStart = hyperLinks.get(i).getStart();
                    hyperLinks.get(i).setStart(hyperLinks.get(i+1).getStart());
                    hyperLinks.get(i+1).setStart(tempStart);

                    tempEnd = hyperLinks.get(i).getEnd();
                    hyperLinks.get(i).setEnd(hyperLinks.get(i+1).getEnd());
                    hyperLinks.get(i+1).setEnd(tempEnd);

                    tempURL = hyperLinks.get(i).getUrl();
                    hyperLinks.get(i).setUrl(hyperLinks.get(i+1).getUrl());
                    hyperLinks.get(i+1).setUrl(tempURL);
                }
            }
        }
    }
}
