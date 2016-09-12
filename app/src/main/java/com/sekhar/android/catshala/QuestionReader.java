package com.sekhar.android.catshala;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sekhar on 12-09-2016.
 */
public class QuestionReader {
    public List<Question> getQuestions(Context context, Long questionSetId) {

        List<QuestionSet> questionSets = new ArrayList<>();

        Gson gson = new Gson();
        JsonReader reader = null;

        try {
            AssetManager assetManager = context.getAssets();
            reader = new JsonReader(new InputStreamReader(assetManager.open("question_set.json")));

            questionSets = gson.fromJson(reader, new TypeToken<List<QuestionSet>>() {
            }.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }

        QuestionSet questionSetResult = new QuestionSet();

        for (QuestionSet questionSet : questionSets) {
            if (questionSet.getId().equals(questionSetId)) {
                questionSetResult = questionSet;
            }
        }

        return questionSetResult.getQuestions();
    }
}
