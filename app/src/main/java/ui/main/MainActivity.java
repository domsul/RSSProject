package ui.main;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marci.rss_projectjava.R;

import database.DatabaseHelper;
import ui.news.NewsFragment;
import ui.overview.OverviewFragment;

public class MainActivity extends AppCompatActivity implements OverviewFragmentActivityListener {

  private FragmentManager fragmentManager = getSupportFragmentManager();
  private Button btnCheck;
  public DatabaseHelper myDb;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setUpOverviewFragment();
    App.setContext(this);
    myDb=new DatabaseHelper(this);

    //usuwanie tabeli z bazy
    //myDb.Destroy();

    //przykładowe dane - dla testu
    //myDb.insertByleCo();
      myDb.DeleteAllData();

      btnCheck = (Button) findViewById(R.id.btn_check);

      // Manually checking internet connection
      checkConnection();

      btnCheck.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              // Manually checking internet connection
              checkConnection();
          }
      });
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
              //przetworzenie danych pobranych z kursora na swój sposób
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
    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int mColor;

        if (isConnected) {
            message = "Good! Connected to Internet";
            mColor = Color.GREEN;
        } else {
            message = "Sorry! Not connected to internet";
            mColor = Color.RED;
        }

        Toast toast =  Toast.makeText(this, message, Toast.LENGTH_LONG);

        TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
        toastMessage.setTextColor(mColor);
        toast.show();
    }
}
