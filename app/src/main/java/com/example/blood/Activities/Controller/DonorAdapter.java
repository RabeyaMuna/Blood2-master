package com.example.blood.Activities.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blood.Activities.FirebaseChat.ChatMessageActivity;
import com.example.blood.Activities.Model.DonorItems;
import com.example.blood.R;

import java.util.ArrayList;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.ViewHolder>  {
    private Context mContext;
    private ArrayList<DonorItems> donorItems;
    public DonorAdapter(Context mContext, ArrayList<DonorItems> donorItems) {
        this.mContext = mContext;
        this.donorItems = donorItems;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.item_donor_list, parent, false);

        return new DonorAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DonorItems ndonorItems= donorItems.get(position);

        TextView donorName = holder.donorName ;
        TextView donorPhone = holder.donorPhone ;
        TextView donorBloodGroup = holder.donorBloodGroup ;
        TextView donorDivision = holder.donorDivision  ;
        RelativeLayout callDoc = holder.callDoc;
        RelativeLayout donorChat=holder.donorChat;

        donorName.setText(ndonorItems.getDonorName());
        donorPhone.setText(ndonorItems.getDonorPhone());
        donorBloodGroup.setText(ndonorItems.getDonorBloodGroup());
        donorDivision.setText(ndonorItems.getDonorDivision());

        callDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + ndonorItems.getDonorPhone()));
                mContext.startActivity(intent);
            }
        });
        donorChat.setOnClickListener(new
                                             View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     Intent intent=new Intent(mContext, ChatMessageActivity.class);
                                                     mContext.startActivity(intent);
                                                 }
                                             });
    }
    public int getItemCount() {
        return donorItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView donorName , donorPhone, donorBloodGroup,donorDivision ;
        RelativeLayout callDoc ;
        RelativeLayout donorChat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            donorName  = itemView.findViewById(R.id.item_donor_Name);
            donorBloodGroup= itemView.findViewById(R.id.item_donor_bloodGroup);
            donorDivision=itemView.findViewById(R.id.item_donor_divison);
            donorPhone=itemView.findViewById(R.id.item_donor_contactNumber);
            callDoc = itemView.findViewById(R.id.call_doc_list_btn);
            donorChat=itemView.findViewById(R.id.donorChat);
        }
    }
}
