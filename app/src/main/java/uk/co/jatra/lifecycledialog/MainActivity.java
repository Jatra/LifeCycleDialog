package uk.co.jatra.lifecycledialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import uk.co.jatra.lifecycledialog.AnswerModel.Answer;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT1 = "TEXT1";
    private static final String KEY = "KEY1";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(TEXT1));
        }

        final LiveDialogFragment.Creator creator = new LiveDialogFragment.Creator(this, KEY)
                .message("Have you rotated recently?")
                .title("Lifecycle")
                .negative("No")
                .positive("Yes")
                .observe(this::handleAnswer);

        button.setOnClickListener(view -> creator.display());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT1, textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void handleAnswer(Answer answer) {
        textView.setText(answer.name());
    }

}
