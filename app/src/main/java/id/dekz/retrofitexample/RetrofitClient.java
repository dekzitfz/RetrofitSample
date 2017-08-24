package id.dekz.retrofitexample;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
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
    private Context context;

    public RetrofitClient(Context context) {
        this.context = context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();

        api = retrofit.create(GithubAPI.class);
    }

    private OkHttpClient getClient(){
        File cacheFile = new File(context.getCacheDir(), "mycache");
        int cacheSize = 10 * 1024 * 1024;
        Cache myCache = new Cache(cacheFile, cacheSize);

        return new OkHttpClient.Builder()
                .cache(myCache)
                .addInterceptor(new OfflineInterceptor())
                .build();
    }

    class OfflineInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(30, TimeUnit.SECONDS)
                        .build();

                request = request.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build();

            return chain.proceed(request);
        }
    }

    class UrlInterceptor implements Interceptor{

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
    }

    public GithubAPI getApi() {
        return api;
    }
}
