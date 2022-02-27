package com.sheharyar.pixabay.ui.pixabaydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.sheharyar.pixabay.databinding.ImagesDetailFragmentBinding
import com.sheharyar.pixabay.utils.Resource
import com.sheharyar.pixabay.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PixabayDetailsFragment : Fragment() {
    private var binding: ImagesDetailFragmentBinding by autoCleared()
    private val viewModel: PixabayDetailsViewModel by viewModels()
    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImagesDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        position = arguments?.getInt("position")!!
//        var hitsList = arguments?.getParcelableArray("hitss")
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.pixabayImageDetails.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        Glide.with(binding.root)
                            .load(it.data.get(position).largeImageURL)
                            .transform(CircleCrop())
                            .into(binding.image)
//it.data.get(position).largeImageURL
                        binding.tagsTextDetails.text = it.data.get(position).tags!!
                        binding.likesTextDetails.text = it.data.get(position).likes?.toString()!!
                        binding.downloadsTextDetails.text = it.data.get(position).downloads?.toString()!!
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE

            }
        })
    }
}