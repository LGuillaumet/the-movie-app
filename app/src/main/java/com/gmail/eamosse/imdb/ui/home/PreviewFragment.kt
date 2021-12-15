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
import com.gmail.eamosse.idbdata.data.Preview
import com.gmail.eamosse.imdb.databinding.FragmentPreviewBinding
import kotlinx.android.synthetic.main.fragment_preview.*
import org.koin.androidx.viewmodel.ext.android.viewModel




class PreviewFragment : Fragment() {
    val args: PreviewFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentPreviewBinding
    private lateinit var adapter: PreviewAdapter

    companion object {
        const val ARG_GENRE = "genre"
    }

    var page: Int = 1
    var scrolled = 0;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreviewBinding.inflate(inflater, container, false)

        return binding.root
    }
    private var loading = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {


            token.observe(viewLifecycleOwner, Observer {

                //récupérer les previews
                getPreviews(categoryId = args.myArg.toInt(), page)
            })

            homeViewModel.previews.observe(
                viewLifecycleOwner
            ) {
                binding.previewsList.adapter = PreviewAdapter(it as MutableList<Preview>) {
                    val action =
                        PreviewFragmentDirections.actionHomeSecondFragmentToMovieAboutFragment(it.id.toString());
                    NavHostFragment.findNavController(this@PreviewFragment).navigate(action)
                }

            }



            previewsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                    if (!loading) {
                        if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == (previews.value?.size
                                ?: 1) - 1) {
                            //bottom of list!
                            addNewPreviews(id = args.myArg.toInt(), page++)
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

