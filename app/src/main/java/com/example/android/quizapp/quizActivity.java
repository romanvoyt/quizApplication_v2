package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class quizActivity extends AppCompatActivity {
    //true answers for 1,2,3,4 questions
    RadioButton singleAnswers[] = new RadioButton[3];
    CheckBox multipleAnswers[] = new CheckBox[2];
    CheckBox multipleWrongAnswers[] = new CheckBox[2];
    EditText freeAnswer;
    String answer5 = "";
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        singleAnswers[0] = (RadioButton) findViewById(R.id.rb_true_answer_0);
        singleAnswers[1] = (RadioButton) findViewById(R.id.rb_true_answer_1);
        singleAnswers[2] = (RadioButton) findViewById(R.id.rb_true_answer_2);

        multipleAnswers[0] = (CheckBox) findViewById(R.id.checkbox_true_answer_3);
        multipleAnswers[1] = (CheckBox) findViewById(R.id.checkbox_true_answer_4);

        multipleWrongAnswers[0] = (CheckBox) findViewById(R.id.wrong_answer_checkBox1);
        multipleWrongAnswers[1] = (CheckBox) findViewById(R.id.wrong_answer_checkBox2);

        freeAnswer = (EditText) findViewById(R.id.freeAnswer);
        Button submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer5 = freeAnswer.getText().toString();
                TextView scoreCounter = (TextView) findViewById(R.id.scores);
                countScores();
                scoreCounter.setText(String.valueOf(score));
                String text = "You scored " + String.valueOf(score) + " points";
                showScore(text);
                //reset scores
                score = 0;
            }
        });

    }

    //check true answers for RadioButtons
    private void checkRadioAnswers(RadioButton r) {
        if (r.isChecked()) {
            score += 1;
        } else {
            score += 0;
        }
    }

    //check true answers for CheckBoxes
    private void checkCheckBoxAnswers(CheckBox c, CheckBox c1) {
        if (c.isChecked() && checkWrongCheckBoxAnswers()==false) {
            score += 1;
        }
    }
    //check wrong answers in 4 question
    private boolean checkWrongCheckBoxAnswers(){
        if(multipleWrongAnswers[0].isChecked() || multipleWrongAnswers[1].isChecked()){
            return true;
        }else{
            return false;
        }
    }

    //count final scores
    private void countScores() {
        for (int i = 0; i < singleAnswers.length; i++) {
            checkRadioAnswers(singleAnswers[i]);
        }
        for (int i = 0; i < multipleAnswers.length; i++) {
            checkCheckBoxAnswers(multipleAnswers[i], multipleWrongAnswers[i]);
        }
        //check final question
        if (answer5.equals("Colourless")) {
            score += 2;
        } else {
            score += 0;
        }
    }

    //Show score
    private void showScore(String text) {
        Toast.makeText(quizActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
