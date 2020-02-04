package com.bulavinalex.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.bulavinalex.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Название рецепта будет здесь")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        //findViewById<Button>(R.id.done_button).setOnClickListener {
        //    addNickname(it)
        binding.nicknameEdit.setOnKeyListener({_, keyCode, _ ->
            //Добавил обработчик нажатия экранной клавиатуры
                        if (keyCode == KeyEvent.KEYCODE_ENTER) {
                            //при нажатии экранного  ENTER вызывается addNoteToRecipe
                            addNoteToRecipe(nickname_edit)
                            //Действие привязано к объекту nickname_edit
                           return@setOnKeyListener true
                       }
                        return@setOnKeyListener false
        })

        binding.nicknameText.setOnClickListener {
            changeRecipeNote(it)
        }
    }

    private fun addNoteToRecipe(view: View){

        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            binding.nicknameEdit.visibility = View.GONE
            binding.nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        // Скрываем экранную клавиатуру
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun changeRecipeNote(view: View){

        binding.apply {

            nicknameEdit.text = nicknameText.editableText
            binding.nicknameEdit.visibility = View.VISIBLE
            binding.nicknameText.visibility = View.GONE
        }
        // Show the keyboard.
        // Показываем экранную клавиатуру
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_IMPLICIT, 0)
    }
}
