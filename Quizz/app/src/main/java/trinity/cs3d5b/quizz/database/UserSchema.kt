package trinity.cs3d5b.quizz.database

import android.support.annotation.StringDef


class UserSchema {

    class COLUMNS {
        companion object {
            const val COLUMN_ID = "_id"
            const val COLUMN_NAME = "NAME"
            const val COLUMN_PROFILE_PICTURE_TYPE = "PROFILE_PICTURE_TYPE"
            const val COLUMN_PROFILE_PICTURE_DATA = "PROFILE_PICTURE_DATA"
            const val COLUMN_HIGH_SCORE = "HIGH_SCORE"

            @Retention(AnnotationRetention.SOURCE)
            @StringDef(PICTURE_TYPE_AVATAR, PICTURE_TYPE_UPLOAD)
            annotation class PictureType
            const val PICTURE_TYPE_AVATAR = "Avatar"
            const val PICTURE_TYPE_UPLOAD = "Upload"
        }
    }

}
