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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class stall_authentication extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String password;
    int stall;
    String[] users = {"Select your stall", "Stall 1", "Stall 2", "Stall 3"};
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stall_authentication);

        Spinner spin =  findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        user = users[position];
        stall = position;
        if (position != 0){

            user_authentication();

        }

    }

    @Override
    public void  onNothingSelected(AdapterView<?> parent) {

        Toast.makeText(getApplicationContext(), "fuckk u" ,Toast.LENGTH_SHORT).show();
    }

    private void user_authentication() {


        AlertDialog.Builder areusure = new AlertDialog.Builder(this);
        areusure.setTitle("Title");


        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        areusure.setView(input);

        areusure.setMessage("Please enter your password")
                .setCancelable(true)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

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

        String[] password_list = {"penis1", "penis1", "penis1"};
        if (!password.equals(password_list[stall - 1])) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        else {

            Intent intent = new Intent(this, update_menu.class);
            startActivity(intent);

        }

    }




}


