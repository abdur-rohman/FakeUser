package id.refactory.fakeuser.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.refactory.fakeuser.databinding.ItemLoadingBinding
import id.refactory.fakeuser.databinding.ItemUserBinding
import id.refactory.fakeuser.models.ResultsItem

class UserAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM = 0
        const val LOADING = 1
    }

    var list = mutableListOf<ResultsItem?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addUsers(users: List<ResultsItem>) {
        val firstIndex = list.lastIndex + 1
        list.addAll(users)
        notifyItemRangeInserted(firstIndex, list.lastIndex)
    }

    inner class LoadingViewHolder(binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(resultsItem: ResultsItem) {
            with(binding) {
                Glide.with(root).load(resultsItem.picture.medium).into(ivUser)
                tvName.text =
                    "${resultsItem.name.title} ${resultsItem.name.first} ${resultsItem.name.last}"
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            null -> LOADING
            else -> ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM -> ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(context), parent, false))
            else -> LoadingViewHolder(
                ItemLoadingBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        list[position]?.let {
            if (holder is ViewHolder) holder.bindData(it)
        }
    }

    override fun getItemCount(): Int = list.size

}