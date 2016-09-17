package com.sekhar.android.catshala;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
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
        ListView listView = (ListView) activity.findViewById(R.id.questions_list_id);
        List<Question> questions = new QuestionReader().getQuestions(activity, 1L);

        QuestionAdapter adapter = new QuestionAdapter(activity, R.layout.question,
                questions);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void createClockView() {
    }
}

class QuestionAdapter extends ArrayAdapter<Question> {
    Context context;

    public QuestionAdapter(Context context, List<Question> questions) {
        super(context, 0, questions);
        this.context = context;
    }

    public QuestionAdapter(Context context, int resource, List<Question> questions) {
        super(context, resource, questions);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Question question = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.question, parent, false);
        }

        // Lookup view for data population
        TextView tvQuestion = (TextView) convertView.findViewById(R.id.question_text_id);
        CheckBox cbOption1 = (CheckBox) convertView.findViewById(R.id.option1);
        CheckBox cbOption2 = (CheckBox) convertView.findViewById(R.id.option2);
        CheckBox cbOption3 = (CheckBox) convertView.findViewById(R.id.option3);
        CheckBox cbOption4 = (CheckBox) convertView.findViewById(R.id.option4);

        // Populate the data into the template view using the data object
        tvQuestion.setText(question.getQuestionNo() + ". " + question.getText());
        cbOption1.setText(question.getOptions().get(0).getText());
        cbOption2.setText(question.getOptions().get(1).getText());
        cbOption3.setText(question.getOptions().get(2).getText());
        cbOption4.setText(question.getOptions().get(3).getText());

        // Return the completed view to render on screen
        return convertView;
    }
}

