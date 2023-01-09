package com.famas.buddies.android.feature_feed.feed_list.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.famas.buddies.android.core.theme.SpaceSemiSmall
import com.famas.buddies.android.core.theme.SpaceSmall
import com.famas.buddies.android.core.theme.SpaceVerySmall
import com.famas.buddies.feature_add_buddy.add_buddy_details.interactors.Gender
import com.famas.buddies.feature_feed.feed_list.domain.model.BuddyDto
import com.google.accompanist.flowlayout.FlowRow
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuddyListItem(buddy: BuddyDto, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Log.d("myTag", buddy.files.toString())
    Card(modifier = modifier.fillMaxWidth(), onClick = {
        onClick()
    }) {
//        LazyRow {
//            items(buddy.files) {
//
//            }
//        }

        Row {
            AsyncImage(
                model = buddy.files.firstOrNull(),
                contentDescription = null,
                modifier = Modifier
                    .weight(0.4f)
                    .height(130.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(SpaceSemiSmall)
            ) {
                Row {
                    Text(
                        text = buddy.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(
                        text = "(breed)",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .alignByBaseline()
                            .padding(start = SpaceVerySmall)
                    )
                }

//                FlowRow(
//                    modifier = Modifier.padding(top = SpaceSemiSmall),
//                    crossAxisSpacing = SpaceVerySmall,
//                    mainAxisSpacing = SpaceSemiSmall
//                ) {
                    OutlinedCard(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                        Text(
                            text = "${
                                Gender.values().firstOrNull { it.id == buddy.gender }?.name ?: "N/A"
                            }, ${buddy.age} Years",
                            modifier = Modifier.padding(
                                vertical = SpaceVerySmall,
                                horizontal = SpaceSmall
                            ),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
//                }

                Row(modifier = Modifier.padding(top = SpaceSemiSmall)) {
                    Text(
                        text = "FROM: ",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(
                        text = "Username",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.alignByBaseline()
                    )
                }

                Row(modifier = Modifier.padding(top = SpaceSemiSmall)) {
                    Text(
                        text = "Uploaded at: ",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(
                        text = SimpleDateFormat(
                            "DD-MM-yyyy",
                            Locale.ROOT
                        ).format(Date(buddy.created_at)),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.alignByBaseline()
                    )
                }
            }
        }

    }
}