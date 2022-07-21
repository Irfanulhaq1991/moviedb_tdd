package com.zenjob.android.browsr

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.zenjob.android.browsr.list.data.remote.Networking
import com.zenjob.android.browsr.list.presentation.ListActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import com.jakewharton.espresso.OkHttp3IdlingResource


@RunWith(AndroidJUnit4::class)
class MovieListUiTest {

    private var server: MockWebServer = MockWebServer()

    private lateinit var okHttp3IdlingResource: OkHttp3IdlingResource


    @get:Rule
    val activityRule = ActivityScenarioRule(ListActivity::class.java)

    @Spy
    lateinit var spyNetworking: Networking

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        server.start(8080)

      val  client  = spyNetworking.provideOkHttpClient(spyNetworking.provideInterceptor())
        okHttp3IdlingResource = OkHttp3IdlingResource.create(
            "okhttp",
            client
        )
        IdlingRegistry.getInstance().register(
            okHttp3IdlingResource
        )
    }

    @After
    fun tearDown() {
        stopKoin() // to remove 'A Koin Application has already been started'
        server.shutdown()
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)

    }


    @Test
    fun `When app is started Then View should be initialised properly`() {
        activityRule.moveToState(Lifecycle.State.CREATED)
        val activity: ListActivity = activityRule.getActivity()
        val recyclerView = activity.findViewById<RecyclerView>(R.id.rc_item_list)
        assertThat(recyclerView).isNotNull()
        assertThat(recyclerView.layoutManager).isInstanceOf(GridLayoutManager::class.java)
        assertThat((recyclerView.layoutManager as GridLayoutManager).spanCount).isEqualTo(2)
    }


    @Test
    fun `When app is started Then the list should be populated`() {
        Mockito.`when`(spyNetworking.provideBasUrl()).thenReturn(server.url("/").toString())

        val response = MockResponse()
            .setBody(MoviesDummyData.provideJson())
            .setResponseCode(200)
        server.enqueue(response)

        activityRule.moveToState(Lifecycle.State.CREATED)
        val activity: ListActivity = activityRule.getActivity()
        val recyclerView = activity.findViewById<RecyclerView>(R.id.rc_item_list)
        assertThat(recyclerView.adapter?.itemCount).isEqualTo(20)
        assertThat(recyclerView.layoutManager).isInstanceOf(GridLayoutManager::class.java)
        assertThat((recyclerView.layoutManager as GridLayoutManager).spanCount).isEqualTo(2)
    }
}