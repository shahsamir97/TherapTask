package com.apps.therapassignment.ui.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.apps.therapassignment.MyApplication
import com.apps.therapassignment.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModels {
        AddNoteViewModelFactory(requireArguments().getInt("noteId"),AddNoteRepo((requireActivity().application as MyApplication).db.notesDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        initUi()
        return binding.root
    }

    private fun initUi(){
        val noteText = requireArguments().getString("note")
        if (noteText != null) binding.noteEditText.setText(noteText)
        binding.saveButton.setOnClickListener {
            viewModel.addNote(binding.noteEditText.text.toString())
        }
    }
}
