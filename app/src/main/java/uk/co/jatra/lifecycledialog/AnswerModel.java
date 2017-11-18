package uk.co.jatra.lifecycledialog;

import android.arch.lifecycle.ViewModel;

import java.io.Serializable;


public class AnswerModel extends ViewModel implements Serializable {
    private SingleLiveEvent<Answer> answer = new SingleLiveEvent<>();

    public void answer(Answer answer) {
        this.answer.setValue(answer);
    }

    public SingleLiveEvent<Answer> getAnswer() {
        return answer;
    }

    public enum Answer {
        NO_ANSWER,
        POSITIVE,
        NEGATIVE
    }
}
