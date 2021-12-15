package com.gmail.eamosse.imdb.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.glide.BidingAdapters
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_movie_about.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val dashboardViewModel: HomeViewModel by viewModel();

    private lateinit var popularButton: Button;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        popularButton = root.findViewById(R.id.buttonPopular);
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        with(dashboardViewModel) {


            popularButton.setOnClickListener {
                //Action perform when the user clicks on the button.
                val action = DashboardFragmentDirections.actionDashboardToPopularList();
                NavHostFragment.findNavController(this@DashboardFragment).navigate(action)
            }

        }
    }
}