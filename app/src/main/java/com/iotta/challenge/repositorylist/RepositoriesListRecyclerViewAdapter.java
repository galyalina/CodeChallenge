package com.iotta.challenge.repositorylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iotta.challenge.R;
import com.iotta.challenge.model.pojo.Repository;
import com.iotta.challenge.utils.JavaUtils;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Repository}
 */
public class RepositoriesListRecyclerViewAdapter extends RecyclerView.Adapter<RepositoriesListRecyclerViewAdapter.ViewHolder> {

    private final List<Repository> mValues;
    private IRepositoryItemListener mItemListener;


    public RepositoriesListRecyclerViewAdapter( List<Repository> items, IRepositoryItemListener itemListener) {
        mItemListener = itemListener;
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
        if(holder.mItem.hasWiki()) {
            holder.mView.setBackgroundResource(R.color.repositoryHasWiki);
        }else{
            holder.mView.setBackgroundResource(R.color.repositoryHasnotWiki);
        }
        holder.mNameTxtView.setText(holder.mItem.getName());
        holder.mDesriptionView.setText(holder.mItem.getDescription());
        holder.mUpdateDateView.setText(JavaUtils.convert(holder.mItem.getLastUpdate()));
        holder.mView.setOnClickListener(new OnRepositoryItemClickListener(holder));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.onRepositoryClick(holder.mItem.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameTxtView;
        public final TextView mDesriptionView;
        public final TextView mUpdateDateView;
        public Repository mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameTxtView = (TextView) view.findViewById(R.id.nameTxt);
            mDesriptionView = (TextView) view.findViewById(R.id.descriptionTxt);
            mUpdateDateView = (TextView) view.findViewById(R.id.updateTxt);
        }
    }

    public class OnRepositoryItemClickListener implements View.OnClickListener{

       private final RepositoriesListRecyclerViewAdapter.ViewHolder  mHolder;

        public OnRepositoryItemClickListener(RepositoriesListRecyclerViewAdapter.ViewHolder holder){
            mHolder = holder;
        }

        @Override
        public void onClick(View v) {
            mItemListener.onRepositoryClick(mHolder.mItem.getId());
        }
    }

    public interface IRepositoryItemListener {

        void onRepositoryClick(String repositoryId);
    }
}
