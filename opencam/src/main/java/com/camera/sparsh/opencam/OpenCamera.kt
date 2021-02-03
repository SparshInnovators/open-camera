package com.camera.sparsh.opencam

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.CountDownTimer
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.functions.Consumer

/**
 * Created by Rahul Sharma on 03-02-2021.
 */

open class OpenCamera {

    companion object {
        private var rxPermissions: RxPermissions? = null
        private val permissions = arrayOf(
            Manifest.permission.CAMERA
        )

        fun begin(c: Context, activity: Activity, cameraStatus: Int) {
            rxPermissions = RxPermissions(activity)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (checkAndRequestCameraPermission(c, activity)) {
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

        private fun checkAndRequestCameraPermission(context: Context, activity: Activity): Boolean {
            val result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
            if (result == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                requestPermission(context, activity)
            }

            return false
        }

        private fun requestPermission(context: Context, activity: Activity) {
            rxPermissions!!.request(
                Manifest.permission.CAMERA
            )
                .subscribe(Consumer { granted: Boolean ->
                    if (granted) {

                        /*Got all the permissions here*/
                        checkAndRequestCameraPermission(context, activity)


                    } else {
                        for (permission in permissions) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(
                                    activity,
                                    permission
                                )
                            ) {
                                AlertDialog.Builder(context)
                                    .setTitle("Error")
                                    .setMessage("We need all the permissions")
                                    .setPositiveButton(
                                        "Allow"
                                    ) { dialog: DialogInterface?, which: Int ->
                                        requestPermission(
                                            context,
                                            activity
                                        )
                                    }
                                    .setNegativeButton(
                                        "Cancel"
                                    ) { dialog: DialogInterface?, which: Int -> false }
                                    .create()
                                    .show()

                            }
                        }
                    }
                })
        }

    }


}