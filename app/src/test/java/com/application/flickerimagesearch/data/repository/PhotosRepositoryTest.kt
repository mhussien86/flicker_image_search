package com.application.flickerimagesearch.data.repository

import com.application.flickerimagesearch.data.api.ApiHelper
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class PhotosRepositoryTest {

    private val apiHelper = mockk<ApiHelper>()
    private val repositry = PhotosRepository(apiHelper = apiHelper)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {

    }

    @Test
    fun fetchImages() {

    }
}