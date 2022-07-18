package com.zenjob.android.browsr.list.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.irfan.sadaparcel.inventory.ItemLayoutManger
import com.irfan.sadaparcel.inventory.RcAdaptor
import com.squareup.picasso.Picasso
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.databinding.ActivityListBinding
import com.zenjob.android.browsr.databinding.ViewholderMovieItemBinding
import com.zenjob.android.browsr.detail.DetailActivity
import com.zenjob.android.browsr.detail.EXTRA_KEY
import com.zenjob.android.browsr.list.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListActivity : AppCompatActivity(),
    Observer<MoviesListUiState>, ItemLayoutManger<Movie> {

    private val viewModel: MoviesListViewModel by viewModel()

    private lateinit var binding: ActivityListBinding

    private val adaptor: RcAdaptor<Movie> by lazy {
        RcAdaptor<Movie>(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)
        adaptor.bindRecyclerView(binding.rcItemList)
        setup()
        viewModel.fetchMoviesList()

    }

    private fun setup() {
        viewModel.uiState.observe(this, this)

        binding.swiperefresh.setOnRefreshListener {
            viewModel.fetchMoviesList()
        }
    }

    override fun onChanged(state: MoviesListUiState) {
        binding.swiperefresh.isRefreshing = state.showLoading
        adaptor.setItems(state.moviesList)
        state.errorMessage?.let {
            Toast.makeText(this, state.errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.userMessageShown()
        }
    }

    override fun getLayoutId(position: Int): Int {
        return R.layout.viewholder_movie_item
    }

    override fun bindView(view: View, position: Int, item: Movie) {
        val binding = DataBindingUtil.bind<ViewholderMovieItemBinding>(view)!!
        binding.movie = item
        binding.root.tag = item
        binding.root.setOnClickListener {
            val movie = it.tag as Movie
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(EXTRA_KEY, movie)
            startActivity(intent)
        }
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}