package com.example.prodigym.ui.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.prodigym.domain.entity.exercise.ExerciseEntity
import com.example.prodigym.ui.theme.ProdiGymTheme

@Composable
fun ExerciseScreen(
    modifier: Modifier = Modifier,
    viewModel: ExerciseScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    ExerciseScreenContent(
        uiState = uiState,
        onRetry = { viewModel.loadExercises() },
        modifier = modifier
    )
}

@Composable
private fun ExerciseScreenContent(
    uiState: ExerciseUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is ExerciseUiState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
        is ExerciseUiState.Success -> {
            LazyColumn(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                items(uiState.exercises) { exercise ->
                    ExerciseItem(exercise = exercise)
                }
            }
        }
        is ExerciseUiState.Error -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = uiState.message,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Button(onClick = onRetry) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}

@Composable
private fun ExerciseItem(
    exercise: ExerciseEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = exercise.gifUrl,
                contentDescription = exercise.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = exercise.bodyPart,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExerciseScreenLoadingPreview() {
    ProdiGymTheme {
        ExerciseScreenContent(
            uiState = ExerciseUiState.Loading,
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExerciseItemPreview() {
    ProdiGymTheme {
        ExerciseItem(
            exercise = ExerciseEntity(
                id = "1",
                name = "Barbell Bench Press",
                bodyPart = "chest",
                equipment = "barbell",
                gifUrl = "",
                target = "pectorals",
                secondaryMuscles = listOf("triceps", "delts"),
                instructions = listOf("Lie on bench", "Lower bar to chest", "Press up")
            )
        )
    }
}
