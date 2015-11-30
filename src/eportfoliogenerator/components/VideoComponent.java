package eportfoliogenerator.components;

import java.io.Serializable;

/**
 * Created by Nauman on 11/22/2015.
 */
public class VideoComponent extends Component implements Serializable
{
    String videoPath;
    String videoName;

    String caption;
    String width;
    String height;

    public VideoComponent(){
        identity = "VideoComponent";
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
