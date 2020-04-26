package com.emilnymann.shoppingapp;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinnerAmount);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.item_amount_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView textViewCount = findViewById(R.id.textViewItemsAmount);
        RecyclerView recyclerViewItemsList = findViewById(R.id.recyclerViewItemsList);
        EditText editTextName = findViewById(R.id.editTextName);
        Button buttonAdd = findViewById(R.id.buttonAdd);
    }
}
