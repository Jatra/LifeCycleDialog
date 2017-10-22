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

        button.setOnClickListener(view -> new LiveCycleDialog().show(getSupportFragmentManager(), "tag"));

        DialogViewModel dialogViewModel = ViewModelProviders.of(this).get(DialogViewModel.class);
        dialogViewModel.getDialogAnswer().observe(this, this::setAnswer);
    }

    private void setAnswer(DialogViewModel.Answer answer) {
        textView.setText(answer.name());
    }
}
