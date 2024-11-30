package com.example.quizapplication

object Constants {

    const val username:String="User_name"
    const val totalQuestion:String="TotalQuestion"
    const val correctAnswer:String="CorrectAnswer"
    fun getQuestions():ArrayList<Question>
    {
        val questionList = ArrayList<Question>()
        val que1=Question(
            1,"What country does this flag belong to", R.drawable.argentina,
            "Argentina","France",
            "Scotland", "Brazil",
            1
        )
        questionList.add(que1)
        val que2=Question(
            2,"What country does this flag belong to",R.drawable.uae,
            "Qatar","Saudi Arabia",
            "Brazil", "UAE",
            4
        )
        questionList.add(que2)
        val que3=Question(
            3,"What country does this flag belong to",R.drawable.germany,
            "Africa","France",
            "Germany", "Belgium",
            3
        )
        questionList.add(que3)
        val que4=Question(
            4,"What country does this flag belong to",R.drawable.uk,
            "United Kingdom","Armenia",
            "India", "South Korea",
            1
        )
        questionList.add(que4)
        val que5=Question(
            5,"What country does this flag belong to",R.drawable.ireland,
            "Italy","Ireland",
            "India", "Turkey",
            2
        )
        questionList.add(que5)
        val que6=Question(
            6,"What country does this flag belong to",R.drawable.czechrepublic,
            "India","Pakistan",
            "Czech Republic", "Italy",
            3

        )
        questionList.add(que6)
        val que7=Question(
            7,"What country does this flag belong to",R.drawable.spain,
            "North Korea","Canada",
            "Rome", "Spain",
            4
        )
        questionList.add(que7)
        val que8=Question(
            8,"What country does this flag belong to",R.drawable.india,
            "India","Ireland",
            "Germany", "UAE",
            1
        )
        questionList.add(que8)
        val que9=Question(
            9,"What country does this flag belong to",R.drawable.scotland,
            "Argentina","France",
            "Scotland", "Brazil",
            3
        )
        questionList.add(que9)
        val que10=Question(
            10,"What country does this flag belong to",R.drawable.france,
            "Argentina","France",
            "Scotland", "Brazil",
            2
        )
        questionList.add(que10)
        return questionList
    }
}