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

import com.example.blood.Activities.Model.UserData;
import com.example.blood.R;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>  {
    private Context mContext;
    private ArrayList<UserData> feed_Items;
    public FeedAdapter(Context mContext, ArrayList<UserData> feed_Items) {
        this.mContext = mContext;
        this.feed_Items = feed_Items;
    }
    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.feed_items_list, parent, false);

        return new FeedAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {
        final UserData nfeed_Items= feed_Items.get(position);

        TextView feedName = holder.feedName ;
        TextView feedBlood = holder.feedBlood ;
        TextView feedPhone = holder.feedPhone ;
        TextView feedAddress = holder.feedAddress ;
        TextView feedDivision = holder.feedDivision ;
        RelativeLayout callDoc = holder.callDoc;

        feedName.setText(nfeed_Items.getName());
        feedBlood.setText(nfeed_Items.getBloodGroup());
        feedPhone.setText(nfeed_Items.getContact());
        feedAddress.setText(nfeed_Items.getAddress());
        feedDivision.setText(nfeed_Items.getDivision());
        callDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + nfeed_Items.getContact()));
                mContext.startActivity(intent);
            }
        });
    }
    public int getItemCount() {
        return feed_Items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView feedName , feedBlood, feedPhone,feedAddress ,feedDivision;
        RelativeLayout callDoc ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feedName  = itemView.findViewById(R.id.Feed_Name);
            feedBlood= itemView.findViewById(R.id.Feed_Blood);
            feedPhone=itemView.findViewById(R.id.Fee_Phone);
            feedAddress=itemView.findViewById(R.id.Fedd_Address);
            callDoc = itemView.findViewById(R.id.Feed_call_feed_btn);
            feedDivision=itemView.findViewById(R.id.Feed_Divison);
        }
    }
}
