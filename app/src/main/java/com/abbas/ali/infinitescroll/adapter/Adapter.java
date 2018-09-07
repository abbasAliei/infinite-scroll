package com.abbas.ali.infinitescroll.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abbas.ali.infinitescroll.model.Contact;
import com.abbas.ali.infinitescroll.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<Contact> contacts = new ArrayList<>();

    public Adapter(Context context) {
        this.context = context;
    }

    public void addContacts(List<Contact> contacts){
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView numberTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_contactItem_name);
            numberTextView = itemView.findViewById(R.id.tv_contactItem_number);
        }

        public void bind(Contact contact){
            nameTextView.setText(contact.getName());
            numberTextView.setText(contact.getNumber());
        }
    }
}
