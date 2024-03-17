package com.example.universityguidebook;

import java.util.Date;

public class Question {

    private String title;
    private String content;
    private String author;
    private String createdAt;
    boolean tag_schedule, tag_scholarship, tag_grade, tag_course, tag_university;

    public Question(){}

    public Question(String title, String content, String author, String createdAt, boolean[] tag_selected){
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;

        tag_schedule = tag_selected[0];
        tag_scholarship = tag_selected[1];
        tag_grade = tag_selected[2];
        tag_course = tag_selected[3];
        tag_university = tag_selected[4];
    }

    public Question(String title, String content, String author, String createdAt, boolean isScheduleTag, boolean isScholarshipTag, boolean isGradeTag,
                    boolean isCourseTag, boolean isUniversityTag){
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;

        tag_schedule = isScheduleTag;
        tag_scholarship = isScholarshipTag;
        tag_grade = isGradeTag;
        tag_course = isCourseTag;
        tag_university = isUniversityTag;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isTag_schedule() {
        return tag_schedule;
    }

    public boolean isTag_scholarship() {
        return tag_scholarship;
    }

    public boolean isTag_grade() {
        return tag_grade;
    }

    public boolean isTag_course() {
        return tag_course;
    }

    public boolean isTag_university() {
        return tag_university;
    }
}
