package uz.jamshid.nestedradiogroup

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rg.setRadioGroupListener({
            Log.d("NestedRadioGroup", "select")
        }, {
            Log.d("NestedRadioGroup", "unSelect")
        },{
            Log.d("NestedRadioGroup", "reSelect")
        })

        rg.setRadioGroupListener(onUnSelect = {
            Log.d("NestedRadioGroup", "onUnSelect")
        })

    }
}
