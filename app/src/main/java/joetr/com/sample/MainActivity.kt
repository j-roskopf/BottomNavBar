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
            Toast.makeText(it.context, "asdf", Toast.LENGTH_SHORT).show()
        })

    }
}
