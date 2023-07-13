package com.hamza.payback.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class ViewModelBaseTest : AppBaseTest() {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
}