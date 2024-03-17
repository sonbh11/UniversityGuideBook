package com.example.universityguidebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionBoard extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseReference;

    private RecyclerView recyclerView;
    protected static QuestionAdapter questionAdapter;
    private List<Question> question = new ArrayList<>();

    ImageButton search, add;

    EditText search_target;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_questionboard, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("GuideBook");

        recyclerView = view.findViewById(R.id.recycler_view_question_board);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        questionAdapter = new QuestionAdapter(question);
        recyclerView.setAdapter(questionAdapter);

        search = view.findViewById(R.id.search_button);
        add = view.findViewById(R.id.add_button);

        init();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddQuestionActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void init(){

        mDatabaseReference.child("Question").child(MainActivity.stdNum).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                question.clear();

                for(DataSnapshot DataSnapshot : snapshot.getChildren()){
                    Question post = DataSnapshot.getValue(Question.class);

                    if(post != null) question.add(post);
                }

                questionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("QuestionBoard", "Failed to read value.");
            }
        });

    }

}