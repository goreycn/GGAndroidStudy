package goreyjp.com.ggandroidstudy.extesions

import android.content.Context
import android.text.InputType
import android.text.Selection
import android.text.Spannable
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_drawable.view.*
import org.jetbrains.anko.onTouch


// 打开键盘
fun EditText.openKeyboard(context: Context){
    val imManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imManager.showSoftInput(this, 0)
}

// 关闭键盘
fun EditText.closeKeyboard(context: Context){
    val imManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imManager.hideSoftInputFromWindow(windowToken, 0)
    // 拿掉一闪一闪的光标
    tfInput.inputType = InputType.TYPE_NULL
}

// 设置TextView点击后的响应  参考: http://www.jianshu.com/p/a7b3aa7897ce
fun EditText.ggOnTouch(){
    onTouch{ view, motionEvent ->
        tfInput.inputType = InputType.TYPE_CLASS_TEXT
        tfInput.onTouchEvent(motionEvent)
        val txt = tfInput.text
        if (txt is Spannable){
            Selection.setSelection(txt, txt.length)
        }
        true
    }
}