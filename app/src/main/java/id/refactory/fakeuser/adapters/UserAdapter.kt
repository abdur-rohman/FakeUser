package id.refactory.fakeuser.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.refactory.fakeuser.databinding.ItemUserBinding
import id.refactory.fakeuser.models.ResultsItem

class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var list = mutableListOf<ResultsItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addUsers(users: List<ResultsItem>) {
        val firstIndex = list.lastIndex + 1
        list.addAll(users)
        notifyItemRangeInserted(firstIndex, list.lastIndex)
    }

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size

}