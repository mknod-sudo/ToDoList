package com.example.simpletodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList items;

    Button btnAdd;
    EditText edtItems;
    RecyclerView vlItems;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        edtItems = findViewById(R.id.edtItems);
        vlItems = findViewById(R.id.vlItems);

        loadItems();


        ItemAdapter.OnLongClickListener onLongClickListener = new ItemAdapter.OnLongClickListener(){

            @Override
            public void OnItemLongClicked(int position) {
                items.remove(position);
                itemAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };

        itemAdapter = new ItemAdapter(items, onLongClickListener);
        vlItems.setAdapter(itemAdapter);
        vlItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String toDoItem = edtItems.getText().toString();

                items.add(toDoItem);
                itemAdapter.notifyItemInserted(items.size() - 1);
                edtItems.setText("");
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
                saveItems();
            }

        });
    }

    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }

    //adding another function which will read every line of the data file
    private void loadItems() {
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), String.valueOf(Charset.defaultCharset())));
        } catch (IOException e) {
            Log.e("MainActivity","Error reading items", e);
        }}

    private void saveItems(){
        try{
            FileUtils.writeLines(getDataFile(), items);}
        catch (IOException e){
            Log.e("MainActivity", "Error writing items", e);

        }

    }
}
