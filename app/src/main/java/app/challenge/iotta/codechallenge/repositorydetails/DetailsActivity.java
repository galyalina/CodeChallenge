package app.challenge.iotta.codechallenge.repositorydetails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import app.challenge.iotta.codechallenge.Injection;
import app.challenge.iotta.codechallenge.R;
import app.challenge.iotta.codechallenge.repositorylist.RepositoriesListFragment;
import app.challenge.iotta.codechallenge.repositorylist.RepositoriesPresenter;
import app.challenge.iotta.codechallenge.utils.AndroidUtils;

public class DetailsActivity extends AppCompatActivity {

    private DetailsPresenter mTasksPresenter;
    public static final String EXTRA_REPOSITORY_ID = "REPOSITORY_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the requested task id
        String repositoryId = getIntent().getStringExtra(EXTRA_REPOSITORY_ID);

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (detailsFragment == null) {
            // Create the fragment
            detailsFragment = DetailsFragment.newInstance(repositoryId);
            AndroidUtils.addFragmentToActivity(getSupportFragmentManager(), detailsFragment, R.id.contentFrame);
        }

        // Create the presenter
        mTasksPresenter = new DetailsPresenter(repositoryId, Injection.provideRepositoriesData(getApplicationContext()), detailsFragment);
    }

}
