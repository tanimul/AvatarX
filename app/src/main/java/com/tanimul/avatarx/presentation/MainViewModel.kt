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
            User(id = 1, name = "Alice Green"),
            User(id = 2, name = "Benjamin Clark"),
            User(id = 3, name = "Chris Thompson"),
            User(id = 4, name = "Daniel Wilson"),
            User(id = 5, name = "Emma Harris"),
            User(id = 6, name = "Frank Lewis"),
            User(id = 7, name = "George Moore"),
            User(id = 8, name = "Hannah Wright"),
            User(id = 9, name = "Ian Foster"),
            User(id = 10, name = "John Doe"),
            User(id = 11, name = "Kate Taylor"),
            User(id = 12, name = "Liam Johnson"),
            User(id = 13, name = "Mia Lewis"),
            User(id = 14, name = "Nathaniel King"),
            User(id = 15, name = "Olivia Thomas"),
            User(id = 16, name = "Peter Brown"),
            User(id = 17, name = "Quincy Adams"),
            User(id = 18, name = "Rachel Smith"),
            User(id = 19, name = "Sophia Martinez"),
            User(id = 20, name = "Tanimul Islam"),
            User(id = 21, name = "Ursula Edwards"),
            User(id = 22, name = "Victor James"),
            User(id = 23, name = "William Scott"),
            User(id = 24, name = "Xander Fields"),
            User(id = 25, name = "Yara Wilson"),
            User(id = 26, name = "Zachary Moore")
        )
        _users.value = dummyUsers
    }

}
