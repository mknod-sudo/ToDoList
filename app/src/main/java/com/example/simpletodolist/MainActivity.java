package com.example.simpletodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final List<String> items;

        Button btnAdd;
        final EditText edtItems;
        RecyclerView vlItems;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        edtItems = findViewById(R.id.edtItems);
        vlItems = findViewById(R.id.vlItems);

        items =  new ArrayList<>();
        items.add("Buy Water");
        items.add("Go to the gym");
        items.add("Do laundry");

        final ItemAdapter itemAdapter = new ItemAdapter(items);
        vlItems.setAdapter(itemAdapter);
        vlItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String toDoItem = edtItems.getText().toString();
                items.add(toDoItem);
                itemAdapter.notifyItemInserted(items.size() - 1);
                edtItems.setText("");
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
