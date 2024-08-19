import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.route.isalmic38online.R

class TasbehFragment : Fragment(R.layout.fragment_tasbeh) {

    private lateinit var tasbehImage: ImageView
    private lateinit var tasbehCounterText: TextView
    private lateinit var tasbehSubhanText: TextView
    private var count = 0
    private var currentRotation = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasbehImage = view.findViewById(R.id.body_sebha_logo)
        tasbehCounterText = view.findViewById(R.id.tasbeh_counter_text)
        tasbehSubhanText = view.findViewById(R.id.tasbeh_subhan_text)

        tasbehSubhanText.setOnClickListener {
            incrementCounterAndCheckZikr()
            rotateTasbeh()
        }
    }

    private fun rotateTasbeh() {
        val rotationStep = 360f / 33
        currentRotation += rotationStep

        val rotate = RotateAnimation(
            currentRotation - rotationStep,
            currentRotation,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 500
            fillAfter = true
        }
        tasbehImage.startAnimation(rotate)
    }

    private fun incrementCounterAndCheckZikr() {
        count++
        tasbehCounterText.text = count.toString()

        if (count == 33) {
            changeZikr()
            count = 0
            currentRotation = 0f
        }
    }

    private fun changeZikr() {
        val currentZikr = tasbehSubhanText.text.toString()

        tasbehSubhanText.text = when (currentZikr) {
            "سبحان الله" -> "الحمد لله"
            "الحمد لله" -> "الله أكبر"
            "الله أكبر" -> "سبحان الله"
            else -> "سبحان الله"
        }
    }
}
