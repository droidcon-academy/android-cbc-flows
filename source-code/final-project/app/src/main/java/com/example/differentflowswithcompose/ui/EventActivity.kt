package com.example.differentflowswithcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.differentflowswithcompose.R
import com.example.differentflowswithcompose.domain.dto.EventDTO
import com.example.differentflowswithcompose.ui.factory.EventViewModelFactory
import com.example.differentflowswithcompose.ui.theme.DifferentFlowsWithComposeTheme

class EventActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<EventViewModel> {
            EventViewModelFactory()
        }

        setContent {
            DifferentFlowsWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        StartEventAndAttendees(viewModel)
                        ParticipantsInfo(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun TotalAttendees(viewModel: EventViewModel) {
    Text(
        text = stringResource(R.string.total_attendees, viewModel.totalAttendees.collectAsState().value)
    )
}

@Composable
fun StartEventAndAttendees(viewModel: EventViewModel) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    viewModel.startEvent()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1F)
            ) {
                Text(text = stringResource(R.string.start_event))
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = {
                    viewModel.endEvent()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1F)
            ) {
                Text(text = stringResource(R.string.end_event))
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TotalAttendees(viewModel)
        }
    }
}

@Composable
fun ParticipantsInfo(viewModel: EventViewModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1F)
                .fillMaxHeight(),
        ) {
            val participantId = 1
            var attendingEvent by remember { mutableStateOf(false) }

            Text(text = stringResource(R.string.participant_1))
            Row {
                Button(
                    modifier = Modifier
                        .weight(1F),
                    onClick = {
                        attendingEvent = true
                        viewModel.joinEvent(participantId)
                    }
                ) {
                    Text(text = stringResource(R.string.join))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier
                        .weight(1F),
                    onClick = {
                        attendingEvent = false
                        viewModel.leaveEvent(participantId)
                    }
                ) {
                    Text(text = stringResource(R.string.leave))
                }
            }
            if (attendingEvent) {

                val state by viewModel.events.collectAsStateWithLifecycle(emptyList())
                LazyColumn {
                    items(items = state,
                        key = {
                            it.hashCode()
                        }) {
                        EventRow(it)
                    }
                }

            }
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1F)
                .fillMaxHeight()
        ) {
            val participantId = 2
            var attendingEvent by remember { mutableStateOf(false) }

            Text(text = stringResource(R.string.participant_2))
            Row {
                Button(
                    modifier = Modifier
                        .weight(1F),
                    onClick = {
                        attendingEvent = true
                        viewModel.joinEvent(participantId)
                    }
                ) {
                    Text(text = stringResource(R.string.join))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier
                        .weight(1F),
                    onClick = {
                        attendingEvent = false
                        viewModel.leaveEvent(participantId)
                    }
                ) {
                    Text(text = stringResource(R.string.leave))
                }
            }
            if (attendingEvent) {

                val state by viewModel.events.collectAsStateWithLifecycle(emptyList())
                LazyColumn {
                    items(items = state,
                        key = {
                            it.hashCode()
                        }) {
                        EventRow(it)
                    }
                }
            }
        }
    }
}

@Composable
fun EventRow(eventDTO: EventDTO) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.tertiary)
    ) {
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 2.dp)) {
            Text(
                text = eventDTO.eventName,
                modifier = Modifier
                    .weight(1F),
                fontWeight = FontWeight.Bold
            )
            Text(stringResource(R.string.by, eventDTO.eventHost))
        }
        Text(
            text = eventDTO.eventTime,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 8.dp)
                .fillMaxWidth(),
            fontSize = 10.sp
        )
    }
}
