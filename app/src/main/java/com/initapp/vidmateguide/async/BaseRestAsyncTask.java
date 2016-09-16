package com.initapp.vidmateguide.async;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;


import com.initapp.vidmateguide.model.Result;
import retrofit.RetrofitError;


@TargetApi(Build.VERSION_CODES.CUPCAKE)
public abstract class BaseRestAsyncTask<Params, ResultClass> extends AsyncTask<Params, Void, Result<ResultClass>> {

    public static final String TAG = BaseRestAsyncTask.class.getSimpleName();

    @Override
    protected void onPostExecute(Result<ResultClass> result) {
        if (result.getError() != null) {
            onFailure(result.getError());
        } else {
            onSuccess(result.getResource());
        }
    }

    public abstract void onFailure(RetrofitError error);

    public abstract void onSuccess(ResultClass result);

}
