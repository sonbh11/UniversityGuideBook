package com.example.universityguidebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddQuestionActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseReference;

    RecyclerView recyclerView;
    QuestionAdapter questionAdapter = QuestionBoard.questionAdapter;

    EditText title_text, main_text;

    TextView cancel, add, schedule_text, scholarship_text, grade_text, course_text, university_text;
    ImageButton tag_schedule, tag_scholarship, tag_grade, tag_course, tag_university;
    // 태그 : 1. 일정, 2. 장학금, 3. 성적, 4. 진로, 5. 대학

    boolean[] tag_selected = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        init();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = title_text.getText().toString();
                String main = main_text.getText().toString();
                if(!title.isEmpty() && !main.isEmpty()){
                    Date currentDate = new Date();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = dateFormat.format(currentDate);


                    //Question question = new Question(title, main, MainActivity.name, dateString, tag_selected[0], tag_selected[1], tag_selected[2], tag_selected[3], tag_selected[4]);
                    Question question = new Question(title, main, MainActivity.name, dateString, tag_selected);

                    questionAdapter.addItem(question);

                    mDatabaseReference.child(dateString).setValue(question);

                    finish();
                }
            }
        });

        tag_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tag_selected[0]){
                    tag_schedule.setImageResource(R.drawable.selected_tag_shape);
                    schedule_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.white));
                    tag_selected[0] = true;
                }
                else{
                    tag_schedule.setImageResource(R.drawable.tag_shape);
                    schedule_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.black));
                    tag_selected[0] = false;
                }
            }
        });

        tag_scholarship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tag_selected[1]){
                    tag_scholarship.setImageResource(R.drawable.selected_tag_shape);
                    scholarship_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.white));
                    tag_selected[1] = true;
                }
                else{
                    tag_scholarship.setImageResource(R.drawable.tag_shape);
                    scholarship_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.black));
                    tag_selected[1] = false;
                }
            }
        });

        tag_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tag_selected[2]){
                    tag_grade.setImageResource(R.drawable.selected_tag_shape);
                    grade_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.white));
                    tag_selected[2] = true;
                }
                else{
                    tag_grade.setImageResource(R.drawable.tag_shape);
                    grade_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.black));
                    tag_selected[2] = false;
                }
            }
        });

        tag_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tag_selected[3]){
                    tag_course.setImageResource(R.drawable.selected_tag_shape);
                    course_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.white));
                    tag_selected[3] = true;
                }
                else{
                    tag_course.setImageResource(R.drawable.tag_shape);
                    course_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.black));
                    tag_selected[3] = false;
                }
            }
        });

        tag_university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tag_selected[4]){
                    tag_university.setImageResource(R.drawable.selected_tag_shape);
                    university_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.white));
                    tag_selected[4] = true;
                }
                else{
                    tag_university.setImageResource(R.drawable.tag_shape);
                    university_text.setTextColor(ContextCompat.getColor(AddQuestionActivity.this, R.color.black));
                    tag_selected[4] = false;
                }
            }
        });
    }

    private void init(){
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("GuideBook").child("Question").child(MainActivity.stdNum);

        cancel = findViewById(R.id.cancel);
        add = findViewById(R.id.add);

        tag_schedule = findViewById(R.id.tag_1);
        tag_scholarship = findViewById(R.id.tag_2);
        tag_grade = findViewById(R.id.tag_3);
        tag_course = findViewById(R.id.tag_4);
        tag_university = findViewById(R.id.tag_5);

        schedule_text = findViewById(R.id.tag_1_text);
        scholarship_text = findViewById(R.id.tag_2_text);
        grade_text = findViewById(R.id.tag_3_text);
        course_text = findViewById(R.id.tag_4_text);
        university_text = findViewById(R.id.tag_5_text);

        title_text = findViewById(R.id.title_text);
        main_text = findViewById(R.id.main_text);
    }
}