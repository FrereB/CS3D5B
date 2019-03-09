package trinity.cs3d5b.quizz.database

import org.bson.Document
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_HIGH_SCORE
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_ID
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_NAME
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_PROFILE_PICTURE_DATA
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.COLUMN_PROFILE_PICTURE_TYPE
import trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.Companion.PictureType

class UserModel {

    var id: String
    var name: String
    var profilePictureType: String
    var profilePictureData: String
    var highScore: Int

    constructor(id: String,
                name: String,
                @PictureType profilePictureType: String,
                profilePictureData: String,
                highScore: Int) {
        this.id = id
        this.name = name
        this.profilePictureType = profilePictureType
        this.profilePictureData = profilePictureData
        this.highScore = highScore
    }

    constructor(document: Document) {
        id = document[COLUMN_ID] as String
        name = document[COLUMN_NAME] as String
        profilePictureType = document[COLUMN_PROFILE_PICTURE_TYPE] as String
        profilePictureData = document[COLUMN_PROFILE_PICTURE_DATA] as String
        highScore = document[COLUMN_HIGH_SCORE] as Int
    }

    fun toDocument() : Document {
        val document = Document()
        document[COLUMN_ID] = id
        document[COLUMN_NAME] = name
        document[COLUMN_PROFILE_PICTURE_TYPE] = profilePictureType
        document[COLUMN_PROFILE_PICTURE_DATA] = profilePictureData
        document[COLUMN_HIGH_SCORE] = highScore
        return document
    }
}
