package com.example.instaclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText name,bio,sport,hobbies,profession;
    private Button update;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        name = v.findViewById(R.id.name);
        bio = v.findViewById(R.id.bio);
        sport = v.findViewById(R.id.sport);
        hobbies = v.findViewById(R.id.hobbies);
        profession = v.findViewById(R.id.profession);
        update = v.findViewById(R.id.update);

        final ParseUser user=ParseUser.getCurrentUser();

        if(user.get("profileName")!=null)
            name.setText(user.get("profileName")+"");
        else
            name.setHint("Nothing entered,please update");

        if(user.get("profilebio")!=null)
            bio.setText(user.get("profilebio")+"");
        else
            bio.setHint("Nothing entered,please update");

        if(user.get("profilesport")!=null)
        sport.setText(user.get("profilesport")+"");
        else
            sport.setHint("Nothing entered,please update");

            if(user.get("profilehobbies")!=null)
        hobbies.setText(user.get("profilehobbies")+"");
        else
        hobbies.setHint("Nothing entered,please update");

        if(user.get("profileprofession")!=null)
        profession.setText(user.get("profileprofession")+"");
        else
        profession.setHint("Nothing entered,please update");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.put("profileName",name.getText().toString());
                user.put("profilebio",bio.getText().toString());
                user.put("profilesport",sport.getText().toString());
                user.put("profilehobbies",hobbies.getText().toString());
                user.put("profileprofession",profession.getText().toString());

                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e==null)
                        {
                            Toast.makeText(getContext(), "Your data is saved", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        return v;
    }
}
