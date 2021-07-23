package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {
    EditText Rname,Remail,Rpassword;

    FirebaseAuth auth;
    Button Rbutton,gtloginView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Rname= findViewById(R.id.Rname);
        Remail= findViewById(R.id.Remail);
        Rpassword = findViewById(R.id.Rpassword);
        Rbutton = findViewById(R.id.Rbutton);
        gtloginView = findViewById(R.id.gtloginView);

        auth = FirebaseAuth.getInstance();


        gtloginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),loginActivity.class));

                finish();
            }
        });


        Rbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                String name = Rname.getText().toString();
                String email = Remail.getText().toString();
                String password = Rpassword.getText().toString();

                if(name.isEmpty()){
                    Rname.setError("name is required !");
                 //   Rname.setError(getResources().getString(R.string.email_error));
                    return;
                }if(email.isEmpty()){
                    Remail.setError("email is required !");
                    return;
                }
//                if (!Patterns.EMAIL_ADDRESS.matcher(Remail.getText().toString()).matches()) {
//                    Remail.setError("error_invalid_email");
//                    //isEmailValid = false;
//                }
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (Remail.getText().toString().trim().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                }

                if(password.isEmpty()){
                    Rpassword.setError("password is required !");
                    return;
                }if(!(password.length()>=6)){
                    Rpassword.setError("Enter 6 digit password required");
                    return;
                }

                //set validation ways


                // Check for a valid email address.
                //
                //
 /*                if (email.getText().toString().isEmpty()) {
                    email.setError(getResources().getString(R.string.email_error));
                    isEmailValid = false;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    email.setError(getResources().getString(R.string.error_invalid_email));
                    isEmailValid = false;
                } else  {
                    isEmailValid = true;
                }

                // Check for a valid password.
                if (password.getText().toString().isEmpty()) {
                    password.setError(getResources().getString(R.string.password_error));
                    isPasswordValid = false;
                } else if (password.getText().length() < 6) {
                    password.setError(getResources().getString(R.string.error_invalid_password));
                    isPasswordValid = false;
                } else  {
                    isPasswordValid = true;
                }

                if (isEmailValid && isPasswordValid) {
                    Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                }



*/



                Toast.makeText(getApplicationContext(),"valid",Toast.LENGTH_SHORT).show();


                auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }


}