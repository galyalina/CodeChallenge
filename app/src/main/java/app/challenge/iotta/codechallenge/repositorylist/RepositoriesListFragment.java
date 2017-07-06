package app.challenge.iotta.codechallenge.repositorylist;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import app.challenge.iotta.codechallenge.R;
import app.challenge.iotta.codechallenge.model.pojo.Repository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A fragment representing a list of Items.
 */
public class RepositoriesListFragment extends Fragment implements RepositoriesContract.View{

    private Handler mHandler;
    private RepositoriesContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private LinearLayout mEmptyRepositoryLayout;
    private ArrayList<Repository> mRepositories;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RepositoriesListFragment() {
    }


    public static RepositoriesListFragment newInstance() {
        RepositoriesListFragment fragment = new RepositoriesListFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_repositories, container, false);
        mRecyclerView = (RecyclerView)root.findViewById(R.id.repositories_list);
        mEmptyRepositoryLayout = (LinearLayout) root.findViewById(R.id.emptyRepositoryLayout);
        mHandler = new Handler(Looper.getMainLooper());
        return root;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showRepositories(List<Repository> repositories) {
        assert mRecyclerView != null;
        mRepositories = (ArrayList<Repository>) repositories;

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                // use a linear layout manager
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(RepositoriesListFragment.this.getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mEmptyRepositoryLayout.setVisibility(View.GONE);
                mRecyclerView.setAdapter(new RepositoriesListRecyclerViewAdapter(mRepositories));
            }
        });

    }


    @Override
    public void showRepositoryDetailsUi(String  repositoryId) {
//            Intent intent = new Intent(this, RepositoryDetailActivity.class);
//            intent.putExtra(RepositoryDetailFragment.ARG_ITEM_ID, repositoryId);
//            startActivity(intent);
    }

    @Override
    public void showLoadingTasksError() {

    }

    @Override
    public void showNoRepositories() {

    }

    @Override
    public void setPresenter(RepositoriesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
