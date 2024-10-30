package com.example.vidu1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vidu1.model.Contact;

import java.util.ArrayList;

public class Danhba extends AppCompatActivity {
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1000;
    private ListView lv_danhba;
    ArrayList<Contact> ds_danhba;
    ArrayAdapter<Contact> arrayAdapter_danhba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danhba);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.danhba), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv_danhba = findViewById(R.id.lv_danhba);
        ds_danhba = new ArrayList<>();
        arrayAdapter_danhba = new ArrayAdapter<>(Danhba.this, android.R.layout.simple_list_item_1, ds_danhba);
        lv_danhba.setAdapter(arrayAdapter_danhba);

            showAllContacts();

    }


    private void showAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            ds_danhba.clear();
            while (cursor.moveToNext()) {
                String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
                String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;

                int vitriCotName = cursor.getColumnIndex(tenCotName);
                int vitriCotPhone = cursor.getColumnIndex(tenCotPhone);

                String name = cursor.getString(vitriCotName);
                String phone = cursor.getString(vitriCotPhone);

                Contact contact = new Contact(name, phone);
                ds_danhba.add(contact);
            }
            arrayAdapter_danhba.notifyDataSetChanged();
            cursor.close();
        } else {
            Toast.makeText(this, "Không thể truy cập danh bạ", Toast.LENGTH_SHORT).show();
        }
    }
}
