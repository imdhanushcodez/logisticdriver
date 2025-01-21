package com.example.logisticsdriver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.MyViewHolder> {

    ArrayList<data_class_auction> dl;
    Context context;
    DatabaseReference db;
    String username = "";
    FirebaseAuth auth;


    public AuctionAdapter(ArrayList<data_class_auction> dl, Context context,DatabaseReference db) {
        this.dl = dl;
        this.context = context;
        this.db = db;
        auth = FirebaseAuth.getInstance();
        username = auth.getCurrentUser().getEmail();
        int indexx = username.indexOf('@');
        username = username.substring(0,indexx);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.auction_recycle_items,parent,false);
        return new AuctionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.from.setText(dl.get(position).getFrom());
        holder.to.setText(dl.get(position).getTo());
        holder.name.setText(dl.get(position).getName());
        holder.product.setText(dl.get(position).getProduct());
        holder.amt.setText(dl.get(position).getBetamt());
        holder.lastbid.setText("LAST BID : "+dl.get(position).getBetname());

        int y = position;



        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rate = Integer.parseInt(dl.get(position).getBetamt())-100;
                String id = dl.get(position).getFrom()+dl.get(position).getTo()+dl.get(position).getName()+dl.get(position).getNumber()+dl.get(position).getProduct();
                db.child(id).child("betamt").setValue(rate+"")
                        .addOnSuccessListener(aVoid -> {
                            // Update local item list
                            dl.get(position).setBetamt(rate+"");
                            notifyItemChanged(position);
                            Toast.makeText(context,"BID VALUE LESSER BY 100 ",Toast.LENGTH_LONG).show();

                            // FOR UPDATING INTO BETAME
                            db.child(id).child("betname").setValue(username).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    dl.get(position).setBetname(username);
                                    notifyItemChanged(position);
                                    Toast.makeText(context,"BIDDER IS "+username,Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context,"BIDDER IS "+username.toUpperCase(),Toast.LENGTH_LONG).show();
                                }
                            });



                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(holder.itemView.getContext(), "Failed to update rate", Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }

    @Override
    public int getItemCount() {

        return dl.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView from,to,name,product,amt,lastbid;
        AppCompatButton btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.from_auction_re);
            to = itemView.findViewById(R.id.to_auction_re);
            name = itemView.findViewById(R.id.owner_auction_re);
            product = itemView.findViewById(R.id.prod_auction_re);
            amt = itemView.findViewById(R.id.amt_auction_re);
            btn = itemView.findViewById(R.id.auction_bid_btn);
            lastbid = itemView.findViewById(R.id.lastbid);

        }
    }
}
