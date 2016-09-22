package com.initapp.vidmateguide.model;

import java.util.ArrayList;

/**
 * Created by Big_Scal on 9/22/2016.
 */
public class VideoResult {
    private String etag;

    private ArrayList<Items> items;

    private String kind;

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "ClassPojo [etag = " + etag + ", items = " + items + ", kind = " + kind + "]";
    }

    public class Items {
        private String id;

        private String etag;

        private Snippet snippet;

        private String kind;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        @Override
        public String toString() {
            return "ClassPojo [id = " + id + ", etag = " + etag + ", snippet = " + snippet + ", kind = " + kind + "]";
        }
    }

    public class Snippet {
        private String[] tags;

        private String publishedAt;

        private String title;

        private String channelId;

        private String defaultAudioLanguage;

        private String description;

        private String categoryId;

        private String channelTitle;

        private Thumbnails thumbnails;

        private String liveBroadcastContent;


        public String[] getTags() {
            return tags;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getDefaultAudioLanguage() {
            return defaultAudioLanguage;
        }

        public void setDefaultAudioLanguage(String defaultAudioLanguage) {
            this.defaultAudioLanguage = defaultAudioLanguage;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public void setChannelTitle(String channelTitle) {
            this.channelTitle = channelTitle;
        }

        public String getLiveBroadcastContent() {
            return liveBroadcastContent;
        }

        public void setLiveBroadcastContent(String liveBroadcastContent) {
            this.liveBroadcastContent = liveBroadcastContent;
        }

        public Thumbnails getThumbnails() {
            return thumbnails;
        }

        public void setThumbnails(Thumbnails thumbnails) {
            this.thumbnails = thumbnails;
        }


        @Override
        public String toString() {
            return "ClassPojo [tags = " + tags + ", publishedAt = " + publishedAt + ", title = " + title + ", channelId = " + channelId + ", defaultAudioLanguage = " + defaultAudioLanguage + ", description = " + description + ", categoryId = " + categoryId + ", channelTitle = " + channelTitle + ",  liveBroadcastContent = " + liveBroadcastContent + " ]";
        }
    }

    public class Thumbnails {

        private Standard standard;

        private High high;


        private Medium medium;


        public Standard getStandard() {
            return standard;
        }

        public void setStandard(Standard standard) {
            this.standard = standard;
        }

        public High getHigh() {
            return high;
        }

        public void setHigh(High high) {
            this.high = high;
        }

        public Medium getMedium() {
            return medium;
        }

        public void setMedium(Medium medium) {
            this.medium = medium;
        }

    }

    public class Standard {
        private String height;

        private String width;

        private String url;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ClassPojo [height = " + height + ", width = " + width + ", url = " + url + "]";
        }
    }

    public class High {
        private String height;

        private String width;

        private String url;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ClassPojo [height = " + height + ", width = " + width + ", url = " + url + "]";
        }
    }

    public class Medium {
        private String height;

        private String width;

        private String url;

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ClassPojo [height = " + height + ", width = " + width + ", url = " + url + "]";
        }
    }


}
