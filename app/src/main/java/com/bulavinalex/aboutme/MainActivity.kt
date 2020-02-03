package com.bulavinalex.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bulavinalex.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Название рецепта будет здесь")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName


        //findViewById<Button>(R.id.done_button).setOnClickListener {
        //    addNickname(it)
        binding.doneButton.setOnClickListener {
            addNoteToRecipe(it)
        }
        binding.nicknameText.setOnClickListener {
            changeRecipeNote(it)
        }
    }

    private fun addNoteToRecipe(view: View){

        binding.apply {
            //binding.nicknameText.text = binding.nicknameEdit.text
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            binding.nicknameEdit.visibility = View.GONE
            binding.doneButton.visibility = View.GONE
            binding.nicknameText.visibility = View.VISIBLE
        }


        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun changeRecipeNote(view: View){

        binding.apply {
            //binding.nicknameText.text = binding.nicknameEdit.text
            nicknameEdit.text = nicknameText.editableText
            //invalidateAll()myName?.
            binding.nicknameEdit.visibility = View.VISIBLE
            binding.doneButton.visibility = View.VISIBLE
            binding.nicknameText.visibility = View.GONE
        }
        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_IMPLICIT, 0)
    }
}
