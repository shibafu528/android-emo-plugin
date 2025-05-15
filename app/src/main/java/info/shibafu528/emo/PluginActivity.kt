package info.shibafu528.emo

import MushroomPluginIO
import PluginIO
import TwiccaPluginIO
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import info.shibafu528.emo.databinding.PluginBinding

class PluginActivity : AppCompatActivity() {
    private lateinit var binding: PluginBinding
    private lateinit var pluginIO: PluginIO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PluginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ITEMS)
        binding.listview.setOnItemClickListener { parent, view, position, id ->
            val style = Style.entries[position + 1]
            val input = pluginIO.getInput()
            val output = Emotional.textToEmotional(input!!, style)
            if (input != output) {
                setResult(RESULT_OK, pluginIO.makeOutput(output))
            }
            finish()
        }

        pluginIO = when (intent.action) {
            "jp.r246.twicca.ACTION_EDIT_TWEET" -> TwiccaPluginIO(intent)
            "com.adamrocker.android.simeji.ACTION_INTERCEPT" -> MushroomPluginIO(intent)
            else -> throw RuntimeException("invalid action")
        }

        if (pluginIO.getInput().isNullOrEmpty()) {
            Toast.makeText(this@PluginActivity, "input is empty", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
    }

    companion object {
        private val ITEMS = listOf(
            Emotional.textToEmotional("Serif (bold)", Style.BOLD_SERIF),
            Emotional.textToEmotional("Serif (italic)", Style.ITALIC_SERIF),
            Emotional.textToEmotional("Serif (bold italic)", Style.BOLD_ITALIC_SERIF),
            Emotional.textToEmotional("Sans-serif (bold)", Style.BOLD_SANS_SERIF),
            Emotional.textToEmotional("Sans-serif (italic)", Style.ITALIC_SANS_SERIF),
            Emotional.textToEmotional("Sans-serif (bold italic)", Style.BOLD_ITALIC_SANS_SERIF),
            Emotional.textToEmotional("Script (bold)", Style.BOLD_SCRIPT),
            Emotional.textToEmotional("Fraktur", Style.FRAKTUR),
            Emotional.textToEmotional("Fraktur (bold)", Style.FRAKTUR_BOLD),
            Emotional.textToEmotional("Monospace", Style.MONOSPACE),
        )
    }
}