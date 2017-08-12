package id.dekz.retrofitexample.service;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by DEKZ on 8/12/2017.
 */

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.i("onStartJob", "Ada internet!!!");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        Log.i("onStopJob", "stopped");
        return true;
    }
}
