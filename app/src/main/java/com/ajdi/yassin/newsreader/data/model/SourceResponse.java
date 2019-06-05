package com.ajdi.yassin.newsreader.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Yassin Ajdi
 * @since 6/5/2019.
 */
public class SourceResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("sources")
    private List<Source> sources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
