package org.track.core.common.opencv;

/**
 * @author blindarcheology@hotmail.com
 * @version 1.0
 * @className Image
 * @description Image
 * @date 2020/10/18 4:01 PM
 **/
public class Image {

    private String id;
    private String openingUrl;
    private String secondurl;
    private int diff;

    public Image() {
    }

    public Image(String id, int diff) {
        this.id = id;
        this.diff = diff;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpeningUrl() {
        return openingUrl;
    }

    public void setOpeningUrl(String openingUrl) {
        this.openingUrl = openingUrl;
    }

    public String getSecondurl() {
        return secondurl;
    }

    public void setSecondurl(String secondurl) {
        this.secondurl = secondurl;
    }
}
