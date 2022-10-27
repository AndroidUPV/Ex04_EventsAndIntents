/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex04_eventsandintents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import upv.dadm.ex04_eventsandintents.databinding.ActivitySecondaryBinding

/**
 * Displays the message received as parameter or a default message if none is received.
 */
class ExplicitIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        val binding = ActivitySecondaryBinding.inflate(layoutInflater)
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)

        // Get the value (String) of the parameter "message" passed within the Intent
        val message : String? = intent.getStringExtra(STRING_MESSAGE)
        // Display this or the default message on the TextView
        binding.tvMessage.text = message ?: getString(R.string.explicit_intent_message)
    }
}