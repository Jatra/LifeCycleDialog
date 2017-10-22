package uk.co.jatra.lifecycledialog;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        Button button = findViewById(R.id.button);

        AnswerModel answerModel = ViewModelProviders.of(this).get(AnswerModel.class);
        button.setOnClickListener(view -> new LifeCycleDialogFragment().show(getSupportFragmentManager(), "tag"));

        answerModel.getDialogAnswer().observe(this, this::setAnswer);
    }

    private void setAnswer(AnswerModel.Answer answer) {
        textView.setText(answer.name());
    }
}
