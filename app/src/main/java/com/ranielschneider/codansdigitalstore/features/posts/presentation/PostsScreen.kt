package com.ranielschneider.codansdigitalstore.features.posts.presentation

import com.ranielschneider.codansdigitalstore.features.posts.presentation.PostViewModel
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ranielschneider.codansdigitalstore.features.posts.domain.Post
import com.ranielschneider.codansdigitalstore.features.users.domain.User
import com.ranielschneider.codansdigitalstore.ui.theme.CodansDigitalStoreTheme
import coil.compose.AsyncImage

@Composable
fun PostsScreen(
    viewModel: PostViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getPosts()
    }

    PostsScreenContent(
        posts = viewModel.posts,
        users = viewModel.users,
        onBackClick = onBackClick,
        onRefresh = {
            viewModel.getPosts()
        }
    )
}

@Composable
fun PostsScreenContent(
    posts: List<Post>,
    users: List<User>,
    onBackClick: () -> Unit,
    onRefresh: () -> Unit
) {
    var searchText by remember {
        mutableStateOf("")
    }

    val filteredPosts = posts.filter { post ->
        post.title.contains(searchText, ignoreCase = true) ||
                post.body.contains(searchText, ignoreCase = true)
    }

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.surface
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 18.dp,
                    vertical = 16.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Voltar",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Postagens",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "Confira as últimas postagens",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.65f
                        )
                    )
                }

                IconButton(
                    onClick = onRefresh
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = "Atualizar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(18.dp),
                placeholder = {
                    Text(
                        text = "Buscar por título ou conteúdo..."
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Pesquisar"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.35f
                    )
                )
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = filteredPosts,
                    key = { post -> post.id }
                ) { post ->
                    PostCard(
                        post = post,
                        user = users.find { it.id == post.userId }
                    )
                }
            }
        }
    }
}

@Composable
fun PostCard(
    post: Post,
    user: User?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.15f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = user?.image,
                    contentDescription = "Foto de ${user?.nome}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }

            Spacer(
                modifier = Modifier.size(14.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = user?.nome ?: "Usuário ${post.userId}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "Postagem ${post.id}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.6f
                            )
                        )
                    }

                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = post.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = post.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.7f
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PostsScreenPreview() {
    CodansDigitalStoreTheme {
        PostsScreenContent(
            posts = listOf(
                Post(
                    id = 1,
                    title = "AW EDAWD SFEDFSEFSEFSF",
                    body = " AWCAWFAWFAWFDAW AWFAW FAWFFG RGSDRG DRGDRGD RGAEWRWFA GRFG ",
                    userId = 1
                ),
                Post(
                    id = 2,
                    title = "D AWDAD WDAZDAWDAWDAWD AWDAWDAWD FAWFAWF AWFAW FAWF AWFAWF ",
                    body = " awfAFAWDAWD AWDA WD, AWDA WDA DAWD AWD AWD AWD AWD AWD AWD AQ2WAW3EQE AWD AWD",
                    userId = 2
                ),
                Post(
                    id = 3,
                    title = "EAWE AWE AWE AWD AWD  EAWE AWE AWEA ",
                    body = "EAWEAWEAWEAW EAWEAW R4R 43R3 4R34 34R3 4R3 4R3 4R3 43RAWDA WD",
                    userId = 3
                ),
                Post(
                    id = 4,
                    title = "MiEAWE",
                    body = "EAWEAWEAWE EAWEAW EAWE AWE AWE E 2EA2E 2A ",
                    userId = 4
                )
            ),
            users = emptyList(),
            onBackClick = {},
            onRefresh = {}
        )
    }
}