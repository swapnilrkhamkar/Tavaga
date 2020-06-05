package com.assignment.tavaga

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.assignment.tavaga.model.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    private lateinit var image: Image

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        Picasso.get().load(image.url).placeholder(R.drawable.progress_animation)
            .into(view.imageViewFull)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fav -> {
                fav()
                true
            }
            R.id.info -> {
                info()
                true
            }

            R.id.download -> {
                download()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    fun setImageModel(image: Image) {
        this.image = image
    }

    private fun download() {
    }

    private fun info() {
    }

    private fun fav() {
    }
}