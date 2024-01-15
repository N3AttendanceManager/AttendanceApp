package xyz.miyayu.attendancereader.feature.classlist

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.core.network.atclass.ClassRepository
import xyz.miyayu.attendancereader.core.network.subject.SubjectRepository
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import xyz.miyayu.attendancereader.model.AtClass
import xyz.miyayu.attendancereader.model.Subject
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val subjectRepository: SubjectRepository,
    private val classRepository: ClassRepository
) : StatefulViewModel<ClassAddUiEvents, Unit>(
    initialState = Unit
) {
    private val _hash = MutableStateFlow<UUID>(UUID.randomUUID())
    val hash = _hash.asStateFlow()
    val subjectId = savedStateHandle.get<Int>(SUBJECT_ID_ARG)!!


    val department = MutableStateFlow<Subject?>(null)
    val atClassList = MutableStateFlow<List<AtClass>>(emptyList())

    init {
        fetchSubject()
        fetchClassList()
    }


    fun fetchClassList() {
        Log.d(this::class.simpleName, "fetchClassList")
        viewModelScope.launch(Dispatchers.IO) {
            classRepository.getAtClassList(subjectId = subjectId).mapBoth(
                success = {
                    atClassList.value = it
                },
                failure = { }
            )
        }
    }

    fun fetchSubject() {
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.getSubject(subjectId = subjectId).mapBoth(
                success = { department.value = it },
                failure = { }
            )
        }
    }


    fun createNewClass(selectedClassTimes: ClassTimes) {
        Log.d(this::class.simpleName, "createNewClass")
        viewModelScope.launch(Dispatchers.IO) {
            classRepository.createAtClass(
                subjectId = subjectId,
                start = selectedClassTimes.getStartDateTime(),
                end = selectedClassTimes.getEndDateTime()
            ).mapBoth(
                success = {
                    fetchClassList()
                    addUiEvents(ClassAddUiEvents.Success)
                    _hash.value = UUID.randomUUID()
                },
                failure = { addUiEvents(ClassAddUiEvents.Failed) }
            )
        }
    }
}