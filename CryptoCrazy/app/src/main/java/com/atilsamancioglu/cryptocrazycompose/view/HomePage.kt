package com.atilsamancioglu.cryptocrazycompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.atilsamancioglu.cryptocrazycompose.viewModel.MealCategoryViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(viewModel: MealCategoryViewModel= hiltViewModel()){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Meal List")})
        },
        content = {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
             items(
                 count = viewModel.categoryMealList.value.size,
                 itemContent = {index->
                     val mealCategory = viewModel.categoryMealList.value[index]
                     Text(text = mealCategory.strCategory)
                 }
             )
            }
        }
    )
}