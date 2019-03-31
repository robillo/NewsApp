package com.assistiveapps.myapplication

import android.app.Activity
import android.app.Application
import android.widget.Toast
import com.assistiveapps.myapplication.data.event.NoInternetEvent
import com.assistiveapps.myapplication.di.component.DaggerNewsAppComponent
import com.assistiveapps.myapplication.di.component.NewsAppComponent
import com.assistiveapps.myapplication.di.module.ContextModule
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class NewsApp : Application() {

    private lateinit var component: NewsAppComponent

    companion object {
        fun get(activity: Activity): NewsApp {
            return activity.application as NewsApp
        }
    }

    override fun onCreate() {
        super.onCreate()

        setup()
    }

    private fun setup() {
        Timber.plant(Timber.DebugTree())

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/Lato.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build())

        component = DaggerNewsAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()

        EventBus.getDefault().register(this)
    }

    fun newsAppComponent(): NewsAppComponent {
        return component
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNoInternetEvent(noInternetEvent: NoInternetEvent) {
        Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_LONG).show()
    }
}
