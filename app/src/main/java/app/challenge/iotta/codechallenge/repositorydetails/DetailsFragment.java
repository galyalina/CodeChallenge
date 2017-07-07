package app.challenge.iotta.codechallenge.repositorydetails;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.challenge.iotta.codechallenge.R;
import app.challenge.iotta.codechallenge.model.pojo.Language;
import app.challenge.iotta.codechallenge.model.pojo.Repository;
import app.challenge.iotta.codechallenge.repositorylist.RepositoriesListFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment implements DetailsContract.View {

    @NonNull
    private static final String ARGUMENT_TASK_ID = "TASK_ID";

    private RecyclerView mLanguagesRecycleView;
    ImageView m_icon;
    TextView m_txtOwnerName;
    TextView m_txtOwnerBlog;
    TextView m_txtOwnerEmail;
    private Handler mHandler;
    private DetailsContract.Presenter mPresenter;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * @return A new instance of fragment DetailsFragment.
     */
    public static DetailsFragment newInstance(String repositoryId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_TASK_ID, repositoryId);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_details, container, false);
        m_icon = (ImageView)root.findViewById(R.id.ownerAvatar);
        m_txtOwnerName = (TextView) root.findViewById(R.id.txtOwnerName);
        m_txtOwnerBlog = (TextView) root.findViewById(R.id.txtOwnerBlog);
        m_txtOwnerEmail = (TextView) root.findViewById(R.id.txtOwnerEmail);
        mLanguagesRecycleView = (RecyclerView) root.findViewById(R.id.recycler_view);

        mPresenter.loadData();
        mHandler = new Handler(Looper.getMainLooper());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadData();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showDetails(Repository repository) {
        Picasso.with(DetailsFragment.this.getContext()).load(repository.getOwner().getmAvatarUrl()).into(m_icon);
        m_txtOwnerName.setText(repository.getOwner().getName());
        m_txtOwnerBlog.setText(repository.getOwner().getmBlogUrl());
        m_txtOwnerEmail.setText(repository.getOwner().getEmail());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mLanguagesRecycleView.setLayoutManager(mLayoutManager);

        LanguagesListAdapter mAdapter = new LanguagesListAdapter(repository.getLanguages());
        mLanguagesRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void showLoadingError(final String errorMessage) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DetailsFragment.this.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showNoOwnerData() {

    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    class LanguagesListAdapter extends RecyclerView.Adapter<LanguagesListAdapter.ViewHolder> {

        private ArrayList<Language> mDataset;

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView mTextView;
            private ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }
        private LanguagesListAdapter(ArrayList<Language> languages) {
            mDataset = languages;
        }

        @Override
        public LanguagesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_language, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText(mDataset.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
}
