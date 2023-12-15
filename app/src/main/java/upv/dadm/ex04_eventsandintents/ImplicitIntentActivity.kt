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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import upv.dadm.ex04_eventsandintents.databinding.ActivitySecondaryBinding

/**
 * Displays a default message and it is started by an implicit Intent.
 */
class ImplicitIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        val binding = ActivitySecondaryBinding.inflate(layoutInflater)
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)

        // Displays the default message on the TextView
        binding.tvMessage.setText(R.string.implicit_intent_message)
    }
}