package xyz.miyayu.attendancereader.feature.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.core.network.student.StudentRepository
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import xyz.miyayu.attendancereader.model.Student
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : StatefulViewModel<UiEvent, UiState>(initialState = UiState.Normal) {
    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students = _students.asStateFlow()
    private val _scanningStudent = MutableStateFlow<Student?>(null)
    val scanningStudent = _scanningStudent.asStateFlow()


    fun onIcFetched(idm: String) {
        scanningStudent.value?.let { student ->
            viewModelScope.launch {
                setUiState(UiState.Loading)
                studentRepository.updateStudentIc(
                    studentId = student.id,
                    icId = idm
                )
                setUiState(UiState.Normal)
                addUiEvents(UiEvent.IcScanned)
            }
        }
    }


    private fun fetchStudents() {
        viewModelScope.launch(Dispatchers.IO) {
            studentRepository.getAllStudent().mapBoth(
                success = { _students.value = it },
                failure = {})
        }
    }

    fun setStudent(student: Student) {
        _scanningStudent.value = student
    }

    init {
        Log.d(this::class.simpleName, "ViewModel Created.")
        fetchStudents()
    }
}