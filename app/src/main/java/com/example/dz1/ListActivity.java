package com.example.dz1;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity implements Fragment1.onClickTransition {

    public final static String INDEX_KEY = "INDEX_KEY";
    public final static String COLOR_KEY = "COLOR_KEY";

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (savedInstanceState == null) {
            Fragment1 fragment1 = new Fragment1();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment1);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void changeFragment(int number, char color) {
        Fragment2 fragment2 = new Fragment2();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX_KEY, number);
        bundle.putChar(COLOR_KEY, color);
        fragment2.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}