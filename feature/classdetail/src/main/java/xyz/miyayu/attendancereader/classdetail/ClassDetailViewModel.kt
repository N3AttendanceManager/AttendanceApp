package xyz.miyayu.attendancereader.classdetail

import dagger.hilt.android.lifecycle.HiltViewModel
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class ClassDetailViewModel @Inject constructor() :
    StatefulViewModel<Unit, Unit>(initialState = Unit) {

}