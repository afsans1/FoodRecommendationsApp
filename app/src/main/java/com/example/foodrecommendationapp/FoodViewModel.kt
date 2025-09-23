import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodrecommendationapp.MenuItem
import com.example.foodrecommendationapp.R

class FoodViewModel(foodNames: Array<String>) : ViewModel() {

    fun getItems(items: Array<String>): List<String>{
        return items.toMutableList()
    }
    fun getImages() : List<Int>{
        val foodImages = mutableListOf<Int>()
        foodImages.add(R.drawable.croissant)
        foodImages.add(R.drawable.breakfast_wrap)
        foodImages.add(R.drawable.ice_cream)
        foodImages.add(R.drawable.coffee)
        foodImages.add(R.drawable.tea)
        foodImages.add(R.drawable.milkshake)
        return foodImages
    }
    fun getMenuItems(items: Array<String>) : List<MenuItem>{
        val images = getImages()
        val foodItems = mutableListOf<MenuItem>()
        items.forEachIndexed { index, name ->
            var image = 0
            if (index < images.size) {
                image = images[index]
            } else {
                image = R.drawable.random_food
            }
            foodItems.add(MenuItem(name.toString(), image))
        }
        return foodItems
    }
    // Properly initialized to something valid
    var currentFood by mutableStateOf("Coffee")
    var currentImage by mutableIntStateOf(R.drawable.coffee)
    var foodItems by mutableStateOf(getMenuItems(foodNames).toMutableList())
    var i by mutableIntStateOf(0)

    fun rerollFood() {
        if(foodItems.isNotEmpty()) {
            val newFood = foodItems.random()
            currentFood = newFood.food_name

            currentImage = when (newFood.food_name) {
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

    fun updateFoodList(addedFood: String) {
        if (i < foodItems.size) {
            val newList = foodItems.toMutableList()
            newList[i] = MenuItem(addedFood, R.drawable.random_food)
            foodItems = newList
            i += 1
            //validated so it adds from the top again
            if (i == foodItems.size) i = 0
        }
    }

    fun removeButton(foodName: String){
        //validation so that there is at least on food item
        if(foodItems.size > 1) {
            val newList = foodItems.filter { it.food_name != foodName }
            foodItems = newList.toMutableList()
        }
    }

}
