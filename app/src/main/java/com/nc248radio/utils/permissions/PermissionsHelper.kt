package com.nc248radio.utils.permissions

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nc248radio.R

//TODO Borrar si no se usa
/*
Se ha de instanciar en el onCreate() del Fragment, si no se hace así ->
java.lang.IllegalStateException: Fragment is attempting to  registerForActivityResult after being created.
Fragments must call registerForActivityResult() before they are created
*/
class PermissionsHelper(
    val activity: Activity,
    val fragment: Fragment
) {

    private lateinit var permissions: Array<String>
    private lateinit var onPermissionsGranted: (() -> Unit)
    private lateinit var onPermissionsRejected: (() -> Unit)
    private lateinit var permissionsInfo: String

    fun checkPermissions(
        permissions: Array<String>,
        functionGranted: () -> Unit,
        functionRejected: () -> Unit,
        permissionsInfo: String
    ) {
        this.permissions = permissions
        this.onPermissionsGranted = functionGranted
        this.onPermissionsRejected = functionRejected
        this.permissionsInfo = permissionsInfo

        if (hasPermissions()) {
            functionGranted()
        } else
            requestPermissionLauncher.launch(permissions)
    }

    private fun hasPermissions():Boolean = permissions.all {
        ContextCompat.checkSelfPermission(fragment.requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private val requestPermissionLauncher =
        fragment.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val areGranted = permissions.entries.all {
                it.value
            }

            if (areGranted) {
                if(::onPermissionsGranted.isInitialized) onPermissionsGranted()
            } else {
                if(shouldShowRequestPermissionRationale()) {
                    if(::onPermissionsRejected.isInitialized) onPermissionsRejected()
                } else
                    showSettingsDialog()
            }

        }

    private fun shouldShowRequestPermissionRationale():Boolean = permissions.all {
        ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
    }

    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(permissionsInfo)
            .setTitle(activity.getString(R.string.permissions_dialog_title))
            .setCancelable(false)
            .setPositiveButton(activity.getString(R.string.settings)) { dialog, _ ->
                dialog.dismiss()
                openSettings()
            }
            .setNegativeButton(activity.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
                if(::onPermissionsRejected.isInitialized) onPermissionsRejected()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", activity.packageName, null)
        intent.data = uri
        requestSettingsLauncher.launch(intent)
    }

    private val requestSettingsLauncher = fragment.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if(hasPermissions()) {
            if (::onPermissionsGranted.isInitialized) onPermissionsGranted()
        } else {
            if (::onPermissionsRejected.isInitialized) onPermissionsRejected()
        }
    }

}