package kr.ac.kumoh.ce.s20191283.s23w07intentdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.ce.s20191283.s23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var main: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.btnMountain.setOnClickListener{
            Toast.makeText(this,"산 이미지",Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra("location", "mountain")
            startActivity(intent)
        }

        main.btnSea.setOnClickListener{
            Toast.makeText(this,"바다 이미지",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra("location", "sea")
            startActivity(intent)
        }


    }
}