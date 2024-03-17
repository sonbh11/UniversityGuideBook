package com.example.universityguidebook;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<Question> question;

    public QuestionAdapter(List<Question> posts) {
        this.question = posts;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question post = question.get(position);
        holder.bind(post);
    }

    public void addItem(Question item){
        question.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return question.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView title, answer_count, tags, date;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            answer_count = itemView.findViewById(R.id.answer_count);
            tags = itemView.findViewById(R.id.tags);
            date = itemView.findViewById(R.id.date);
        }

        public void bind(Question post) {
            String tag_text = "";
            title.setText(post.getTitle());
            answer_count.setText("0");

            if(post.isTag_schedule()) tag_text = tag_text.concat("#학사일정 ");
            if(post.isTag_scholarship()) tag_text = tag_text.concat("#장학금 ");
            if(post.isTag_grade()) tag_text = tag_text.concat("#성적 ");
            if(post.isTag_course()) tag_text = tag_text.concat("#진로 ");
            if(post.isTag_university()) tag_text = tag_text.concat("#대학 ");

            tags.setText(tag_text);
            date.setText(post.getCreatedAt());
        }
    }
}