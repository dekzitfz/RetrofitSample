package id.dekz.retrofitexample;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DEKZ on 8/12/2017.
 */

public class RetrofitClient {

    private GithubAPI api;

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

        api = retrofit.create(GithubAPI.class);
    }

    private OkHttpClient getClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request req = chain.request();

                        HttpUrl httpUrl = req.url().newBuilder()
                                .addQueryParameter("appid", "83003ca00bb8eec11d7976f5ee0282fd")
                                .build();

                        req = req.newBuilder()
                                .url(httpUrl)
                                .build();

                        return chain.proceed(req);
                    }
                })
                .build();
    }

    public GithubAPI getApi() {
        return api;
    }
}
