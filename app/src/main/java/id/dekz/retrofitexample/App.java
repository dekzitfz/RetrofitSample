package id.dekz.retrofitexample;

import android.app.Application;
import android.content.Context;

/**
 * Created by DEKZ on 8/12/2017.
 */

public class App extends Application {

    private static RetrofitClient client;

    @Override
    public void onCreate() {
        super.onCreate();

        client = new RetrofitClient(getApplicationContext());
    }

    public static RetrofitClient getClient() {
        return client;
    }

    public Context getContext(){
        return getApplicationContext();
    }

}
