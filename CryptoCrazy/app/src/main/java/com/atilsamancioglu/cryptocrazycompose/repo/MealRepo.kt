import com.atilsamancioglu.cryptocrazycompose.model.MealListCategory
import com.atilsamancioglu.cryptocrazycompose.servis.MealApi
import com.atilsamancioglu.cryptocrazycompose.util.Resource
import dagger.Module
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton


@ActivityScoped
class MealRepo @Inject constructor(private val api: MealApi) {

    suspend fun getCategoryMealList(): Resource<List<MealListCategory>> {
        return try {
            val response = api.getMealCategoryList()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error("Hata oluştu: ${e.localizedMessage}")
        }
    }
}
