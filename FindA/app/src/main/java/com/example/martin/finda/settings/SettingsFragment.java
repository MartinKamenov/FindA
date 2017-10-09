package com.example.martin.finda.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.martin.finda.FindAApplication;
import com.example.martin.finda.R;
import com.example.martin.finda.base.BaseContracts;
import com.example.martin.finda.models.SettingsConfiguration;
import com.example.martin.finda.repositories.GenericCacheRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements SettingsContracts.ISettingsView {


    private SettingsContracts.ISettingsPresenter mPresenter;
    private View root;

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

        Spinner translateFromSpinner = root.findViewById(R.id.translate_from_spinner);
        translateFromSpinner.setAdapter(getAdapter());
        Spinner translateToSpinner = root.findViewById(R.id.translate_to_spinner);
        translateToSpinner.setAdapter(getAdapter());
    }

    public ArrayAdapter<String> getAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                mPresenter.getTranslationLanguages()
        );

        return adapter;
    }
}
