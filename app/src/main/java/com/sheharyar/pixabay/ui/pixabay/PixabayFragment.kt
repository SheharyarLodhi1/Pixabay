package com.sheharyar.pixabay.ui.pixabay

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sheharyar.pixabay.R
import com.sheharyar.pixabay.data.entities.HitsList
import com.sheharyar.pixabay.databinding.ImagesFragmentBinding
import com.sheharyar.pixabay.utils.Resource
import com.sheharyar.pixabay.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PixabayFragment : Fragment(), ImagesAdapter.ImageItemListener {
    private var binding : ImagesFragmentBinding by autoCleared()
    private val viewModel :PixabayViewModel by viewModels()
    private lateinit var adapter: ImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = ImagesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.pixabayImages.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE

            }
        })
    }

    private fun setupRecyclerView() {
        adapter = ImagesAdapter(this)
        binding.imagesRv.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.imagesRv.adapter = adapter
    }

    override fun onClickedImage(hitsList: HitsList, position : Int) {
        // show user dialog and ask user to show details of clicked image
        showDetailScreenDialog(hitsList, position)
       /* findNavController().navigate(R.id.action_imagesFragment_to_imagesDetailFragment,
        bundleOf("id" to imageId))*/
    }

    private fun showDetailScreenDialog(hitsList: HitsList, position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Pixabay Alert")
        builder.setMessage("By clicking on Ok button you will redirect to details screen..")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            findNavController().navigate(R.id.action_imagesFragment_to_imagesDetailFragment,
                bundleOf("position" to position))
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater = inflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_col2 -> {
                binding.imagesRv.layoutManager = GridLayoutManager(requireContext(), 2)
            }
            R.id.menu_col3 -> {
                binding.imagesRv.layoutManager = GridLayoutManager(requireContext(), 3)
            }
            R.id.menu_col4 -> {
                binding.imagesRv.layoutManager = GridLayoutManager(requireContext(), 4)
            }

        }
        return super.onOptionsItemSelected(item)

    }
}