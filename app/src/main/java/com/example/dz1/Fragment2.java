package com.example.dz1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    final static String listKey = "LIST_KEY";
    final static String indexKey = "INDEX";
    ArrayList<String> strings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment2, container, false);
        Bundle bundle = getArguments();
        int index = bundle.getInt(indexKey) + 1;
        final TextView view = rootView.findViewById(R.id.fragment2_text);
        view.setText(String.valueOf(index));
        if ((index % 2) == 0) {
            view.setTextColor(Color.parseColor(getString(R.string.color_red)));
        } else {
            view.setTextColor(Color.parseColor(getString(R.string.color_blue)));
        }
        return rootView;

    }
}