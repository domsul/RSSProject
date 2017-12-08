package ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.marci.rss_projectjava.R;

import ui.news.NewsFragment;
import ui.overview.OverviewFragment;

public class MainActivity extends AppCompatActivity implements OverviewFragmentActivityListener {

  private FragmentManager fragmentManager = getSupportFragmentManager();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setUpOverviewFragment();
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
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
