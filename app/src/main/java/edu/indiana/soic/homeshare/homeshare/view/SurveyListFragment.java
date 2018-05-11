package edu.indiana.soic.homeshare.homeshare.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.databinding.SurveyListFragmentBinding;
import edu.indiana.soic.homeshare.homeshare.di.Injectable;
import edu.indiana.soic.homeshare.homeshare.view.adapter.SurveyListAdapter;
import edu.indiana.soic.homeshare.homeshare.viewmodel.SurveyListViewModel;

public class SurveyListFragment extends Fragment implements Injectable {
    public static final String TAG = "SurveyListFragment";
    private SurveyListAdapter surveyListAdapter;
    private SurveyListFragmentBinding binding;
    private SurveyListViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.survey_list_fragment, container, false);
        surveyListAdapter = new SurveyListAdapter(getActivity());
        surveyListAdapter.setClickListener(((view, url, surveyId) -> {
            showSurvey(url, surveyId);
        }));
        binding.surveyList.setAdapter(surveyListAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SurveyListViewModel.class);
        viewModel.getSurveyList().observe(this, surveys -> {
            if (surveys.size() != 0) {
                surveyListAdapter.setSurveyList(surveys);
                binding.emptylist.setVisibility(View.GONE);
            } else {
                binding.emptylist.setVisibility(View.VISIBLE);
            }

        });
        viewModel.refreshSurveyList();
    }

    private void showSurvey(String url, String surveyId) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            viewModel.updateSurveyStatus(surveyId);
            ((SurveyActivity) getActivity()).showSurvey(url);
        }
    }
}
