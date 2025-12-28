package com.cacaosd.platform.core.permission

import androidx.compose.runtime.Composable

@Composable
actual fun PermissionHandler(
    permission: Permission,
    content: @Composable (PermissionStatus) -> Unit
) {
    content(PermissionStatus.Granted)
}


actual fun getPostNotificationsPermission(): Permission = object :
    Permission {
    override val permissionName: String
        get() = ""
}