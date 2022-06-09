package com.mpwd2.momomotus.data.dataSources.remote.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mpwd2.momomotus.data.entities.User

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserFirebase @Inject constructor(){

    private val mCollection = Firebase.firestore.collection("Users")

    fun addUser(id: String, user: User): Task<Void> =
        mCollection.document(id).set(user, SetOptions.merge())

    fun getAllUsers(): CollectionReference = mCollection


    fun getUserById(id: String): DocumentReference = mCollection.document(id);

}