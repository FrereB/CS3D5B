package trinity.cs3d5b.quizz.database

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import java.io.ByteArrayOutputStream

class PictureEncoder {

    fun convertUriToBitmap(context: Context, uri: Uri): Bitmap {
        //All the path of the picture from the user phone
        val filePathCol = arrayOf(MediaStore.Images.Media.DATA)

        //Cursor to access to the path of the picture
        val cursor = context.contentResolver.query(uri, filePathCol, null, null, null)
        cursor?.moveToFirst()

        //We recover the path of the picture

        val columIndex = cursor!!.getColumnIndex(filePathCol[0])
        val imgPath = cursor?.getString(columIndex)
        cursor?.close()
        //get the Image
        return BitmapFactory.decodeFile(imgPath)

        // This is rubbish.
    }

    fun encodeBitmapToBase64(bitmap: Bitmap): String {
        val os = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, os)
        val byteArray = os.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun decodeBase64ToBitmap(base64: String): Bitmap {
        val decodedBase64 = Base64.decode(base64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBase64, 0, decodedBase64.size)
    }

}
