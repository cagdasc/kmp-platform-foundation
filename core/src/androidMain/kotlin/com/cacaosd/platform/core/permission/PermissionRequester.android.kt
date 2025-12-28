@file:OptIn(ExperimentalCoroutinesApi::class)

package com.cacaosd.platform.core.permission

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
actual fun PermissionHandler(
    permission: Permission,
    content: @Composable (PermissionStatus) -> Unit
) {
    val context = LocalContext.current
    var permissionStatus by remember { mutableStateOf<PermissionStatus?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionStatus = if (isGranted) {
            PermissionStatus.Granted
        } else {
            PermissionStatus.Denied
        }
    }

    LaunchedEffect(permission) {
        val isGranted = ContextCompat.checkSelfPermission(
            context,
            permission.permissionName
        ) == PackageManager.PERMISSION_GRANTED

        if (isGranted) {
            permissionStatus = PermissionStatus.Granted
        } else {
            permissionLauncher.launch(permission.permissionName)
        }
    }

    permissionStatus?.let { content(it) }
}

actual fun getPostNotificationsPermission(): Permission = object : Permission {
    override val permissionName: String
        get() = "android.permission.POST_NOTIFICATIONS"
}