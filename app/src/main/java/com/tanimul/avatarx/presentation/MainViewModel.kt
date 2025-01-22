package com.tanimul.avatarx.presentation

import androidx.lifecycle.ViewModel
import com.tanimul.avatarx.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        val dummyUsers = listOf(
            User(id = 1, name = "Tanimul Islam"),
            User(id = 2, name = "John Doe"),
            User(id = 3, name = "Jane Doe"),
            User(id = 4, name = "Alice Smith"),
            User(id = 5, name = "Bob Johnson"),
            User(id = 6, name = "Michael Brown"),
            User(id = 7, name = "Emily Davis"),
            User(id = 8, name = "Daniel Wilson"),
            User(id = 9, name = "Sophia Martinez"),
            User(id = 10, name = "David Anderson"),
            User(id = 11, name = "Olivia Thomas"),
            User(id = 12, name = "James White"),
            User(id = 13, name = "Emma Harris"),
            User(id = 14, name = "Benjamin Clark"),
            User(id = 15, name = "Mia Lewis")
        )
        _users.value = dummyUsers
    }
}
