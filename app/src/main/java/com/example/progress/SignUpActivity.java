package com.example.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity
{
    EditText Name,Email,PhoneNumber,Password,Username;
    Button callLogInButton,SubmitButton;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    public void Register(View view)
    {
        rootnode=FirebaseDatabase.getInstance();
        reference=rootnode.getReference("Users");

        if(!validateName() |!validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername())
        {
            return;
        }

        String vname=Name.getText().toString();
        String vemail=Email.getText().toString();
        String vphonenumber=PhoneNumber.getText().toString();
        String vusername=Username.getText().toString();
        String vpassword=Password.getText().toString();
        Log.i("name",""+vname);
        Log.i("email",""+vemail);
        Log.i("ph",""+vphonenumber);
        Log.i("username",""+vusername);
        Log.i("pass",""+vpassword);
        //get all the values
        Helperclass obj=new Helperclass(vname,vusername,vemail,vpassword,vphonenumber);
        reference.child(vusername).setValue(obj);
    }

    public boolean validateName()
    {
    String value=Name.getText().toString();
    if(value.isEmpty())
    {
        Name.setError("Field cannot be Empty");
        return false;
    }
    else
    {
        Name.setError(null);
        return true;
    }
}

    public boolean validateEmail()
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String value=Email.getText().toString();
        if(value.isEmpty())
        {
            Email.setError("Field cannot be Empty");
            return false;
        }
        else if (!value.matches(emailPattern))
        {
            Email.setError("Invalid Email");
            return false;
        }
        else
        {
            Email.setError(null);
            return true;
        }
}

    public boolean validatePhoneNo()
    {
        String value=PhoneNumber.getText().toString();
        if(value.isEmpty())
        {
            PhoneNumber.setError("Field cannot be Empty");
            return false;
        }
        else
        {
            PhoneNumber.setError(null);
            return true;
        }
    }

    public boolean validateUsername()
    {
        String value=Username.getText().toString();
        if(value.isEmpty())
        {
            Username.setError("Field cannot be Empty");
            return false;
        }
        else if(value.length()>15)
        {
            Username.setError("Username too long");
            return false;
        }
        else
        {
            Username.setError(null);
            return true;
        }
    }

    public boolean validatePassword()
    {
        String value=Password.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if(value.isEmpty())
        {
            Password.setError("Field cannot be Empty");
            return false;
        }
        else if(!value.matches(passwordVal))
        {
            Password.setError("Password too weak");
            return false;
        }
        else
        {
            Password.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //hooks
        callLogInButton=findViewById(R.id.callLogInButton);
        SubmitButton=findViewById(R.id.submitButton);
        Name=findViewById(R.id.name);
        Email=findViewById(R.id.email);
        PhoneNumber=findViewById(R.id.phoneNumber);
        Password=findViewById(R.id.password);
        Username=findViewById(R.id.username);


    }
}
