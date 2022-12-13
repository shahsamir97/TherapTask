package com.apps.therapassignment.ui.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.apps.therapassignment.MyApplication
import com.apps.therapassignment.database.Note
import com.apps.therapassignment.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val note: Note by lazy { requireArguments().getParcelable("note")!! }
    private val viewModel: AddNoteViewModel by viewModels {
        AddNoteViewModelFactory(
            note,
            AddNoteRepo((requireActivity().application as MyApplication).db.notesDao())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        initUi()
        setUpObservers()
        return binding.root
    }

    private fun initUi() {
        if(note.note!= null) binding.noteEditText.setText(note.note)
        binding.saveButton.setOnClickListener {
            viewModel.addNote(binding.noteEditText.text.toString())
        }
    }

    private fun setUpObservers() {
        viewModel.showMessage.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.isDataInserted.observe(viewLifecycleOwner){
            if (it)
            findNavController().navigateUp()
        }
    }
}
