package uk.co.jatra.lifecycledialog;

import android.arch.lifecycle.ViewModel;


public class AnswerModel extends ViewModel {
    public enum Answer {
        NO_ANSWER,
        YES,
        NO
    }
    private SingleLiveEvent<Answer> dialogAnswer = new SingleLiveEvent<>();

    public void answer(Answer answer) {
        dialogAnswer.setValue(answer);
    }

    public SingleLiveEvent<Answer>  getDialogAnswer() {
        return dialogAnswer;
    }
}
