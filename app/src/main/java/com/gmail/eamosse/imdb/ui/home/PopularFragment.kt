package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Popular
import com.gmail.eamosse.imdb.databinding.FragmentPopularBinding
import kotlinx.android.synthetic.main.fragment_popular.popularList
import org.koin.androidx.viewmodel.ext.android.viewModel




class PopularFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentPopularBinding
    private lateinit var adapter: PopularAdapter


    var page: Int = 1
    var scrolled = 0;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false)

        return binding.root
    }
    private var loading = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {


            token.observe(viewLifecycleOwner, Observer {

                //récupérer les movies popular
                getPopulars(page)
            })

            homeViewModel.populars.observe(
                viewLifecycleOwner
            ) {
                binding.popularList.adapter = PopularAdapter(it as MutableList<Popular>) {
                    val action =
                      PopularFragmentDirections.actionHomeSecondFragmentToMovieAboutFragment(it.id.toString());
                    NavHostFragment.findNavController(this@PopularFragment).navigate(action)
                }

            }



            popularList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                    if (!loading) {
                        if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == (populars.value?.size
                                ?: 1) - 1) {
                            //bottom of list!
                            addNewPopulars(page++)
                            loading = true
                            val handler = Handler()
                            handler.postDelayed({
                                Log.e("scroll", dy.toString())

                                loading = false
                            }, 300)

                        }
                    }
                }
            })




        }

    }
}

