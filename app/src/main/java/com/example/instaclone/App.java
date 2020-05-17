package com.example.instaclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("yyLCTRJQnlNdXplX7Id6HQk29faqGDJcv1fG4TW9")
                // if defined
                .clientKey("Mr9hn2369u3xxM01e9KcBYVId0irGzeyVOoF4Sc0")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
