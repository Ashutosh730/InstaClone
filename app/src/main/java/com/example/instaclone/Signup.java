package com.example.instaclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends AppCompatActivity {

    private EditText signid,signpass,email;
    private Button signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signid=findViewById(R.id.signid);
        signpass=findViewById(R.id.signpass);
        email=findViewById(R.id.email);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);

        if(ParseUser.getCurrentUser()!=null)
        {
            transition();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser user=new ParseUser();
                user.setEmail(email.getText().toString());
                user.setUsername(signid.getText().toString());
                user.setPassword(signpass.getText().toString());

                signpass.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if(keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN)
                        {

                            onClick(signup);
                        }

                        return false;
                    }
                });


                if(email.getText().toString().equals("")
                        ||signpass.getText().toString().equals("")
                        ||signid.getText().toString().equals("")){
                    Toast.makeText(Signup.this, "Email,userId and password are necessary", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(Signup.this, user.get("username")+ " is signed in", Toast.LENGTH_SHORT).show();
                                transition();
                            } else {
                                Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Signup.this,Login.class);
                startActivity(intent);

            }
        });
    }

        public void root (View view)
        {
            try
            {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        private void transition(){
        Intent intent=new Intent(Signup.this,SocialActivity.class);
        startActivity(intent);
        }
}
