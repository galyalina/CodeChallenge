package app.challenge.iotta.codechallenge.repositorylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.challenge.iotta.codechallenge.R;
import app.challenge.iotta.codechallenge.model.pojo.Repository;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Repository}
 */
public class RepositoriesListRecyclerViewAdapter extends RecyclerView.Adapter<RepositoriesListRecyclerViewAdapter.ViewHolder> {

    private final List<Repository> mValues;

    public RepositoriesListRecyclerViewAdapter( List<Repository> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getDescription());
        holder.mContentView.setText(mValues.get(position).getName());
        holder.mView.setOnClickListener(new OnRepositoryItemClickListener(holder));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Repository mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public class OnRepositoryItemClickListener implements View.OnClickListener{

       RepositoriesListRecyclerViewAdapter.ViewHolder mHolder;

        public OnRepositoryItemClickListener(RepositoriesListRecyclerViewAdapter.ViewHolder holder){
            mHolder = holder;
        }

        @Override
        public void onClick(View v) {
            //mPresenter.openRepositoryDetails(mHolder.mItem.getId());
        }
    }
}
