package api.dribble.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by bhaskar on 2014-08-25.
 */
public class Player {
    public long id;
    public String name;
    public String username;

    public String url;

    @SerializedName("avatar_url")
    public String avatarUrl;

    public String location;

    @SerializedName("twitter_screen_name")
    public String twitterScreenName;

    @SerializedName("drafted_by_player_id")
    public String draftedByPlayerId;

    @SerializedName("shots_count")
    public int shotsCount;

    @SerializedName("draftees_count")
    public int drafteesCount;

    @SerializedName("followers_count")
    public int followersCount;

    @SerializedName("following_count")
    public int followingCount;

    @SerializedName("comments_count")
    public int commentsCount;

    @SerializedName("comments_received_count")
    public int commentsReceivedCount;

    @SerializedName("likes_count")
    public int likesCount;

    @SerializedName("likes_received_count")
    public int likesReceivedCount;

    @SerializedName("rebounds_count")
    public int reboundsCount;

    @SerializedName("rebounds_received_count")
    public int reboundsReceivedCount;

    @SerializedName("created_at")
    public Date createdAt;
}
