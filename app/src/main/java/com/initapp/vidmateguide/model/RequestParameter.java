package com.initapp.vidmateguide.model;

import java.io.Serializable;

/**
 * Created by Big_Scal on 9/22/2016.
 */
public class RequestParameter implements Serializable {

    private String part;
    private String order;
    private String maxResults;
    private String q;
    private String chart;
    private String channelId;
    private String videoCategoryId;
    private String type;
    private String regionCode;
    private String nextPageToken;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(String maxResults) {
        this.maxResults = maxResults;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getVideoCategoryId() {
        return videoCategoryId;
    }

    public void setVideoCategoryId(String videoCategoryId) {
        this.videoCategoryId = videoCategoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
