package `in`.diptanuchakraborty.agecalculator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class SupportMePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support_me_page)

        //mycode.support

        var hireMeBtn=findViewById<Button>(R.id.hireMeBtn)
        hireMeBtn.setOnClickListener {
            val url = "http://www.diptanuchakraborty.in"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        var paypal_link=findViewById<ImageView>(R.id.rwer)
        paypal_link.setOnClickListener {
            val url = "https://paypal.me/diptncofficial?locale.x=en_GB"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}