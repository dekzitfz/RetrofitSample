package id.dekz.retrofitexample;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by DEKZ on 8/24/2017.
 */

public class MyIdlingRes implements IdlingResource {

    @Nullable
    private volatile ResourceCallback mCallback;

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    //if state = idle (true), call onTransitionToIdle()
    public void setIdleState(boolean state){
        mIsIdleNow.set(state);
        if (state && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
}
