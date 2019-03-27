package com.example.dz1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.dz1.Fragment1.COLOR_KEY;
import static com.example.dz1.Fragment1.INDEX_KEY;


public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.fragment2, container, false);
        Bundle bundle = getArguments();

        int mIndex = 0;
        char mColor = 'n';
        if (bundle != null) {
            mIndex = bundle.getInt(INDEX_KEY);
            mColor = bundle.getChar(COLOR_KEY);
        }
        final TextView view = rootView.findViewById(R.id.fragment2_text);
        view.setText(String.valueOf(mIndex));
        if (mColor == 'r') {
            view.setTextColor(Color.parseColor(getString(R.string.color_red)));
        } else if (mColor == 'b') {
            view.setTextColor(Color.parseColor(getString(R.string.color_blue)));
        }
        return rootView;
    }
}