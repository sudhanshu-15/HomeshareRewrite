package edu.indiana.soic.homeshare.homeshare.view;

import android.arch.lifecycle.ViewModelProvider;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;

import javax.inject.Inject;

import edu.indiana.soic.homeshare.homeshare.R;
import edu.indiana.soic.homeshare.homeshare.databinding.SurveyWebFragmentBinding;
import edu.indiana.soic.homeshare.homeshare.di.Injectable;

public class SurveyWebFragment extends Fragment implements Injectable {

    public static final String TAG = "SurveyWebFragment";
    public static final String URL_KEY = "survey_url";
    private SurveyWebFragmentBinding binding;


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.survey_web_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String surveyUrl = getArguments().getString(URL_KEY);
        if (surveyUrl.length() != 0 && URLUtil.isValidUrl(surveyUrl)){
            Log.d(TAG, "onActivityCreated: Vaild");
            binding.surveyWebView.getSettings().setJavaScriptEnabled(true);
            binding.surveyWebView.loadUrl(surveyUrl);
        }else{
            Log.d(TAG, "onActivityCreated: Invalid");
            binding.surveyWebView.setVisibility(View.GONE);
            binding.invalidSurvey.setVisibility(View.VISIBLE);
        }
    }

    public static SurveyWebFragment forSurveyLink(String surveyUrl) {
        SurveyWebFragment fragment = new SurveyWebFragment();
        Bundle args = new Bundle();
        args.putString(URL_KEY, surveyUrl);
        fragment.setArguments(args);
        return fragment;
    }
}
