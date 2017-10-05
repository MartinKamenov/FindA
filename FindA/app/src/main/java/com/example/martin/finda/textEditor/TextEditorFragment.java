package com.example.martin.finda.textEditor;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.martin.finda.R;
import com.example.martin.finda.base.BaseContracts;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextEditorFragment extends Fragment implements TextEditorContracts.ITextEditorView, View.OnClickListener {


    private View root;
    private EditText textContainer;
    private TextEditorContracts.ITextEditorPresenter mPresenter;

    private Button googleSearchBtn;
    private Button copyToClipboardBtn;
    private Button translateBtn;

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
        setListeners();
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

    public void setListeners() {
        this.googleSearchBtn = root.findViewById(R.id.google_search_btn);
        this.copyToClipboardBtn = root.findViewById(R.id.clipboard_btn);
        this.translateBtn = root.findViewById(R.id.translate_btn);

        googleSearchBtn.setOnClickListener(this);
        copyToClipboardBtn.setOnClickListener(this);
        translateBtn.setOnClickListener(this);
    }

    @Override
    public void setPresenter(BaseContracts.Presenter presenter) {
        this.mPresenter = (TextEditorContracts.ITextEditorPresenter) presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.google_search_btn:
                searchInGoogle(textContainer.getText().toString());
                break;
        }
    }

    public void searchInGoogle(String text) {
        String googleurl = "http://www.google.com/search?q=";
        String url = googleurl + text;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
