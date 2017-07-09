package com.iotta.challenge;

/**
 * Created by Galya on 06/07/2017.
 */

import com.iotta.challenge.model.repositoriesmgr.IRepositoriesManager;
import com.iotta.challenge.model.repositoriesmgr.RepositoriesManager;

/**
 * Enables injection of mock implementations for
 * {@link RepositoriesManager} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static IRepositoriesManager provideRepositoriesData() {
        return RepositoriesManager.getInstance();
    }
}
