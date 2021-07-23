package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button logout,verifyButton;

    TextView verifyText;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        verifyButton = findViewById(R.id.verifyButton);
        verifyText = findViewById(R.id.verifyText);

        auth =FirebaseAuth.getInstance();

        if(!auth.getCurrentUser().isEmailVerified()){
            verifyText.setVisibility(View.VISIBLE);
            verifyButton.setVisibility(View.VISIBLE);
        }


        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Verifacation sent",Toast.LENGTH_SHORT).show();
                        verifyText.setVisibility(View.GONE);
                        verifyButton.setVisibility(View.GONE);
                    }
                });


            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(getApplicationContext(),loginActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_simple,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.resetPassword){
//            startActivity(new Intent(getApplicationContext(),resetPassword.class));
            startActivity(new Intent(getApplicationContext(),resetPassword2.class));
        }
        return super.onOptionsItemSelected(item);
    }
}