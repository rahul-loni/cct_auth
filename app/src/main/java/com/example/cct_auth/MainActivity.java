package com.example.cct_auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
     EditText email_txt,password_txt,cpassword_txt;
     Button btn;
     FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         firebaseAuth=FirebaseAuth.getInstance();
        email_txt=findViewById(R.id.txt_email);
        password_txt=findViewById(R.id.txt_password);
        cpassword_txt=findViewById(R.id.txt_Cpassword);
        btn=findViewById(R.id.btn_signup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get Data
                String email=email_txt.getText().toString().trim();
                String password=password_txt.getText().toString().trim();
                String cPassword=cpassword_txt.getText().toString().trim();

                if (email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if (cPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter CPassword", Toast.LENGTH_SHORT).show();
                }
                if (cPassword.equals(password)){
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Signup Complete", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),Signup.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(MainActivity.this, "Signup Not complete", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
}