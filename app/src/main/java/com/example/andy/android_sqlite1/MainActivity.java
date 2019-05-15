package com.example.andy.android_sqlite1;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SQL2 sqlDB;
    TextView text1;
    Button buttonCreate;
    Button buttonInsert;
    Button buttonDelete;
    Button buttonDropTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlDB = new SQL2(getBaseContext());

        text1 = (TextView) findViewById(R.id.statusText);

        buttonCreate = (Button) findViewById(R.id.buttonCreate);
        buttonInsert = (Button) findViewById(R.id.buttonInsert);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDropTable = (Button) findViewById(R.id.buttonDropTable);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB.createTable();
                text1.setText("Table 'Clients' Created !");
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    sqlDB.saveNewRecord2("My Name", "My Lastname", new Random().nextInt(18));
                    Cursor cs = sqlDB.getData();
                    cs.moveToFirst();
                    if (cs.moveToFirst()) {
                        do {
                            text1.setText(cs.getString(0) + " " + cs.getString(1) + " " +
                                    cs.getString(2) + " " + cs.getString(3) + "\n");
                        }
                        while (cs.moveToNext());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    text1.setText("Error Inserting Client !");
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cs = sqlDB.getData();
                if (cs != null) {
                    try {
                        cs.moveToLast();
                        sqlDB.deleteClient(Integer.parseInt(cs.getString(0)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (CursorIndexOutOfBoundsException cex) {
                        cex.printStackTrace();
                    }

                }
            }
        });

        buttonDropTable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sqlDB.dropTable();
                text1.setText("Table Dropped");
            }
        });

    }
}
