package com.example.mycurse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.mycurse.Adapters.UserAdapters
import com.example.mycurse.Models.UserData
import com.example.mycurse.R
import com.example.mycurse.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import xyz.teamgravity.checkinternet.CheckInternet

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
    private lateinit var mRequestQueue: RequestQueue
    private lateinit var userAdapters: UserAdapters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        binding.swipeResh.setOnRefreshListener {
            loads()
            binding.swipeResh.isRefreshing = false
        }

        loads()

        return binding.root
    }
    private fun loads() {
        CheckInternet().check() { connected ->
            if (connected) {
                binding.Chlotti.visibility = View.GONE
                binding.rvView.visibility = View.VISIBLE
                mRequestQueue = Volley.newRequestQueue(requireContext())
                fetchJsonArrayRequest()
            } else {
                binding.Chlotti.visibility = View.VISIBLE
                binding.rvView.visibility = View.GONE
                binding.Chlotti.playAnimation()
            }
        }
    }
    private fun fetchJsonArrayRequest() {
        val url = "https://nbu.uz/uz/exchange-rates/json"

        val jsonArratRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {
                    val type = object : TypeToken<ArrayList<UserData>>() {}.type
                    val list = Gson().fromJson<ArrayList<UserData>>(response.toString(), type)


                    userAdapters = UserAdapters(list,{
                        val bundle = Bundle()
                        bundle.putFloat("summ",it.cbPrice.toFloat())
                        bundle.putString("nam",it.code)
                        findNavController().navigate(R.id.action_homeFragment_to_convertFragment,bundle)
                    })
                    binding.rvView.adapter = userAdapters
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                }
            }
        )
        mRequestQueue.add(jsonArratRequest)
    }

}