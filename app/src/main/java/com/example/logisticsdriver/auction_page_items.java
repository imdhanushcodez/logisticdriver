package com.example.logisticsdriver;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
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

public class auction_page_items extends AppCompatActivity {

    RecyclerView rc;
    ArrayList<data_class_auction> dl;
    AuctionAdapter myadapter;

    DatabaseReference db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auction_page_items);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..."); // Set your own message
        progressDialog.setCancelable(false); // Set if the user can cancel the operation
        progressDialog.show();


        rc = findViewById(R.id.auction_recycle_page);

        db = FirebaseDatabase.getInstance().getReference("AUCTIONJOBS");

        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        dl = new ArrayList<>();
        myadapter = new AuctionAdapter(dl,this,db);

        rc.setAdapter(myadapter);
        final int[] i = {0};

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dl.clear();

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    data_class_auction ddl = dataSnapshot.getValue(data_class_auction.class);
                    dl.add(ddl);
                    i[0]+=1;
                }
                myadapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(auction_page_items.this, "Can't retrive from database", Toast.LENGTH_SHORT).show();
            }
        });


    }
}