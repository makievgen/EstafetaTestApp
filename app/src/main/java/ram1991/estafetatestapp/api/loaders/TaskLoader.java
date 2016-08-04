package ram1991.estafetatestapp.api.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import ram1991.estafetatestapp.App;
import ram1991.estafetatestapp.R;
import ram1991.estafetatestapp.api.ApiFactory;
import ram1991.estafetatestapp.api.RestApi;
import ram1991.estafetatestapp.api.model.NetworkResponse;
import ram1991.estafetatestapp.api.model.Task;
import retrofit2.Call;

public class TaskLoader extends BaseLoader {

    private NetworkResponse mResponse;
    private String mUrl;
    private String mLogin;
    private String mCompanyId;
    private String mPass;

    public TaskLoader(Context context, String url, String login, String companyId, String pass) {
        super(context);
        mUrl = url;
        mLogin = login;
        mCompanyId = companyId;
        mPass = pass;
    }

    @NonNull
    @Override
    protected NetworkResponse apiCall() throws IOException {
        mResponse = new NetworkResponse();
        if (getId() == R.id.task_loader) {
            if (App.getNetworkConnection(getContext()) == App.CONNECTED) {
                try {
                    RestApi restApi = ApiFactory.getRestApi(
                            mUrl,
                            mLogin,
                            mCompanyId,
                            mPass);
                    Call<List<Task>> call = restApi.getList("");
                    List<Task> tasks = call.execute().body();
                    mResponse.setData(tasks);
                    mResponse.setState(App.CONNECTED);
                } catch (IOException e) {
                    mResponse.setState(App.ERROR);
                }
            } else {
                mResponse.setState(App.NO_NETWORK);
            }
        }
        return mResponse;
    }
}