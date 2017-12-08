package api;

import model.RssObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marci on 2017-12-02.
 */

public interface NewYorkTimesApi {

  @GET("api.json/")
  Call<RssObject> newsList(@Query("rss_url") String link);
}
