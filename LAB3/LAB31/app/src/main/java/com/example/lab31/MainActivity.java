package com.example.lab31;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;
    private Contact selectedContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        databaseHandler = new DatabaseHandler(this);
        databaseHandler.deleteAllContacts();

        for (int i = 0; i < 10; i++) {
            String name = "Nguyễn Văn An " + i;
            String phone = "012345678" + i;


            Contact contact = new Contact(null, name, phone);
            databaseHandler.addContact(contact);
        }

        showData();
        ListView lvContacts = findViewById(R.id.listViewContacts);
        lvContacts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedContact = (Contact) parent.getItemAtPosition(position);
                if (selectedContact != null) {
                    databaseHandler.deleteContact(selectedContact.getId());
                    showData();
                    Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }


    private List<Contact> getData() {
        return databaseHandler.getAllContacts();
    }

    private void showData() {
        ListView lvContacts = findViewById(R.id.listViewContacts);
        List<Contact> contactsData = getData();


        ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this, contactsData);
        lvContacts.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
    }
}
