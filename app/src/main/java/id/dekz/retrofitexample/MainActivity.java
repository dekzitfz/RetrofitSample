package id.dekz.retrofitexample;

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

import id.dekz.retrofitexample.model.APIResponse;
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

        FirebaseJobDispatcher dispatcher =
                new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));

        Job kerjaan = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("myjob")
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .build();

        dispatcher.mustSchedule(kerjaan);

        /*Intent i = new Intent(this, MyIntentService.class);
        i.putExtra("data", "data from activity");
        startService(i);*/

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
