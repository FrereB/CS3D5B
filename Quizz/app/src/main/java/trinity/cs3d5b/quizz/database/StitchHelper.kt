package trinity.cs3d5b.quizz.database

import android.content.Context
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import trinity.cs3d5b.quizz.R

/**
 * A class that initializes connection to the MongoDB database.
 */
class StitchHelper(private val context: Context) {

    companion object {
        private var mStitchAppClient: StitchAppClient? = null
        var mStitchAuthId: String? = null
        var mStitchServiceClient: RemoteMongoClient? = null
    }

    fun initialize(callback: Callback) {
        if (mStitchAppClient == null) {
            Stitch.initializeDefaultAppClient(context.resources.getString(R.string.stitch_app_id))
            mStitchAppClient = Stitch.getDefaultAppClient()
        }

        mStitchAppClient?.let {
            it.auth.loginWithCredential(AnonymousCredential())
                    .addOnSuccessListener { task ->
                        mStitchAuthId = task.id
                        mStitchServiceClient =
                                mStitchAppClient?.getServiceClient(RemoteMongoClient.factory,
                                        "mongodb-atlas")
                        callback.onSuccess()
                    }
                    .addOnFailureListener { callback.onFailure() }
        }
    }

    interface Callback {
        /**
         * Callback for a successful database initialization.
         */
        fun onSuccess()

        /**
         * Callback for a failed database initialization.
         */
        fun onFailure()
    }

}
