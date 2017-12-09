package ui.main;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.marci.rss_projectjava.R;

import database.DatabaseHelper;
import ui.news.NewsFragment;
import ui.overview.OverviewFragment;

public class MainActivity extends AppCompatActivity implements OverviewFragmentActivityListener {

  private FragmentManager fragmentManager = getSupportFragmentManager();
  public DatabaseHelper myDb;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setUpOverviewFragment();
    myDb=new DatabaseHelper(this);
    myDb.insertByleCo();
    Cursor c=myDb.SelectData();
    Toast t;
      Context context = getApplicationContext();
      if (c.moveToFirst()){
          do {
              String column1 = c.getString(0);
              String column2 = c.getString(1);
              String column3 = c.getString(2);
              t=Toast.makeText(context,column1+", "+column2+", "+column3,Toast.LENGTH_LONG);
              t.show();
          } while(c.moveToNext());
      }
      c.close();
      myDb.DeleteAllData();
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {    super.onRestoreInstanceState(savedInstanceState);
    onCreate(savedInstanceState);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  private void setUpOverviewFragment() {
    fragmentManager.beginTransaction()
        .replace(R.id.fragment_container, new OverviewFragment())
        .commit();
  }

  @Override
  public void onClickItem(String url) {
    Bundle bundle = new Bundle();
    bundle.putString("newsUrl", url);
    Fragment currentFragment = new NewsFragment();

    currentFragment.setArguments(bundle);
    fragmentManager.beginTransaction()
        .replace(R.id.fragment_container, currentFragment)
        .addToBackStack(null)
        .commit();
  }

}
