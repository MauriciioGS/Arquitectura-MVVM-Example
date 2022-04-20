package com.anncode.offersandcoupons.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.mauriciogs.arquitecturamvvm.model.CouponObservable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl: CouponRepository {

    private var coupons = MutableLiveData<List<Coupon>>()
    // Subject MutableLiveData
    // Lista de objervers : List Coupon
    // cuando List coupon cambie, le afecta al mutableListData
    // Métodos en cascada -> observe: notifica o actualiza cambios

    //TODA LA LÓGICA DE CONEXIÓN, ejecuta la llamada y llena a lista
    override fun callCoupunsAPI() {
        //CONTROLLER
        var couponsList: ArrayList<Coupon>? = ArrayList<Coupon>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e("ERROR: ", it) }
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    couponsList?.add(coupon)
                    Log.d("Cupon",coupon.title)
                }
                //VIEW

                // Asignamos la lista recién llenada a una lista mutableLiveData, que obedece al patron observer
                coupons.value = couponsList
            }
        })
        //CONTROLLER

    }

    override fun getCoupuns() : MutableLiveData<List<Coupon>>{
        return coupons // Devuelve el subject
    }

}