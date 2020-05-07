package com.example.reciever;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class update_menu extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    DatabaseReference database;
    String password;

    int stall;

    Spinner spin;

    String[] users = {"Select your stall", "Stall 1", "Stall 2", "Stall 3"};
    String user;

    String[] menuslots = {"Specialfood1", "Specialfood2", "Specialfood3", "Specialfood4", "Specialfood5"};
    int stall_number = 1;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu2);

        spin =  findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        Button reset = findViewById(R.id.buttonreset);
        Button add = findViewById(R.id.buttonadd2);

        add.setOnClickListener(this);
        reset.setOnClickListener(this);

        database = FirebaseDatabase.getInstance().getReference("Stall " + stall_number);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        user = users[position];
        stall = position;
        if (position != 0){

            user_authentication();

        }

        else if (user.equals(users[0])) {

            Toast.makeText(getApplicationContext(), "fuckk u" ,Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void  onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(getApplicationContext(), "fuckk u" ,Toast.LENGTH_SHORT).show();
    }

    private void user_authentication() {

        spin =  findViewById(R.id.spinner2);

        AlertDialog.Builder areusure = new AlertDialog.Builder(this);
        areusure.setTitle("Title");


        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        areusure.setView(input);

        areusure.setMessage("Please enter your password")
                .setCancelable(true)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        user = users[0];
                        dialog.cancel();

                    }
                })

                .setPositiveButton("Verify", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        {
                            password = input.getText().toString();
                            verification();
                        }

                    }
                });

        AlertDialog alert = areusure.create();
        alert.show();

    }


    private void verification(){
        spin = findViewById(R.id.spinner2);
        String[] password_list = {"penis1", "penis1", "penis1"};
        if (!password.equals(password_list[stall - 1])) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonreset){

            reset();
            return;
        }

        AlertDialog.Builder areusure = new AlertDialog.Builder(this);
        areusure.setTitle("Update Menu");

        areusure.setMessage("Are you sure you want to update this?")
                .setCancelable(true)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                    }
                })

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        {
                           updatedata();
                           retrive_data();
                        }

                    }
                });

        AlertDialog alert = areusure.create();
        alert.show();


    }

    public void retrive_data(){
        final TextView menu_item =  new TextView(this);
        final LinearLayout orders = findViewById(R.id.menulist);
        orders.addView(menu_item);

        if (i > 4) {

            i = 0;
        }
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.child(menuslots[i]).getValue(String.class);
                menu_item.setText(value);
                i++;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });



    }

    public void updatedata(){

         if (i > 4){
            i = 0;
        }
        EditText input = findViewById(R.id.editText);
        database.child(menuslots[i]).setValue(input.getText().toString());
        i++;
    }

    public void reset(){

        for (int i = 0; i < menuslots.length; i++){

            database.child(menuslots[i]).setValue(null);
        }


    }


}





