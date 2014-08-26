package api.dribble;

import api.dribble.model.Comments;
import api.dribble.model.Player;
import api.dribble.model.Players;
import api.dribble.model.Shot;
import api.dribble.model.Shots;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by bhaskar on 2014-08-25.
 */
public interface DribbbleService {
    @GET("/shots/{id}")
    Observable<Shot> getShot(@Path("id") long id);

    @GET("/shots/{id}/rebounds")
    Observable<Shots> getRebounds(@Path("id") long id);

    @GET("/shots/{id}/rebounds")
    Observable<Shots> getRebounds(@Path("id") long id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("/shots/{id}/comments")
    Observable<Comments> getComments(@Path("id") long id);

    @GET("/shots/{id}/comments")
    Observable<Comments> getComments(@Path("id") long id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("/shots/popular")
    Observable<Shots> getPopularShots();

    @GET("/shots/popular")
    Observable<Shots> getAlPopularShots(@Query("page") int page, @Query("per_page") int perPage);

    @GET("/shots/debuts")
    Observable<Shots> getNewShots();

    @GET("/shots/debuts")
    Observable<Shots> getNewShots(@Query("page") int page, @Query("per_page") int perPage);

    @GET("/shots/everyone")
    Observable<Shots> getAllShots();

    @GET("/shots/everyone")
    Observable<Shots> getAllShots(@Query("page") int page, @Query("per_page") int perPage);

    @GET("/players/{id}/shots")
    Observable<Shots> getShotsForPlayer(@Path("id") String id);

    @GET("/players/{id}/shots")
    Observable<Shots> getShotsForPlayer(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("/players/{id}/shots/following")
    Observable<Shots> getFollowingShotsForPlayer(@Path("id") String id);

    @GET("/players/{id}/shots/following")
    Observable<Shots> getFollowingShotsForPlayer(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("/players/{id}/shots/likes")
    Observable<Shots> getLikesShotsForPlayer(@Path("id") String id);

    @GET("/players/{id}/shots/likes")
    Observable<Shots> getLikesShotsForPlayer(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("/players/{id}")
    Observable<Player> getPlayer(@Path("id") String id);

    @GET("/players/{id}/followers")
    Observable<Players> getFollowersForPlayer(@Path("id") String id);

    @GET("/players/{id}/followers")
    Observable<Players> getFollowersForPlayer(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("/players/{id}/following")
    Observable<Players> getFollowingForPlayer(@Path("id") String id);

    @GET("/players/{id}/following")
    Observable<Players> getFollowingForPlayer(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("/players/{id}/draftees")
    Observable<Players> getDrafteesForPlayer(@Path("id") String id);

    @GET("/players/{id}/draftees")
    Observable<Players> getDrafteesForPlayer(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);
}
