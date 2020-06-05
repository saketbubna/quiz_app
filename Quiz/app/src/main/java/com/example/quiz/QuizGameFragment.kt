package com.example.quiz

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.quiz.databinding.FragmentQuizGameBinding
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_quiz_game.*
import android.content.Context.MODE_PRIVATE

/**
 * A simple [Fragment] subclass.
 */
class QuizGameFragment : Fragment() {
    lateinit var binding:FragmentQuizGameBinding
    lateinit var currentQuestion:Question
    private var questionIndex = 0
    lateinit var sharedPreferences:SharedPreferences
    private val maxNumberOfQuestion = 7
    var WrongAnswerList:ArrayList<String> = ArrayList()
     var score=0
    var questions:ArrayList<Question> = arrayListOf(
        Question("Which is the Independence day of Bangladesh?",
            arrayListOf("26 March","21 Feb","14th April","16 December") ),
        Question("Who is the first man landed on moon?",
            arrayListOf("Neil Armstrong","Edwin Aldrin", "Michael Collins", "Yuri Gregory")),
        Question("Socrates is best known for - ",
            arrayListOf("Philosophy","Mathmetics","Physiology","Astrology")),
        Question("How many states does USA have? ",
            arrayListOf("50","45","55","49")),
        Question("Which is not an Europian Country? ",
            arrayListOf("Combodia","Estonia","Lithunia","Moldova")),
        Question("Who is the first President of USA? ",
            arrayListOf("George Washington","William Henry Harrison","Abraham Lincoln","Franklin D. Roosevelt")),
        Question("Which one is the largest ocean? ",
            arrayListOf("Pacific","Atlantic","Mediterian","Arctic")),
        Question("What country has a town named Marathon? ",
            arrayListOf("USA","GREECE","ITALY","FRANCE")),
        Question("What well-known mountain pass connects Pakistan and Afghanistan? ",
            arrayListOf("Khyber Pass","Malakand Pass","Ahmad Pass","Shandar Pass")),
        Question("What country was formerly known as Ceylon?",
            arrayListOf("Sri Lanka","Sweden","Vietnam","Switzerland"))
    )

    lateinit var answers:ArrayList<String>
    lateinit var selectedAnswer:String

    private fun setQuestion(){
        currentQuestion = questions[questionIndex]
        answers = ArrayList(currentQuestion.theAnswer)
        answers.shuffle()
        Log.d("ANSWERGROUP",answers[0]+" "+answers[0]+" "+answers[1]+" "+answers[2]+" "+answers[3]+" ")
        Log.d("ANSWERREAL",currentQuestion.theAnswer[0])

    }
    private fun checkAnswer(answer:String){
        if(answer.equals(currentQuestion.theAnswer[0])){
            score+=1
        }
        else{
            WrongAnswerList.add(currentQuestion.theQuestion)
        }
        questionIndex++
        if (questionIndex<maxNumberOfQuestion){
            setQuestion()
            binding.invalidateAll()
        }
        else{
            getScore()
        }
    }
    private fun getScore(){
         var highestScore=sharedPreferences.getInt("HIGHESTSCORE",0)
        if (score>highestScore){
            val editor =sharedPreferences.edit()
            editor.putInt("HIGHESTSCORE",score)
            editor.apply()
        }
        if(score>=3){
            //Toast.makeText(activity,"You won the game",Toast.LENGTH_LONG).show()
            //Navigation.findNavController(view!!).navigate(R.id.action_quizGameFragment_to_quizWonFragment2)
            val action =QuizGameFragmentDirections.actionQuizGameFragmentToQuizWonFragment2(score,WrongAnswerList.toTypedArray())
            view!!.findNavController().navigate(action)



        }
        else{

            //Toast.makeText(activity,"You Lost the game",Toast.LENGTH_LONG).show()
           // Navigation.findNavController(view!!).navigate(R.id.action_quizGameFragment_to_quizLostFragment2)
            val action =QuizGameFragmentDirections.actionQuizGameFragmentToQuizLostFragment2(score,WrongAnswerList.toTypedArray())
            view!!.findNavController().navigate(action)


        }
    }

    private fun randomQuestion(){
        questions.shuffle()
        setQuestion()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_quiz_game,container,false)

        randomQuestion()
        binding.quiz=this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        option1.setOnClickListener {
            checkAnswer(option1.text.toString())
        }
        option2.setOnClickListener {
            checkAnswer(option2.text.toString())
        }
        option3.setOnClickListener {
            checkAnswer(option3.text.toString())
        }
        option4.setOnClickListener {
            checkAnswer(option4.text.toString())
        }
        sharedPreferences = activity!!.getSharedPreferences("RAMANUJ", MODE_PRIVATE)

    }

}



