package com.example.lifer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.lifer.Data.Contact;

import java.util.List;
import com.example.lifer.R;
public class ContactsAdapter extends ArrayAdapter<Contact> {
    private LayoutInflater inflater;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_contact, parent, false);
        }

        Contact contact = getItem(position);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView phoneTextView = view.findViewById(R.id.phoneTextView);

        nameTextView.setText(contact.getName());
        phoneTextView.setText(contact.getPhoneNumber());

        return view;
    }
}
