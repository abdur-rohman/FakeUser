package id.refactory.fakeuser.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bicepstudio.customadapter.listener.OnCustomScroll
import id.refactory.fakeuser.R
import id.refactory.fakeuser.adapters.UserAdapter
import id.refactory.fakeuser.api.UserClient
import id.refactory.fakeuser.databinding.FragmentUsersBinding
import id.refactory.fakeuser.databinding.ItemUserBinding
import id.refactory.fakeuser.models.ResultsItem
import id.refactory.fakeuser.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersFragment : Fragment() {
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

    private lateinit var binding: FragmentUsersBinding
    private val adapter by lazy { UserAdapter(requireContext()) }
    private var page = 1
    private var list = mutableListOf<ResultsItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false).apply {
            rvUsers.adapter = adapter
            tieSearch.doOnTextChanged { text, _, _, _ -> searchUserByName(text.toString()) }
            rvUsers.layoutManager?.let {
                rvUsers.addOnScrollListener(object : OnCustomScroll(it, 3) {
                    override fun onLoadMore(page: Int, totalItemsCount: Int) {
                        if (page <= 10 && list.size % 100 == 0) {
                            loadMore()
                        }
                    }
                })
            }
        }

        getAllUsers()

        return binding.root
    }

    private fun getAllUsers() {
        UserClient.service.getAllUser(page = page).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (page == 1) {
                            adapter.list = it.results.toMutableList()
                        } else {
//                            adapter.hideLoading()
                            adapter.addUsers(it.results)
                        }

                        list.addAll(it.results)

                        page++
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                if (page > 1) adapter.hideLoading()

                t.printStackTrace()

                Toast.makeText(
                    requireContext(),
                    t.message ?: "Oops something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun loadMore() {
//        adapter.showLoading()

        getAllUsers()
    }

    private fun searchUserByName(query: String) {
        adapter.list = list.asSequence().filter {
            val name = "${it.name.first} ${it.name.last}"

            name.contains(query, true)
        }.toMutableList()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UsersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}