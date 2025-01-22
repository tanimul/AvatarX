package com.tanimul.avatarx.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanimul.avatarx.databinding.ItemUserBinding
import com.tanimul.avatarx.domain.model.User
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class UserAdapter(private val viewModel: MainViewModel) :
    ListAdapter<User, UserAdapter.ManagePatientsViewHolder>(DiffCallBack()) {
    class ManagePatientsViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: User, viewModel: MainViewModel) {
            binding.item = data
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ManagePatientsViewHolder {
        return ManagePatientsViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ManagePatientsViewHolder, position: Int) {
        holder.onBind(getItem(position), viewModel)
    }

    private class DiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean =
            oldItem == newItem
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@BindingAdapter(
    value = ["bindUsers", "bindViewModel"],
    requireAll = true
)
fun RecyclerView.bindManagePatients(
    data: List<User>?,
    viewModel: MainViewModel
) {
    if (adapter == null) adapter = UserAdapter(viewModel)
    val value = data ?: emptyList()
    val adapter = adapter as? UserAdapter
    adapter?.submitList(value)
}