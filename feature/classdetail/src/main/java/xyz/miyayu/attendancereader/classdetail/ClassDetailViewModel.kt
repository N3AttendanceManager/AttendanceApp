package xyz.miyayu.attendancereader.classdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.core.domain.AttendanceResourceUseCase
import xyz.miyayu.attendancereader.core.domain.model.AttendanceResources
import xyz.miyayu.attendancereader.core.network.attendances.AttendanceRepository
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import xyz.miyayu.attendancereader.model.Classifications
import xyz.miyayu.attendancereader.model.Student
import javax.inject.Inject

@HiltViewModel
class ClassDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val attendanceResourceUseCase: AttendanceResourceUseCase,
    private val attendanceRepository: AttendanceRepository
) : StatefulViewModel<Unit, UiState>(initialState = UiState.Normal) {
    val classId = savedStateHandle.get<Int>(CLASS_ID_ROUTE_ARG)!!

    private val _attendanceResources = MutableStateFlow<AttendanceResources?>(null)
    val attendanceResources = _attendanceResources.asStateFlow()

    private val _lastScannedStudents = MutableStateFlow<Student?>(null)
    val lastScannedStudents = _lastScannedStudents.asStateFlow()

    private val _lastClassification = MutableStateFlow<Classifications?>(null)
    val lastClassification = _lastClassification.asStateFlow()

    fun onCardScanned(idm: String, classifications: Classifications) {
        viewModelScope.launch {
            setUiState(UiState.Loading)
            attendanceRepository.registerAttendance(
                idm = idm,
                classId = classId,
                classificationId = classifications.id
            ).mapBoth(
                success = {
                    _lastScannedStudents.value = it
                    _lastClassification.value = classifications
                },
                failure = {}
            )
            setUiState(UiState.Normal)
            fetchAttendanceResources()
        }
    }

    fun onAttendanceManualSelected(student: Student, classifications: Classifications) {
        viewModelScope.launch {
            setUiState(UiState.Loading)
            attendanceRepository.registerManualAttendance(
                studentId = student.id,
                classId = classId,
                classificationId = classifications.id
            ).mapBoth(
                success = {
                    _lastClassification.value = classifications
                },
                failure = {}
            )
            setUiState(UiState.Normal)
            fetchAttendanceResources()
        }

    }

    private fun fetchAttendanceResources() {
        viewModelScope.launch {
            setUiState(UiState.Loading)
            attendanceResourceUseCase.execute(classId = classId)
                .mapBoth(
                    success = { _attendanceResources.value = it },
                    failure = {},
                )
            setUiState(UiState.Normal)

        }
    }

    init {
        fetchAttendanceResources()
    }

}