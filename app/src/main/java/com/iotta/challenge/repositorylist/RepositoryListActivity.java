package com.iotta.challenge.repositorylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.iotta.challenge.Injection;
import com.iotta.challenge.R;
import com.iotta.challenge.utils.AndroidUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An activity representing a list of Repositories. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link } representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RepositoryListActivity extends AppCompatActivity{

    private RepositoriesPresenter mTasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

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
        mTasksPresenter = new RepositoriesPresenter(Injection.provideRepositoriesData(getApplicationContext()), repositoriesListFragment);
    }

}
