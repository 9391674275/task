package com.task.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_new_one.*

class NewActivity : AppCompatActivity() {
    private lateinit var image:String
    private lateinit var desc:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_one)
        image= intent.getStringExtra("img")!!
        desc= intent.getStringExtra("desc")!!

        description.setText(desc)
        Picasso.get().load(image).into(imageView)

    }

}
