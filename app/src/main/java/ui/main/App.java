package ui.main;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dominik on 2017-12-09.
 */

public class App extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        App.mContext = mContext;
    }
}
