package com.sekhar.android.catshala;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sekhar on 12-09-2016.
 */
public class QuestionViewGenerator {

    private Activity activity;

    public QuestionViewGenerator(Activity activity) {
        this.activity = activity;
    }

    public void createView() {
        createQuestionsView();
        createClockView();

    }

    private void createQuestionsView() {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.questions_list_id);
        List<Question> questions = new QuestionReader().getQuestions(activity, 1L);

        QuestionAdapter adapter = new QuestionAdapter(questions);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void createClockView() {
    }
}

class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

    private List<Question> moviesList;

    public QuestionAdapter(List<Question> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Question question = moviesList.get(position);

        holder.tvQuestion.setText(question.getQuestionNo() + ". " + question.getText());
        holder.cbOption1.setText(question.getOptions().get(0).getText());
        holder.cbOption2.setText(question.getOptions().get(1).getText());
        holder.cbOption3.setText(question.getOptions().get(2).getText());
        holder.cbOption4.setText(question.getOptions().get(3).getText());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvQuestion;
        public CheckBox cbOption1, cbOption2, cbOption3, cbOption4;

        public MyViewHolder(View view) {
            super(view);

            tvQuestion = (TextView) view.findViewById(R.id.question_text_id);
            cbOption1 = (CheckBox) view.findViewById(R.id.option1);
            cbOption2 = (CheckBox) view.findViewById(R.id.option2);
            cbOption3 = (CheckBox) view.findViewById(R.id.option3);
            cbOption4 = (CheckBox) view.findViewById(R.id.option4);
        }
    }
}