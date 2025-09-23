import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodrecommendationapp.R

class FoodViewModel(aList: List<String>) : ViewModel() {

    // Properly initialized to something valid
    var currentFood by mutableStateOf("Coffee")
    var currentImage by mutableIntStateOf(R.drawable.coffee)

    // Function to pick a new random food + update image
    fun rerollFood(foodNames: Array<String>) {
        val newFood = foodNames.random()
        currentFood = newFood

        currentImage = when (newFood) {
            "Croissant" -> R.drawable.croissant
            "Breakfast Wrap" -> R.drawable.breakfast_wrap
            "Ice Cream" -> R.drawable.ice_cream
            "Coffee" -> R.drawable.coffee
            "Tea" -> R.drawable.tea
            "Milkshake" -> R.drawable.milkshake
            else -> R.drawable.random_food
        }
    }
}
