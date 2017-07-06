package app.challenge.iotta.codechallenge;

/**
 * Created by Galya on 06/07/2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;

import app.challenge.iotta.codechallenge.model.repositoriesmgr.IRepositoriesManager;
import app.challenge.iotta.codechallenge.model.repositoriesmgr.RepositoriesManager;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of mock implementations for
 * {@link RepositoriesManager} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static IRepositoriesManager provideRepositoriesData(@NonNull Context context) {
        checkNotNull(context);
        return RepositoriesManager.getInstance(context);
    }
}
