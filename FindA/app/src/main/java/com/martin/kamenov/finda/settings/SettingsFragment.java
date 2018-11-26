package com.martin.kamenov.finda.settings;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.martin.kamenov.finda.FindAApplication;
import com.martin.kamenov.finda.R;
import com.martin.kamenov.finda.base.BaseContracts;
import com.martin.kamenov.finda.menu.MenuActivity;
import com.martin.kamenov.finda.models.SettingsConfiguration;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements SettingsContracts.ISettingsView, View.OnClickListener {


    private SettingsContracts.ISettingsPresenter mPresenter;
    private View root;
    private Button saveBtn;
    private Button cancelBtn;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setPresenter(new SettingsPresenter());
        mPresenter.subscribe(this);
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_settings, container, false);
        saveBtn = root.findViewById(R.id.save_settings);
        cancelBtn = root.findViewById(R.id.cancel_settings);
        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        SettingsConfiguration configuration = mPresenter.getSettingsConfiguration();
        showSettings(configuration);
        return root;
    }

    public FindAApplication getApp() {
        return (FindAApplication) getActivity().getApplication();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(BaseContracts.Presenter presenter) {
        this.mPresenter = (SettingsContracts.ISettingsPresenter) presenter;
    }

    public void showSettings(SettingsConfiguration settingsConfiguration) {
        Boolean voiceRecognitionIsSwitched = settingsConfiguration.getVoiceRecognition();
        String from = settingsConfiguration.getTranslateFrom();
        String to = settingsConfiguration.getTranslateTo();


        Spinner translateFromSpinner = root.findViewById(R.id.translate_from_spinner);
        translateFromSpinner.setAdapter(getAdapter());
        Spinner translateToSpinner = root.findViewById(R.id.translate_to_spinner);
        translateToSpinner.setAdapter(getAdapter());
        Switch voiceRecognitionSwitch = root.findViewById(R.id.voice_recognition_switch);

        short foundBoth = 0;

        //String[] fullLanguages = mPresenter.getFullTranslationLanguages();
        String[] languages = mPresenter.getTranslationLanguages();


        Integer fullFromIndex = java.util.Arrays.asList(languages).indexOf(from);
        Integer fullToIndex = java.util.Arrays.asList(languages).indexOf(to);
        translateFromSpinner.setSelection(fullFromIndex);
        translateToSpinner.setSelection(fullToIndex);
        voiceRecognitionSwitch.setChecked(voiceRecognitionIsSwitched);
    }

    public ArrayAdapter<String> getAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                mPresenter.getFullTranslationLanguages()
        );

        return adapter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_settings:
                Spinner translateToSpinner = getActivity().findViewById(R.id.translate_from_spinner);
                String from = translateToSpinner.getSelectedItem().toString();
                Spinner translateFromSpinner = getActivity().findViewById(R.id.translate_to_spinner);
                String to = translateFromSpinner.getSelectedItem().toString();
                String shortFrom = "en";
                String shortTo = "bg";
                short foundBoth = 0;
                String[] languages = mPresenter.getFullTranslationLanguages();
                String[] shortLanguages = mPresenter.getTranslationLanguages();
                for (int i = 0; i < languages.length; i++) {
                    if(languages[i].contains(from)) {
                        shortFrom = shortLanguages[i];
                        foundBoth++;
                    }
                    if(languages[i].contains(to)) {
                        shortTo = shortLanguages[i];
                        foundBoth++;
                    }
                    if(foundBoth >= 2) {
                        break;
                    }
                }
                // Switch
                Switch voiceRecognitionStitch = root.findViewById(R.id.voice_recognition_switch);
                boolean isChecked = voiceRecognitionStitch.isChecked();

                SettingsConfiguration config = new SettingsConfiguration(shortFrom, shortTo, isChecked);
                mPresenter.setSettingsConfiguration(config);
                Toast.makeText(getActivity(), "settings saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.cancel_settings:
                Intent intent2 = new Intent(getActivity(), MenuActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                break;
        }
    }
}
