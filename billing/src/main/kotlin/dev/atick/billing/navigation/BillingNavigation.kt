/*
 * Copyright 2024 Atick Faisal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.atick.billing.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.atick.billing.ui.BillingRoute
import kotlinx.serialization.Serializable

/**
 * Object for the billing screen destination.
 */
@Serializable
data object Billing

/**
 * Navigates to the billing screen.
 *
 * @param navOptions The navigation options.
 */
fun NavController.navigateToBilling(navOptions: NavOptions? = null) {
    navigate(Billing, navOptions)
}

/**
 * Builds the billing screen.
 *
 * @param onBackClick The on back click callback.
 * @param onShowSnackbar The on show snackbar callback.
 */
fun NavGraphBuilder.billingScreen(
    onBackClick: () -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable<Billing> {
        BillingRoute(
            onBackClick = onBackClick,
            onShowSnackbar = onShowSnackbar,
        )
    }
}
