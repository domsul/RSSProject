package ui.news.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marci.rss_projectjava.R;

import java.util.ArrayList;
import java.util.List;

import model.Item;

/**
 * Created by marci on 2017-12-02.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

  private List<Item> newsList = new ArrayList<>();

  public NewsAdapter(List<Item> newsList) {
    this.newsList = newsList;
  }

  @Override
  public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
    return new NewsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final NewsViewHolder holder, int position) {
    final Item newsItem = newsList.get(position);
    holder.bindView(newsItem);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        view.getContext().startActivity(new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(newsItem.link)
            )
        );
      }
    });
  }

  @Override
  public int getItemCount() {
    return newsList.size();
  }
}
