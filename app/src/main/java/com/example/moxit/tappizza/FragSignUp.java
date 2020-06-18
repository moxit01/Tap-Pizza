package com.example.moxit.tappizza;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragSignUp extends Fragment {

    Button BtnSuSignUp,BtnSuCancel;
    EditText FirstName,MiddleName,LastName,UserId,ContactNo,SuEmailId,SuPassword;
    public FragSignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_sign_up, container, false);
    }


    public static boolean isContactNoValid(String Conno){

        //regex for mobile number

        String regExpn="\\d{10}";
        CharSequence inputStr=Conno;
        Pattern pattern =Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(inputStr);
        if(matcher.matches())
            return true;
        else
            return false;
    }
    public void CleanEditText(){
        FirstName.setText("");
        MiddleName.setText("");
        LastName.setText("");
        UserId.setText("");
        ContactNo.setText("");
        SuEmailId.setText("");
        SuPassword.setText("");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BtnSuSignUp=(Button) view.findViewById(R.id.BtnSuSignUp);
        BtnSuCancel=(Button) view.findViewById(R.id.BtnSuCancel);
        FirstName=(EditText) view.findViewById(R.id.FirstName);
        MiddleName=(EditText) view.findViewById(R.id.MiddleName);
        LastName=(EditText) view.findViewById(R.id.LastName);
        UserId=(EditText)view.findViewById(R.id.UserId);
        ContactNo=(EditText) view.findViewById(R.id.ContactNo);
        SuEmailId=(EditText) view.findViewById(R.id.SuEmailId);
        SuPassword=(EditText) view.findViewById(R.id.SuPassword);


        BtnSuSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //this is to get the text from edit text and check it is empty
                if (FirstName.getText().toString().isEmpty()){

                    //this is to show the error message  on the screen

                    Toast.makeText(getActivity(),"First Name Required..!!",Toast.LENGTH_SHORT).show();
                    FirstName.setError("First Name Required..!!");
                    return;
                }else if (MiddleName.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Middle Name Required..!!",Toast.LENGTH_SHORT).show();
                    MiddleName.setError("Middle Name Required..!!");
                    return;
                }else if(LastName.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(),"Last Name Required..!!",Toast.LENGTH_SHORT).show();
                    LastName.setError("Last Name Required..!!");
                    return;
                }
                else if(UserId.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(),"User Id Required..!!",Toast.LENGTH_SHORT).show();
                    LastName.setError("User Id Required..!!");
                    return;
                }
                //this function is to validate the contact number
                else if(!isContactNoValid(ContactNo.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "Contact No Required..!!", Toast.LENGTH_SHORT).show();
                    ContactNo.setError("Contact No Required..!!");
                    return;
                }
                //this function is to validate the email
                else if(SuEmailId.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Email Required..!!", Toast.LENGTH_SHORT).show();
                    SuEmailId.setError("Email Id Required..!!");
                    return;
                }else if (!Patterns.EMAIL_ADDRESS.matcher(SuEmailId.getText().toString().trim()).matches())
                {
                    Toast.makeText(getActivity(), "Invalid Email Id", Toast.LENGTH_SHORT).show();
                    SuEmailId.setError("Invalid Email Id.!!");
                    return;
                }
                else if(SuPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Password Required..!!", Toast.LENGTH_SHORT).show();
                    SuPassword.setError("Password Required..!!");
                    return;
                }else{
                    ArrayList<HashMap<String,String>> ARRUSER = new ArrayList<>();

                    // to validtae email and contact no.

                    ARRUSER=new DBHandler(getActivity()).UserDataReadFunction(
                            "select * from UserData Where UContactNo='"+ContactNo.getText().toString().trim()+"'" +
                                    "or UEmailId='"+SuEmailId.getText().toString().trim()+"'");
                    if(ARRUSER.isEmpty()) {
                        ARRUSER.clear();
                        ARRUSER=new DBHandler(getActivity()).UserDataReadFunction("Select * from UserData");
                        ContentValues CV=new ContentValues();

                        //to manage the UID

                        CV.put("UID",ARRUSER.size()+1);
                        CV.put("UFName",FirstName.getText().toString().trim());
                        CV.put("UMName",MiddleName.getText().toString().trim());
                        CV.put("ULName",LastName.getText().toString().trim());
                        CV.put("UserId",UserId.getText().toString().trim());
                        CV.put("UContactNo",ContactNo.getText().toString().trim());
                        CV.put("UEmailId",SuEmailId.getText().toString().trim());
                        CV.put("UPassword",SuPassword.getText().toString().trim());


                        int Flag=new DBHandler(getActivity()).InsertUserData(CV);
                        if(Flag==1) {
                            CleanEditText();
                            Toast.makeText(getActivity(), "User Accout has been successfully Created",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            CleanEditText();
                            Toast.makeText(getActivity(),"UnExpected Error",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Email Id or Contact No Already Registered", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        BtnSuCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CleanFields();
            }
        });
    }

    public void CleanFields()
    {
        SuEmailId.setText("");
        SuPassword.setText("");
        FirstName.setText("");
        MiddleName.setText("");
        LastName.setText("");
        UserId.setText("");
        ContactNo.setText("");
    }


}
