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
import android.widget.TextView;
import android.widget.Toast;

import com.example.phonefind.MainActivity;
import com.example.phonefind.R;
import com.example.phonefind.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    Button btn_Connexion;
    TextView createAccount, tv_passer;
    EditText et_email, et_password;
    FirebaseAuth mAuth;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_Connexion = (Button)findViewById(R.id.btnconnection);
        createAccount = (TextView) findViewById(R.id.label_newaccount);
        tv_passer = (TextView) findViewById(R.id.tv_skip);
        et_email = (EditText) findViewById(R.id.txtNameLogin);
        et_password = (EditText) findViewById(R.id.txtPasswordLogin);

        mAuth = FirebaseAuth.getInstance();

        btn_Connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();

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

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);

                                    Intent i = new Intent(LoginActivity.this,AccueilActivity.class);

                                    i.putExtra("Email", email);

                                    startActivity(i);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    // ...
                                }

                                // ...
                            }
                        });

            }
        });

        /*btn_Connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, AccueilActivity.class);
                startActivity(i);
                finish();
            }
        });*/

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        tv_passer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });



    }
}