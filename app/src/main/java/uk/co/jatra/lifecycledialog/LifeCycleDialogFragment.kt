package uk.co.jatra.lifecycledialog

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import uk.co.jatra.lifecycledialog.Answer.*

class LifeCycleDialogFragment : DialogFragment() {

    private var answerModel: AnswerModel? = null
    private var answer: Answer = NO_ANSWER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        answerModel = ViewModelProviders.of(activity).get(AnswerModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = android.app.AlertDialog.Builder(context)
        builder.setMessage("Have you rotated recently?")
        builder.setPositiveButton("Yes!") { dialogInterface, i -> answer = YES }
        builder.setNegativeButton("No") { dialogInterface, i -> answer = NO }
        return builder.create()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        answerModel!!.answer(answer)
    }
}