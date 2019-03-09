package trinity.cs3d5b.quizz.authentication

import trinity.cs3d5b.quizz.database.UserModel

/**
 * This class caches the logged in user. Only one user should be logged in
 * at any given time. The user must logout at the end of its instance.
 */
class AuthCache {

    companion object {
        var userModel: UserModel? = null

        fun login(userModel: UserModel) {
            this.userModel = userModel
        }

        fun logout() {
            userModel = null
        }

        fun isLoggedIn() = userModel != null
    }

}
