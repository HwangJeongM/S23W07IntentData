package kr.ac.kumoh.ce.s20191283.s23w07intentdata

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.ce.s20191283.s23w07intentdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object{
        const val KEY_NAME = "IMAGE"
        const val MOUNTAIN = "mountain"
        const val SEA = "sea"
    }

    private lateinit var main: ActivityMainBinding
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if (it.resultCode != Activity.RESULT_OK)
            return@registerForActivityResult

        val result = it.data?.getIntExtra(ImageActivity.IMAGE_RESULT, ImageActivity.NONE)
//        val str = when (result){
//            ImageActivity.LIKE -> "좋아요"
//            ImageActivity.DISLIKE -> "싫어요"
            AnotherActivity.IMAGE_RESULT
            AnotherActivity.IMAGE_NAME)
        val str = when (result){
            AnotherActivity.LIKE -> "좋아요"
            AnotherActivity.DISLIKE -> "싫어요"
            else -> "error"
        }
        when(it.data?.getStringExtra(ImageActivity.IMAGE_NAME)) {
            MOUNTAIN -> main.btnMountain.text = "산($str)"
            SEA -> main.btnSea.text = "바다($str)"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main = ActivityMainBinding.inflate(layoutInflater)
        setContentView(main.root)

        main.btnMountain.setOnClickListener{
            Toast.makeText(this,"산 이미지",Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ImageActivity::class.java)
            intent.putExtra(KEY_NAME   , "mountain")
            startActivity(intent)


            main.btnMountain.setOnClickListener(this)
            main.btnSea.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ImageActivity::class.java)
        val value = when (v?.id){
            main.btnMountain.id -> {
                Toast.makeText(this, "바다 이미지", Toast.LENGTH_SHORT).show()
                MOUNTAIN
            }
            main.btnSea.id -> {
            Toast.makeText(this, "바다 이미지", Toast.LENGTH_SHORT).show()
                SEA
            }
            else -> return
        }
        //val intent = Intent(this, //ImageActivity::class.java
            //AnotherActivity::class.java)
        intent.putExtra(KEY_NAME, value)
        //startActivity(intent)
        startForResult.launch(intent)
    }
}