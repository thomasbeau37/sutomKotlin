package com.mpwd2.momomotus.data.repositories

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.mpwd2.momomotus.data.dataSources.remote.firebase.UserFirebase
import com.mpwd2.momomotus.data.entities.User
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.lang.reflect.Constructor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val userFirebase: UserFirebase) {
//    fun getUserByid(id: String) = callbackFlow {  }

    private lateinit var currentUser: User


    fun addUser(id: String, user: com.mpwd2.momomotus.data.entities.User): Flow<Boolean> =
        callbackFlow {
            val subscription = userFirebase.addUser(id, user).addOnCompleteListener {
                if (it.isSuccessful) {
                    trySend(true).isSuccess
                } else {
                    trySend(false).isFailure
                    cancel(it.exception?.message.toString())
                }
            }

            awaitClose { subscription.isComplete }
        }

    fun getUser(id: String): Flow<User?> = callbackFlow {
        val subscription = userFirebase.getUserById(id)
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                if (documentSnapshot!!.exists()) {
                    val snapshotUser = documentSnapshot.toObject(User::class.java)
                    if (snapshotUser != null) {
                        currentUser = snapshotUser
                    }
                    trySend(snapshotUser!!).isSuccess
                } else {
                    trySend(null).isFailure
                    cancel(firebaseFirestoreException?.message.toString())
                }
            }
        awaitClose()
    }

    fun getAllUsers() = callbackFlow {
        val subscription = userFirebase.getAllUsers()
            .addSnapshotListener { snapshot, exception ->
                if(snapshot != null){
                    trySend(snapshot.map{ item -> item.toObject(User::class.java)}.sortedByDescending { it.score }).isSuccess

                }else{
                    trySend(null).isFailure
                    cancel(exception?.message.toString())
                }


            }
        awaitClose()
    }


    fun getCurrentUser() = currentUser
}