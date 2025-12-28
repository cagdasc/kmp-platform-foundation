package com.cacaosd.platform.core.permission

import androidx.compose.runtime.Composable

sealed class PermissionStatus {
    object Granted : PermissionStatus()
    object Denied : PermissionStatus()
}

@Composable
expect fun PermissionHandler(
    permission: Permission,
    content: @Composable (PermissionStatus) -> Unit
)

interface Permission {
    val permissionName: String
}

expect fun getPostNotificationsPermission(): Permission
