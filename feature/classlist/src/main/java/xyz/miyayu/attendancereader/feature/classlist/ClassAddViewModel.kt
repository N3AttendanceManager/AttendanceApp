package xyz.miyayu.attendancereader.feature.classlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.core.network.atclass.ClassRepository
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class ClassAddViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val classRepository: ClassRepository
) : StatefulViewModel<ClassAddUiEvents, Unit>(initialState = Unit) {
    private val subjectId = ClassAddArgs(savedStateHandle).subjectId
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


}