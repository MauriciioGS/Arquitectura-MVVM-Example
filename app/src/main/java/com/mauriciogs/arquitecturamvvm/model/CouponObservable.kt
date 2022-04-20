package com.mauriciogs.arquitecturamvvm.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.model.CouponRepository
import com.anncode.offersandcoupons.model.CouponRepositoryImpl

class CouponObservable : BaseObservable() {
    // Conn directa con REpositorio

    private var couponRepository : CouponRepository = CouponRepositoryImpl()

    fun callCoupons(){
        couponRepository.callCoupunsAPI()
    }

    // Conn directa con ViewModel
    fun getCoupons(): MutableLiveData<List<Coupon>> {
        return couponRepository.getCoupuns()
    }
}