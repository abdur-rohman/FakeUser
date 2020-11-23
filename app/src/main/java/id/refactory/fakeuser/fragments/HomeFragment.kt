package id.refactory.fakeuser.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.refactory.fakeuser.R
import id.refactory.fakeuser.adapters.ViewPagerAdapter
import id.refactory.fakeuser.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentHomeBinding
    private val fragments by lazy {
        listOf(UsersFragment(), FavoritesFragment(), ProfileFragment())
    }
    private val adapter by lazy { ViewPagerAdapter(fragments, childFragmentManager, lifecycle) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            vpHome.adapter = adapter
            bnvHome.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.users_menu -> {
                        vpHome.currentItem = 0

                        return@setOnNavigationItemSelectedListener true
                    }

                    R.id.favorites_menu -> {
                        vpHome.currentItem = 1

                        return@setOnNavigationItemSelectedListener true
                    }

                    R.id.profile_menu -> {
                        vpHome.currentItem = 2

                        return@setOnNavigationItemSelectedListener true
                    }
                }

                false
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}