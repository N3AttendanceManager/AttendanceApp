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
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class ClassDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val attendanceResourceUseCase: AttendanceResourceUseCase
) : StatefulViewModel<Unit, Unit>(initialState = Unit) {
    val classId = savedStateHandle.get<Int>(CLASS_ID_ROUTE_ARG)!!

    private val _attendanceResources = MutableStateFlow<AttendanceResources?>(null)
    val attendanceResources = _attendanceResources.asStateFlow()

    init {
        viewModelScope.launch {
            attendanceResourceUseCase.execute(classId = classId)
                .mapBoth(
                    success = { _attendanceResources.value = it },
                    failure = {},
                )
        }
    }

}