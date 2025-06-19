// ListActivity.kt
//Screen Two of the app showing buttons to display Song List and ratings >=2, and a button to return to the main screen


package za.co.varsitycollege.st10476840.practicumexam2025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ListActivity : AppCompatActivity() {

    private lateinit var  songTitles :List<String>
    private lateinit var artistsName: List<String>
    private lateinit var ratings : List<String>
    private lateinit var comments: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Layout with buttons and a TextView for output
        setContentView(R.layout.activity_list)


       // Arrays passed from MainActivity
        songTitles = intent.getStringArrayListExtra("songTitles")?: emptyList()
        artistsName = intent.getStringArrayListExtra("artistsName")?: emptyList()
        ratings = intent.getStringArrayListExtra("ratings")?: emptyList()
        comments = intent.getStringArrayListExtra("comments")?: emptyList()

        val btnDisplay = findViewById<Button>(R.id.btnDisplay)
        val btnShowRatingTwoOrMore = findViewById<Button>(R.id.btnShowRatingTwoOrMore)
        val btnReturn = this.findViewById<Button>(R.id.btnReturn)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)

        // Full Music Playlist details
        btnDisplay.setOnClickListener {
            val builder = StringBuilder()
            for (i in songTitles.indices){
                builder.append("songTitles: ${songTitles[i]}\n")
                builder.append("artistsName: ${artistsName[i]}\n")
                builder.append("ratings: ${ratings[i]}\n")
                if (comments[i].isNotEmpty()){
                    builder.append("Comments: ${comments[i]}\n")
                }
                builder.append("\n")
            }
            tvOutput.text = builder.toString()
        }
        //To show song names with rating >=2
        btnShowRatingTwoOrMore.setOnClickListener {
            val filteredsongTitles = mutableListOf<String>()
            for (i in songTitles.indices){
                if (ratings[i] >= 2.toString()){
                    filteredsongTitles.add(songTitles[i])

                }
            }
            tvOutput.text = if (filteredsongTitles.isNotEmpty()) {
                "songTitles with ratings of 2 or more: \n + filteredsongTitles.joinToString(separator = \n"
            }else{
                "No songTitles with ratings 2 or more"
            }
        }

        // Return to main screen
        btnReturn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)

            // Clear activity stack and go back
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}