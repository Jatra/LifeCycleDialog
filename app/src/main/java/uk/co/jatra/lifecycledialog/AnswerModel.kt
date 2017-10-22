package uk.co.jatra.lifecycledialog

import android.arch.lifecycle.ViewModel


class AnswerModel : ViewModel() {
    val dialogAnswer = SingleLiveEvent<Answer>()

    fun answer(answer: Answer) {
        dialogAnswer.value = answer
    }
}
