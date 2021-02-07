package `in`.diptanuchakraborty.agecalculator

import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject

class processapi{

    lateinit var gg:Any


}
class api {

    fun apiCall(){
        val apiurl = "https://agecalculatorapp.diptanuchakraborty.in/public/api/data"
val jsonObject= JSONObject()
        val stringRequest = JsonObjectRequest(Request.Method.GET,apiurl,jsonObject,
            Response.Listener { response ->
                var gg=response

var obj=processapi()
                obj.gg=response

            },
            Response.ErrorListener {

            })

    }

}