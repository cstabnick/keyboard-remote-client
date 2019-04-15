package edu.msu.stabnic4.keyboardremote

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.text.method.Touch
import android.util.AttributeSet
import android.view.View

class RemoteView @JvmOverloads constructor(
    // Variables for parent class construct
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0,

    // Set variables for this class
    var textPaint: Paint = Paint()

) : View(context, attrs, defStyle) {


    init {
        textPaint.color = Color.parseColor("#000000")
        textPaint.strokeWidth = 3.0f
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.scale(3f, 3f)
        canvas?.drawText(resources.getText(R.string.instructions).toString(), 50f, 50f, textPaint)
        canvas?.drawText(resources.getText(R.string.instructions2).toString(), 50f, 60f,textPaint)

        canvas?.save()
    }
}