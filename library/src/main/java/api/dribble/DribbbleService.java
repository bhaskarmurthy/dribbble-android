package api.dribble;

import api.dribble.model.Shot;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by bhaskar on 2014-08-25.
 */
public interface DribbbleService {
    @GET("/shots/{id}")
    Observable<Shot> getShot(@Path("id") long id);
}
