package com.example.tic_tac_toe

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*

var num = 1
var count = 0
var draw = false
val CHANNEL_ID = "My Channel"
val REMINDER_NOTIFACTION_ID = 100
val REQ_CODE_PI_TRY_GAME = 100
class MainActivity : AppCompatActivity() {
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tryGameIntent = Intent(this,SplashScreen::class.java)
        tryGameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val tryGamePI = PendingIntent.getActivity(applicationContext, REQ_CODE_PI_TRY_GAME,tryGameIntent,PendingIntent.FLAG_MUTABLE)

        val drawable : Drawable? = ResourcesCompat.getDrawable(resources,R.drawable.tic_tac,null)
        val bitmapDrawable = drawable as BitmapDrawable
        val largeIcon = bitmapDrawable.bitmap
        var notifyTryGame : Notification

        val bigPicStyle = Notification.BigPictureStyle()
            .bigPicture(largeIcon)
            .bigLargeIcon(largeIcon)
            .setBigContentTitle("Big Picture Style...")
            .setSummaryText("This is Big Picture Style using Big Image and Text...")

        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notifyTryGame = Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.ttt)
                    .setContentText("Try TIC TAC TOE with your friends !")
                    .setStyle(bigPicStyle)
                    .setContentIntent(tryGamePI)
                    .setSubText("Play 2 player game with your friends...")
                    .setChannelId(CHANNEL_ID)
                    .build()
            nm.createNotificationChannel(NotificationChannel(CHANNEL_ID,"My Channel",NotificationManager.IMPORTANCE_HIGH))
        }
        else {
            notifyTryGame = Notification.Builder(this)
                .setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.ttt)
                .setContentText("Try TIC TAC TOE with your friends !")
                .setStyle(bigPicStyle)
                .setContentIntent(tryGamePI)
                .setSubText("Play 2 player game with your friends...")
                .build()
        }

        nm.notify(REMINDER_NOTIFACTION_ID,notifyTryGame)

        val scale2 = AnimationUtils.loadAnimation(this,R.anim.scale2)
        lltemplate.startAnimation(scale2)

        val scale = AnimationUtils.loadAnimation(this,R.anim.scale)
        icon.startAnimation(scale)

        btnreset.setOnClickListener {
            resetreset()
        }

    }

    fun check(view : View) {
        var scale2 = AnimationUtils.loadAnimation(this,R.anim.scale2)
        scale2.duration=1000
        val v = findViewById<Button>(view.id)
        if (v.text.equals("") && num==1) {
            txtTurn.text = "Player O Turn"
            v.startAnimation(scale2)
            v.text = "X"
            v.setTextColor(getColor(R.color.black))
            num = 0
            count++
        } else if(v.text.equals("") && num==0) {
            txtTurn.text = "Player X Turn"
            v.setTextColor(getColor(R.color.green))
            v.startAnimation(scale2)
            v.text = "O"
            num = 1
            count++
        }

            if (txt(btn1)==txt(btn2) && txt(btn2)==txt(btn3) && !txt(btn1).equals("")) {
                Toast.makeText(this@MainActivity,"${txt(btn1)} WIN !!!",Toast.LENGTH_SHORT).show()
                setColorOnWin(btn1,btn2,btn3)
                reset()
            } else if (txt(btn1)==txt(btn4) && txt(btn4)==txt(btn7) && !txt(btn1).equals("")) {
                Toast.makeText(this@MainActivity,"${txt(btn1)} WIN !!!",Toast.LENGTH_SHORT).show()
                setColorOnWin(btn1,btn4,btn7)
                reset()
            } else if (txt(btn1)==txt(btn5) && txt(btn5)==txt(btn9) && !txt(btn1).equals("")) {
                Toast.makeText(this@MainActivity,"${txt(btn1)} WIN !!!",Toast.LENGTH_SHORT).show()
                setColorOnWin(btn1,btn5,btn9)
                reset()
            } else if (txt(btn2)==txt(btn5) && txt(btn5)==txt(btn8) && !txt(btn2).equals("")) {
                Toast.makeText(this@MainActivity,"${txt(btn2)} WIN !!!",Toast.LENGTH_SHORT).show()
                setColorOnWin(btn2,btn5,btn8)
                reset()
            } else if (txt(btn3)==txt(btn6) && txt(btn6)==txt(btn9) && !txt(btn9).equals("")) {
                Toast.makeText(this@MainActivity,"${txt(btn3)} WIN !!!",Toast.LENGTH_SHORT).show()
                setColorOnWin(btn3,btn6,btn9)
                reset()
            } else if (txt(btn3)==txt(btn5) && txt(btn5)==txt(btn7) && !txt(btn7).equals("")) {
                Toast.makeText(this@MainActivity,"${txt(btn3)} WIN !!!",Toast.LENGTH_SHORT).show()
                setColorOnWin(btn3,btn5,btn7)
                reset()
            } else if (txt(btn4)==txt(btn5) && txt(btn5)==txt(btn6) && !txt(btn6).equals("")) {
                Toast.makeText(this@MainActivity,"${txt(btn4)} WIN !!!",Toast.LENGTH_SHORT).show()
                setColorOnWin(btn4,btn5,btn6)
                reset()
            } else if (txt(btn7)==txt(btn8) && txt(btn8)==txt(btn9) && !txt(btn9).equals("")) {
                Toast.makeText(this@MainActivity,"${txt(btn8)} WIN !!!",Toast.LENGTH_SHORT).show()
                setColorOnWin(btn7,btn8,btn9)
                reset()
            } else if (count == 9) {
                Toast.makeText(this@MainActivity,"Match Draw !!!",Toast.LENGTH_SHORT).show()
                draw = true
                reset()
            }
    }

    fun txt(btn : Button) : String {
        return btn.text.toString()
    }

    fun setColorOnWin(b1 : Button,b2 : Button,b3 : Button) {
        b1.setBackgroundColor(getColor(R.color.white))
        b2.setBackgroundColor(getColor(R.color.white))
        b3.setBackgroundColor(getColor(R.color.white))
    }
    fun reset() {
        num = 1
        count = 0
         Handler().postDelayed(Runnable {
             txtTurn.text = "Player X Turn"
             btn1.setBackgroundColor(getColor(R.color.pp))
             btn2.setBackgroundColor(getColor(R.color.pp))
             btn3.setBackgroundColor(getColor(R.color.pp))
             btn4.setBackgroundColor(getColor(R.color.pp))
             btn5.setBackgroundColor(getColor(R.color.pp))
             btn6.setBackgroundColor(getColor(R.color.pp))
             btn7.setBackgroundColor(getColor(R.color.pp))
             btn8.setBackgroundColor(getColor(R.color.pp))
             btn9.setBackgroundColor(getColor(R.color.pp))
             btn1.text= ""
             btn2.text= ""
             btn3.text= ""
             btn4.text= ""
             btn5.text= ""
             btn6.text= ""
             btn7.text= ""
             btn8.text= ""
             btn9.text= ""
        },1300)
    }

    fun resetreset(){
        txtTurn.text = "Player X Turn"
        num = 1
        count = 0
        btn1.setBackgroundColor(getColor(R.color.pp))
        btn2.setBackgroundColor(getColor(R.color.pp))
        btn3.setBackgroundColor(getColor(R.color.pp))
        btn4.setBackgroundColor(getColor(R.color.pp))
        btn5.setBackgroundColor(getColor(R.color.pp))
        btn6.setBackgroundColor(getColor(R.color.pp))
        btn7.setBackgroundColor(getColor(R.color.pp))
        btn8.setBackgroundColor(getColor(R.color.pp))
        btn9.setBackgroundColor(getColor(R.color.pp))
        btn1.text= ""
        btn2.text= ""
        btn3.text= ""
        btn4.text= ""
        btn5.text= ""
        btn6.text= ""
        btn7.text= ""
        btn8.text= ""
        btn9.text= ""
    }


}

