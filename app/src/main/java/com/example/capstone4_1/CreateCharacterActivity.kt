package com.example.capstone4_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.example.capstone4_1.databinding.ActivityCreateCharacterBinding
import com.rd.draw.controller.DrawController.ClickListener

class CreateCharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateCharacterBinding.inflate(layoutInflater)

        //스텁 코드
        binding.viewPager.adapter = CharacterListPagerAdapter(arrayListOf(R.drawable.sprite_char1, R.drawable.sprite_char2, R.drawable.sprite_char3))

        binding.viewPagerIndicater.setClickListener(ClickListener {
            binding.viewPagerIndicater.setSelected(it)
            binding.viewPager.setCurrentItem(it, true)
        })

        setContentView(binding.root)
    }
}

class CharacterListPagerAdapter(private val data: ArrayList<Int>) : PagerAdapter() {
    inner class ViewHolder {
        lateinit var image: ImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.character_list, container, false)

        return view.apply {
            this.findViewById<ImageView>(R.id.imageCharacter).setImageResource(data[position])

            container.addView(this)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return data.size
    }
}