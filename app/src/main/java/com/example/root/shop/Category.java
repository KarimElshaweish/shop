package com.example.root.shop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Category.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Category#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Category extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Category() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Category.
     */
    // TODO: Rename and change types and number of parameters
    public static Category newInstance(String param1, String param2) {
        Category fragment = new Category();
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
    GridView gridView;
    String Letter[]={"أجهزة موبايل","كفرات","شواحن وكبايل","ملحقات الصوت","بطاريات متنقلة","منوعات إلكترونية"};
    int Icons[]={R.drawable.iphonex,R.drawable.covers,R.drawable.cabl,R.drawable.sound,R.drawable.powerbank,R.drawable.iphonex};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_category, container, false);
         gridView=view.findViewById(R.id.gridView);
         GridViewAdapter adapter=new GridViewAdapter(Icons,Letter,getContext());
         gridView.setAdapter(adapter);
         gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Intent intent;
                    switch (i){
                        case 0:
                             intent=new Intent(getContext(),Mobile.class);
                            startActivity(intent);
                            break;
                        case 1:
                            intent=new Intent(getContext(),Covers.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent=new Intent(getContext(),Cable.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent =new Intent(getContext(),Sound.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent=new Intent(getContext(),PowerBank.class);
                            startActivity(intent);
                            break;
                        case 5:
                            intent=new Intent(getContext(),Electornic.class);
                            startActivity(intent);
                            break;

                    }
             }
         });
         return view;
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
            Toast.makeText(context, "الفئات", Toast.LENGTH_SHORT).show();
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
