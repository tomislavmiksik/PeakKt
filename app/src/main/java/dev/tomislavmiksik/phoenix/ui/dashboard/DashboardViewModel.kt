package dev.tomislavmiksik.phoenix.ui.dashboard

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tomislavmiksik.phoenix.core.domain.model.HealthSnapshot
import dev.tomislavmiksik.phoenix.core.domain.repository.HealthConnectRepository
import dev.tomislavmiksik.phoenix.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val healthConnectRepository: HealthConnectRepository
) : BaseViewModel<DashboardState, DashboardEvent, DashboardAction>(
    initialState = DashboardState()
) {

    init {
        trySendAction(DashboardAction.LoadHealthData)
    }

    override fun handleAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.LoadHealthData -> handleLoadHealthData()
            is DashboardAction.RefreshData -> handleRefreshData()
            is DashboardAction.AddEntryClick -> handleAddEntryClick()
            is DashboardAction.Internal.HealthDataLoaded -> handleHealthDataLoaded(
                action.snapshot,
                action.stepsByDate
            )

            is DashboardAction.Internal.HealthDataError -> handleHealthDataError(action.message)
        }
    }

    private fun handleLoadHealthData() {
        mutableStateFlow.value = state.copy(isLoading = true, error = null)
        fetchAllHealthData()
    }

    private fun handleRefreshData() {
        mutableStateFlow.value = state.copy(isRefreshing = true, error = null)
        fetchAllHealthData()
    }

    private fun fetchAllHealthData() {
        viewModelScope.launch {
            try {
                val snapshot = healthConnectRepository.getTodaySnapshot()
                val stepsByDate = healthConnectRepository.getStepsForDateRange(
                    startDate = state.dateRange.first,
                    endDate = state.dateRange.second
                )
                sendAction(DashboardAction.Internal.HealthDataLoaded(snapshot, stepsByDate))
            } catch (e: Exception) {
                sendAction(DashboardAction.Internal.HealthDataError(e.message ?: "Unknown error"))
            }
        }
    }

    private fun handleHealthDataLoaded(
        snapshot: HealthSnapshot,
        stepsByDate: Map<LocalDate, Long>
    ) {
        mutableStateFlow.value = state.copy(
            isLoading = false,
            isRefreshing = false,
            steps = snapshot.steps,
            heartRate = snapshot.heartRate,
            weight = snapshot.weight,
            exerciseCount = snapshot.exerciseCount,
            stepsByDate = stepsByDate
        )
    }

    private fun handleHealthDataError(message: String) {
        mutableStateFlow.value = state.copy(
            isLoading = false,
            isRefreshing = false,
            error = message
        )
    }

    private fun handleAddEntryClick() {
        sendEvent(DashboardEvent.NavigateToAddEntry)
    }
}

data class DashboardState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val stepsByDate: Map<LocalDate, Long> = emptyMap(),
    val steps: Long = 0,
    val heartRate: Long = 0,
    val weight: Double = 0.0,
    val dateRange: Pair<LocalDate, LocalDate> = Pair(
        LocalDate.now().withDayOfMonth(1),
        LocalDate.now().withDayOfMonth(31)
    ),
    val exerciseCount: Int = 0
)

sealed class DashboardEvent {
    data object NavigateToAddEntry : DashboardEvent()
}

sealed class DashboardAction {
    data object LoadHealthData : DashboardAction()
    data object RefreshData : DashboardAction()
    data object AddEntryClick : DashboardAction()

    sealed class Internal : DashboardAction() {
        data class HealthDataLoaded(
            val snapshot: HealthSnapshot,
            val stepsByDate: Map<LocalDate, Long>
        ) : Internal()

        data class HealthDataError(val message: String) : Internal()
    }
}