package com.camera.sparsh.opencam

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


/**
 * Created by Rahul Sharma on 03-02-2021.
 */
open class OpenCamera {

    companion object {
        public val CAMERA_REQUEST_CODE = 2001
        fun begin(c: Context, activity: Activity, cameraStatus: Int) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (checkAndRequestCameraPermission(activity)) {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    /* capture the result in the activity whose object is passed to this function */
                    activity.startActivityForResult(cameraIntent, cameraStatus)
                } else {
                    Toast.makeText(
                        activity.applicationContext,
                        "Permission not granted",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        private fun checkAndRequestCameraPermission(activity: Activity): Boolean {
            val result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
            if (result == PackageManager.PERMISSION_GRANTED) {
                return true
            }
            return false
        }

    }


}