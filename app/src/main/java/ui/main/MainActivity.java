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
    App.setContext(this);
    myDb=new DatabaseHelper(this);
    myDb.Destroy();

    //przykładowe dane - dla testu
    //myDb.insertByleCo();
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
      Cursor c = myDb.SelectData();
      Toast t;
      Context context = getApplicationContext();
      if (c.moveToFirst()){
          do {
              String column1 = c.getString(c.getColumnIndex("ID"));
              String column2 = c.getString(c.getColumnIndex("TITLE"));
              String column3 = c.getString(c.getColumnIndex("CONTENT"));
              String column4 = c.getString(c.getColumnIndex("DATE"));
              String column5 = c.getString(c.getColumnIndex("DESCRIPTION"));
              String column6 = c.getString(c.getColumnIndex("CATEGORY"));
              t=Toast.makeText(context,column1+", "+column2+", "+column3+", "+column4+", "+column5+", "+column6,Toast.LENGTH_LONG);
              t.show();
          } while(c.moveToNext());
      }

      c.close();
    fragmentManager.beginTransaction()
        .replace(R.id.fragment_container, currentFragment)
        .addToBackStack(null)
        .commit();
  }

}
