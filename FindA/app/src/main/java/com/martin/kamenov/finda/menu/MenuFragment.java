package com.martin.kamenov.finda.menu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.martin.kamenov.finda.base.BaseContracts;
import com.martin.kamenov.finda.camera.CameraActivity;
import com.martin.kamenov.finda.gallery.GalleryActivity;
import com.martin.kamenov.finda.R;
import com.martin.kamenov.finda.settings.SettingsActivity;
import com.martin.kamenov.finda.textEditor.TextEditorActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener
        , MenuContracts.IMenuView{

    private View root;

    private Button cameraBtn;
    private Button galeryBtn;
    private Button traslatorBtn;
    private Button settingsBtn;
    private MenuContracts.IMenuPresenter mPresenter;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.setPresenter(new MenuPresenter());
        mPresenter.subscribe(this);
        root = inflater.inflate(R.layout.fragment_menu, container, false);
        setListeners();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
                navigate(getActivity(), CameraActivity.class);
                break;
            case R.id.gallery:
                navigate(getActivity(), GalleryActivity.class);
                break;
            case R.id.translator:
                Intent intent = new Intent(getActivity(), TextEditorActivity.class);
                intent.putExtra("foundText", "");
                startActivity(intent);
                break;
            case R.id.settings:
                navigate(getActivity(), SettingsActivity.class);
                break;
        }
    }

    @Override
    public void setPresenter(BaseContracts.Presenter presenter) {
        this.mPresenter = (MenuContracts.IMenuPresenter) presenter;
    }


    @Override
    public void navigate(Activity currentActivity, Class newActivity) {
        Intent intent = new Intent(currentActivity, newActivity);
        startActivity(intent);
    }

    @Override
    public void setListeners() {
        cameraBtn = root.findViewById(R.id.camera);
        cameraBtn.setOnClickListener(this);
        galeryBtn = root.findViewById(R.id.gallery);
        galeryBtn.setOnClickListener(this);
        traslatorBtn = root.findViewById(R.id.translator);
        traslatorBtn.setOnClickListener(this);
        settingsBtn = root.findViewById(R.id.settings);
        settingsBtn.setOnClickListener(this);
    }
}
