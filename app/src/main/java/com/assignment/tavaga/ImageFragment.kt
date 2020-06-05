package com.assignment.tavaga

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.tavaga.adapter.ImageAdapter
import com.assignment.tavaga.model.Image
import com.assignment.tavaga.network.Error
import com.assignment.tavaga.network.ImageViewModel
import kotlinx.android.synthetic.main.fragment_image.view.*

class ImageFragment : Fragment() {

    private lateinit var imageViewModel: ImageViewModel
    private lateinit var imagesList: ArrayList<Image>
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var activity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as AppCompatActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_image, container, false)

        val layoutManager: RecyclerView.LayoutManager
        view.recyclerViewImage.setHasFixedSize(true)
        layoutManager = GridLayoutManager(activity, 2)
        view.recyclerViewImage.layoutManager = layoutManager
        imagesList = ArrayList()
        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)

        imageViewModel.getImages().observe(viewLifecycleOwner, Observer { images ->

            Log.e("RESOURCE", " $images")
            if (images.data != null) {
                view.progressBar.visibility = View.GONE
                imagesList = images.data

                imageAdapter = ImageAdapter(
                    activity,
                    images.data
                )
                view.recyclerViewImage.adapter = imageAdapter
                imageAdapter.notifyDataSetChanged()

                imageAdapter.onItemClick = { image ->
                    Log.e("SSSSCCCCC", " $image")
                    val fragmentManager = parentFragmentManager
                    val detailFragment = DetailFragment()
                    detailFragment.setImageModel(image)
                    val fragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.parentImageFragment, detailFragment)
                    fragmentTransaction.addToBackStack("")
                    fragmentTransaction.commit()
                }

            } else {
                view.progressBar.visibility = View.GONE
                if (images.message != null) {
                    Error(activity, images.message).showError()
                }

            }
        })

        return view

    }
}