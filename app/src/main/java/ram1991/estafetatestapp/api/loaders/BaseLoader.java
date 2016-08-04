package ram1991.estafetatestapp.api.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import ram1991.estafetatestapp.api.model.NetworkResponse;

/**
 * Base loader with general methods
 */
public abstract class BaseLoader extends AsyncTaskLoader<NetworkResponse> {

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public NetworkResponse loadInBackground() {
        try {
            return apiCall();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected abstract NetworkResponse apiCall() throws IOException;
}
