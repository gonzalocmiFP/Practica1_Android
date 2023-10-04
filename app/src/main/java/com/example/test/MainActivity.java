package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, "HOLA", Toast.LENGTH_LONG).show();
        Button buttonTest = findViewById(R.id.botonprincipal);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "HOLA", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void diceAdios(View view) {
        // Toast.makeText(this, "ADIOS", Toast.LENGTH_SHORT).show();
        // Leo lo que ha escrito el usuario
        TextView nameText = findViewById(R.id.editTextText);
        String name = nameText.getText().toString();
        Toast.makeText(this, "Hola" + name, Toast.LENGTH_LONG).show();
    }

    public void changeToInsert(View view) {
        Intent nIntent = new Intent(MainActivity.this, InsertActivity.class);
        startActivity(nIntent);
    }

}