package ui.overview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marci.rss_projectjava.R;

import ui.main.OverviewFragmentActivityListener;
import utils.Links;

/**
 * Created by marci on 2017-12-02.
 */

public class OverviewFragment extends Fragment {

  private OverviewFragmentActivityListener listener = null;
  private Button technologyNewsBtn;
  private Button scienceNewsBtn;
  private Button europeanNewsBtn;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OverviewFragmentActivityListener) {
      listener = (OverviewFragmentActivityListener) context;
    } else {
      throw new ClassCastException("context: " + context.toString() + " has to implement interface: OverviewFragmentActivityListener");
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_overview, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    setUpButtons(view);
    setUpListenersForButtons();
  }

  private void setUpButtons(View view) {
    technologyNewsBtn = view.findViewById(R.id.technologyNewsBtn);
    scienceNewsBtn = view.findViewById(R.id.scienceNewsBtn);
    europeanNewsBtn = view.findViewById(R.id.europeanNewsBtn);
  }

  private void setUpListenersForButtons() {
    technologyNewsBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onItemSelected(Links.TECHNOLOGY_NEWS_LINK);
      }
    });
    scienceNewsBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onItemSelected(Links.SCIENCE_NEWS_LINK);
      }
    });
    europeanNewsBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onItemSelected(Links.EUROPEAN_NEWS_LINK);
      }
    });
  }

  private void onItemSelected(String url) {
    listener.onClickItem(url);
  }
}
