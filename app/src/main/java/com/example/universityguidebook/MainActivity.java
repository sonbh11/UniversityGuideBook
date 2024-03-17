package com.example.universityguidebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private AFragment fragmentA;
    private QuestionBoard QBoard;
    private FragmentTransaction transaction;

    public static String stdNum;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        fragmentA = new AFragment();
        QBoard = new QuestionBoard();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentA).commitAllowingStateLoss();
    }

    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        int id = view.getId();
        if (id == R.id.btn_fragmentA) {
            transaction.replace(R.id.frameLayout, fragmentA).commitAllowingStateLoss();
        } else if (id == R.id.btn_fragmentB) {
            transaction.replace(R.id.frameLayout, QBoard).commitAllowingStateLoss();
        }
    }
}