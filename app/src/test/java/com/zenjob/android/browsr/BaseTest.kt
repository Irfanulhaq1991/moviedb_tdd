package com.zenjob.android.browsr

import io.mockk.MockKAnnotations

open class BaseTest {
   open fun setup() {
    MockKAnnotations.init(this, relaxUnitFun = true)
   }
}
