package com.example.mihai.moviequotesapp.async;

import android.os.AsyncTask;

import com.example.mihai.moviequotesapp.async.base.AsyncRunner;

public class AsyncRunnerImpl implements AsyncRunner {

    @Override
    public void runInBackground(Runnable action) {
        run(action);
    }

    private static void run (final Runnable action) {
        new AsyncTask<Boolean, Void, Void>() {

            @Override
            protected Void doInBackground(Boolean... booleans) {
                action.run();
                return null;
            }
        }.execute(true);
    }
}
