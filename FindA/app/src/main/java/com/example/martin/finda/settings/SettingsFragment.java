package com.example.martin.finda.settings;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.martin.finda.FindAApplication;
import com.example.martin.finda.R;
import com.example.martin.finda.base.BaseContracts;
import com.example.martin.finda.menu.MenuActivity;
import com.example.martin.finda.models.SettingsConfiguration;
import com.example.martin.finda.repositories.GenericCacheRepository;

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
        String from = settingsConfiguration.getTranslateFrom();
        String to = settingsConfiguration.getTranslateTo();


        Spinner translateFromSpinner = root.findViewById(R.id.translate_from_spinner);
        translateFromSpinner.setAdapter(getAdapter());
        Spinner translateToSpinner = root.findViewById(R.id.translate_to_spinner);
        translateToSpinner.setAdapter(getAdapter());

        short foundBoth = 0;

        //String[] fullLanguages = mPresenter.getFullTranslationLanguages();
        String[] languages = mPresenter.getTranslationLanguages();


        Integer fullFromIndex = java.util.Arrays.asList(languages).indexOf(from);
        Integer fullToIndex = java.util.Arrays.asList(languages).indexOf(to);
        translateFromSpinner.setSelection(fullFromIndex);
        translateToSpinner.setSelection(fullToIndex);


        /*for (int i = 0; i < languages.length; i++) {
            String str = fullLanguages[i];
            if(str.contains(from)) {
                translateFromSpinner.setSelection(i);
                foundBoth++;
            }
            if(str.contains(to)) {
                translateToSpinner.setSelection(i);
                foundBoth++;
            }
            if(foundBoth==2) {
                break;
            }
        }*/
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



                SettingsConfiguration config = new SettingsConfiguration(shortFrom, shortTo);
                mPresenter.setSettingsConfiguration(config);
                Toast.makeText(getActivity(), "settings saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.cancel_settings:
                Intent intent2 = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
