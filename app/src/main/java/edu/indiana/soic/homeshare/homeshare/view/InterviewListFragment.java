package edu.indiana.soic.homeshare.homeshare.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.databinding.InterviewListFragmentBinding;
import edu.indiana.soic.homeshare.homeshare.di.Injectable;
import edu.indiana.soic.homeshare.homeshare.view.adapter.InterviewListAdapter;
import edu.indiana.soic.homeshare.homeshare.viewmodel.InterviewListViewModel;

public class InterviewListFragment extends Fragment implements Injectable {

    public static final String TAG = "InterviewListFragment";
    private InterviewListAdapter adapter;
    private InterviewListFragmentBinding binding;
    private InterviewListViewModel viewModel;


    @Inject
    ViewModelProvider.Factory viewModelFactory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.interview_list_fragment, container, false);
        adapter = new InterviewListAdapter(getActivity());
        binding.interviewList.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InterviewListViewModel.class);
        viewModel.getInterviewList().observe(this, interviews -> {
            if (interviews.size() != 0) {
                adapter.setInterviewList(interviews);
            } else {
                binding.emptylist.setVisibility(View.VISIBLE);
            }
        });
        viewModel.refreshInterviewList();
    }

}
