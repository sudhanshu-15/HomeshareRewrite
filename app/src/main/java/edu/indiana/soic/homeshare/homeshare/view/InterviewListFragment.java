package edu.indiana.soic.homeshare.homeshare.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.data.model.Participant;
import edu.indiana.soic.homeshare.homeshare.databinding.InterviewListFragmentBinding;
import edu.indiana.soic.homeshare.homeshare.di.Injectable;
import edu.indiana.soic.homeshare.homeshare.view.adapter.InterviewListAdapter;
import edu.indiana.soic.homeshare.homeshare.viewmodel.InterviewListViewModel;

public class InterviewListFragment extends Fragment implements Injectable {

    public static final String TAG = "InterviewListFragment";
    private static final long DIFF_TIME = 600000;
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
        adapter.setClickListener((view, scheduleDate) -> {
            startZoom(scheduleDate);
        });
        binding.interviewList.setAdapter(adapter);
        Button button = binding.cardInactive.findViewById(R.id.okButton);
        button.setOnClickListener(view -> {
            hideCard(view);
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InterviewListViewModel.class);
        viewModel.getInterviewList().observe(this, interviews -> {
            if (interviews.size() != 0) {
                adapter.setInterviewList(interviews);
                binding.emptylist.setVisibility(View.GONE);
            } else {
                binding.emptylist.setVisibility(View.VISIBLE);
            }
        });
        viewModel.refreshInterviewList();
    }

    private void startZoom(long scheduleDate) {
        long currentDate = System.currentTimeMillis();
        long diff = Math.abs(currentDate - scheduleDate);
        Log.d(TAG, "startZoom: " + diff);
        String zoomLink = viewModel.getParticipant().getZoomLink();
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            if (zoomLink.length() != 0 && diff <= DIFF_TIME) {
                ((InterviewActivity)getActivity()).startZoom(zoomLink);
            } else {
                binding.cardInactive.setVisibility(View.VISIBLE);
            }
        }
    }

    public void hideCard(View view) {
        binding.cardInactive.setVisibility(View.GONE);
    }

}
