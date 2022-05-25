package com.example.capstone4_1

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class EndActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

    }
    //캡쳐버튼클릭
    fun mOnCaptureClick(v: View?) {
        //전체화면
        val rootView = window.decorView
        val screenShot = ScreenShot(rootView)
        if (screenShot != null) {
            //갤러리에 추가
            sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(screenShot)))
        }
    }

    //화면 캡쳐하기
    fun ScreenShot(view: View): File? {
        view.isDrawingCacheEnabled = true
        val screenBitmap = view.drawingCache
        val filename = "screenshot.png"
        val file = File(Environment.getExternalStorageDirectory().toString() + "/Pictures", filename)
        var os: FileOutputStream? = null

        try {
            os = FileOutputStream(file)
            screenBitmap.compress(Bitmap.CompressFormat.PNG, 90, os) //비트맵을 PNG파일로 변환
            os.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        view.isDrawingCacheEnabled = false
        return file
    }

}