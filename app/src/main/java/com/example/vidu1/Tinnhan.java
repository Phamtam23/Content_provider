package com.example.vidu1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Telephony;
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
import com.example.vidu1.model.SMS;

import java.util.ArrayList;

public class Tinnhan extends AppCompatActivity {
    private int REQUEST_CODQ_ASK_PERMISSONS = 1001;
    private ListView lv_sms;
    ArrayList<SMS> ds_sms;
    ArrayAdapter<SMS> apdapter_sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tinnhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv_sms=findViewById(R.id.lv_sms);
        ds_sms=new ArrayList<>();

        apdapter_sms=new ArrayAdapter<>(Tinnhan.this,android.R.layout.simple_list_item_1,ds_sms);
        lv_sms.setAdapter((apdapter_sms));
            showAllContacts();

    }



    private void showAllContacts() {
        Uri uri = Telephony.Sms.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            ds_sms.clear();
            while (cursor.moveToNext()) {
                String tenCotName =Telephony.Sms.ADDRESS;
                String tenCotSms = Telephony.Sms.BODY;

                int vitriCotName = cursor.getColumnIndex(tenCotName);
                int vitriCotSms = cursor.getColumnIndex(tenCotSms);

                String name = cursor.getString(vitriCotName);
                String sms = cursor.getString(vitriCotSms);

                SMS newsms = new SMS(name, sms);
                ds_sms.add(newsms);
            }
            apdapter_sms.notifyDataSetChanged();
            cursor.close();
        } else {
            Toast.makeText(this, "Không thể truy cập danh bạ", Toast.LENGTH_SHORT).show();
        }
    }

}