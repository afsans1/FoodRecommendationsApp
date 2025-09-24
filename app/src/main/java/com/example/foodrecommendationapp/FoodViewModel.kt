import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.foodrecommendationapp.MenuItem
import com.example.foodrecommendationapp.R

class FoodViewModel(foodNames: Array<String>, foodImages: Array<String>) : ViewModel() {
    
//    this method creates the list of MenuItems
//    by assigning the correct image based on the menu item name
//    both lists of images and names contain the same items in order so they
//    get assigned to each other in the right order
    fun getMenuItems(foodNames: Array<String>, foodImages: Array<String>) : List<MenuItem>{
        val foodItems = mutableListOf<MenuItem>()
        for(i in 0..foodNames.size){
            foodItems.add(MenuItem(foodNames[i], foodImages[i]))
        }
        return foodItems
    }
    // Properly initialized to something valid
    // I decided to make the initial value to croissant since
    // that food item has the same name in both english and french so there
    // arent any discrepancies between the name of the item and the language
    // the device is set to
    var currentFood by mutableStateOf("Croissant")
    var currentImage by mutableIntStateOf(R.drawable.croissant)
    var foodItems by mutableStateOf(foodNames.toMutableList())


    //this method randomly changes what the current item is by
    //randomly getting an item from the array of MenuItems and
    //and assigning it the right image to it based on the name(in english or in french)
    fun rerollFood() {
        if(foodItems.isNotEmpty()) {
            val newFood = foodItems.random()
            currentFood = newFood.toString()

            //have translation to get the correct image based on the foodname
            currentImage = when (newFood.toString()) {
                "Croissant" -> R.drawable.croissant
                "Breakfast Wrap", "Wrap petit-déjeuner" -> R.drawable.breakfast_wrap
                "Ice Cream", "Crème glacée" -> R.drawable.ice_cream
                "Coffee", "Café" -> R.drawable.coffee
                "Tea", "Thé" -> R.drawable.tea
                "Milkshake", "Lait Frappé" -> R.drawable.milkshake
                else -> R.drawable.random_food
            }
        }
    }

    //this method creates a new list based on foodItems. It creates
    //a new menu item with the string name of the food the user added
    //and the default image for the added foods and adds it to that list.
    //The foodItems list is then assigned to be the new modified list of menu items
    fun updateFoodList(addedFood: String) {
        val newList = foodItems.toMutableList()
        newList.add(MenuItem(addedFood, R.drawable.random_food.toString()).toString())
        if(foodItems.size >= 6 ){
            foodItems.drop(0)
        }
        foodItems = newList
    }

    //this method stores all the menu items that are not
    //the one that is to be removed in a new list
    //and foodItems is assigned to be that list that it turned into
    //a mutable list
    fun removeButton(foodName: String){
        //validation so that there is at least on food item
        if(foodItems.size > 1) {
            val newList = foodItems.filter { it.toString() != foodName }
            foodItems = newList.toMutableList()
        }
    }



}
