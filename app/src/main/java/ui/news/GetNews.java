package ui.news;

import api.NewYorkTimesApi;
import model.RssObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.Links;

/**
 * Created by marci on 2017-12-02.
 */

public class GetNews implements Callback<RssObject> {

  private String link;
  private CallbackRss callback;
  private Call<RssObject> callRSSObject = null;

  private Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(Links.RETROFIT_RSS_ENDPOINT_API)
      .addConverterFactory(GsonConverterFactory.create())
      .build();

  private NewYorkTimesApi newYorkTimesApi = retrofit.create(NewYorkTimesApi.class);

  GetNews(String link, CallbackRss callback) {
    this.link = link;
    this.callback = callback;
  }

  void start() {
    if (callRSSObject != null) {
      callRSSObject.cancel();
    }

    callRSSObject = newYorkTimesApi.newsList(link);
    callRSSObject.enqueue(this);
  }

  void stop() {
    if (callRSSObject != null) {
      callRSSObject.cancel();
    }
  }

  @Override
  public void onResponse(Call<RssObject> call, Response<RssObject> response) {
    callRSSObject = null;
    if (response.isSuccessful()) {
      RssObject rssObject = response.body();
      callback.onSuccess(rssObject);
    }
  }

  @Override
  public void onFailure(Call<RssObject> call, Throwable t) {
    callRSSObject = null;
    t.printStackTrace();
  }
}
