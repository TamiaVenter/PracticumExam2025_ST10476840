//ST10476840, Tamia Venter

//MainActivity.kt
// This is the entry Activity- Screen one of the Music Playlist Manager App
// It uses parallel arrays to store song details and it allows users to create and manage playlists

package za.co.varsitycollege.st10476840.practicumexam2025

import android.app.ListActivity
import android.content.Intent
import android.media.Rating
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    //Parallel Arrays to store Song Details
    private val songTitles = mutableListOf<String>(
        "Gyalchester",
        "Long As I Live",
        "Blessed",
        "Pluto(Remember You)"
    )
    private val artistNames = mutableListOf<String>(
        "Drake",
        "Toni Braxton",
        "Daniel Caesar",
        "Dj Clock ft Beatenberg"
    )
    private val Ratings = mutableListOf<String>(
        "4",
        "1",
        "2",
        "3"
    )
    private val Comments = mutableListOf<String>(
        "Rap",
        "Dance song",
        "Best Love song",
        "Memories"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Layout contains three buttons: Add to Playlist,GoToList Screen, Exit
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnGoToList = findViewById<Button>(R.id.btnGoToList)
        val btnExit = findViewById<Button>(R.id.btnExit)

        // Add new song to Playlist
        btnAdd.setOnClickListener {
            showAddSongToPlaylist()
        }
        //Navigate to Screen Two(ListActivity)
        btnGoToList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            //Pass the parallel arrays as ArrayLists in the intent extras to next screen
            intent.putStringArrayListExtra("SongTitles", ArrayList(songTitles))
            intent.putStringArrayListExtra("artistNames", ArrayList(artistNames))
            intent.putStringArrayListExtra("Ratings", ArrayList(Ratings))
            intent.putStringArrayListExtra("Comments", ArrayList(Comments))
            startActivity(intent)
        }
        //Exit App
        btnExit.setOnClickListener {
            finishAffinity() //Close App
        }
        //Shows the information the user entered and add to parallel arrays
        val songTitleEditText = findViewById<EditText>(R.id.songTitleEditText)
        songTitleEditText.hint = "Enter Song Title"
        val artistsNameEditText = findViewById<EditText>(R.id.artistsNameEditText)
        artistsNameEditText.hint = "Enter Artists Name"
        val ratingsEditText = findViewById<EditText>(R.id.ratingsEditText)
        ratingsEditText.hint = "Enter Rating"
        val commentsEditText = findViewById<EditText>(R.id.commentsEditText)
        commentsEditText.hint = "Leave a comment"

        val dialogView = null
        AlertDialog.Builder(this)
            .setTitle("Add to Playlist")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->

                // Read values and add to arrays if valid
                val songTitle = songTitleEditText.toString().trim()
                val artistsName = artistsNameEditText.toString().trim()
                val ratings = ratingsEditText.toString().trim()
                val comments = commentsEditText.toString().trim()

                if (songTitle.isEmpty() || artistsName.isEmpty() || ratings.isEmpty()) {
                    Toast.makeText(this, "Please fill in everything", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                if (ratings == null || ratings <= 0.toString()) {
                    Toast.makeText(this, "Rating must be a positive number", Toast.LENGTH_SHORT)
                        .show()
                    return@setPositiveButton

                }
                // Add Playlist details to parallel arrays
                songTitle.add(songTitle)
                artistsName.add(artistsName)
                ratings.add(ratings)
                comments.add(comments)

                Toast.makeText(this, "Song Added", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showAddSongToPlaylist() {
        TODO("Not yet implemented")
    }
}

private fun String.add(songTitle: String) {
    TODO("Not yet implemented")
}