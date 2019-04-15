package edu.msu.stabnic4.keyboardremote

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class RemoteView @JvmOverloads constructor(
    // Variables for parent class construct
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    // Paint object used to print instructions
    val textPaint: Paint = Paint()

    // Touch objects used to keep track of touches
    var touch1: Touch = Touch()
    var touch2: Touch = Touch()

    var server : ServerConnection = ServerConnection()

    init {
        // Set color for info text
        textPaint.color = Color.parseColor("#000000")
        textPaint.strokeWidth = 3.0f

        // Starts server connection
        server.startConnection("192.168.43.170", 19924)
    }


    /**
     * Nested class used to reference a touch
     */
    class Touch (

    ){
        var id: Int = -1

        // Current Locations
        var x: Float = 0f
        var y: Float = 0f

        // Previous locations
        var x2: Float = 0f
        var y2: Float = 0f


        /**
         * Setter for current Position
         */
        fun setPos(x: Float, y: Float){
            this.x = x
            this.y = y
        }

        /**
         * Sets last position,
         */
        fun setLastPos() {
            x2 = x
            y2 = y
        }
    }


    /**
     * Handles touch events, checks here for which gesture is occuring if there is one
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x: Float? = event?.getX()
        var y: Float? = event?.getX()

        val id: Int = event!!.getPointerId(event.actionIndex)

        // Switch for what event is happening
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN -> {
                Log.i("HIYA", "HIHI")
                touch1.id = id
                Log.i("HIYA", touch1.id.toString())
                touch2.id = -1

            }

        }
        return true
    }

    /**
     * Overridden function, draws the information text
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.scale(3f, 3f)
        canvas?.drawText(resources.getText(R.string.instructions).toString(), 50f, 50f, textPaint)
        canvas?.drawText(resources.getText(R.string.instructions2).toString(), 50f, 60f,textPaint)

        canvas?.save()
    }
}