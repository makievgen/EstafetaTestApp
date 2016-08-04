package ram1991.estafetatestapp.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiFactory {
    private static final String AUTHORIZATION = "Authorization";
    private static String sUrl;
    private static String sUsername;
    private static String sCompanyId;
    private static String sPassword;
    private static RestApi sRestApi;

    public static RestApi getRestApi(String url, String username, String companyId, String password) {
        ApiFactory.sUrl = url;
        ApiFactory.sUsername = username;
        ApiFactory.sCompanyId = companyId;
        ApiFactory.sPassword = password;
        sRestApi = getRetrofit().create(RestApi.class);
        return sRestApi;
    }

    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(sUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClient(sUsername, sCompanyId, sPassword))
                .build();
    }

    private static OkHttpClient createHttpClient(final String username, final String companyId, final String password) {
        return new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        String credential = Credentials.basic(username, companyId, password);
                        return response.request().newBuilder()
                                .header(AUTHORIZATION, credential)
                                .build();
                    }
                })
                .build();
    }
}