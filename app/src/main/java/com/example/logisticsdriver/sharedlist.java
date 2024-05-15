package com.example.logisticsdriver;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sharedlist extends AppCompatActivity {

    RecyclerView rc;
    ArrayList<dataclass> dl;
    Myadapter myadapter;
    ImageView btntoupload;
    displaynone disaapter;

    DatabaseReference db = FirebaseDatabase.getInstance().getReference("USERSJOB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sharedlist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        rc = findViewById(R.id.rechome);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        dl = new ArrayList<>();
        myadapter = new Myadapter(dl,this);

        rc.setAdapter(myadapter);
        final int[] i = {0};

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    dataclass ddl = dataSnapshot.getValue(dataclass.class);
                    dl.add(ddl);
                    i[0]+=1;


                }
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(sharedlist.this, "Can't retrive from database", Toast.LENGTH_SHORT).show();
            }
        });


    }
}