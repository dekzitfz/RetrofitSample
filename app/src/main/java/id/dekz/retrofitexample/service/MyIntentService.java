package id.dekz.retrofitexample.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DEKZ on 8/12/2017.
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("onhandle", intent.getStringExtra("data"));
    }
}
