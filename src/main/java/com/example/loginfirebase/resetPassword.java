package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class resetPassword extends AppCompatActivity {

    EditText newpass1,newpass2;
    Button savebutton;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        user = FirebaseAuth.getInstance().getCurrentUser();
        newpass1 = findViewById(R.id.newpass1);
        newpass1 = findViewById(R.id.newpass1);
        savebutton = findViewById(R.id.savebutton);



        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = newpass1.getText().toString();
                String pass2 = newpass2.getText().toString();



                if(pass1.isEmpty()){
                    newpass1.setError("Required Filed!");
                    return;
                }
                if(pass2.isEmpty()){
                    newpass2.setError("Required Filed!");
                    return;
                }
                if(!(pass1.equals(pass2))){
                    newpass2.setError("Password should be same!");
                    return;
                }

                user.updatePassword(pass1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(resetPassword.this,"Password update",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });
    }
}