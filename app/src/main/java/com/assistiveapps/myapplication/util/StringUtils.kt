package com.assistiveapps.myapplication.util

class StringUtils {
    companion object {
        private const val END_CHAR_COUNT = 4

        fun getDateFromString(string: String): String {
            val timeCharIndex = string.indexOf('T')
            return string.substring(0, timeCharIndex)
        }

        fun getTimeFromString(string: String): String {
            val timeCharIndex = string.indexOf('T')
            return string.substring(timeCharIndex + 1, string.length - END_CHAR_COUNT)
        }
    }
}