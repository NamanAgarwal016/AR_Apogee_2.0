package com.bitspilani.apogeear.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bitspilani.apogeear.Models.Event_Details;
import com.bitspilani.apogeear.R;

import java.util.ArrayList;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    private ArrayList<Event_Details> event_details;
    private String attend;
    private Context context;
    private static ClickListener clickListener;
    private boolean check,exists;


    public HorizontalAdapter(ArrayList<Event_Details> event_details, Context context,String attend,boolean exists) {
        this.event_details=event_details;
        this.context=context;
        this.attend=attend;
        check=false;
        this.exists=exists;
    }

    @NonNull
    @Override
    public HorizontalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontalitem,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(event_details.get(position).getName());
        Log.d("Event",event_details.get(position).getName());


            if (event_details.get(position).getName().equals(attend))
                check = true;

            if (event_details.size() == 1) {

                holder.hor.setVisibility(View.GONE);
                holder.or.setVisibility(View.INVISIBLE);
                holder.or1.setVisibility(View.INVISIBLE);
                holder.hor1.setVisibility(View.GONE);
                holder.hor2.setVisibility(View.VISIBLE);
                if(exists) {
                    holder.hor2.setBackgroundColor(Color.parseColor("#4ECE60"));
                    holder.text.setTextColor(Color.parseColor("#4ECE60"));
                }

            } else if (position == 0) {
                holder.or.setVisibility(View.INVISIBLE);
//            holder.hor1.setVisibility(View.GONE);
//            holder.hor2.setVisibility(View.VISIBLE);
//            holder.hor.setVisibility(View.GONE);
                if(exists) {
                    if (event_details.get(position).getName().equals(attend)) {
                        holder.hor.setBackgroundColor(Color.parseColor("#4ECE60"));
                        holder.hor1.setBackgroundColor(Color.parseColor("#4ECE60"));
                        holder.text.setTextColor(Color.parseColor("#4ECE60"));
                    } else if (!check) {
                        holder.or1.setBackgroundColor(Color.parseColor("#4ECE60"));
                        holder.hor.setBackgroundColor(Color.parseColor("#4ECE60"));
                    }
                }
            } else if (position == event_details.size() - 1) {
                holder.hor.setVisibility(View.INVISIBLE);
                holder.or1.setVisibility(View.INVISIBLE);

                if(exists) {
                    if (event_details.get(position).getName().equals(attend)) {
                        holder.or.setBackgroundColor(Color.parseColor("#4ECE60"));
                        holder.hor1.setBackgroundColor(Color.parseColor("#4ECE60"));
                        holder.text.setTextColor(Color.parseColor("#4ECE60"));
                    }
                }
            } else {
                holder.hor.setVisibility(View.INVISIBLE);
                if(exists) {
                    if (!check) {
                        holder.or.setBackgroundColor(Color.parseColor("#4ECE60"));
                        holder.or1.setBackgroundColor(Color.parseColor("#4ECE60"));
                    } else if (event_details.get(position).getName().equals(attend)) {
                        holder.or.setBackgroundColor(Color.parseColor("#4ECE60"));
                        holder.hor1.setBackgroundColor(Color.parseColor("#4ECE60"));
                        holder.text.setTextColor(Color.parseColor("#4ECE60"));
                    }
                }
            }

    }


    @Override
    public int getItemCount() {
        return event_details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        View hor,hor1,hor2,or,or1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.horizontal_item_text);
            hor=itemView.findViewById(R.id.hor);
            hor1=itemView.findViewById(R.id.hor1);
            hor2=itemView.findViewById(R.id.hor2);
            or=itemView.findViewById(R.id.or);
            or1=itemView.findViewById(R.id.or1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition(), view);
                }
            });
        }
    }

    public interface ClickListener {
        void onItemClicked(int position, View v);
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        HorizontalAdapter.clickListener = clickListener;
    }
}
