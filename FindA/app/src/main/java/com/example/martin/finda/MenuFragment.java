package com.example.martin.finda;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {


    private View root;
    private Button cameraBtn;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_menu, container, false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraBtn = root.findViewById(R.id.camera);
        cameraBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
                Toast.makeText(getActivity(), "Camera", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
