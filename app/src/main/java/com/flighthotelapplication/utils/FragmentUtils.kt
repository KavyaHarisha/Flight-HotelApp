package com.flighthotelapplication.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.flighthotelapplication.R
import java.text.ParseException
import java.text.SimpleDateFormat

object FragmentUtils {

    val TRANSITION_POP = 0
    val TRANSITION_FADE_IN_OUT = 1
    val TRANSITION_SLIDE_LEFT_RIGHT = 2
    val TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT = 3
    val TRANSITION_NONE = 4

    internal annotation class FragmentAnimation


    fun replaceFragment(
        activity: AppCompatActivity?,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean, @FragmentAnimation animationType: Int
    ) {

        if (null == activity)
            return

        val fragmentManager = activity.getSupportFragmentManager()
        val transaction = fragmentManager.beginTransaction()

        when (animationType) {
            TRANSITION_POP -> transaction.setCustomAnimations(
                R.anim.anim_enter,
                R.anim.anim_exit,
                R.anim.anim_pop_enter,
                R.anim.anim_pop_exit
            )
            TRANSITION_FADE_IN_OUT -> transaction.setCustomAnimations(
                R.anim.anim_frag_fade_in,
                R.anim.anim_frag_fade_out
            )
            TRANSITION_SLIDE_LEFT_RIGHT -> transaction.setCustomAnimations(
                R.anim.slide_in_from_rigth,
                R.anim.slide_out_to_left,
                R.anim.slide_in_from_left,
                R.anim.slide_out_to_right
            )
            TRANSITION_SLIDE_LEFT_RIGHT_WITHOUT_EXIT -> transaction.setCustomAnimations(
                R.anim.slide_in_from_rigth,
                0
            )
            TRANSITION_NONE -> transaction.setCustomAnimations(0, 0)
            else -> transaction.setCustomAnimations(0, 0)
        }

        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.canonicalName)

        transaction.replace(id, fragment, fragment.javaClass.canonicalName)
        transaction.commit()
    }

    fun convertDateString(convertDate: String): String? {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputFormat = SimpleDateFormat("HH:mm aa")
            val date = inputFormat.parse(convertDate)
            return outputFormat.format(date!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    fun timeDifference(time1: String?, time2: String?): String {
        val format = SimpleDateFormat("HH:mm aa")
        val date1 = format.parse(time1)
        val date2 = format.parse(time2)
        val difference = date2.time - date1.time
        val diffMinutes = difference / (60 * 1000) % 60
        val diffHours = difference / (60 * 60 * 1000) % 24
        return "${diffHours}H ${diffMinutes}m"
    }
}