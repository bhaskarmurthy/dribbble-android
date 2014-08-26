package api.dribble.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by bhaskar on 2014-08-26.
 */
public class Comment {
    public long id;
    public String body;

    @SerializedName("likes_count")
    public int likesCount;

    @SerializedName("created_at")
    public Date createdAt;

    public Player player;
}
