package trinity.cs3d5b.quizz.database

import org.bson.Document
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_HIGH_SCORE
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_ID
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_NAME
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_PROFILE_PICTURE

class UserModel {

    var id: String
    var name: String
    var profilePicture: String
    var highScore: Int

    constructor(id: String, name: String, profilePicture: String, highScore: Int) {
        this.id = id
        this.name = name
        this.profilePicture = profilePicture
        this.highScore = highScore
    }

    constructor(document: Document) {
        id = document[COLUMN_ID] as String
        name = document[COLUMN_NAME] as String
        profilePicture = document[COLUMN_PROFILE_PICTURE] as String
        highScore = document[COLUMN_HIGH_SCORE] as Int
    }

    fun toDocument() : Document {
        val document = Document()
        document[COLUMN_ID] = id
        document[COLUMN_NAME] = name
        document[COLUMN_PROFILE_PICTURE] = profilePicture
        document[COLUMN_HIGH_SCORE] = highScore
        return document
    }
}
