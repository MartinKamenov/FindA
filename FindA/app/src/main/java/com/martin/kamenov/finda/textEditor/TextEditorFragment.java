package com.martin.kamenov.finda.textEditor;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.martin.kamenov.finda.FindAApplication;
import com.martin.kamenov.finda.R;
import com.martin.kamenov.finda.base.BaseContracts;
import com.martin.kamenov.finda.menu.MenuActivity;
import com.martin.kamenov.finda.models.SettingsConfiguration;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


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
    private FloatingActionButton searchVoiceBtn;
    private final String googleUrl = "http://www.google.com/search?q=";
    private final String saveText = "Copied to clipboard";
    private LinearLayout pageContainer;

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
        pageContainer = (LinearLayout) root.findViewById(R.id.text_editor_container);
        textContainer.setOnClickListener(this);
        pageContainer.setOnClickListener(this);

        String foundText = "";
        if(getActivity().getIntent().hasExtra("foundText")) {
            foundText = getActivity().getIntent().getExtras().getString("foundText");
        }
        if(foundText.length()==0) {
            // TO DO:
            // SHOW KEYBOARD IF THERE IS NO TEXT
            textContainer.requestFocus();
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
        textContainer.setText(foundText);

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
        this.searchVoiceBtn = (FloatingActionButton)root.findViewById(R.id.search_voice_btn);
        SettingsConfiguration settingsConfiguration = mPresenter.getSettingsConfiguration();
        if(settingsConfiguration.getVoiceRecognition()) {
            searchVoiceBtn.setOnClickListener(this);
        } else {
            searchVoiceBtn.setVisibility(View.GONE);
        }

        googleSearchBtn.setOnClickListener(this);
        copyToClipboardBtn.setOnClickListener(this);
        translateBtn.setOnClickListener(this);
        toMenuBtn.setOnClickListener(this);
    }

    @Override
    public FindAApplication getApp() {
        return (FindAApplication) getActivity().getApplication();
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
            case R.id.text_holder:
                focusOnTextContainer();
                break;
            case R.id.text_editor_container:
                focusOnTextContainer();
                break;
            case R.id.clipboard_btn:
                copyTextToClipboard(text);
                break;
            case R.id.search_voice_btn:
                searchVoiceText();
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

    private void focusOnTextContainer() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(textContainer, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void searchInGoogle(String text) {
        String url = googleUrl + text;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void searchVoiceText() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if(intent.resolveActivity(getActivity().getPackageManager())!=null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(getActivity(), "Your device don't support speech input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if(resultCode==RESULT_OK&&data!=null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textContainer.setText(result.get(0));
                }
                break;

        }
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
            return;
        }
        if(!isOnline()) {
            Toast.makeText(getActivity(), "Not connected to internet", Toast.LENGTH_SHORT).show();
            return;
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

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
