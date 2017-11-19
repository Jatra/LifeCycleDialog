package uk.co.jatra.lifecycledialog;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import uk.co.jatra.lifecycledialog.AnswerModel.Answer;

import static uk.co.jatra.lifecycledialog.AnswerModel.Answer.NO_ANSWER;

public class LiveDialogFragment extends DialogFragment {

    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    public static final String POSITIVE = "positive";
    public static final String NEGATIVE = "negative";
    public static final String ANSWER_MODEL = "answerModel";
    private static final String KEY = "KEY";
    private Answer answer = NO_ANSWER;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setTitle(getArgument(TITLE));
        builder.setMessage(getArgument(MESSAGE));
        builder.setPositiveButton(getArgument(POSITIVE), (dialogInterface, i) -> answer = Answer.POSITIVE);
        builder.setNegativeButton(getArgument(NEGATIVE), (dialogInterface, i) -> answer = Answer.NEGATIVE);
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getAnswerModel().answer(answer);
    }

    private String getArgument(String key) {
        return (String) getArguments().get(key);
    }

    private AnswerModel getAnswerModel() {
        return (AnswerModel) getArguments().getSerializable(ANSWER_MODEL);
    }

    public static class LiveDialog {
        private final Bundle bundle;
        private final String key;
        private final AnswerModel answerModel;
        private final FragmentActivity activity;

        public LiveDialog(FragmentActivity activity, String key) {
            this.activity = activity;
            this.key = key;
            bundle = new Bundle();
            bundle.putString(KEY, key);
            answerModel = ViewModelProviders.of(activity).get(key, AnswerModel.class);
            bundle.putSerializable(ANSWER_MODEL, answerModel);
        }

        public LiveDialog title(@StringRes int title) {
            bundle.putString(TITLE, activity.getString(title));
            return this;
        }

        public LiveDialog message(@StringRes int message) {
            bundle.putString(MESSAGE, activity.getString(message));
            return this;
        }

        public LiveDialog positive(@StringRes int positive) {
            bundle.putString(POSITIVE, activity.getString(positive));
            return this;
        }

        public LiveDialog negative(@StringRes int negative) {
            bundle.putString(NEGATIVE, activity.getString(negative));
            return this;
        }

        public LiveDialog observe(Observer<Answer> observer) {
            answerModel.getAnswer().observe(activity, observer);
            return this;
        }

        public void display() {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            LiveDialogFragment dialogFragment = new LiveDialogFragment();
            dialogFragment.setArguments(bundle);
            dialogFragment.show(fragmentManager, key);
        }
    }
}