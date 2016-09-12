package com.sekhar.android.catshala;

import java.util.List;

/**
 * Created by sekhar on 12-09-2016.
 */
public class QuestionSet {

    private Long id;
    private List<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}


