package za.ac.iie.imad_project


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)

        button.setOnClickListener {
            try {
                val editText: EditText = findViewById(R.id.editTextText)
                val inputTime = editText.text.toString().toInt()
                when {
                    inputTime !in 0..23 -> showError("Invalid hour (0-23)")
                    else -> displaySuggestion(generateMealSuggestion(inputTime))
                }
            } catch (e: Exception) {
                showError("Invalid number format, please enter an hour between o and 23")
            }
        }

        button2.setOnClickListener {
            val editText: EditText = findViewById(R.id.editTextText)
            editText.text.clear()
            val textView: TextView =findViewById(R.id.textView2)
            textView.text = "Enter hour (0-23)"
            button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
            handleMealSuggestion()
        }
        button2.setOnClickListener {
            resetAppState()
        }
    }

    private fun resetAppState() {
        val defaultMessage = "hello again! please enter an hour between 0 and 23 to get a meal suggestion."
        val editText: EditText = findViewById(R.id.editTextText)
        editText.text.clear()
        val textView: TextView = findViewById(R.id.textView2)
        textView.text = defaultMessage
        val button: Button = findViewById(R.id.button)
        button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.background_light))
    }

    private fun handleMealSuggestion() {



        val editText: EditText = findViewById(R.id.editTextText)
        val inputTimeStr = editText.text.toString().trim()
        val inputTime = inputTimeStr.toIntOrNull() ?:run {
            showError("Invalid number format , please try an hour between 0 and 23)")
            return
        }
        val suggestion = generateMealSuggestion (inputTime)
        displaySuggestion(suggestion)
    }

    private fun generateMealSuggestion(hour: Int): String {
        val mealTime = when (hour) {
            in 5..9 -> "Breakfast"
            in 10..11 -> "Mid-morning Snack"
            in 12..14 -> "Lunch"
            in 15..16 -> "Mid-afternoon Snack"
            in 17..19 -> "Dinner"
            else -> "Late-night Snack"
        }

        return when (mealTime) {
            "Breakfast" -> " Pancakes with maple syrup and fresh berries."
            "Mid-morning Snack" -> " Mixed nuts and dried fruits."
            "Lunch" -> " Chicken noodle soup with crusty bread."
            "Mid-afternoon Snack" -> " Blueberry muffin and green tea."
            "Dinner" -> " Chicken curry with rice and naan."
            else -> " Warm milk with cookies."
        }
    }


    private fun displaySuggestion(suggestion: String) {

        val textView: TextView = findViewById(R.id.textView2)
        val button: Button = findViewById(R.id.button)
        textView.text = "We suggest you : $suggestion"
        button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
    }

    private fun showError(message: String) {

        val textView: TextView =findViewById(R.id.textView2)
        textView.text = message
        val button: Button = findViewById(R.id.button)
        button.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
    }
}



