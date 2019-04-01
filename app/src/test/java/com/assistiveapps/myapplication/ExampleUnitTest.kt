package com.assistiveapps.myapplication

import com.assistiveapps.myapplication.util.StringUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun date_isCorrect() {
        assertEquals("2019-04-01", StringUtils.getDateFromString("2019-04-01T05:42:00Z"))
    }

    @Test
    fun time_isCorrect() {
        assertEquals("05:42", StringUtils.getTimeFromString("2019-04-01T05:42:00Z"))
    }
}
