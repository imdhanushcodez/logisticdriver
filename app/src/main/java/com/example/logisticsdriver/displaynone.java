package com.example.logisticsdriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class displaynone extends RecyclerView.Adapter<displaynone.displayviewholder> {
    Context context;
    String k;

    public displaynone(Context context, String k) {
        this.context = context;
        this.k = k;
    }

    @NonNull
    @Override
    public displayviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displaynonemsg,parent,false);
        return new displayviewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull displayviewholder holder, int position) {
        holder.tv.setText(k);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class displayviewholder extends RecyclerView.ViewHolder{
            TextView tv;
        public displayviewholder(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv1);
        }
    }
}
