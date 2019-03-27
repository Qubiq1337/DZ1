package com.example.dz1;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    Button mButton;
    ArrayList<String> mStrings;
    public final static String LIST_KEY = "LIST_KEY";
    public final static String INDEX_KEY = "INDEX_KEY";
    public final static String COLOR_KEY = "COLOR_KEY";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View rootView =
                inflater.inflate(R.layout.fragment1, container, false);

        final GridLayoutManager layout;
        final RecyclerView recyclerView = rootView.findViewById(R.id.my_list);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layout = new GridLayoutManager(getActivity(), 4);
        } else {
            layout = new GridLayoutManager(getActivity(), 3);
        }
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(new MyAdapter(mStrings));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mButton = rootView.findViewById(R.id.add_number_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addElementToList(mStrings);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStrings = new ArrayList<>();
        if (savedInstanceState == null) {
            fillList(mStrings);
        } else {
            mStrings = savedInstanceState.getStringArrayList(LIST_KEY);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<Fragment1.MyViewHolder> {

        private List<String> mData;


        MyAdapter(List<String> data) {
            mData = data;
        }

        @NonNull
        @Override
        public Fragment1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View v = inflater.inflate(R.layout.list_element, viewGroup, false);
            return new Fragment1.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull Fragment1.MyViewHolder myViewHolder, final int i) {
            final String str = mData.get(i);
            final char mCharColor;
            if ((Integer.valueOf(str) % 2) == 0) {
                myViewHolder.mTextView.setTextColor(Color.parseColor(getString(R.string.color_red)));
                mCharColor = 'r';
            } else {
                myViewHolder.mTextView.setTextColor(Color.parseColor(getString(R.string.color_blue)));
                mCharColor = 'b';
            }

            myViewHolder.mTextView.setText(str);
            myViewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment2 fragment2 = new Fragment2();
                    Bundle bundle = new Bundle();
                    bundle.putInt(INDEX_KEY, Integer.valueOf(str));
                    bundle.putStringArrayList(LIST_KEY, mStrings);
                    bundle.putChar(COLOR_KEY, mCharColor);
                    fragment2.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container, fragment2);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    void addElementToList(List<String> toFill) {
        toFill.add(toFill.size() + 1 + "");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(LIST_KEY, mStrings);
    }

    void fillList(List<String> toFill) {
        for (int i = 1; i < 101; i++) {
            toFill.add(i + "");
        }
    }
}
