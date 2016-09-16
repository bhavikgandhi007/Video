package com.initapp.vidmateguide.model;

/**
 * Created by Big_Scal on 9/16/2016.
 */
public class Items {
    private Id id;

    private String etag;

    private Snippet snippet;

    private String kind;

    public Id getId ()
    {
        return id;
    }

    public void setId (Id id)
    {
        this.id = id;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public Snippet getSnippet ()
    {
        return snippet;
    }

    public void setSnippet (Snippet snippet)
    {
        this.snippet = snippet;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", etag = "+etag+", snippet = "+snippet+", kind = "+kind+"]";
    }


    public class Id
    {
        private String videoId;

        private String kind;

        public String getVideoId ()
        {
            return videoId;
        }

        public void setVideoId (String videoId)
        {
            this.videoId = videoId;
        }

        public String getKind ()
        {
            return kind;
        }

        public void setKind (String kind)
        {
            this.kind = kind;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [videoId = "+videoId+", kind = "+kind+"]";
        }
    }

    public class Snippet
    {
        private String publishedAt;

        private String title;

        private String channelId;

        private String description;

        private String channelTitle;

        private Thumbnails thumbnails;

        private String liveBroadcastContent;

        public String getPublishedAt ()
        {
            return publishedAt;
        }

        public void setPublishedAt (String publishedAt)
        {
            this.publishedAt = publishedAt;
        }

        public String getTitle ()
        {
            return title;
        }

        public void setTitle (String title)
        {
            this.title = title;
        }

        public String getChannelId ()
        {
            return channelId;
        }

        public void setChannelId (String channelId)
        {
            this.channelId = channelId;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getChannelTitle ()
        {
            return channelTitle;
        }

        public void setChannelTitle (String channelTitle)
        {
            this.channelTitle = channelTitle;
        }

        public Thumbnails getThumbnails ()
        {
            return thumbnails;
        }

        public void setThumbnails (Thumbnails thumbnails)
        {
            this.thumbnails = thumbnails;
        }

        public String getLiveBroadcastContent ()
        {
            return liveBroadcastContent;
        }

        public void setLiveBroadcastContent (String liveBroadcastContent)
        {
            this.liveBroadcastContent = liveBroadcastContent;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [publishedAt = "+publishedAt+", title = "+title+", channelId = "+channelId+", description = "+description+", channelTitle = "+channelTitle+", thumbnails = "+thumbnails+", liveBroadcastContent = "+liveBroadcastContent+"]";
        }
    }

    public class Thumbnails
    {

        private High high;

        private Medium medium;

        public High getHigh ()
        {
            return high;
        }

        public void setHigh (High high)
        {
            this.high = high;
        }

        public Medium getMedium ()
        {
            return medium;
        }

        public void setMedium (Medium medium)
        {
            this.medium = medium;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [ high = "+high+", medium = "+medium+"]";
        }
    }

    public class High
    {
        private String height;

        private String width;

        private String url;

        public String getHeight ()
        {
            return height;
        }

        public void setHeight (String height)
        {
            this.height = height;
        }

        public String getWidth ()
        {
            return width;
        }

        public void setWidth (String width)
        {
            this.width = width;
        }

        public String getUrl ()
        {
            return url;
        }

        public void setUrl (String url)
        {
            this.url = url;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [height = "+height+", width = "+width+", url = "+url+"]";
        }
    }

    public class Medium
    {
        private String height;

        private String width;

        private String url;

        public String getHeight ()
        {
            return height;
        }

        public void setHeight (String height)
        {
            this.height = height;
        }

        public String getWidth ()
        {
            return width;
        }

        public void setWidth (String width)
        {
            this.width = width;
        }

        public String getUrl ()
        {
            return url;
        }

        public void setUrl (String url)
        {
            this.url = url;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [height = "+height+", width = "+width+", url = "+url+"]";
        }
    }

    public class Default
    {
        private String height;

        private String width;

        private String url;

        public String getHeight ()
        {
            return height;
        }

        public void setHeight (String height)
        {
            this.height = height;
        }

        public String getWidth ()
        {
            return width;
        }

        public void setWidth (String width)
        {
            this.width = width;
        }

        public String getUrl ()
        {
            return url;
        }

        public void setUrl (String url)
        {
            this.url = url;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [height = "+height+", width = "+width+", url = "+url+"]";
        }
    }

}
