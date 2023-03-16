package ivo.sociomile.android_sdk_sample

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ivo.sociomile.android_sdk_sample.databinding.FragmentSecondBinding
import ivo.sociomile.android_sdk_sample.databinding.ShuffleLayoutBinding
import kotlinx.android.synthetic.main.menu_layout.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ShuffleFragment : Fragment() {

    private var _binding: ShuffleLayoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ShuffleLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}