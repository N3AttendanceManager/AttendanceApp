package xyz.miyayu.attendancereader.feature.classlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.core.network.subject.SubjectRepository
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import xyz.miyayu.attendancereader.model.Department
import xyz.miyayu.attendancereader.model.Subject
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val subjectRepository: SubjectRepository
) : StatefulViewModel<Unit, Unit>(
    initialState = Unit
) {
    val department = MutableStateFlow<Subject?>(null)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            subjectRepository.getSubject(subjectId = subjectId).mapBoth(
                success = { department.value = it },
                failure = { }
            )
        }
    }


    val subjectId = ClazzArgs(savedStateHandle).subjectId
}