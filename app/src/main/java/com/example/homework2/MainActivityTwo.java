package com.example.homework2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivityTwo extends AppCompatActivity {
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        Button editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(v -> {
            Intent editInfo = new Intent(MainActivityTwo.this, MainActivity.class);
            startActivity(editInfo);
        });
        Intent intent = getIntent();
        if(contact == null) {
            contact = (Contact) intent.getSerializableExtra("contact");
        }
        contact = retrieveSharedPreferences();
        retrieveInfo(contact);
    }

    public void retrieveInfo(Contact contact) {
        TextView name = findViewById(R.id.name_data);
        name.setText(contact.getName());
        TextView occupation = findViewById(R.id.occupation_data);
        occupation.setText(contact.getOccupation());
        TextView age = findViewById(R.id.age_data);
        age.setText(contact.getAge());
        TextView address = findViewById(R.id.address_data);
        address.setText(contact.getAddress());
        TextView email = findViewById(R.id.email_data);
        email.setText(contact.getEmail());
        TextView phone = findViewById(R.id.phone_number_data);
        phone.setText(contact.getPhone());
//        TextView freelance = findViewById(R.id.freelance_data);
//        freelance.setText(contact.getFreelance().toString());
    }

    public Contact retrieveSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("contact", "");
        Contact contact = gson.fromJson(json, Contact.class);
        retrieveInfo(contact);
        return contact;
    }
}
