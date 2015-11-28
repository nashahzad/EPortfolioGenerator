package eportfoliogenerator.components;

import java.io.Serializable;

/**
 * Created by Nauman on 11/20/2015.
 */
public class HyperLinkComponent
{
    String url;
    int start;
    int end;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
