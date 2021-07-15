package com.example.managementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserEventAdapter extends RecyclerView.Adapter<UserEventAdapter.UserEventViewHolder> {

    private List<UserEvent> userEvents;

    public UserEventAdapter(List<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    public class UserEventViewHolder extends RecyclerView.ViewHolder{

        TextView titleTv;
        TextView descriptionTv;
        TextView dateTv;

        public UserEventViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.card_view_eventTitleTv);
            descriptionTv = itemView.findViewById(R.id.card_view_eventDescriptionTv);
            dateTv = itemView.findViewById(R.id.card_view_eventDateTv);
        }
    }

    @NonNull
    @Override
    public UserEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_event_cell, parent, false);
        UserEventViewHolder userEventViewHolder = new UserEventViewHolder(view);
        return userEventViewHolder;
    }

    @Override
    public int getItemCount() {
        return userEvents.size();
    }

    @Override
    public void onBindViewHolder(@NonNull UserEventViewHolder holder, int position) {

        UserEvent userEvent = userEvents.get(position);
        holder.titleTv.setText(userEvent.getTitle());
        holder.descriptionTv.setText(userEvent.getDescription());
        holder.dateTv.setText(userEvent.getDate());
    }
}
