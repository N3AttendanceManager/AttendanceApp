package xyz.miyayu.attendancereader.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> StateFlow<List<T>>.ConsumeEvent(
    eventBlock: (T) -> Unit,
    consume: (T) -> Unit
) {
    LaunchedEffect(Unit) {
        this@ConsumeEvent.collect { events ->
            events.forEach { event ->
                eventBlock.invoke(event)
                consume.invoke(event)
            }
        }
    }
}