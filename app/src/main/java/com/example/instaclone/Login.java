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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    private EditText logpass,logid;
    private Button signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logid=findViewById(R.id.logid);
        logpass=findViewById(R.id.logpass);
        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    logpass.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {

                            if(keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN)
                            {

                                onClick(login);
                            }

                            return false;
                        }
                    });
                    if(logpass.getText().toString().equals("")
                            ||logid.getText().toString().equals("")){
                        Toast.makeText(Login.this, "UserId and password are necessary", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ParseUser user = new ParseUser();
                        user.logInInBackground(logid.getText().toString(), logpass.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null) {
                                    Toast.makeText(Login.this, logid.getText().toString() + " is logged in", Toast.LENGTH_SHORT).show();
                                    transition();
                                } else {
                                    Toast.makeText(Login.this, "Invalid Userid or Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
            });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Signup.class);
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
        Intent intent=new Intent(Login.this,SocialActivity.class);
        startActivity(intent);
    }

}
