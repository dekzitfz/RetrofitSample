package id.dekz.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import id.dekz.retrofitexample.model.ReposResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubAPI api = retrofit.create(GithubAPI.class);

        api.getReposOfUser("mojombo")
                .enqueue(new Callback<List<ReposResponse>>() {
                    @Override
                    public void onResponse(Call<List<ReposResponse>> call, Response<List<ReposResponse>> response) {
                        if(response.body() != null){
                            for (ReposResponse repo : response.body()){
                                Log.i("repo", repo.getName());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ReposResponse>> call, Throwable t) {

                    }
                });
    }
}
