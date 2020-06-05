package com.example.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.quiz.databinding.FragmentCorrectAnswerBinding
import kotlinx.android.synthetic.main.fragment_wrong_answer.*

/**
 * A simple [Fragment] subclass.
 */
class QuizWonFragment : Fragment() {
    lateinit var binding:FragmentCorrectAnswerBinding
    var earnedScore ="0"
    var wronAnswerText="    "
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_correct_answer,container,false)
        binding.btnPlayNext.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_quizWonFragment2_to_titleFragment)
        }
        binding.quizwon=this
        return binding.root
        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args=QuizWonFragmentArgs.fromBundle(it)
            earnedScore= args.score.toString()
            if (args.wrongAnswer .isNotEmpty()){
                yourwronganswer.visibility=View.VISIBLE
                txt_wrong_answer.visibility=View.VISIBLE
                for (ans in args.wrongAnswer){
                    wronAnswerText = wronAnswerText+ans+"\n"
                }
            }
        }
    }
    }
/*class QuizWonFragment : Fragment() {
    lateinit var binding:FragmentCorrectAnswerBinding
    var earnedScore: String = ""
    var wrongAnswerText: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_correct_answer, container, false)
        binding.btnPlayNext.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_quizWonFragment2_to_titleFragment)
        }
        binding.quizwon = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val args = QuizWonFragmentArgs.fromBundle(it)
            earnedScore = args.score.toString()
            if (args.wrongAnswer.isNotEmpty()) {
                yourwronganswer.visibility = View.VISIBLE
                txt_wrong_answer.visibility = View.VISIBLE

                for (ans in args.wrongAnswer) {
                    wrongAnswerText = wrongAnswerText + ans + "\n"
                }
            }
        }
    }
}*/






