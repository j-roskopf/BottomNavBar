package joetr.com.sample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeContainer.registerClickListener(View.OnClickListener {
            Toast.makeText(it.context, "Home clicked", Toast.LENGTH_SHORT).show()
        })

        favoriteContainer.registerClickListener(View.OnClickListener {
            Toast.makeText(it.context, "Likes clicked", Toast.LENGTH_SHORT).show()
        })


        searchContainer.registerClickListener(View.OnClickListener {
            Toast.makeText(it.context, "Search clicked", Toast.LENGTH_SHORT).show()
        })


        profileContainer.registerClickListener(View.OnClickListener {
            Toast.makeText(it.context, "Profile clicked", Toast.LENGTH_SHORT).show()
        })

        faceContainer.registerClickListener(View.OnClickListener {
            Toast.makeText(it.context, "Face clicked", Toast.LENGTH_SHORT).show()
        })
    }
}
