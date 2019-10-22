package uz.jamshid.libarary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.RadioButton
import java.lang.Exception

class NestedRadioGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var activeChild: RadioButton? = null
    private var unselectable = false

    private var listener: NestedRadioGroupListener? = null

    init {
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                setUpChild()
            }
        })

        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.NestedRadioGroup,
            0, 0
        )
        unselectable = typedArray.getBoolean(R.styleable.NestedRadioGroup_unselectable, false)

        typedArray.recycle()
    }

    fun setRadioGroupListener(onSelect:((View)->Unit)?=null, onUnSelect:((View)->Unit)?=null, onReselect:((View)->Unit)?=null){
        this.listener = object : NestedRadioGroupListener{
            override fun onUnSelect(view: View) {
                onUnSelect?.invoke(view)
            }

            override fun onSelect(view: View) {
                onSelect?.invoke(view)
            }

            override fun onReselect(view: View) {
                onReselect?.invoke(view)
            }

        }
    }

    fun setRadioGroupListener(listener: NestedRadioGroupListener){
        this.listener = listener
    }

    fun setUpChild() {

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.setOnClickListener {

                val rb = findRadioButtonOnChild(it)

                if (activeChild == rb) {
                    if (unselectable) {
                        rb.isChecked = false
                        activeChild = null
                        listener?.onUnSelect(it)
                    }else listener?.onReselect(it)
                } else {
                    listener?.onSelect(it)
                    rb.isChecked = true
                    activeChild?.isChecked = false
                    activeChild = rb
                }
            }
        }
    }

    private fun findRadioButtonOnChild(child: View): RadioButton {
        if (child is ViewGroup) {
            for (i in 0 until child.childCount) {
                val view = child.getChildAt(i)
                if (view is RadioButton)
                    return view

                if (view is ViewGroup)
                    findRadioButtonOnChild(view)
            }
        }
        throw Exception("You need to add radio button inside of NestedRadioGroup")
    }

    interface NestedRadioGroupListener {
        fun onSelect(view: View)
        fun onUnSelect(view: View)
        fun onReselect(view: View)
    }
}