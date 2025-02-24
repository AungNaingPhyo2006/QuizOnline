package com.anp.quizonline

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.anp.quizonline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var quizModelList : MutableList<QuizModel>
    private lateinit var adapter: QuizListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        quizModelList = mutableListOf()
        getDataFromFirebase()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun setupRecyclerView(){
        adapter = QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
    private fun getDataFromFirebase(){
        val listQuestionModel = mutableListOf<QuestionModel>()
        listQuestionModel.add(QuestionModel("What is Android?", mutableListOf("Language","Os", "Product","None" ) ,"Os"))
        listQuestionModel.add(QuestionModel("Who own Android?", mutableListOf("Apple","MicroSoft", "Google","Samsung" ) ,"Google"))
        listQuestionModel.add(QuestionModel("Which assistant android uses?", mutableListOf("Siri","Cortana", "Google Assistant","Alexa" ) ,"Google Assistant"))


        quizModelList.add(QuizModel("1","Programming","All the basic Programming","10",listQuestionModel))
//        quizModelList.add(QuizModel("2","Computer","All the basic computer","20"))
//        quizModelList.add(QuizModel("3","Geography","All the basic geography","15"))
        setupRecyclerView()
    }
}