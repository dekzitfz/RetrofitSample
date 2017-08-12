package id.dekz.retrofitexample;

import java.util.List;

import id.dekz.retrofitexample.model.APIResponse;
import id.dekz.retrofitexample.model.ReposResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by DEKZ on 8/10/2017.
 */

public interface GithubAPI {

    @GET("users")
    Call<List<APIResponse>> getUsers();

    @GET("users/{username}/repos")
    Call<List<ReposResponse>> getReposOfUser(@Path("username") String username);
}
