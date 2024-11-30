package com.example.quizapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), OnClickListener {
    private var mUsername:String?=null
    private var mSelectedOptionNum:Int=0
    private var mCurrentPosition:Int=1
    private var mCorrectAnswers:Int=10
    private var mQuestionsList:ArrayList<Question>?=null
    private var progressBar:ProgressBar? = null
    private var tvProgressBar:TextView?=null
    private var image:ImageView?=null
    private var questions:TextView?=null
    private var option1:TextView?=null
    private var option2:TextView?=null
    private var option3:TextView?=null
    private var option4:TextView?=null
    private var submitBtn: Button?=null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        mUsername=intent.getStringExtra(Constants.username)
        progressBar=findViewById(R.id.progressbar)
        tvProgressBar=findViewById(R.id.tv_progress)
        questions=findViewById(R.id.tv_question)
        image=findViewById(R.id.imageView)

        option1=findViewById(R.id.optionOne)
        option2=findViewById(R.id.optionTwo)
        option3=findViewById(R.id.optionThree)
        option4=findViewById(R.id.optionFour)
        submitBtn=findViewById(R.id.btn_submit)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        option1?.setOnClickListener(this)
        option2?.setOnClickListener(this)
        option3?.setOnClickListener(this)
        option4?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)
    }

    private fun setQuestion()
    {

            val question: Question = mQuestionsList!![mCurrentPosition-1]
            defaultSelectedBg()

            if (mCurrentPosition == mQuestionsList!!.size - 1) {
                submitBtn?.text = "Finish"
            } else {
                submitBtn?.text = "Submit"
            }

            progressBar?.progress = mCurrentPosition
            tvProgressBar?.text = "$mCurrentPosition" + "/" + progressBar?.max
            questions?.text = question.Question
            image?.setImageResource(question.Image)
            option1?.text = question.Option1
            option2?.text = question.Option2
            option3?.text = question.Option3
            option4?.text = question.Option4

    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            R.id.optionOne-> {
                option1?.let { selectedOptionBg(it, 1) }
            }
            R.id.optionTwo-> {
                option2?.let  { selectedOptionBg(it, 2) }
            }
            R.id.optionThree-> {
                option3?.let { selectedOptionBg(it, 3) }
            }
            R.id.optionFour-> {
                option4?.let  { selectedOptionBg(it, 4) }
            }
            R.id.btn_submit->{
                if(mSelectedOptionNum==0)
                {
                    mCurrentPosition++
                    when
                    {
                        mCurrentPosition<=mQuestionsList!!.size-1 ->
                        {
                            setQuestion()
                        }
                        else->{
                            val intent=Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.username,mUsername)
                            intent.putExtra(Constants.correctAnswer,mCorrectAnswers)
                            intent.putExtra(Constants.totalQuestion,mQuestionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else
                {
                    val question=mQuestionsList?.get(mCurrentPosition-1)
                    if(question?.correctAnswer!=mSelectedOptionNum)
                    {
                        answerCheck(mSelectedOptionNum,R.drawable.wron_answer)
                        --mCorrectAnswers
                    }


                    answerCheck(question!!.correctAnswer,R.drawable.right_answer)

                    if(mCurrentPosition==mQuestionsList!!.size-1)
                    {
                        submitBtn?.text="Finish"
                    }
                    else
                    {
                        submitBtn?.text="Next Question"
                    }

                    mSelectedOptionNum=0
                }
            }
        }
    }

    private fun defaultSelectedBg()
    {

        val options=ArrayList<TextView>()

        option1?.let { options.add(0,it)}
        option2?.let { options.add(1,it)}
        option3?.let { options.add(2,it)}
        option4?.let { options.add(3,it)}

        for(option in options){
            option.setTextColor(Color.parseColor("#BFBFBF"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.default_option_bg)
        }

    }

    private fun selectedOptionBg(tv:TextView,SelectedOptionNum:Int)
    {
        defaultSelectedBg()
        mSelectedOptionNum=SelectedOptionNum
        tv.setTextColor(Color.parseColor("#777777"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.selected_option_bg)
    }



    private fun answerCheck(selectedOption:Int, drawableView:Int){
            when(selectedOption)
            {
                1->{
                    option1?.background=ContextCompat.getDrawable(this,drawableView)
                }
                2->{
                    option2?.background=ContextCompat.getDrawable(this,drawableView)
                }
                3->{
                    option3?.background=ContextCompat.getDrawable(this,drawableView)
                }
                4-> {
                    option4?.background = ContextCompat.getDrawable(this, drawableView)
                }
            }
    }

}