package com.learning.notex.ui.Notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.notex.R
import com.learning.notex.databinding.NotesFragmentBinding

class NotesFragment : Fragment() {

    companion object {
        fun newInstance() = NotesFragment()
    }

    private lateinit var viewModel: NotesViewModel
    private lateinit var binding:NotesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= NotesFragmentBinding.inflate(layoutInflater)
        val view=binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        viewModel.all_notes.observe(viewLifecycleOwner, Observer {

            val recycler_view=binding.rView
            val adapter=RecyclerViewAdapter(it)
            recycler_view.adapter=adapter
            recycler_view.layoutManager= GridLayoutManager(requireContext(),2)




        })
    }

}