package model;

import android.content.Context;

import java.util.List;

import database.DatabaseHelper;
import ui.main.App;

/**
 * Created by marci on 2017-12-02.
 */

public class RssObject {

  public String Status;
  public Feed feed;
  public List<Item> items;

  public void Funkcja(){
    Context context= App.getContext();
    DatabaseHelper myDb;
    myDb=new DatabaseHelper(context);

    for(Item item:items){
      String title=item.title;
      String content=item.content;
      String pubDate=item.pubDate;
      String description=item.description;
      String category=item.categories.get(0);
      myDb.insertData(title,content,pubDate,description,category);
    }
  }
}
