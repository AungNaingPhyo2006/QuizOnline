package com.anp.quizonline

data class QuizModel(
    val id : String,
    val title : String,
    val subTitle : String,
    val time : String,
    val questionList : List<QuestionModel>
){
    constructor() : this(id = "", title = "", subTitle ="", time="", emptyList())
}

data class QuestionModel(
    val question : String,
    val options : List<String>,
    val correct : String,
){
    constructor() : this(question = "", emptyList(), correct ="")
}
