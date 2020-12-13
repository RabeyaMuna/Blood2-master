package com.example.blood.Activities.Model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.blood.R;
public class DonorViewContent extends RecyclerView.ViewHolder {
    public TextView name,contactNumber,bloodGroup,division;
    public DonorViewContent(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.item_donor_Name);
        contactNumber=(TextView)itemView.findViewById(R.id.item_donor_contactNumber);
        bloodGroup=(TextView)itemView.findViewById(R.id.item_donor_contactNumber);
        division=(TextView)itemView.findViewById(R.id.item_donor_divison);

    }
}
