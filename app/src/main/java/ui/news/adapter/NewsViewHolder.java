package ui.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.marci.rss_projectjava.R;

import model.Item;

/**
 * Created by marci on 2017-12-02.
 */

class NewsViewHolder extends RecyclerView.ViewHolder {

  private TextView newsTitle;
  private TextView newsPubDate;
  private TextView newsContent;


  NewsViewHolder(View itemView) {
    super(itemView);
    newsTitle = itemView.findViewById(R.id.txtTitle);
    newsPubDate = itemView.findViewById(R.id.txtPubdate);
    newsContent = itemView.findViewById(R.id.txtContent);
  }

  void bindView(Item item) {
    newsTitle.setText(item.title);
    newsPubDate.setText(item.pubDate);
    newsContent.setText(item.content);
  }
}
