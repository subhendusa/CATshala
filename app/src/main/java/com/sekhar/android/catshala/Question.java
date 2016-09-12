package com.sekhar.android.catshala;

import java.util.List;

/**
 * Created by sekhar on 12-09-2016.
 */
public class Question {
    Long id;
    Long questionNo;
    String text;
    List<QuestionOption> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Long questionNo) {
        this.questionNo = questionNo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOption> options) {
        this.options = options;
    }

}

class QuestionOption {
    Long id;
    String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
