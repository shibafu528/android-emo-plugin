import android.content.Intent

private const val EXTRA_MUSHROOM_REPLACE_KEY = "replace_key"

interface PluginIO {
    fun getInput(): String?
    fun makeOutput(output: String?): Intent
}

class TwiccaPluginIO(private val intent: Intent) : PluginIO {
    override fun getInput(): String? = intent.getStringExtra(Intent.EXTRA_TEXT)
    override fun makeOutput(output: String?): Intent = Intent().putExtra(Intent.EXTRA_TEXT, output)
}

class MushroomPluginIO(private val intent: Intent) : PluginIO {
    override fun getInput(): String? = intent.getStringExtra(EXTRA_MUSHROOM_REPLACE_KEY)
    override fun makeOutput(output: String?): Intent = Intent().putExtra(EXTRA_MUSHROOM_REPLACE_KEY, output)
}