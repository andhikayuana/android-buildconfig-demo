package id.yuana.buildconfig.demo

import android.app.Application
import id.yuana.buildconfig.demo.data.DataRepository

class App : Application() {

    val repository by lazy {
        DataRepository.Impl()
    }

    override fun onCreate() {
        super.onCreate()

    }
}