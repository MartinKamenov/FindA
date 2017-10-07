package com.example.martin.finda.textEditor;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martin.finda.R;
import com.example.martin.finda.base.BaseContracts;
import com.example.martin.finda.menu.MenuActivity;


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
    private Button toMenuBtn;
    private final String googleUrl = "http://www.google.com/search?q=";
    private final String saveText = "Copied to clipboard";

    public TextEditorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setPresenter(new TextEditorPresenter((TextEditorActivity)getActivity()));
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

    @Override
    public void setListeners() {
        this.googleSearchBtn = root.findViewById(R.id.google_search_btn);
        this.copyToClipboardBtn = root.findViewById(R.id.clipboard_btn);
        this.translateBtn = root.findViewById(R.id.translate_btn);
        this.toMenuBtn = root.findViewById(R.id.menu_btn);

        googleSearchBtn.setOnClickListener(this);
        copyToClipboardBtn.setOnClickListener(this);
        translateBtn.setOnClickListener(this);
        toMenuBtn.setOnClickListener(this);
    }

    @Override
    public void setPresenter(BaseContracts.Presenter presenter) {
        this.mPresenter = (TextEditorContracts.ITextEditorPresenter) presenter;
    }

    @Override
    public void onClick(View view) {
        String text = textContainer.getText().toString();
        switch (view.getId()) {
            case R.id.google_search_btn:
                searchInGoogle(text);
                break;
            case R.id.clipboard_btn:
                copyTextToClipboard(text);
                break;
            case R.id.translate_btn:
                this.translateBtn = root.findViewById(R.id.translate_btn);
                String textInButton = translateBtn.getText().toString();
                if(textInButton.contains("text")) {
                    translateText(text);
                } else {
                    showOriginalText();
                }
                break;
            case R.id.menu_btn:
                returnToMenu();
                break;
        }
    }

    @Override
    public void searchInGoogle(String text) {
        String url = googleUrl + text;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void copyTextToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", text);
        clipboard.setPrimaryClip(clip);
        makeToast(saveText);
    }

    private void returnToMenu() {
        Intent intent = new Intent(getActivity(), MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void translateText(String text) {
        if(text.length() == 0) {
            Toast.makeText(getActivity(), "Must have some text", Toast.LENGTH_SHORT).show();
        }
        showSpinner();
        mPresenter.translateText(text);
    }

    private void showSpinner() {
        getActivity().findViewById(R.id.text_editor_container).setVisibility(View.GONE);
        getActivity().findViewById(R.id.spinner_container).setVisibility(View.VISIBLE);
    }

    private void showOriginalText() {
        Button button = (Button)root.findViewById(R.id.translate_btn);
        button.setText("Translate text");
        EditText area = (EditText)root.findViewById(R.id.text_holder);
        area.setText(this.mPresenter.getOriginalText());
    }
}
