package com.example.blood.Activities.Controller;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.blood.Activities.Model.Blood_Group_profile_items;
import com.example.blood.R;

import java.util.ArrayList;

public class Adapter_of_matched_bloodGroup_profile extends RecyclerView.Adapter<Adapter_of_matched_bloodGroup_profile.ViewHolder> {


    private Context mContext;
    private ArrayList<Blood_Group_profile_items> blood_group_profile_items;
    public Adapter_of_matched_bloodGroup_profile(Context mContext, ArrayList<Blood_Group_profile_items> blood_group_profile_items) {
        this.mContext = mContext;
        this.blood_group_profile_items = blood_group_profile_items;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.activity_adapter_of_matched_blood_group_profile, parent, false);

        return new Adapter_of_matched_bloodGroup_profile.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Blood_Group_profile_items nblood_Group_profile_items= blood_group_profile_items.get(position);

        TextView bloodGroup_Name = holder.bloodGroup_Name ;
        TextView bloodGroup_Phone = holder.bloodGroup_Phone  ;
        TextView bloodGroup_BloodGroup = holder.bloodGroup_BloodGroup;
        TextView bloodGroup_Division = holder.bloodGroup_Division  ;
        RelativeLayout callDoc = holder.callDoc;

        bloodGroup_Name.setText(nblood_Group_profile_items.getBlood_bank_Name());
        bloodGroup_Phone.setText(nblood_Group_profile_items.getBlood_bank_Phone());
        bloodGroup_BloodGroup.setText(nblood_Group_profile_items.getBlood_bank_BloodGroup());
        bloodGroup_Division.setText(nblood_Group_profile_items.getBlood_bank_Division());

        callDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + nblood_Group_profile_items.getBlood_bank_Phone()));
                mContext.startActivity(intent);
            }
        });
    }
    public int getItemCount() {
        return blood_group_profile_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bloodGroup_Name ,bloodGroup_Phone, bloodGroup_BloodGroup,bloodGroup_Division ;
        RelativeLayout callDoc ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bloodGroup_Name  = itemView.findViewById(R.id.Blood_bank_adapter_item_donor_Name);
            bloodGroup_BloodGroup= itemView.findViewById(R.id.Blood_bank_adapter_item_bloodGroup);
            bloodGroup_Division=itemView.findViewById(R.id.Blood_bank_adapter_item_divison);
            bloodGroup_Phone=itemView.findViewById(R.id.Blood_bank_adapter_item_contactNumber);
            callDoc = itemView.findViewById(R.id.Blood_Group_adapter_list_call_doc_list_btn);
        }
    }

}