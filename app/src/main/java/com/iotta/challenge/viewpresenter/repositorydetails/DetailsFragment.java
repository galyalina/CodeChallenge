package com.iotta.challenge.viewpresenter.repositorydetails;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.iotta.challenge.R;
import com.iotta.challenge.model.pojo.Language;
import com.iotta.challenge.model.pojo.Repository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment implements DetailsContract.View {

    @NonNull
    private static final String ARGUMENT_TASK_ID = "TASK_ID";

    private View m_rootView;
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
        m_rootView = root;
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

        ImageView icon = (ImageView)m_rootView.findViewById(R.id.ownerAvatar);
        Picasso.with(DetailsFragment.this.getContext()).load(repository.getOwner().getmAvatarUrl()).into(icon);
        TextView txtOwnerName = (TextView) m_rootView.findViewById(R.id.txtOwnerName);
        TextView txtDescription = (TextView) m_rootView.findViewById(R.id.txtDescription);
        TextView txtLanguages = (TextView) m_rootView.findViewById(R.id.txtLanguages);
        TextView txtOwnerBlog = (TextView) m_rootView.findViewById(R.id.txtOwnerBlog);
        TextView txtOwnerEmail = (TextView) m_rootView.findViewById(R.id.txtOwnerEmail);
        RecyclerView mLanguagesRecycleView = (RecyclerView) m_rootView.findViewById(R.id.recycler_view);

        txtOwnerName.setText(repository.getOwner().getName());
        txtOwnerBlog.setText(repository.getOwner().getmBlogUrl());
        txtOwnerEmail.setText(repository.getOwner().getEmail());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        mLanguagesRecycleView.setLayoutManager(mLayoutManager);

        if(repository.getLanguages().size()==0){
            txtLanguages.setText(R.string.details_languages_empty_list_text);
        }else{
            txtLanguages.setVisibility(View.GONE);
            mLanguagesRecycleView.setVisibility(View.VISIBLE);
        }

        if(TextUtils.isEmpty(repository.getDescription())){
            txtDescription.setText(R.string.details_description_empty_text);
        }else{
            txtDescription.setText(repository.getDescription());
        }
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

        private final ArrayList<Language> mDataset;

        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView mTextView;
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
