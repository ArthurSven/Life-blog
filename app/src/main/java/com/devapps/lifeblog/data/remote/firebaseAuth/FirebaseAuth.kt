package com.devapps.lifeblog.data.remote.firebaseAuth
import com.devapps.lifeblog.data.remote.models.User
import com.google.firebase.auth.ActionCodeEmailInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

 class FirebaseAuth {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.
    getInstance("https://life-blog-d2fcd-default-rtdb.europe-west1.firebasedatabase.app")
        .getReference("Users")
    get() = field

    fun loginUser(email: String, password: String, onComplete: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val authResult = auth.signInWithEmailAndPassword(email, password).await()
                withContext(Dispatchers.Main) {
                    onComplete(authResult !=null)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onComplete(false)
                }
            }
        }
    }

    fun registerUser(
         firstname: String,
        lastname: String,
        email: String,
        username: String,
        password: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password)
                if (result != null) {
                    val user = User(firstname, lastname, email, username)
                    database.child(username).setValue(user).await()
                    withContext(Dispatchers.Main) {
                        onComplete(true, null)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onComplete(false, "User Registration failed")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onComplete(false, e.message)
                }
            }
        }

    }

    fun getUser(): FirebaseUser? {
        return auth.currentUser
    }


}