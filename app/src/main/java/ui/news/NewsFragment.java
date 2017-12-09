package ui.news;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.marci.rss_projectjava.R;

import model.RssObject;
import ui.news.adapter.NewsAdapter;

/**
 * Created by marci on 2017-12-02.
 */

public class NewsFragment extends Fragment implements CallbackRss {

  private RecyclerView newsRecyclerView;
  private GetNews getNews = null;
  private ProgressBar progressBar;
  private Toolbar toolbarNews;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    getNews = new GetNews(getArguments().getString("newsUrl", ""), this);
    return inflater.inflate(R.layout.fragment_news, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    toolbarNews = view.findViewById(R.id.toolbarNews);
    progressBar = view.findViewById(R.id.progressBar);
    newsRecyclerView = view.findViewById(R.id.recyclerView);
    newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    getNews.start();
  }

  @Override
  public void onSuccess(RssObject rss) {
      rss.Funkcja();
      toolbarNews.setTitle(rss.feed.title.split(";")[1]);
      progressBar.setVisibility(View.INVISIBLE);
      NewsAdapter newsAdapter = new NewsAdapter(rss.items);
      newsRecyclerView.setAdapter(newsAdapter);
      newsAdapter.notifyDataSetChanged();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    getNews.stop();
  }
}
