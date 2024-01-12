package xyz.miyayu.attendancereader.feature.classlist

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class ClassViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : StatefulViewModel<Unit, Unit>(
    initialState = Unit
) {
    init {
        Log.d("TEST", savedStateHandle.get<String>(SUBJECT_ID_ARG).toString())
    }

    val subjectId = ClazzArgs(savedStateHandle).subjectId
}