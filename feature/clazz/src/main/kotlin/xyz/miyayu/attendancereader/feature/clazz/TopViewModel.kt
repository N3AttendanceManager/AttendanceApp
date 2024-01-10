package xyz.miyayu.attendancereader.feature.clazz

import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.core.domain.GetSubjectAndDepartmentUseCase
import xyz.miyayu.attendancereader.designsystem.viewmodel.StatefulViewModel
import xyz.miyayu.attendancereader.feature.clazz.state.top.TopUiState
import xyz.miyayu.attendancereader.model.Department
import xyz.miyayu.attendancereader.model.Subject
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val getSubjectAndDepartmentUseCase: GetSubjectAndDepartmentUseCase
) : StatefulViewModel<Nothing, TopUiState>(
    initialState = TopUiState.Nothing
) {
    private val _subjects = MutableStateFlow<List<Subject>>(emptyList())
    val subjects = _subjects.asStateFlow()

    private val _departments = MutableStateFlow<List<Department>>(emptyList())
    val departments = _departments.asStateFlow()

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getSubjectAndDepartmentUseCase.execute().mapBoth(
                success = { pair ->
                    _subjects.value = pair.subjects
                    _departments.value = pair.departments
                },
                failure = {}
            )
        }
    }
}