package edu.indiana.soic.homeshare.homeshare.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.marlonlom.utilities.timeago.TimeAgo;

import java.util.List;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.data.model.Interview;
import edu.indiana.soic.homeshare.homeshare.databinding.SingleRowInterviewBinding;

public class InterviewListAdapter extends RecyclerView.Adapter<InterviewListAdapter.InterviewViewHolder> {
    public static final String TAG = "InterviewListAdapter";
    private List<Interview> interviewList;
    LayoutInflater layoutInflater;

    @NonNull
    @Override
    public InterviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleRowInterviewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.single_row_interview, parent, false);
        InterviewViewHolder holder = new InterviewViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InterviewViewHolder holder, int position) {
        Interview currentInterview = interviewList.get(position);
        holder.binding.setInterview(currentInterview);
        String scheduledDate = TimeAgo.using(currentInterview.getDateScheduled());
        holder.binding.interviewDue.setText("Interview Date: " + scheduledDate);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return interviewList == null ? 0 : interviewList.size();
    }

    private ClickListener clickListener;

    public interface ClickListener {
        void onItemClicked();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public InterviewListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setInterviewList(List<Interview> interviewList) {
        if (this.interviewList == null) {
            this.interviewList = interviewList;
            notifyItemRangeInserted(0, interviewList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return InterviewListAdapter.this.interviewList.size();
                }

                @Override
                public int getNewListSize() {
                    return interviewList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return InterviewListAdapter.this.interviewList
                            .get(oldItemPosition)
                            .getInterviewId()
                            .equals(interviewList.get(newItemPosition).getInterviewId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return false;
                }
            });
            this.interviewList = interviewList;
            result.dispatchUpdatesTo(this);
        }

    }

    class InterviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final SingleRowInterviewBinding binding;

        public InterviewViewHolder(SingleRowInterviewBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
