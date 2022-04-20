package com.anncode.offersandcoupons.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.anncode.offersandcoupons.model.Coupon
import com.mauriciogs.arquitecturamvvm.BR
import com.mauriciogs.arquitecturamvvm.viewmodel.CouponViewModel
import com.squareup.picasso.Picasso
import java.text.FieldPosition

class RecyclerCouponsAdapter(var couponViewModel: CouponViewModel, var resource: Int) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerCouponsAdapter.CardCouponHolder>() {

    private var coupons : List<Coupon>? = null

    fun setCouponsList(coupons : List<Coupon>){
        this.coupons = coupons
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCouponHolder {
        var layoutInflater : LayoutInflater = LayoutInflater.from(p0.context)
        var binding : ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardCouponHolder(binding) // Enviando binding habilita la tarjeta a travez del bindeo
    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int) {
        p0.setDataCard(couponViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIDForPosition(position)
    }

    fun getLayoutIDForPosition(position: Int) :Int {
        return resource
    }

    class CardCouponHolder(binding: ViewDataBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        private var binding : ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(couponViewModel: CouponViewModel, position: Int){
            binding?.setVariable(BR.model, couponViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }
    }

}
