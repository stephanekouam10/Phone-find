package com.example.phonefind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    Button btn_Register;
    EditText et_username, et_email, et_phone, et_password, et_cPassword;
    String id, username, email ="", phone, password = "", cPassword;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabase, userRoot;

    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_Register = (Button)findViewById(R.id.btnregister);
        et_username = (EditText) findViewById(R.id.txtNameLogin);
        et_email = (EditText) findViewById(R.id.txtEmail);
        et_phone = (EditText) findViewById(R.id.txtPhone);
        et_password = (EditText) findViewById(R.id.txtPasswordLogin);
        et_cPassword = (EditText) findViewById(R.id.txtConfirmPasswordLogin);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        btn_Register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                username = et_username.getText().toString().trim();
                email = et_email.getText().toString().trim();
                phone = et_phone.getText().toString().trim();
                password = et_password.getText().toString().trim();
                cPassword = et_cPassword.getText().toString().trim();

                if(username.isEmpty()){
                    et_username.setError("Username is required!");
                    et_username.requestFocus();
                    return;
                }

                if(email.isEmpty()){
                    et_email.setError("Email is required!");
                    et_email.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    et_email.setError("Please enter valid email!");
                    et_email.requestFocus();
                    return;
                }

                if(phone.isEmpty()){
                    et_phone.setError("Phone number is required!");
                    et_phone.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    et_password.setError("Password is required!");
                    et_password.requestFocus();
                    return;
                }

                if(password.length() < 6){
                    et_password.setError("Min password length is 6 characters");
                    et_password.requestFocus();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegisterActivity.this, "createUserWithEmail:success",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);

                                    HashMap<String, Object> result = new HashMap<>();
                                    String id = user.getUid();
                                    result.put("id", id);
                                    result.put("username", username);
                                    result.put("email", email);
                                    result.put("phone", phone);
                                    result.put("password", password);

                                    userRoot = mDatabase.child("users").child(id);

                                    startActivity(new Intent(RegisterActivity.this, AccueilActivity.class));

                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}