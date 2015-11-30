package eportfoliogenerator.components;

import java.io.Serializable;

/**
 * Created by Nauman on 11/30/2015.
 */
public abstract class Component implements Serializable
{
    String identity;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
