package xyz.miyayu.attendancereader.feature.classlist

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class ClassAddViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : StatefulViewModel<Unit, Unit>(initialState = Unit) {
    val subjectId = ClassAddArgs(savedStateHandle).subjectId

}