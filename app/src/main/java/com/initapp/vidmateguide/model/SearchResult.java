package com.initapp.vidmateguide.model;

import java.util.ArrayList;

/**
 * Created by Big_Scal on 9/16/2016.
 */
public class SearchResult {
    private String regionCode;

    private String etag;

    private ArrayList<Items> items;

    private PageInfo pageInfo;

    private String nextPageToken;

    private String kind;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "ClassPojo [regionCode = " + regionCode + ", etag = " + etag + ", items = " + items + ", pageInfo = " + pageInfo + ", nextPageToken = " + nextPageToken + ", kind = " + kind + "]";
    }

    public class PageInfo {
        private String totalResults;

        private String resultsPerPage;

        public String getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(String totalResults) {
            this.totalResults = totalResults;
        }

        public String getResultsPerPage() {
            return resultsPerPage;
        }

        public void setResultsPerPage(String resultsPerPage) {
            this.resultsPerPage = resultsPerPage;
        }

        @Override
        public String toString() {
            return "ClassPojo [totalResults = " + totalResults + ", resultsPerPage = " + resultsPerPage + "]";
        }
    }
}
