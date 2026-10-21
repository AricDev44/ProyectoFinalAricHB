package com.example.proyectofinalarichb.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import java.util.Locale

data class RestaurantUiState(
    val userLocation: LatLng? = null,
    val locationAddress: String = "Obteniendo ubicación...",
    val hasLocationPermission: Boolean = false,
)

class CloseRestaurantsViewModel : ViewModel() {

    private val _userLocation = MutableStateFlow<LatLng?>(null)

    private val _hasLocationPermission = MutableStateFlow(false)


    val uiState: StateFlow<RestaurantUiState> = combine(
        _userLocation,
        _hasLocationPermission
    ) { location, permissionGranted ->
        val address = if (location != null) {
            "Lat: ${String.format(Locale.US, "%.4f", location.latitude)}, Lng: ${String.format(Locale.US, "%.4f", location.longitude)}"
        } else if (permissionGranted) {
            "Obteniendo ubicación actual..."
        } else {
            "Permiso de ubicación no concedido"
        }
        RestaurantUiState(
            userLocation = location,
            locationAddress = address,
            hasLocationPermission = permissionGranted
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = RestaurantUiState()
    )

    fun updateLocationPermission(isGranted: Boolean) {
        _hasLocationPermission.value = isGranted
    }

    fun updateUserLocation(latLng: LatLng) {
        _userLocation.value = latLng
    }
}