package com.zenjob.android.browsr

import io.mockk.MockKAnnotations

open class BaseTest {
   open fun setUp() {
    MockKAnnotations.init(this, relaxUnitFun = true)
   }
}
