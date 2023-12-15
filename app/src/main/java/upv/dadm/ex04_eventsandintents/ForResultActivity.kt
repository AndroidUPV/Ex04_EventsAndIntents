/*
 * Copyright (c) 2022-2023 Universitat Politècnica de València
 * Authors: David de Andrés and Juan Carlos Ruiz
 *          Fault-Tolerant Systems
 *          Instituto ITACA
 *          Universitat Politècnica de València
 *
 * Distributed under MIT license
 * (See accompanying file LICENSE.txt)
 */

package upv.dadm.ex04_eventsandintents

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import upv.dadm.ex04_eventsandintents.databinding.ActivitySecondaryBinding

/**
 * Displays a default message and returns a message.
 */
class ForResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        val binding = ActivitySecondaryBinding.inflate(layoutInflater)
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)

        // Add a callback to be executed when the user presses the Back button
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Create a new Intent and include the result to return
                // Set this Intent as the result to be returned by the activity
                setResult(RESULT_OK, Intent().putExtra(STRING_RESULT, "Incoming result!"))
                // Destroy the activity
                finish()
            }
        })

        // Displays the default message on the TextView
        binding.tvMessage.setText(R.string.for_result_message)
    }
}