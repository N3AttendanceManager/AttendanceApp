package xyz.miyayu.attendancereader.feature.classlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.core.network.atclass.ClassRepository
import xyz.miyayu.attendancereader.core.network.subject.SubjectRepository
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import xyz.miyayu.attendancereader.model.AtClass
import xyz.miyayu.attendancereader.model.Department
import xyz.miyayu.attendancereader.model.Subject
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val subjectRepository: SubjectRepository,
    private val classRepository: ClassRepository
) : StatefulViewModel<ClassAddUiEvents, Unit>(
    initialState = Unit
) {
    val department = MutableStateFlow<Subject?>(null)
    val atClassList = MutableStateFlow<List<AtClass>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.getSubject(subjectId = subjectId).mapBoth(
                success = { department.value = it },
                failure = { }
            )
            classRepository.getAtClassList(subjectId = subjectId).mapBoth(
                success = { atClassList.value = it },
                failure = { }
            )

        }
    }

    fun createNewClass(selectedClassTimes: ClassTimes) {
        viewModelScope.launch(Dispatchers.IO) {
            classRepository.createAtClass(
                subjectId = subjectId,
                start = selectedClassTimes.getStartDateTime(),
                end = selectedClassTimes.getEndDateTime()
            ).mapBoth(
                success = { addUiEvents(ClassAddUiEvents.Success) },
                failure = { addUiEvents(ClassAddUiEvents.Failed) }
            )
        }
    }



    val subjectId = ClazzArgs(savedStateHandle).subjectId
}