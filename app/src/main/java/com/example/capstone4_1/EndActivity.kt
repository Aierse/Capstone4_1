package com.example.capstone4_1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class EndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)
        val shareBtn: Button = findViewById(R.id.shareBtn)
        val sc: Button = findViewById(R.id.restartBtn)

        shareBtn.setOnClickListener(View.OnClickListener {
            capture()
        })
        sc.setOnClickListener(View.OnClickListener {
            capture()
        })


    }

    fun capture(): String? {
        val now = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date(System.currentTimeMillis()))
        val mPath = cacheDir.absolutePath+"/$now.png"

        var bitmap: Bitmap? = null
        val captureView = window.decorView.rootView	//캡처할 뷰

        bitmap = Bitmap.createBitmap(captureView.width, captureView.height, Bitmap.Config.ARGB_8888)

        var canvas = Canvas(bitmap)
        captureView.draw(canvas)

        if(bitmap == null) {
            return null
        }else {
            val imageFile = File(mPath)
            val outputStream = FileOutputStream(imageFile)
            outputStream.use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                outputStream.flush()
            }
            if(imageFile == null) {
                //캡쳐 실패한 경우
            }else{
                try {
                    val intent = Intent(Intent.ACTION_SEND)
                    val fileUri : Uri? = FileProvider.getUriForFile(applicationContext, "com.example.capstone4_1", imageFile)
                    fileUri?.let {
                        intent.putExtra(Intent.EXTRA_STREAM, fileUri)
                        intent.type = "image/*"
                        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                        val shareIntent = Intent.createChooser(intent, null)
                        startActivity(intent)
                    }
                }catch(e: ActivityNotFoundException) {
                    //공유할 수 있는 어플리케이션이 없을 때 처리
                }
            }
        }
        return mPath

    }


    //화면 캡쳐하기
    fun ScreenShot() {
        val view = window.decorView.rootView
        var screenBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(screenBitmap)
        view.draw(canvas)

        try {
            val cachePath = File(applicationContext.cacheDir, "images")
            cachePath.mkdirs() // don't forget to make the directory
            val stream =
                FileOutputStream("$cachePath/image.png") // overwrites this image every time
            screenBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
            val newFile = File(cachePath, "image.png")
            val contentUri = FileProvider.getUriForFile(
                applicationContext,
                "com.example.capstone4_1", newFile
            )
            val Sharing_intent = Intent(Intent.ACTION_SEND)
            Sharing_intent.type = "image/png"
            Sharing_intent.putExtra(Intent.EXTRA_STREAM, contentUri)
            startActivity(Intent.createChooser(Sharing_intent, "Share image"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}