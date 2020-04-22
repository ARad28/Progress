package com.example.progress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity
{
    Button callSignUp;
    Button Submit;
    TextInputLayout Username,Password;
    public void Signup(View view)//calls signup class
    {
        Intent signup= new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(signup);
    }

    public boolean validateUsername()
    {
        String value=Username.getEditText().getText().toString();
        if(value.isEmpty())
        {
            Username.setError("Field cannot be Empty");
            return false;
        }
        else
        {
            Username.setError(null);
            Username.setEnabled(false);
            return true;
        }
    }

    public boolean validatePassword()
    {
        String value=Password.getEditText().getText().toString();
        if(value.isEmpty())
        {
            Password.setError("Field cannot be Empty");
            return false;
        }
        else
        {
            Password.setError(null);
            Password.setEnabled(false);
            return true;
        }
    }

    public void LoginUser(View view)
    {
        if(!validateUsername() || !validatePassword())
        {
            return;
        }
        else
        {
            userExist();
        }
    }

    private void userExist()
    {
        final String inputUsername=Username.getEditText().getText().toString().trim();
        final String inputPassword=Password.getEditText().getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser=reference.orderByChild("username").equalTo(inputUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Username.setError(null);
                    Username.setErrorEnabled(false);
                    String passwordFromDB=dataSnapshot.child(inputUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(inputPassword))
                    {
                        Password.setError(null);
                        Password.setErrorEnabled(false);
                        String nameFromDB=dataSnapshot.child(inputUsername).child("name").getValue(String.class);
                        String emailFromDB=dataSnapshot.child(inputUsername).child("email").getValue(String.class);
                        String phonenumberFromDB=dataSnapshot.child(inputUsername).child("phonenumber").getValue(String.class);
                        String usernameFromDB=dataSnapshot.child(inputUsername).child("username").getValue(String.class);

                        Log.i("Logged In","true");
                    }
                    else
                    {
                        Log.i("wrong pass","true");
                        Password.setError("Wrong password entered");
                        Password.requestFocus();
                    }
                }
                else
                {
                    Username.setError("Username Empty");
                    Username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //hooks
        callSignUp=findViewById(R.id.signupButton);
        Submit=findViewById(R.id.submitButton);
        Username=findViewById(R.id.usernameEditText);
        Password=findViewById(R.id.passwordEditText);

    }
}
