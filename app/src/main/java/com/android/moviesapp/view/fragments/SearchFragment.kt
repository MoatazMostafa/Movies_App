package com.android.moviesapp.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.moviesapp.R
import com.android.moviesapp.models.Movies
import com.android.moviesapp.view.MovieActivity
import com.android.moviesapp.view.adapters.MoviesAdapter
import com.android.moviesapp.viewmodels.SearchFragmentViewModel
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {
    private lateinit var searchFragmentViewModel: SearchFragmentViewModel

    @SuppressLint("ShowToast")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchFragmentViewModel = SearchFragmentViewModel(activity?.application!!)
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        view.search_moviesRV.layoutManager = LinearLayoutManager(context)
        view.search_buttonBTN.setOnClickListener{
            if(view.searchET.text!!.isEmpty()){
                view.search_textInputLayout.error = getString(R.string.please_enter_movie_name)
            }else {
                searchFragmentViewModel.getMoviesByName(view.searchET.text.toString()){ isSuccess, moviesResponse, errorMsg ->
                    if (isSuccess) {
                        view.search_moviesRV.adapter = MoviesAdapter(moviesResponse?.results){
                            movieClicked(it)
                        }
                    } else
                        Toast.makeText(activity, errorMsg, Toast.LENGTH_SHORT)

                }
            }
        }
        return view
    }

    private fun movieClicked(movie: Movies) {
        val intent = Intent(activity, MovieActivity::class.java)
        intent.putExtra("Movie", movie)
        this.startActivity(intent)
    }
}