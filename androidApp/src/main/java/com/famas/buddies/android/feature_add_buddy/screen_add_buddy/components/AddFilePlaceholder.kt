package com.famas.buddies.android.feature_add_buddy.screen_add_buddy.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.famas.buddies.android.core.theme.SpaceMedium
import com.famas.buddies.android.core.theme.SpaceSmall
import com.famas.buddies.android.core.theme.SpaceVerySmall
import com.famas.buddies.android.util.getScreenSize
import com.famas.buddies.util.ImageFile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFilesLt(
    files: List<ImageFile>,
    onAddFiles: (List<ImageFile>) -> Unit,
    onRemoveFile: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val screenSize = getScreenSize()

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
    ) { list ->
        onAddFiles(list.map { ImageFile(it, context.contentResolver) })
    }

    Column {
        Text(
            text = "Add Photos/Videos",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = SpaceVerySmall)
        )
        LazyVerticalGrid(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = SpaceVerySmall),
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.Center,
        ) {
            items(files) {
                ImageFileItem(
                    file = it.uri,
                    modifier = Modifier.aspectRatio(1f)
                )
            }

            item {
                AddFilePlaceholder {
                    launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            }
        }
    }
}

@Composable
fun ImageFileItem(file: Uri, modifier: Modifier = Modifier) {
    AsyncImage(
        model = file,
        contentDescription = null,
        modifier = modifier.background(Color.Red),
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFilePlaceholder(modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedCard(
        onClick = { onClick() }, modifier = modifier
            .aspectRatio(1f)
            .padding(start = SpaceMedium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = SpaceSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            Text(text = "Add")
        }
    }
}