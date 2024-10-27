package com.mentalhealthapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentalhealthapp.R;

public class MainActivity extends AppCompatActivity {

    String[] questions = {
            "Sadness.",
            "Pessimism.",
            "Post Failure.",
            "Loss Of Pleasure.",
            "Guilty Feelings.",
            "Punishment Feelings.",
            "Self-Dislike.",
            "Self-Criticalness.",
            "Suicidal Thoughts or Wishes.",
            "Crying.",
            "Agitation.",
            "Loss of Interest.",
            "Indecisiveness.",
            "Worthlessness.",
            "Loss of Energy.",
            "Changes in Sleep Pattern.",
            "Irritability.",
            "Changes in Appetite.",
            "Concentration Difficulty.",
            "Tiredness or Fatigue.",
            "Loss of Interest in Sex."
    };


    String[][] options = {
            {"I do not feel sad", "I feel sad", "I am sad all the time", "I can't stand it"},
            {"I am not discouraged", "I feel discouraged", "I have nothing to look forward to", "The future feels hopeless"},
            {"I don't feel like a failure", "I have failed more than average", "All I see is failure", "I am a complete failure"},
            {"I get satisfaction as usual", "I don't enjoy things anymore", "No satisfaction from anything", "I feel dissatisfied with everything"},
            {"I don't feel guilty", "I feel guilty often", "I feel guilty most of the time", "I feel guilty all the time"},
            {"I don't feel punished", "I may be punished", "I expect punishment", "I feel I am being punished"},
            {"I don't feel disappointed", "I am disappointed in myself", "I am disgusted with myself", "I hate myself"},
            {"I don't feel worse than others", "I criticize myself", "I blame myself constantly", "I blame myself for everything"},
            {"No thoughts of killing myself", "Thoughts but won't act", "I would like to kill myself", "I would kill myself if I could"},
            {"I don't cry more than usual", "I cry more than I used to", "I cry all the time", "I can't cry even if I want to"},
            {"I am not irritated", "I am slightly more irritated", "I am quite irritated", "I feel irritated all the time"},
            {"I haven't lost interest in people", "I am less interested in people", "I lost most of my interest", "I have no interest in people"},
            {"I make decisions well", "I delay decisions", "I struggle making decisions", "I can't make decisions at all"},
            {"I don't feel worse about my looks", "I worry about looking unattractive", "I feel I look permanently unattractive", "I believe I look ugly"},
            {"I work as well as before", "It takes effort to start work", "I have to push myself hard", "I can't do any work at all"},
            {"I sleep as usual", "I don't sleep as well as before", "I wake up early and can't sleep", "I wake up several hours early"},
            {"I don't get more tired", "I get tired easily", "I get tired doing anything", "I am too tired to do anything"},
            {"My appetite is normal", "My appetite isn't as good", "My appetite is worse", "I have no appetite"},
            {"I haven't lost much weight", "I lost more than 5 pounds", "I lost more than 10 pounds", "I lost more than 15 pounds"},
            {"I am not worried about health", "I am worried about aches and pains", "I am very worried about health issues", "I can't think of anything else but health"},
            {"No change in my interest in sex", "Less interested in sex", "Almost no interest in sex", "Lost interest in sex completely"}
    };

    int currentQuestion = 0;
    TextView questionText;
    RadioGroup optionsGroup;
    Button backButton, nextButton, submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        questionText = findViewById(R.id.question_text);
        optionsGroup = findViewById(R.id.options_group);
        backButton = findViewById(R.id.back_button);
        nextButton = findViewById(R.id.next_button);
        submitButton = findViewById(R.id.submit_button);


        displayQuestion();


        backButton.setOnClickListener(view -> {
            if (currentQuestion > 0) {
                currentQuestion--;
                displayQuestion();
            } else {
                Toast.makeText(this, "This is the first question.", Toast.LENGTH_SHORT).show();
            }
        });


        nextButton.setOnClickListener(view -> {
            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                displayQuestion();
            } else {
                Toast.makeText(this, "This is the last question.", Toast.LENGTH_SHORT).show();
            }
        });


        submitButton.setOnClickListener(view -> {
            int selectedOption = optionsGroup.indexOfChild(findViewById(optionsGroup.getCheckedRadioButtonId()));
            if (selectedOption != -1) {
                Toast.makeText(this, "Answer Recorded!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please select an option.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayQuestion() {
        questionText.setText(questions[currentQuestion]);
        optionsGroup.clearCheck(); // Clear previous selection

        for (int i = 0; i < options[currentQuestion].length; i++) {
            ((RadioButton) optionsGroup.getChildAt(i)).setText(options[currentQuestion][i]);
        }
    }
}
