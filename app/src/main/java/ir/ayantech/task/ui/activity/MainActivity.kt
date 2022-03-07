package ir.ayantech.task.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import ir.ayantech.task.R
import ir.ayantech.task.databinding.ActivityMainBinding
import ir.ayantech.task.helper.changeVisibility
import ir.ayantech.task.ui.fragment.BaseFragment
import ir.ayantech.task.ui.fragment.FirstFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            showFirstFragment()
        }
    }

    private fun showFirstFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack("first")
            add<FirstFragment>(R.id.mainContainer)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}