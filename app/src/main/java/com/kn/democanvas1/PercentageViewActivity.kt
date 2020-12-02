package com.kn.democanvas1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSeekBar
import com.kn.democanvas1.widget.OnSeekBarChangeSimpleListener
import kotlinx.android.synthetic.main.activity_percentage_view.*
import kotlinx.android.synthetic.main.custom_dialog.view.*
import java.util.*

class PercentageViewActivity : AppCompatActivity() {
    private var percentageView: PercentageView? = null
    private var percentageModelList: MutableList<PercentageModel>? = null

    private var mValueDialog: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percentage_view)
        percentageView = findViewById(R.id.percentageView)
        percentageModelList = ArrayList()
        val percentageModel = PercentageModel()


        if(percentageModelList!!.size > 0){
            percentageModel.value = 0F
            percentageModelList!!.add(percentageModel)
            percentageView!!.setData(percentageModelList)

        }else{
//            percentageModel.value = 0F
//            percentageModel.value = 1F

            percentageModelList!!.add(percentageModel)
            percentageView!!.setData(percentageModelList)
        }



        val seekBar = findViewById<AppCompatSeekBar>(R.id.seekBar)
        seekBar.max = 360
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeSimpleListener() {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                percentageView!!.setStartAngle(progress.toFloat())
            }
        })


        btnRemove.setOnClickListener {
            percentageModelList!!.clear()
            percentageView!!.setData(percentageModelList)
        }
        addFab.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setTitle("Pie Chart Form")
            val mAlertDialog = mBuilder.show()
            mDialogView.dialogYesBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                mValueDialog = mDialogView.dialogValue.text.toString()
                //set the input text in TextView
                val percentageModel = PercentageModel()
                val value = mValueDialog
                Log.d("vvv", "value: " + value)
                Log.d("vvv", "List Value: " + percentageModelList!!.size)
                percentageModel.value = value!!.toInt().toFloat()
                percentageModelList!!.add(percentageModel)
                percentageView!!.setData(percentageModelList)
            }
            //cancel button click of custom layout
            mDialogView.dialogCancelBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }

    }
}
