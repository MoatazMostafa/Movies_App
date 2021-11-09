package com.android.moviesapp.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.moviesapp.viewmodels.HomeFragmentViewModel
import com.android.moviesapp.R
import com.android.moviesapp.models.Movies
import com.android.moviesapp.models.repository.Utils.NOW_PLAYING
import com.android.moviesapp.models.repository.Utils.TOP_RATED
import com.android.moviesapp.models.repository.Utils.UPCOMING
import com.android.moviesapp.view.adapters.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeFragmentViewModel: HomeFragmentViewModel
    private lateinit var moviesRV:RecyclerView
    private lateinit var progressBar: ProgressBar
    private var selectedToggleButton = NOW_PLAYING

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentViewModel = HomeFragmentViewModel(activity?.application!!)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        moviesRV = view.home_moviesRV
        moviesRV.layoutManager = LinearLayoutManager(context)
        progressBar = view.home_progressBar
        view.home_button_toggle_group.check(R.id.home_now_playingBTN)
        view.home_button_toggle_group.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.home_upcomingBTN -> selectedToggleButton = UPCOMING
                    R.id.home_top_ratedBTN -> selectedToggleButton = TOP_RATED
                }
                showMoviesList(view.home_searchET.text.toString())
            }
        }
        view.home_searchET.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(enterdText: CharSequence, start: Int, before: Int, count: Int) { showMoviesList(enterdText.toString()) }
        })
        getMoviesLists()
        return view
    }

    @SuppressLint("ShowToast")
    private fun getMoviesLists() {
        homeFragmentViewModel.getNowPlayingMovies { isSuccess, moviesResponse, errorMsg ->
            if (isSuccess) {
                homeFragmentViewModel.nowPlayingMovies = moviesResponse?.results
                showMoviesList("")
            } else
                Toast.makeText(activity, errorMsg, Toast.LENGTH_SHORT)
        }
        homeFragmentViewModel.getupComingMovies { isSuccess, moviesResponse, errorMsg ->
            if (isSuccess) {
                homeFragmentViewModel.upComingMovies = moviesResponse?.results
                showMoviesList("")
            } else
                Toast.makeText(activity, errorMsg, Toast.LENGTH_SHORT)
        }
        homeFragmentViewModel.getTopRatedMovies { isSuccess, moviesResponse, errorMsg ->
            if (isSuccess) {
                homeFragmentViewModel.topRatedMovies = moviesResponse?.results
                showMoviesList("")
            } else
                Toast.makeText(activity, errorMsg, Toast.LENGTH_SHORT)
        }
    }

    private fun showMoviesList(text: String) {
        val filteredList:List<Movies>
        progressBar.visibility = View.GONE
        when (selectedToggleButton){
            NOW_PLAYING ->{
                if(text.isEmpty())
                moviesRV.adapter = MoviesAdapter(homeFragmentViewModel.nowPlayingMovies){ movieClicked(it) }
                else {
                    filteredList = homeFragmentViewModel.nowPlayingMovies!!.filter {
                        it.originalTitle?.toUpperCase(Locale.ROOT)!!.contains(text.toUpperCase(Locale.ROOT))
                    }
                    moviesRV.adapter = MoviesAdapter(filteredList) { movieClicked(it) }
                }
            }
            UPCOMING -> {
                if(text.isEmpty())
                    moviesRV.adapter = MoviesAdapter(homeFragmentViewModel.upComingMovies){ movieClicked(it) }
                else {
                    filteredList = homeFragmentViewModel.upComingMovies!!.filter {
                        it.originalTitle?.toUpperCase(Locale.ROOT)!!.contains(text.toUpperCase(Locale.ROOT))
                    }
                    moviesRV.adapter = MoviesAdapter(filteredList) { movieClicked(it) }
                }
            }
            TOP_RATED -> {
                if(text.isEmpty())
                    moviesRV.adapter = MoviesAdapter(homeFragmentViewModel.topRatedMovies){ movieClicked(it) }
                else {
                    filteredList = homeFragmentViewModel.topRatedMovies!!.filter {
                        it.originalTitle?.toUpperCase(Locale.ROOT)!!.contains(text.toUpperCase(Locale.ROOT))
                    }
                    moviesRV.adapter = MoviesAdapter(filteredList){ movieClicked(it) }
                }
            }
        }
    }


    private fun movieClicked(movie: Movies) {

    }
}