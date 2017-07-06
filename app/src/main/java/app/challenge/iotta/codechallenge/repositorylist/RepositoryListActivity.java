package app.challenge.iotta.codechallenge.repositorylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import app.challenge.iotta.codechallenge.Injection;
import app.challenge.iotta.codechallenge.R;
import app.challenge.iotta.codechallenge.utils.AndroidUtils;

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

    private static final String CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY";
    private RepositoriesPresenter mTasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        RepositoriesListFragment tasksFragment = (RepositoriesListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            // Create the fragment
            tasksFragment = RepositoriesListFragment.newInstance();
            AndroidUtils.addFragmentToActivity(getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }

        // Create the presenter
        mTasksPresenter = new RepositoriesPresenter(Injection.provideRepositoriesData(getApplicationContext()), tasksFragment);


        // Load previously saved state, if available.
        if (savedInstanceState != null) {
            RepositoryFilterType currentFiltering = (RepositoryFilterType) savedInstanceState.getSerializable(CURRENT_FILTERING_KEY);
            mTasksPresenter.setFiltering(currentFiltering);
        }
    }

}
