package com.example.progress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity
{
    TextInputLayout Name,Email,PhoneNumber,Password,Username;

    Button callLogInButton,SubmitButton;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    public void go(View view)
    {
        rootnode=FirebaseDatabase.getInstance();
        reference=rootnode.getReference("users");

        //ass soon as you accept values in the emulator the app crashes
        //fuck firebase for now just try to print it in the log
        //bas text leke print karva de
        String vname=Name.getEditText().getText().toString();
        String vemail=Email.getEditText().getText().toString();
        String vphonenumber=PhoneNumber.getEditText().getText().toString();
        String vusername=Username.getEditText().getText().toString();
        String vpassword=Password.getEditText().getText().toString();
        Log.i("name",""+vname);
        Log.i("email",""+vemail);
        Log.i("ph",""+vphonenumber);
        Log.i("username",""+vusername);
        Log.i("pass",""+vpassword);

        //get all the values
        //Helperclass obj=new Helperclass(vname,vusername,vemail,vpassword,vphonenumber);
        //reference.setValue(obj);
        //reference.setValue("lolol");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //hooks
        callLogInButton=findViewById(R.id.callLogInButton);
        SubmitButton=findViewById(R.id.submitButton);
        Name=findViewById(R.id.NameEditText);
        Email=findViewById(R.id.emailEditText);
        PhoneNumber=findViewById(R.id.phoneNumberEditText);
        Password=findViewById(R.id.phoneNumberEditText);


    }
}
