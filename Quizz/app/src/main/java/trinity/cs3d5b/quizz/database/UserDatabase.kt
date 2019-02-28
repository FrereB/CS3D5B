package trinity.cs3d5b.quizz.database

import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection
import org.bson.Document
import trinity.cs3d5b.quizz.database.StitchHelper.Companion.mStitchAuthId
import trinity.cs3d5b.quizz.database.StitchHelper.Companion.mStitchServiceClient
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_HIGH_SCORE
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_ID

/**
 * This class provides an interface for interacting with the User database.
 */
class UserDatabase {

    companion object {
        private var mDbCollection: RemoteMongoCollection<Document>? = null
        private const val AUTH_ID = "owner_id"

        private fun appendAuthId(document: Document) {
            document[AUTH_ID] = mStitchAuthId
        }
    }

    init {
        mDbCollection = mStitchServiceClient?.getDatabase("database_user")
                ?.getCollection("users")
    }

    fun insert(userModel: UserModel, callback: Callback?) {
        mDbCollection?.let {
            val document = userModel.toDocument()
            appendAuthId(document)

            it.insertOne(document)
                    .addOnSuccessListener {
                        callback?.onSuccess()
                    }
                    .addOnFailureListener {
                        callback?.onFailure()
                    }

        } ?: run {
            callback?.onFailure()
        }
    }

    fun delete(userId: String, callback: Callback?) {
        mDbCollection?.let {
            val filter = Document()
            filter[COLUMN_ID] = userId
            appendAuthId(filter)

            it.deleteOne(filter)
                    .addOnSuccessListener { callback?.onSuccess() }
                    .addOnFailureListener { callback?.onFailure() }

        } ?: run {
            callback?.onFailure()
        }
    }

    fun update(userId: String, newUserModel: UserModel, callback: Callback?) {
        mDbCollection?.let {
            val filter = Document()
            filter[COLUMN_ID] = userId
            appendAuthId(filter)

            val document = newUserModel.toDocument()
            appendAuthId(document)

            it.updateOne(filter, document)
                    .addOnSuccessListener { callback?.onSuccess() }
                    .addOnFailureListener { callback?.onFailure() }

        } ?: run {
            callback?.onFailure()
        }
    }

    fun retrieveAll(callback: RetrieveCallback) {
        mDbCollection?.let {
            val queryResult: ArrayList<Document> = ArrayList()

            val query = it.find().sort(Document(COLUMN_HIGH_SCORE, -1))
            query.into(queryResult)
                    .addOnSuccessListener {
                        // Convert the documents to UserModels
                        val userList: ArrayList<UserModel> = ArrayList()

                        queryResult.forEach { queryDocument ->
                            userList.add(UserModel(queryDocument))
                        }

                        callback.onSuccess(userList)
                    }
                    .addOnFailureListener { callback.onFailure() }

        } ?: run {
            callback.onFailure()
        }
    }

    interface Callback {
        /**
         * Callback for a successful database operation.
         */
        fun onSuccess()

        /**
         * Callback for a failed database operation.
         */
        fun onFailure()
    }

    interface RetrieveCallback {
        /**
         * Callback for a successful retrieve database operation.
         */
        fun onSuccess(userList: ArrayList<UserModel>)

        /**
         * Callback for a failed retrieve database operation.
         */
        fun onFailure()
    }
}
