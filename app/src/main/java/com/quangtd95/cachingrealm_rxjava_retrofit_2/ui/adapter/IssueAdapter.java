package com.quangtd95.cachingrealm_rxjava_retrofit_2.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quangtd95.cachingrealm_rxjava_retrofit_2.R;
import com.quangtd95.cachingrealm_rxjava_retrofit_2.services.response.IssueResponse;

import java.util.List;

/**
 * Quang_TD on 7/23/2017.
 */

public class IssueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<IssueResponse> issueResponses;

    public IssueAdapter(List<IssueResponse> issueResponses) {
        this.issueResponses = issueResponses;
    }

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_issue, parent, false);
        return new IssueViewHolder(view);
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((IssueViewHolder) holder).setData(issueResponses.get(position));
    }

    @Override public int getItemCount() {
        return issueResponses == null ? 0 : issueResponses.size();
    }

    private class IssueViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvBody;

        IssueViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
        }

        public void setData(IssueResponse issueResponse) {
            tvTitle.setText(issueResponse.getTitle());
            tvBody.setText(issueResponse.getBody());
        }
    }
}
