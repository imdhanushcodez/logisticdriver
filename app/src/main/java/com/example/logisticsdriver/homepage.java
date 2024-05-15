package com.example.logisticsdriver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class homepage extends AppCompatActivity {
    ConstraintLayout btn,about_page;
    FirebaseAuth auth;
    TextView user_id;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        btn = findViewById(R.id.btn1);
        about_page = findViewById(R.id.about_page);
        user_id = findViewById(R.id.user_id);

        auth = FirebaseAuth.getInstance();
        String k = "";
        k = auth.getCurrentUser().getEmail();
        String o = "";
        for(int i=0;i<k.length();i++) {
            if(k.charAt(i)=='@') break;
            o+=k.charAt(i);
        }

        user_id.setText(o.toUpperCase());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(homepage.this, sharedlist.class);
                startActivity(I);
            }
        });

        about_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this,about_page.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}