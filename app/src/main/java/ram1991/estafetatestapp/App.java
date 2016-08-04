package ram1991.estafetatestapp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class App extends Application {
    public static final int ERROR = -1;
    public static final int NO_NETWORK = 0;
    public static final int CONNECTED = 1;

    @NonNull
    public static int getNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            return CONNECTED;
        }
        return NO_NETWORK;
    }
}
