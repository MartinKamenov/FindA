package com.example.martin.finda.textEditor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.martin.finda.R;
import com.example.martin.finda.base.BaseContracts;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextEditorFragment extends Fragment implements TextEditorContracts.ITextEditorView {


    private View root;
    private EditText textContainer;
    private TextEditorContracts.ITextEditorPresenter mPresenter;

    public TextEditorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setPresenter(new TextEditorPresenter());
        mPresenter.subscribe(this);
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_edit_text, container, false);
        textContainer = (EditText) root.findViewById(R.id.text_holder);
        if(getActivity().getIntent().hasExtra("foundText")) {
            textContainer.setText(getActivity().getIntent().getExtras().getString("foundText"));
        }

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(BaseContracts.Presenter presenter) {
        this.mPresenter = (TextEditorContracts.ITextEditorPresenter) presenter;
    }
}
