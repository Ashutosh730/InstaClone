package com.example.instaclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class PicTab extends Fragment {

    private ImageView road;
    private EditText description;
    private Button share;

    public PicTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_pic_tab, container, false);

        road=v.findViewById(R.id.road);
        description=v.findViewById(R.id.description);
        share=v.findViewById(R.id.share);



        return  v;
    }
}
