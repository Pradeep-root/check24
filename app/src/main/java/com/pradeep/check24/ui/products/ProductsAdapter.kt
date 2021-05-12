package com.pradeep.check24.ui.products

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.pradeep.check24.R
import com.pradeep.check24.data.model.Product
import com.pradeep.check24.ui.details.ProductDetailActivity
import com.pradeep.check24.utils.Constants
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter(private val products : ArrayList<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.product_item,
                parent,
                false
            )
        )

        // TODO based on availibility change the ui
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
        holder.itemView.setOnClickListener {
            openDetailActivity(products[position])
        }
    }


    override fun getItemCount() = products.size

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       fun bind(product: Product){
            Glide.with(itemView)
               .load(product.imageUrl)
               .placeholder(context.getDrawable(R.drawable.ic_baseline_image_24))
               .error(context.getDrawable(R.drawable.ic_baseline_image_not_supported_24))
               .diskCacheStrategy(DiskCacheStrategy.DATA)
               .into(itemView.img_product_item)
           itemView.tv_product_name.text = product.name
           itemView.tv_product_detail.text = product.description
           itemView.tv_product_price.text = "Preis ${product.price?.value} ${product.price?.currency}"
           itemView.rating_product.rating = product.rating?.toFloat()!!
       }
    }

    fun updateList(newProducts: MutableList<Product>){
        products.clear()
        products.addAll(newProducts)
        notifyDataSetChanged()
    }

    private fun openDetailActivity(product: Product){
        Intent(context, ProductDetailActivity::class.java).apply {
            putExtra(Constants.PRODUCT_PUT_EXTRA_KEY, product)
            context.startActivity(this)
        }
    }

    private inner class ViewUnavailableViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }
}