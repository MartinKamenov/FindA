package com.example.martin.finda;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTextFragment extends Fragment {


    private View root;
    private EditText textContainer;

    public EditTextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_edit_text, container, false);
        textContainer = (EditText) root.findViewById(R.id.text_holder);
        if(getActivity().getIntent().hasExtra("foundText")) {
            textContainer.setText(getActivity().getIntent().getExtras().getString("foundText"));
        }

        return root;
    }

}
