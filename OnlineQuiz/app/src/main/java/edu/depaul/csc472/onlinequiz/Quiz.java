package edu.depaul.csc472.onlinequiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends Activity {

    String userId;
    int questionId = 0;
    ArrayList<Integer> visitedQuestions = new ArrayList<>();
    TextView txtQuestion;
    RadioButton rdBtnA, rdBtnB, rdBtnC, rdBtnD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        rdBtnA = (RadioButton) findViewById(R.id.rdBtnA);
        rdBtnB = (RadioButton) findViewById(R.id.rdBtnB);
        rdBtnC = (RadioButton) findViewById(R.id.rdBtnC);
        rdBtnD = (RadioButton) findViewById(R.id.rdBtnD);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getCharSequenceExtra("UserId").toString();
            Toast.makeText(getApplicationContext(), "UserId = " + userId, Toast.LENGTH_SHORT).show();

            Question question = GetQuestion();
            txtQuestion.setText(question.getQuestion().toString());
            rdBtnA.setText(question.getOp1().toString());
            rdBtnB.setText(question.getOp2().toString());
            rdBtnC.setText(question.getOp3().toString());
            rdBtnD.setText(question.getOp4().toString());
        }
    }

    public Question GetQuestion() {
        questionId = 2;
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        return dbHandler.GetQuestion(questionId);
    }
}