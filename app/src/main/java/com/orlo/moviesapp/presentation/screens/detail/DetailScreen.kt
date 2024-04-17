package com.orlo.moviesapp.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.orlo.moviesapp.data.remote.MovieApi
import com.orlo.moviesapp.util.RatingBar
import com.orlo.moviesapp.util.getAverageColor

@Composable
fun DetailScreen() {

    val detailViewModel = hiltViewModel<DetailViewModel>()
    val detailScreenState = detailViewModel.detailState.collectAsState().value

    val backDropImage = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailScreenState.movie?.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val posterDropImage = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailScreenState.movie?.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .verticalScroll(rememberScrollState())
    ) {

        if (backDropImage is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
//                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = detailScreenState.movie?.title
                )
            }
        }

        if (backDropImage is AsyncImagePainter.State.Success) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                painter = backDropImage.painter,
                contentDescription = detailScreenState.movie?.title,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(240.dp)
            ) {
                if (posterDropImage is AsyncImagePainter.State.Error) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(70.dp),
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = detailScreenState.movie?.title
                        )
                    }
                }

                if (posterDropImage is AsyncImagePainter.State.Success) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)),
                        painter = posterDropImage.painter,
                        contentDescription = detailScreenState.movie?.title,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            detailScreenState.movie?.let {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = it.title,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    ) {

                        RatingBar(
                            starsModifier = Modifier.size(18.dp),
                            rating = it.vote_average / 2
                        )

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = it.vote_average.toString().take(3),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 14.sp,
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Language: " + it.original_language,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        text = "Release date: " + it.release_date
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        text = it.vote_count.toString() + " Vote"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            text = "Overview",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        detailScreenState.movie?.let {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                text = it.overview,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))


    }

}
