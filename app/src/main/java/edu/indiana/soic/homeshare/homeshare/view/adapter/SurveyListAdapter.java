package edu.indiana.soic.homeshare.homeshare.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.data.model.Survey;
import edu.indiana.soic.homeshare.homeshare.databinding.SingleRowSurveyBinding;

public class SurveyListAdapter extends RecyclerView.Adapter<SurveyListAdapter.SurveyViewHolder> {

    List<Survey> surveyList;
    LayoutInflater layoutInflater;
    private ClickListener clickListener;

    public interface ClickListener {
        void onItemClicked(View view, int pos);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public SurveyListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setSurveyList(List<Survey> surveyList) {
        if (this.surveyList == null) {
            this.surveyList = surveyList;
            notifyItemRangeInserted(0, surveyList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return SurveyListAdapter.this.surveyList.size();
                }

                @Override
                public int getNewListSize() {
                    return surveyList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return SurveyListAdapter.this
                            .surveyList.get(oldItemPosition)
                            .getSurveyId().equals(surveyList.get(newItemPosition).getSurveyId());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Survey survey = surveyList.get(newItemPosition);
                    Survey oldSurvey = surveyList.get(oldItemPosition);
                    return survey.getId() == oldSurvey.getId()
                            && Objects.equals(survey.getParticipantId(), oldSurvey.getParticipantId());
                }
            });
            this.surveyList = surveyList;
            result.dispatchUpdatesTo(this);
        }

    }

    @NonNull
    @Override
    public SurveyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleRowSurveyBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.single_row_survey, parent, false);
        SurveyViewHolder holder = new SurveyViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyViewHolder holder, int position) {
        Survey currentSurvey = surveyList.get(position);
        holder.binding.setSurvey(currentSurvey);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return surveyList == null ? 0 : surveyList.size();
    }

    class SurveyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final SingleRowSurveyBinding binding;

        public SurveyViewHolder(SingleRowSurveyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onClick(View view) {

        }
    }
}
