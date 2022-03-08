package ir.ayantech.task.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import ir.ayantech.task.R
import ir.ayantech.task.api.ApiInterface
import ir.ayantech.task.api.Data
import ir.ayantech.task.api.DataModelOutput
import ir.ayantech.task.databinding.FragmentFirstBinding
import ir.ayantech.task.helper.*
import ir.ayantech.task.ui.adapter.ColorAdapter
import ir.ayantech.task.ui.adapter.TopUsersAdapter
import retrofit2.Call
import retrofit2.Response

class FirstFragment : BaseFragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupColorAdapter()
        setupActions()
        getTopUsers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
            binding.destinationEt.setText(result)
        }
    }

    private fun getTopUsers() {
        val apiInterface = ApiInterface.create().getUsers()
        binding.prgBar.makeVisible()
        apiInterface.enqueue(object : retrofit2.Callback<DataModelOutput> {
            override fun onResponse(
                call: Call<DataModelOutput>?,
                response: Response<DataModelOutput>?
            ) {
                binding.prgBar.makeGone()
                response?.body()?.let {
                    setUsersAdapter(it.data)
                }
            }

            override fun onFailure(call: Call<DataModelOutput>?, t: Throwable?) {
                binding.prgBar.makeGone()
            }
        })
    }

    private fun setUsersAdapter(topUsersList: List<Data>) {
        binding.topUsersRv.verticalSetup()
        binding.topUsersRv.adapter = TopUsersAdapter(topUsersList)
    }

    private fun setupActions() {
        binding.destinationEt.textChanges {
            binding.goBtn.isEnabled = it.isNotEmpty()
        }

        binding.goBtn.setOnClickListener {
            hideKeyboard()
            showSearchFragment()
        }
    }

    private fun showSearchFragment() {
        val bundle = bundleOf("destination" to binding.destinationEt.text.toString())
        activity?.supportFragmentManager?.commit {
            setReorderingAllowed(true)
            addToBackStack("second")
            add<SearchFragment>(R.id.mainContainer, args = bundle)
        }
    }

    private fun setupColorAdapter() {
        binding.colorRv.gridSetup(useCustomGrid = true)
        binding.colorRv.adapter = ColorAdapter(
            listOf(
                "#FFBD1E40", "#FF2D684E", "#FFFFC107", "#FF2196F3", "#FFFF5722",
                "#FF3F51B5", "#FFE91E63"
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}