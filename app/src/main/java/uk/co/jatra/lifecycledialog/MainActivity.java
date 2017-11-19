package uk.co.jatra.lifecycledialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import uk.co.jatra.lifecycledialog.LiveDialogFragment.LiveDialog;

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

        final LiveDialog liveDialog = new LiveDialog(this, KEY)
                .title(R.string.dialog_title)
                .message(R.string.dialog_message)
                .negative(R.string.dialog_negative_button_label)
                .positive(R.string.dialog_positive_button_label)
                .observe(answer -> textView.setText(answer.name()));

        button.setOnClickListener(view -> liveDialog.display());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT1, textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

}
