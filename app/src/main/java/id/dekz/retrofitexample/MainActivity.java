package id.dekz.retrofitexample;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*FirebaseJobDispatcher dispatcher =
                new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));

        Job kerjaan = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("myjob")
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .build();

        dispatcher.mustSchedule(kerjaan);*/

        /*Intent i = new Intent(this, MyIntentService.class);
        i.putExtra("data", "data from activity");
        startService(i);*/

        App.getClient().getApi()
                .getWeather()
                .enqueue(new Callback<CurrentWeatherResponse>() {
                    @Override
                    public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                        if(response.body()!=null){
                            CurrentWeatherResponse data = response.body();
                            Log.i("response", data.toString());
                            saveToDB(data);
                        }else{
                            Log.w("warning", "body is null!");
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {
                        Log.w("warning", t.getLocalizedMessage());
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
