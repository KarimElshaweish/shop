package com.example.root.shop;

import android.content.Context;
import android.content.Intent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.root.shop.config.Config;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Cheap.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Cheap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cheap extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Cheap() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cheap.
     */
    // TODO: Rename and change types and number of parameters
    public static Cheap newInstance(String param1, String param2) {
        Cheap fragment = new Cheap();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    EditText fnEditText;
    EditText snEditText;
    EditText cityEditText;
    EditText statEditText;
    EditText streetEditText;
    EditText postEditText;
    EditText phoneEditText;
    EditText altphoneEditText;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    User values ;
    String id;
    String Json_string;
    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_cheap, container, false);
          //paypal serv

        Button btn_save=view.findViewById(R.id.btn_save);
        fnEditText=view.findViewById(R.id.txt_fristname);
        snEditText=view.findViewById(R.id.txt_secandname);
        cityEditText=view.findViewById(R.id.txt_city);
        statEditText=view.findViewById(R.id.txt_state);
        streetEditText=view.findViewById(R.id.txt_street);
        postEditText=view.findViewById(R.id.txt_posta);
        phoneEditText=view.findViewById(R.id.txt_phonenumber);
        altphoneEditText=view.findViewById(R.id.txt_altphonenumber);

        progressBar=view.findViewById(R.id.progress);
      /*  try {
            String method="userjson";
            Background_Task background_task=new Background_Task(getContext());
            String result=background_task.execute(method).get();
            parsJson(view,result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              uploadCheapInfo_mysqli(view);
            }
        });
        return view;
    }
    public void parsJson(View view,String result){
        UserSessionManager sessionManager=new UserSessionManager(getContext()) ;
        HashMap<String,String>User=sessionManager.getUserDatails();
        this.view=view;
        if(result.isEmpty()){
            Toast.makeText(view.getContext(), "No Json", Toast.LENGTH_SHORT).show();
        }else{
            try {
                JSONObject jsonObject = new JSONObject(result);
               JSONArray jsonArray=jsonObject.getJSONArray("server response");
                int count=0;
                int i=jsonArray.length();
                while (count<=i){
                    JSONObject jo=jsonArray.getJSONObject(count);
                    if(jo.getString("Email").equals(User.get("email"))) {
                        info.Fname = (jo.getString("FName"));
                        info.Sname=(jo.getString("LName"));
                        info.City=(jo.getString("City"));
                        info.State=(jo.getString("State"));
                        info.Street=jo.getString("Street");
                        info.Phone=jo.getString("PhoneNumber");
                        info.AltPhone=jo.getString("AltPhoneNumber");
                        break;
                    }
                    count++;

                }
                fnEditText.setText(info.Fname);
                snEditText.setText(info.Sname);
                cityEditText.setText(info.City);
                statEditText.setText(info.State);
                streetEditText.setText(info.Street);
                phoneEditText.setText(info.Phone);
                altphoneEditText.setText(info.AltPhone);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    public void uploadCheapInfo_mysqli(View view){
        progressBar.setVisibility(View.VISIBLE);
        String fristName=fnEditText.getText().toString();
        String secandName=snEditText.getText().toString();
        String cityName=cityEditText.getText().toString();
        String stateName=statEditText.getText().toString();
        String streetName=streetEditText.getText().toString();
        String Post=postEditText.getText().toString();
        String AltPhone=altphoneEditText.getText().toString();
        String Phone=phoneEditText.getText().toString();
        UserSessionManager sessionManager=new UserSessionManager(getContext());
        HashMap<String,String>User=sessionManager.getUserDatails();
        String method="insert";
        Background_Task background_task=new Background_Task(getContext());
        background_task.execute(method,fristName,secandName,cityName,
                stateName,streetName,Post,AltPhone,Phone,User.get("email"));
        progressBar.setVisibility(View.GONE);
    }
    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds:dataSnapshot.child("Users").getChildren()){
            User user=new User();
            user.setFName(ds.child(id).getValue(User.class).getFName());
            user.setSName(ds.child(id).getValue(User.class).getSName());
            user.setCity(ds.child(id).getValue(User.class).getCity());
            values=user;
        }
    }

    public void updateDate(){
        progressBar.setVisibility(View.VISIBLE);
        String fristName=fnEditText.getText().toString();
        String secandName=snEditText.getText().toString();
        String cityName=cityEditText.getText().toString();
        String stateName=statEditText.getText().toString();
        String streetName=streetEditText.getText().toString();
        int postNumber;
        if(!postEditText.getText().toString().isEmpty()) {
             postNumber = Integer.parseInt(postEditText.getText().toString());
        }else
            postNumber=0;
        int phoneNumber;
        if(!phoneEditText.getText().toString().isEmpty()) {
             phoneNumber = Integer.parseInt(phoneEditText.getText().toString());
        }else
             phoneNumber=0;
        int altPhoneNumber;
        if(!altphoneEditText.getText().toString().isEmpty()) {
             altPhoneNumber = Integer.parseInt(altphoneEditText.getText().toString());
        }else
            altPhoneNumber=0;
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            User mUser1=new User(fristName,secandName,cityName,stateName,streetName,phoneNumber
            ,altPhoneNumber,postNumber);
            DatabaseReference mDatabaseReference=FirebaseDatabase.getInstance().getReference();
            String[] idName=user.getEmail().split("@");
            mDatabaseReference.child("Users").child(idName[0]).setValue(mUser1);
            progressBar.setVisibility(View.GONE);
        }
    }
    public void get_json(View view) throws ExecutionException, InterruptedException {
        String method="userjson";
        Background_Task background_task=new Background_Task(getContext());
        background_task.execute(method).get();
        Toast.makeText(view.getContext(),info.Fname, Toast.LENGTH_SHORT).show();
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
