package uk.co.jatra.lifecycledialog

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answerModel = ViewModelProviders.of(this).get(AnswerModel::class.java)

        button.setOnClickListener { view -> LifeCycleDialogFragment().show(supportFragmentManager, "tag") }

        answerModel.dialogAnswer.observe(this, Observer<Answer> { it?.let { it1 -> this.setAnswer(it1) } })
    }

    private fun setAnswer(answer: Answer) {
        textview.text = answer.name
    }
}
