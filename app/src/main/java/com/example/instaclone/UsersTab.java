package com.example.instaclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class UsersTab extends Fragment {

    private ListView list;
    private ArrayList arraylist;
    private ArrayAdapter arrayAdapter;

    public UsersTab() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_users_tab, container, false);

        list=v.findViewById(R.id.list);
        arraylist=new ArrayList();
        arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arraylist);
        final TextView txt=v.findViewById(R.id.txt);

        ParseQuery<ParseUser> query=ParseUser.getQuery();

        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if(e==null)
                {
                    if(objects.size()>0)
                    {
                        for(ParseUser user:objects)
                        {
                            arraylist.add(user.getUsername());
                        }

                        list.setAdapter(arrayAdapter);
                        txt.animate().alpha(0).setDuration(500);
                        list.setVisibility(View.VISIBLE);

                    }

                }

            }
        });

        return v;
    }
}
