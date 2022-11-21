/*
 * Copyright (c) 2022
 * David de Andrés and Juan Carlos Ruiz
 * Development of apps for mobile devices
 * Universitat Politècnica de València
 */

package upv.dadm.ex04_eventsandintents

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import upv.dadm.ex04_eventsandintents.databinding.ActivityMainBinding

// Constants for exchanging information between activities
const val STRING_MESSAGE = "upv.dadm.ex04_eventsandintents.MESSAGE"
const val STRING_RESULT = "upv.dadm.ex04_eventsandintents.RESULT"

/**
 * Displays several Buttons that let the user launch some explicit and implicit Intents.
 */
class MainActivity : AppCompatActivity() {

    // Register a callback to execute whenever the activity launched returns a result
    private val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // We can also check the result code provided by the returning activity
            if (result.resultCode == RESULT_OK) {
                // Display the  message received or a default one if none
                Toast.makeText(
                    this@MainActivity,
                    result.data?.getStringExtra(STRING_RESULT) ?: getString(R.string.no_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the automatically generated view binding for the layout resource
        val binding = ActivityMainBinding.inflate(layoutInflater)
        // Set the activity content to the root element of the generated view
        setContentView(binding.root)

        // Set OnClickListener for all Buttons
        binding.bShowNotification.setOnClickListener { displayNotification() }
        binding.bExplicitIntent.setOnClickListener { launchExplicitIntent() }
        binding.bExplicitIntentWithParameter.setOnClickListener { launchExplicitIntentWithParameter() }
        binding.bExplicitIntentForResult.setOnClickListener { launchExplicitIntentForResult() }
        binding.bImplicitIntentSystemChooses.setOnClickListener { launchImplicitIntentSystemChooses() }
        binding.bImplicitIntentUserChooses.setOnClickListener { launchImplicitIntentUserChooses() }
    }

    // Display a quick little message in a popup
    private fun displayNotification() {
        Toast.makeText(
            this@MainActivity, R.string.notification_message, Toast.LENGTH_SHORT
        ).show()
    }

    // Launch an activity, selected by the user, to navigate to ETSINF's location
    private fun launchImplicitIntentUserChooses() {
        // Intent to navigate to ETSINF's location
        val intent = Intent()
            .setAction(ACTION_VIEW)
            .setData(Uri.parse("geo:39.4819305,-0.3469791?z=18")) // geo:latitude,longitude?z=zoom

        // Create a chooser for the user to select the application to handle the Intent
        val chooser = Intent.createChooser(intent, getString(R.string.chooser_message))

        // Check that there is an activity that can handle this intent
        if (chooser.resolveActivity(packageManager) != null) {
            startActivity(chooser)
        }
        // If not, then show an error message to the user
        else {
            Toast.makeText(
                this@MainActivity, R.string.error_message, Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Launch an activity, selected by the system, to navigate to ETSINF's location
    private fun launchImplicitIntentSystemChooses() {
        // Intent to navigate to ETSINF's location
        val intent = Intent()
            .setAction(ACTION_VIEW)
            .setData(Uri.parse("geo:39.4819305,-0.3469791?z=18")) // geo:latitude,longitude?z=zoom

        // Check that there is an activity that can handle this intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
        // If not, then show an error message to the user
        else {
            Toast.makeText(
                this@MainActivity, R.string.error_message, Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Launch the new activity and wait for a result
    private fun launchExplicitIntentForResult() {
        launcher.launch(Intent(this, ForResultActivity::class.java))
    }

    // Launch the new activity passing a parameter within the Intent
    private fun launchExplicitIntentWithParameter() {
        startActivity(
            Intent(this, ExplicitIntentActivity::class.java).putExtra(
                STRING_MESSAGE,
                "Hello, this is David"
            )
        )
    }

    // Nothing special, just launch the new activity
    private fun launchExplicitIntent() {
        startActivity(Intent(this, ExplicitIntentActivity::class.java))
    }

}