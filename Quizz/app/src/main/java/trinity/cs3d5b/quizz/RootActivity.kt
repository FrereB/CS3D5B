package trinity.cs3d5b.quizz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import trinity.cs3d5b.quizz.database.StitchHelper

/**
 * A root activity that is mainly used to initialize components critical for the app.
 * Caution: For optimal user experience, the loading time should be kept to a minimum.
 */
class RootActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        val stitchHelper = StitchHelper(this)
        stitchHelper.initialize(StitchCallback(this))
    }

    class StitchCallback(private val activity: Activity) : StitchHelper.Callback {
        override fun onSuccess() {
            val startIntent = Intent(activity, LoginPage::class.java)
            startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(startIntent)
        }

        override fun onFailure() {
            activity.finish()
        }
    }
}
