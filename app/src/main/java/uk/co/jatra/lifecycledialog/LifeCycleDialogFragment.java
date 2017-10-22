package uk.co.jatra.lifecycledialog;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import static uk.co.jatra.lifecycledialog.DialogViewModel.Answer.NO;
import static uk.co.jatra.lifecycledialog.DialogViewModel.Answer.NO_ANSWER;
import static uk.co.jatra.lifecycledialog.DialogViewModel.Answer.YES;

public class LifeCycleDialogFragment extends DialogFragment {

    private DialogViewModel dialogViewModel;
    private DialogViewModel.Answer answer = NO_ANSWER;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogViewModel = ViewModelProviders.of(getActivity()).get(DialogViewModel.class);
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
        dialogViewModel.answer(answer);
    }
}