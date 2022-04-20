package com.mauriciogs.arquitecturamvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anncode.offersandcoupons.model.Coupon
import com.mauriciogs.arquitecturamvvm.R
import com.mauriciogs.arquitecturamvvm.databinding.ActivityMainBinding
import com.mauriciogs.arquitecturamvvm.viewmodel.CouponViewModel


class MainActivity : AppCompatActivity(){

    private var couponsViewModel : CouponViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBindings(savedInstanceState)
        supportActionBar?.hide()


        //VIEW

    }

    // Pone en acción el sitema de bindeo de Android dado de alta en el xml
    fun setUpBindings(savedInstanceState: Bundle?){
        var activityMainBinding : ActivityMainBinding // Clase oculta que se crea en automático
        = DataBindingUtil.setContentView(this, R.layout.activity_main)

        couponsViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)
        activityMainBinding.setModel(couponsViewModel)
        setUpListUpdate()
    }


    fun setUpListUpdate(){
        couponsViewModel?.callCoupons() // Primera llamada: LLena la lista
        couponsViewModel?.getCoupons()?.observe(this, Observer { couponsList : List<Coupon> ->
            couponsViewModel?.setCoupponsInRecylerAdapter(couponsList)
        })
    }

}
