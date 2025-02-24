package com.anp.quizonline

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anp.quizonline.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        var questionModelList : List<QuestionModel> = listOf()
        var time : String = ""
    }
    lateinit var binding : ActivityQuizBinding

    var currentQuestionIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn0.setOnClickListener(this@QuizActivity)
            btn1.setOnClickListener(this@QuizActivity)
            btn2.setOnClickListener(this@QuizActivity)
            btn3.setOnClickListener(this@QuizActivity)
            nextBtn.setOnClickListener(this@QuizActivity)
        }
        loadQuestions()
        startTimer()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun startTimer(){
        val totalTimeInMillis = time.toInt() * 60 * 1000L
        object : CountDownTimer(totalTimeInMillis , 1000L){
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val minutes = seconds / 60
                val remainingSeconds = seconds % 60
                binding.timerIndicatorTextview.text = String.format("%02d:%02d",minutes,remainingSeconds)

            }

            override fun onFinish() {
            //Finished the Quiz
            }

        }.start()
    }
    private fun loadQuestions(){
        binding.apply {
            questionIndicatorTextview.text = "Questions ${currentQuestionIndex + 1}/ ${questionModelList.size}"
            questionProgressIndicator.progress = (currentQuestionIndex.toFloat() / questionModelList.size.toFloat() * 100).toInt()
            questionTextview.text = questionModelList[currentQuestionIndex].question
            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            btn3.text = questionModelList[currentQuestionIndex].options[3]
        }
    }

    override fun onClick(v: View?) {
        binding.apply {
            btn0.setBackgroundColor(getColor(R.color.gray))
            btn1.setBackgroundColor(getColor(R.color.gray))
            btn2.setBackgroundColor(getColor(R.color.gray))
            btn3.setBackgroundColor(getColor(R.color.gray))
        }
        val clickedBtn = v as Button
        if(clickedBtn.id == R.id.next_btn){
            currentQuestionIndex++
            loadQuestions()
        }else{
            //options buttons
            clickedBtn.setBackgroundColor(getColor(R.color.orange))
        }
    }
}