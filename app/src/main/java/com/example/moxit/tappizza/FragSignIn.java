package com.example.moxit.tappizza;


import android.content.Intent;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragSignIn extends Fragment {
    EditText SiEmailId,SiPassword,SiUserId;
    Button BtnSiSignIn,BtnSiCancel;


    public FragSignIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_sign_in, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BtnSiSignIn=(Button) view.findViewById(R.id.BtnSiSignIn);
        BtnSiCancel=(Button) view.findViewById(R.id.BtnSiCancel);
        SiUserId=(EditText) view.findViewById(R.id.SiUserId);
        SiEmailId=(EditText) view.findViewById(R.id.SiEmailId);
        SiPassword=(EditText) view.findViewById(R.id.SiPassword);

        BtnSiSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SiUserId.getText().toString().isEmpty()){


                    Toast.makeText(getActivity(),"User Id Required..!!",Toast.LENGTH_SHORT).show();
                    SiUserId.setError("User Id Required..!!");
                    return;
                }

                else if (SiEmailId.getText().toString().isEmpty()){


                    Toast.makeText(getActivity(),"Email Id Required..!!",Toast.LENGTH_SHORT).show();
                    SiEmailId.setError("Email Id Required..!!");
                    return;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(SiEmailId.getText().toString().trim()).matches())
                {
                    Toast.makeText(getActivity(), "Invalid Email Id", Toast.LENGTH_SHORT).show();
                    SiEmailId.setError("Invalid Email Id.!!");

                }else if (SiPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Password Required..!!",Toast.LENGTH_SHORT).show();
                    SiPassword.setError("Password Required..!!");
                    return;
                }else {
                    ArrayList<HashMap<String,String>> ArrUNameUPass = new DBHandler(getActivity()).UserDataReadFunction("select * from UserData where UEmailId='"+SiEmailId.getText().toString().trim()+"' and UPassword='"+SiPassword.getText().toString()+"'");
                    if(ArrUNameUPass.isEmpty())
                        Toast.makeText(getActivity(), "Invalid Username Or password..!!", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(getActivity(), "Logged In", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), JSONDemoActivity.class));
                    }
                }

            }
        });
        BtnSiCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CleanFields();

            }
        });
    }
    public void CleanFields()
    {
        SiUserId.setText("");
        SiEmailId.setText("");
        SiPassword.setText("");
    }


}
