package com.e.dropmistake.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.dropmistake.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecommendFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecommendFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecommendFragment() {
        // Required empty public constructor
    }

    public static RecommendFragment newInstance(String[] url,String[] point) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_PARAM1,url);
        args.putStringArray(ARG_PARAM2,point);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_recommend,container,false);
        //题目
        WebView ex1=view.findViewById(R.id.ex1);
        WebView ex2=view.findViewById(R.id.ex2);
        WebView ex3=view.findViewById(R.id.ex3);
        //答案
        WebView answer1=view.findViewById(R.id.answer1);
        WebView answer2=view.findViewById(R.id.answer2);
        WebView answer3=view.findViewById(R.id.answer3);
        //知识点
        TextView point1=view.findViewById(R.id.point1);
        TextView point2=view.findViewById(R.id.point2);
        TextView point3=view.findViewById(R.id.point3);



        Bundle bundle=getArguments();
        String[] url=new String[6];
        //String[] answer=new String[3];
        String[] point=new String[3];
        if (bundle!=null){
            url=bundle.getStringArray(ARG_PARAM1);
            point=bundle.getStringArray(ARG_PARAM2);
        }
        Toast.makeText(getActivity(),url[0],Toast.LENGTH_LONG).show();

        WebSettings webSettings1=ex1.getSettings();
        webSettings1.setUseWideViewPort(true);
        webSettings1.setLoadWithOverviewMode(true);

        WebSettings webSettings2=ex2.getSettings();
        webSettings2.setUseWideViewPort(true);
        webSettings2.setLoadWithOverviewMode(true);

        WebSettings webSettings3=ex3.getSettings();
        webSettings3.setUseWideViewPort(true);
        webSettings3.setLoadWithOverviewMode(true);

        WebSettings webSettings4=answer1.getSettings();
        webSettings4.setUseWideViewPort(true);
        webSettings4.setLoadWithOverviewMode(true);

        WebSettings webSettings5=answer2.getSettings();
        webSettings5.setUseWideViewPort(true);
        webSettings5.setLoadWithOverviewMode(true);

        WebSettings webSettings6=answer3.getSettings();
        webSettings6.setUseWideViewPort(true);
        webSettings6.setLoadWithOverviewMode(true);
        point1.setText(point[0]);
        point2.setText(point[1]);
        point3.setText(point[2]);

        ex1.loadUrl(url[0]);
        ex2.loadUrl(url[2]);
        ex3.loadUrl(url[4]);

        answer1.loadUrl(url[1]);
        answer2.loadUrl(url[3]);
        answer3.loadUrl(url[5]);

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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
