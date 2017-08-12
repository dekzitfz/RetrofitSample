package id.dekz.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import id.dekz.retrofitexample.model.APIResponse;
import id.dekz.retrofitexample.model.OpenWeatherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getClient().getApi()
                .getWeather()
                .enqueue(new Callback<OpenWeatherModel>() {
                    @Override
                    public void onResponse(Call<OpenWeatherModel> call, Response<OpenWeatherModel> response) {
                        Log.i("weather", response.body().getName());
                    }

                    @Override
                    public void onFailure(Call<OpenWeatherModel> call, Throwable t) {

                    }
                });
    }
}
