package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Database.DatabaseAux;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Insert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void changeToMain(View view) {
        Intent nIntent = new Intent(Insert.this, MainActivity.class);
        startActivity(nIntent);
    }

    public void insertValues(View v) {
        TextView nameTextView = findViewById(R.id.insertName);
        TextView emailTextView = findViewById(R.id.insertEmail);

        String nameString = nameTextView.getText().toString();
        String emailString = emailTextView.getText().toString();

        DatabaseAux aux = new DatabaseAux(Insert.this);
        SQLiteDatabase db = aux.getWritableDatabase();

        if (db != null && !nameString.isEmpty() && !emailString.isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("name", nameString);
            values.put("email", emailString);

            long res = db.insert("users", null, values);
            if (res >= 0) {
                Toast.makeText(this, "Insertado correctamente", Toast.LENGTH_LONG).show();
                nameTextView.setText("");
                emailTextView.setText("");
            } else {
                Toast.makeText(this, "Fallo al insertar", Toast.LENGTH_LONG).show();
            }
            db.close();
        } else {
            Toast.makeText(this, "Fallo al insertar", Toast.LENGTH_LONG).show();
        }

        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        Map<String, String> users = new HashMap<>();
        users.put("email", nameString);
        users.put("name", emailString);

        firestoreDb.collection("2DAM").document(nameString)
                .set(users)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("DEBUG", "TODO OK");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ERROR", e.getMessage());
                    }
                });
    }
}