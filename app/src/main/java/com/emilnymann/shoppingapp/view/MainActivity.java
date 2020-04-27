package com.emilnymann.shoppingapp.view;

import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.emilnymann.shoppingapp.R;
import com.emilnymann.shoppingapp.persistence.DbTable;
import com.emilnymann.shoppingapp.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

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

        MainActivityViewModel mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        List<DbTable> dbTables = new ArrayList<>();
        ItemAdapter itemAdapter = new ItemAdapter(dbTables);

        recyclerViewItemsList.setAdapter(itemAdapter);
        recyclerViewItemsList.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerViewItemsList.addItemDecoration(itemDecoration);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String amount = spinner.getSelectedItem().toString();
                DbTable dbTable = new DbTable();
                dbTable.item = name;
                dbTable.quantity = Integer.valueOf(amount);
                mViewModel.insertNewItem(dbTable);
                editTextName.getText().clear();
            }
        });

        mViewModel.getItems().observe(this, dbTables1 -> {
            itemAdapter.setData(dbTables1);
            itemAdapter.notifyDataSetChanged();
        });

        mViewModel.getItemCount().observe(this, integer -> textViewCount.setText(String.valueOf(integer)));
    }
}
