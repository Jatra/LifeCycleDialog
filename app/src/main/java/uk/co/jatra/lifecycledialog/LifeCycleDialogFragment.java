package uk.co.jatra.lifecycledialog;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import static uk.co.jatra.lifecycledialog.AnswerModel.Answer.NO;
import static uk.co.jatra.lifecycledialog.AnswerModel.Answer.NO_ANSWER;
import static uk.co.jatra.lifecycledialog.AnswerModel.Answer.YES;

public class LifeCycleDialogFragment extends DialogFragment {

    private AnswerModel answerModel;
    private AnswerModel.Answer answer = NO_ANSWER;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        answerModel = ViewModelProviders.of(getActivity()).get(AnswerModel.class);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setMessage("Have you rotated recently?");
        builder.setPositiveButton("Yes!", (dialogInterface, i) -> {
            answer = YES;
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
            answer = NO;
        });
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        answerModel.answer(answer);
    }
}