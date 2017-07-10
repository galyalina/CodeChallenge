package com.iotta.challenge.viewpresenter.repositorydetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.iotta.challenge.Injection;
import com.iotta.challenge.R;
import com.iotta.challenge.utils.AndroidUtils;
import com.iotta.challenge.utils.Logger;

/**
 * Container Activity that has {@link DetailsFragment} inside
 */
public class DetailsActivity extends AppCompatActivity {

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
        new DetailsPresenter(repositoryId, Injection.provideRepositoriesData(), detailsFragment);

        Logger.debug("Details Fragment created");
    }

}
