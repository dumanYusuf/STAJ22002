package com.atilsamancioglu.cryptocrazycompose.viewModel

import MealRepo
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.cryptocrazycompose.model.Category
import com.atilsamancioglu.cryptocrazycompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class MealCategoryViewModel @Inject constructor(
    private val repo:MealRepo
):ViewModel(){

    var categoryMealList= mutableStateOf<List<Category>>(listOf())
    var errorMessage=mutableStateOf("")
    var isLoading=mutableStateOf(false)

    init {
        loadMealListCategory()
    }

    fun loadMealListCategory(){
        viewModelScope.launch {
            isLoading.value=true
            val result=repo.getCategoryMealList()

            when(result){
                is Resource.Success->{
                    val categoryItem=result.data!!.mapIndexed { index, item ->
                        Category(item.categories[0].idCategory,item.categories[0].strCategory,item.categories[0].strCategoryThumb,item.categories[0].strCategoryThumb)
                    }as List<Category>
                    errorMessage.value=""
                    isLoading.value=true
                    categoryMealList.value +=categoryItem
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                    isLoading.value=false
                }
                else->{
                    println("viewmodel hata cıktı")
                }
            }
        }
    }


}