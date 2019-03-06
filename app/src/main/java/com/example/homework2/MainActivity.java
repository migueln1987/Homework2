package com.example.homework2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final  Pattern VALID_PHONE_NUMBER_REGEX =
            Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
    static final int REQUEST_IMAGE_CAPTURE = 1;

    SharedPreferences sharedPreferences;

    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.occupation)
    EditText occupation;
    @BindView(R.id.age_dropdown)
    Spinner ageDropdown;
    @BindView(R.id.e_mail)
    EditText emailText;
    @BindView(R.id.phone)
    EditText phoneText;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.submit_button)
    Button submit_button;
    @BindView(R.id.textInputLayout)
    TextInputLayout emailWrapper;
    @BindView(R.id.textInputLayout2)
    TextInputLayout phoneWrapper;


    ArrayList<String> ages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        boolean secondActivity = sharedPreferences.getBoolean("second_activity", false);
        if(secondActivity) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("second_activity", false).apply();
            Intent intent = new Intent(this, MainActivityTwo.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ages = populateListWithAges();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ages);
        ageDropdown.setAdapter(adapter);
//        sharedPreferences = getSharedPreferences("sharedPreferences",MODE_PRIVATE);
    }

    public ArrayList populateListWithAges() {
        for (int i = 15; i < 101; i++) {
            ages.add(String.valueOf(i));
        }
        return ages;
    }

    public DataValidation validateText(EditText view) {
        Matcher matcher;
        if(R.id.e_mail == view.getId()){
            matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(view.getText().toString());
            if(matcher.find()) {
                return DataValidation.VALID_EMAIL;
            } else {
                return DataValidation.INVALID_EMAIL;
            }
        } else {
            matcher = VALID_PHONE_NUMBER_REGEX.matcher(view.getText().toString());
            if(matcher.find()) {
                return DataValidation.VALID_PHONE_NUMBER;
            } else {
                return DataValidation.INVALID_PHONE_NUMBER;
            }
        }
    }

    @OnClick(R.id.submit_button)
    public void submitButton(View view) {
        String email = emailWrapper.getEditText().getText().toString();
        String phone = phoneWrapper.getEditText().getText().toString();
        
        if(!validEmail(email)) {
            emailWrapper.setError("Not a valid email address.");
        } else if (!validPhone(phone)) {
            phoneWrapper.setError("Not a valid phone number");
        } else {
            emailWrapper.setErrorEnabled(false);
            phoneWrapper.setErrorEnabled(false);
            startNewActivity();
        }

    }

    public void startNewActivity() {
        Intent intent = new Intent(this, MainActivityTwo.class);
        Contact tempContact = new Contact(name.getText().toString(), occupation.getText().toString(), ageDropdown.getSelectedItem().toString(), address.getText().toString(),emailText.getText().toString(), phoneText.getText().toString());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("second_activity", true).apply();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Convert Contact object to JSON
        Gson gson = new Gson();
        String json = gson.toJson(tempContact);
        // Add JSON text to SharedPreferences
        editor.putString("contact", json);
        editor.commit();

        intent.putExtra("contact", tempContact);
        startActivity(intent);
    }



    private boolean validEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    private boolean validPhone(String phone) {
        Matcher matcher = VALID_PHONE_NUMBER_REGEX .matcher(phone);
        return matcher.find();
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
