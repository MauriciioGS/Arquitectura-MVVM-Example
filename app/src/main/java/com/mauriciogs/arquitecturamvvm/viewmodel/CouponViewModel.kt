package com.mauriciogs.arquitecturamvvm.viewmodel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.view.RecyclerCouponsAdapter
import com.mauriciogs.arquitecturamvvm.R
import com.mauriciogs.arquitecturamvvm.model.CouponObservable
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class CouponViewModel : ViewModel(){ // CouponViewModel -> CouponObservable -> Repository

    private var couponObservable : CouponObservable = CouponObservable()
    private var recyclerCouponsAdapter : RecyclerCouponsAdapter? = null

    fun callCoupons(){
        couponObservable.callCoupons()
    }

    fun getCoupons() : MutableLiveData<List<Coupon>> {
        return couponObservable.getCoupons()
    }

    fun setCoupponsInRecylerAdapter(coupons : List<Coupon>){
        recyclerCouponsAdapter?.setCouponsList(coupons)
        recyclerCouponsAdapter?.notifyDataSetChanged()
    }

    // Une una lista de elementos aa un layout
    fun getRecyclerCouponsAdapter() : RecyclerCouponsAdapter?{
        recyclerCouponsAdapter = RecyclerCouponsAdapter(this, R.layout.card_coupon)
        return recyclerCouponsAdapter
    }

    fun getCouponAt(position : Int) : Coupon? {
        var coupons : List<Coupon>? = couponObservable.getCoupons().value // Obtiene la lista
        return coupons?.get(position)
    }

    companion object{
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(imageView: CircleImageView, imageUrl: String?){
            imageUrl?.let {
                if (it.isNotEmpty())
                    Picasso.get().load(it).into(imageView)
            }
        }
    }
}