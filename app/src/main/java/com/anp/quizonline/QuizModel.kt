package com.anp.quizonline

data class QuizModel(
    val id : String,
    val title : String,
    val subTitle : String,
    val time : String
){
    constructor() : this(id = "", title = "", subTitle ="", time="")
}
