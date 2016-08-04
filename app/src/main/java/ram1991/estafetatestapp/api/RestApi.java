package ram1991.estafetatestapp.api;

import java.util.List;

import ram1991.estafetatestapp.api.model.Task;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestApi {
    @GET()
    Call<List<Task>> getList(@Url String url);
}

