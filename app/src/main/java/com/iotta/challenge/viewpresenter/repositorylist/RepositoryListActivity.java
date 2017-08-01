package com.iotta.challenge.viewpresenter.repositorylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.iotta.challenge.Injection;
import com.iotta.challenge.R;
import com.iotta.challenge.utils.AndroidUtils;
import com.iotta.challenge.utils.Logger;

/**
 * An activity representing a list of Repositories, which when touched,
 * lead to a {@link com.iotta.challenge.viewpresenter.repositorydetails.DetailsActivity} representing
 * item details.
 */
public class RepositoryListActivity extends AppCompatActivity{

    private RepositoriesPresenter mTasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Logger.debug("RepositoryListActivity created");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        RepositoriesListFragment repositoriesListFragment = (RepositoriesListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (repositoriesListFragment == null) {
            // Create the fragment
            repositoriesListFragment = RepositoriesListFragment.newInstance();
            AndroidUtils.addFragmentToActivity(getSupportFragmentManager(), repositoriesListFragment, R.id.contentFrame);
        }

        // Create the presenter
        mTasksPresenter = new RepositoriesPresenter(Injection.provideRepositoriesData(), repositoriesListFragment);
    }

}
