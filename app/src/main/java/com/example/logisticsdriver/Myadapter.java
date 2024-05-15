package com.example.logisticsdriver;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    private static final int SMS_PERMISSION_CODE = 101;
    private static final String PHONE_NUMBER = "your_phone_number_here";
    private static final String SMS_MESSAGE = "Hello! This is a test SMS.";

    ArrayList<dataclass> datalist;
    Context context;

    public Myadapter(ArrayList<dataclass> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleitems,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context)
                .load(Uri.parse(datalist.get(position).getImgurl())) // Assuming getImageUri() returns a string URI
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.img.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // No need for implementation here
                    }
                });

        holder.from.setText(datalist.get(position).getFrom());
        holder.to.setText(datalist.get(position).getTo());
        holder.name.setText(datalist.get(position).getName());
        holder.product.setText(datalist.get(position).getProduct());
        holder.date.setText(datalist.get(position).getDate());
        int y = position;



        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "SMS will be sent soon", Toast.LENGTH_SHORT).show();
                String k = datalist.get(y).getNumber();
                sendSms(k);

            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView from,to,name,product,date;
        ImageView img;
        AppCompatButton btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.rcfrom);
            to = itemView.findViewById(R.id.rcto);
            name = itemView.findViewById(R.id.rcname);
            product = itemView.findViewById(R.id.rcproduct);
            img = itemView.findViewById(R.id.rcimage);
            btn = itemView.findViewById(R.id.rcbtn);
            date = itemView.findViewById(R.id.rcdate);
        }
    }

    private void sendSms(String phoneNumber) {
        try {
            // SMS message content
            String smsMessage = "Hey job has be accepted get ready.";

            // Use SmsManager to send SMS
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);

            Toast.makeText(context, "SMS sent successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Failed to send SMS.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
