package api.dribble.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by bhaskar on 2014-08-25.
 */
public class Shot {
    public int id;
    public String title;
    public String description;

    public String url;

    @SerializedName("short_url")
    public String shortUrl;

    @SerializedName("image_url")
    public String imageUrl;

    @SerializedName("image_teaser_url")
    public String imageTeaserUrl;

    public int width;
    public int height;

    @SerializedName("views_count")
    public int viewsCount;

    @SerializedName("likes_count")
    public int likesCount;

    @SerializedName("comments_count")
    public int commentsCount;

    @SerializedName("rebounds_count")
    public int reboundsCount;

    @SerializedName("rebound_source_id")
    public long reboundSourceId;

    @SerializedName("created_at")
    public Date createdAt;

    public Player player;
}
