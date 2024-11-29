package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mentalhealthapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> responses = new ArrayList<>();

    String[] questions = {
            "Do you feel sad or down most of the time?",
            "How do you feel about your future? Do you find yourself feeling discouraged?",
            "Do you feel like a failure or that you’ve failed more than others?",
            "Do you still enjoy things the way you used to, or has that changed?",
            "Do you feel guilty frequently or all the time?",
            "Do you feel like you deserve to be punished or that you are being punished?",
            "Are you disappointed in yourself or do you feel disgusted with yourself?",
            "How critical are you of yourself? Do you blame yourself for things going wrong?",
            "Have you had thoughts of harming or killing yourself?",
            "Do you find yourself crying more than usual, or feeling like you want to cry but can’t?",
            "Do you feel more irritated or annoyed than usual?",
            "Have you lost interest in being around other people?",
            "Do you find it harder to make decisions than before?",
            "How do you feel about your appearance? Do you worry that you look unattractive?",
            "Are you able to work or do tasks as well as before, or has it become harder?",
            "Are you having trouble sleeping or waking up earlier than usual?",
            "Do you get tired easily, or feel too tired to do anything?",
            "Has your appetite changed? Do you find yourself eating less or losing weight?",
            "Have you noticed any significant weight loss recently?",
            "Are you more worried about your physical health, or experiencing any constant physical problems?"
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
            {"I am not worried about health", "I am worried about aches and pains", "I am very worried about health issues", "I can't think of anything else but health"}
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
                int selectedOption = optionsGroup.indexOfChild(findViewById(optionsGroup.getCheckedRadioButtonId()));
                if (selectedOption != -1) {
                    // Toast.makeText(this, "Answer Recorded! " + selectedOption, Toast.LENGTH_SHORT).show();
                    responses.add(currentQuestion, selectedOption);
                }
                currentQuestion++;
                displayQuestion();
            } else {
                Toast.makeText(this, "This is the last question.", Toast.LENGTH_SHORT).show();
            }
        });


        submitButton.setOnClickListener(view -> {
            int selectedOption = optionsGroup.indexOfChild(findViewById(optionsGroup.getCheckedRadioButtonId()));
            if (selectedOption != -1) {
                // Toast.makeText(this, "Answer Recorded!", Toast.LENGTH_SHORT).show();
                responses.add(currentQuestion, selectedOption);
                StringBuilder empty = new StringBuilder();
                for(int i = 0; i < responses.size(); i++) {
                    empty.append(String.valueOf(responses.get(i))).append(", ");
                }
                Log.d("Bata de bhai", empty.toString());
                Intent intent = new Intent(MainActivity.this, GetResponseActivity.class);
                intent.putIntegerArrayListExtra("responses", responses);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Please select an option.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayQuestion() {
        questionText.setText(questions[currentQuestion]);
        optionsGroup.clearCheck(); // Clear previous selection

        if(currentQuestion == 19) {
            submitButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.GONE);
        }
        else {
            submitButton.setVisibility(View.GONE);
            nextButton.setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < options[currentQuestion].length; i++) {
            ((RadioButton) optionsGroup.getChildAt(i)).setText(options[currentQuestion][i]);
        }
    }
}
