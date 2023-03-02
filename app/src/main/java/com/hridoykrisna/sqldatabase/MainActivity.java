package com.hridoykrisna.sqldatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hridoykrisna.sqldatabase.DB.AppDatabase;
import com.hridoykrisna.sqldatabase.DB.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button cancelBtn, submitBtn;
    RecyclerView userRV;
    EditText firstNameEt, lastNameEt, mobileNoET;

    String firstName, lastName, mobile;
    User user;
    AppDatabase appDatabase;
    List<User> userList;
    UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameEt = findViewById(R.id.firstNameETId);
        lastNameEt = findViewById(R.id.lastNameETId);
        mobileNoET = findViewById(R.id.mobileNoETId);
        submitBtn = findViewById(R.id.submitButtonId);
        cancelBtn = findViewById(R.id.cancelButtonId);
        userRV = findViewById(R.id.userRVId);

        appDatabase = AppDatabase.getDBInstance(getApplicationContext());

        setUserRV();

        user = new User();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEt.getText().toString();
                lastName = lastNameEt.getText().toString();
                mobile = mobileNoET.getText().toString();

                if (firstName.equals("")) {
                    firstNameEt.setError("Please Write First Name");
                    firstNameEt.requestFocus();
                    return;
                }
                if (lastName.equals("")) {
                    lastNameEt.setError("Please Write Last Name");
                    lastNameEt.requestFocus();
                    return;
                }
                if (mobile.equals("")) {
                    mobileNoET.setError("Please Write Mobile No");
                    mobileNoET.requestFocus();
                    return;
                }

                user.firstName = firstName;
                user.lastName = lastName;
                user.mobile = mobile;


                appDatabase.userDao().insert(user);
                Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                try {
                    setUserRV();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelBtn.setOnClickListener(v -> {
            user.firstName = "";
            user.lastName = "";
            user.mobile = "";
        });


    }

    private void setUserRV() {
        try {
            userList = appDatabase.userDao().getAllUser();
            userRV.setLayoutManager(new LinearLayoutManager(this));
          /*  DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            userRV.addItemDecoration(dividerItemDecoration);*/
            userListAdapter = new UserListAdapter(this, userList);
            userRV.setAdapter(userListAdapter);
            userListAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}