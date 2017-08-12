package id.dekz.retrofitexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import id.dekz.retrofitexample.model.APIResponse;
import id.dekz.retrofitexample.service.MyIntentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, MyIntentService.class);
        i.putExtra("data", "data from activity");
        startService(i);

        /*App.getClient().getApi()
                .getUsers()
                .enqueue(new Callback<List<APIResponse>>() {
                    @Override
                    public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                        for(APIResponse user : response.body()){
                            Log.i("user", user.getLogin());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<APIResponse>> call, Throwable t) {

                    }
                });*/
    }
}
