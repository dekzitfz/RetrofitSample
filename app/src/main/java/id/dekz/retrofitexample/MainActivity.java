package id.dekz.retrofitexample;

import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.ArrayList;
import java.util.List;

import id.dekz.retrofitexample.data.WeatherContract;
import id.dekz.retrofitexample.model.APIResponse;
import id.dekz.retrofitexample.model.OpenWeatherModel;
import id.dekz.retrofitexample.model.weather.CurrentWeatherResponse;
import id.dekz.retrofitexample.service.MyIntentService;
import id.dekz.retrofitexample.service.MyJobService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ListAdapter adapter;

    @Nullable
    private MyIdlingRes idlingResource;

    @VisibleForTesting
    @NonNull
    public MyIdlingRes getIdlingResource(){
        if(idlingResource == null) idlingResource = new MyIdlingRes();
        return idlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIdlingResource();

        lv = (ListView) findViewById(R.id.lv);
        adapter = new ListAdapter();
        lv.setAdapter(adapter);
        loadData();
    }

    private void loadData(){
        idlingResource.setIdleState(false);

        App.getClient().getApi()
                .getUsers()
                .enqueue(new Callback<List<APIResponse>>() {
                    @Override
                    public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                        if(response.body()!=null){
                            adapter.setData(response.body());
                        }else{
                            Toast.makeText(MainActivity.this, "response body is null!", Toast.LENGTH_SHORT).show();
                        }
                        idlingResource.setIdleState(true);
                    }

                    @Override
                    public void onFailure(Call<List<APIResponse>> call, Throwable t) {
                        idlingResource.setIdleState(true);
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveToDB(CurrentWeatherResponse data){
        getContentResolver().delete(WeatherContract.WeatherEntry.CONTENT_URI,null,null);

        ContentValues cv = new ContentValues();
        cv.put(WeatherContract.WeatherEntry.WEATHER_ID, data.getId());
        cv.put(WeatherContract.WeatherEntry.WEATHER_DATE, String.valueOf(data.getDt()));
        cv.put(WeatherContract.WeatherEntry.WEATHER_DESC, data.getWeather().get(0).getDescription());
        cv.put(WeatherContract.WeatherEntry.WEATHER_TEMP, data.getMain().getTemp());

        getContentResolver().insert(WeatherContract.WeatherEntry.CONTENT_URI, cv);
    }
}
