package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Database.DatabaseAux;

public class Delete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
    }

    public void changeToMain(View view) {
        Intent nIntent = new Intent(Delete.this, MainActivity.class);
        startActivity(nIntent);
    }

    public void deleteCallback(View view) {
        TextView nameView = findViewById(R.id.deleteName);
        TextView emailView = findViewById(R.id.deleteEmail);

        String nameString = nameView.getText().toString();
        String emailString = emailView.getText().toString();

        SQLiteDatabase db = new DatabaseAux(this).getWritableDatabase();

        if (db != null && !nameString.isEmpty() && !emailString.isEmpty()) {
            long res = db.delete("users", "name = '" + nameString + "' and email = '" + emailString + "'", null);
            //db.update();
            if (res >= 0) {
                Toast.makeText(this, "TODO OK", Toast.LENGTH_LONG).show();
                nameView.setText("");
                emailView.setText("");
            } else {
                Toast.makeText(this, "TODO MAL", Toast.LENGTH_LONG).show();
            }
            db.close();
        } else {
            Toast.makeText(this, "Error al acceder a la base de datos", Toast.LENGTH_LONG).show();
        }
    }
}