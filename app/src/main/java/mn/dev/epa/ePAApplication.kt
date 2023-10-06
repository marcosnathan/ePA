package mn.dev.epa

import android.app.Application
import mn.dev.epa.data.AppContainer
import mn.dev.epa.data.AppContainerImpl

class ePAApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}