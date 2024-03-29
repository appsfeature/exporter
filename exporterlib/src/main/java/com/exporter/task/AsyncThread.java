package com.exporter.task;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;

import java.util.concurrent.Callable;

public abstract class AsyncThread<Params, Progress, Result> {

    @MainThread
    protected void onPreExecute() {
    }

    @WorkerThread
    @SuppressWarnings("unchecked")
    protected abstract Result doInBackground(Params... params);


    @MainThread
    protected void onPostExecute(Result result) {
    }

    @MainThread
    @SuppressWarnings("unchecked")
    protected void onProgressUpdate(Progress... values) {
    }

    @SafeVarargs
    @MainThread
    public final void execute(Params... params) {
        onPreExecute();
        TaskRunner.getInstance().executeAsync(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                return doInBackground(params);
            }
        }, new TaskRunner.Callback<Result>() {
            @Override
            public void onComplete(Result response) {
                onPostExecute(response);
            }
        });
    }

    @SafeVarargs
    @MainThread
    public final void executeOnExecutor(Object object, Params... params) {
        execute(params);
    }

    private Handler handler;

    public Handler getHandler() {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        return handler;
    }

    @SafeVarargs
    @WorkerThread
    protected final void publishProgress(Progress... values) {
        if (getHandler() != null) {
            getHandler().post(new Runnable() {
                @Override
                public void run() {
                    onProgressUpdate(values);
                }
            });
        }
    }
}
