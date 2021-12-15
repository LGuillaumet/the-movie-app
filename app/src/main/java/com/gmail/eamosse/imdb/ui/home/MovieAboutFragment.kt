package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentMovieAboutBinding
import com.gmail.eamosse.imdb.glide.BidingAdapters
import kotlinx.android.synthetic.main.fragment_movie_about.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieAboutFragment : Fragment() {
    val args: MovieAboutFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentMovieAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    getMovieById(id = args.movieId.toInt())
/*
                   movieLike.setOnClickListener(View.OnClickListener {
                        Log.e("CLICK", movie.value?.title!!)
                    })*/
                }
            )

            movie.observe(
                viewLifecycleOwner,
                Observer {
                    binding.item = it;
                    BidingAdapters.changeImage(binding.movieImg, it.poster_path);
                }
            )
        }
    }
}

