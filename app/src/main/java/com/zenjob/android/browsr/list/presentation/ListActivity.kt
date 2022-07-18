package com.zenjob.android.browsr.list.presentation

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.irfan.sadaparcel.inventory.ItemLayoutManger
import com.irfan.sadaparcel.inventory.RcAdaptor
import com.squareup.picasso.Picasso
import com.zenjob.android.browsr.R
import com.zenjob.android.browsr.databinding.ActivityListBinding
import com.zenjob.android.browsr.databinding.ViewholderMovieItemBinding
import com.zenjob.android.browsr.list.di.movieListModule
import com.zenjob.android.browsr.list.di.networkModule
import com.zenjob.android.browsr.list.domain.model.Movie
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin


class ListActivity : AppCompatActivity(),
    Observer<MoviesListUiState>, ItemLayoutManger<Movie> {
    private val viewModel: MoviesListViewModel by viewModel()
    private lateinit var binding: ActivityListBinding

    private val adaptor: RcAdaptor<Movie> by lazy {
        RcAdaptor<Movie>(this).apply { bindRecyclerView(binding.list) }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)

        setupKoin()
        observeViewModel()
        viewModel.fetchMoviesList()
    }

    private fun observeViewModel() {
        viewModel.uiState.observe(this, this)
    }

    override fun onChanged(state: MoviesListUiState) {
        binding.uiState = state
        if (state.errorMessage.isNotEmpty()) {
            viewModel.userMessageShown()
            return
        }
        adaptor.setItems(state.moviesList)
    }

    private fun setupKoin() {
        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@ListActivity)
            // use modules
            modules(movieListModule, networkModule)
        }
    }

    override fun getLayoutId(position: Int): Int {
        return R.layout.viewholder_movie_item
    }

    override fun bindView(view: View, position: Int, item: Movie) {
        val binding = ViewholderMovieItemBinding.bind(view)
        binding.movie = item
        binding.root.tag = item
        binding.root.setOnClickListener{
            val item = it.tag  as Movie
        }
    }


}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}