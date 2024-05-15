package com.example.logisticsdriver;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class about_page extends AppCompatActivity {

    FirebaseAuth auth;
    TextView userid;
    String k = "WELCOME USER";
    ConstraintLayout log_out,help_me,about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userid = findViewById(R.id.textuser);
        log_out = findViewById(R.id.log_out);
        help_me = findViewById(R.id.help_me);
        about_us = findViewById(R.id.about_us);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..."); // Set your own message
        progressDialog.setCancelable(false); // Set if the user can cancel the operation
        progressDialog.show();

        auth = FirebaseAuth.getInstance();
        k = auth.getCurrentUser().getEmail();
        String o = "";
        for(int i=0;i<k.length();i++) {
            if(k.charAt(i)=='@') break;
            o+=k.charAt(i);
        }

        userid.setText(o.toUpperCase());
        progressDialog.dismiss();


        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent i = new Intent(about_page.this, loginpage.class);
                startActivity(i);
                finish();
            }
        });


        help_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+"9361049096"));
                startActivity(intent);
            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Dialog dialog1 = new Dialog(about_page.this,R.style.transaction);
                dialog1.setContentView(R.layout.dialogue_about_us);
                dialog1.show();*/

                AlertDialog.Builder builder = new AlertDialog.Builder(about_page.this,R.style.CustomDialogTheme);
                View view = LayoutInflater.from(about_page.this).inflate(R.layout.dialogue_about_us, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.show();






            }
        });

    }
}