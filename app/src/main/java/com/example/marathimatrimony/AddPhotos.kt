@file:Suppress("DEPRECATION")

package com.example.marathimatrimony

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marathimatrimony.datamodel.WriteImageUrl
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import java.util.*

class AddPhotos : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var docRef: DocumentReference
    private lateinit var userID: String
    private lateinit var storageReference: StorageReference
     var imageUri: Uri? =null

    lateinit var myUri: String
    lateinit var profilePhoto: ImageView
    lateinit var choosePhotoBtn: Button
    lateinit var upLoadPhotoBtn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_photos)
        profilePhoto = findViewById(R.id.profilePhoto)
        choosePhotoBtn = findViewById(R.id.choosePhotoBtn)
        upLoadPhotoBtn = findViewById(R.id.upLoadPhotoBtn)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        userID = auth.currentUser!!.uid
        docRef = db.collection("Users").document(userID)
        storageReference = FirebaseStorage.getInstance().reference.child("profilePhoto")


        choosePhotoBtn.setOnClickListener {
            addPhoto()
        }
        upLoadPhotoBtn.setOnClickListener {
            upLoadPhoto()
        }

        getUserInfo()

    }

    private fun addPhoto() {

        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, 111)
        Picasso.get().load(imageUri).into(profilePhoto).toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data!!
            profilePhoto.setImageURI(imageUri)
            Toast.makeText(this, "Profile Photo Selected Successfully", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Profile Photo Not Selected", Toast.LENGTH_SHORT).show()
            getUserInfo()
        }

    }

    private fun getUserInfo() {

        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            } else {
                if (snapshot != null && snapshot.exists()) {

                    val image: String? =snapshot.getString("imageUrl")
                    Picasso.get().load(image).into(profilePhoto)
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun upLoadPhoto() {
        var progressBar=ProgressDialog(this)
        progressBar.setTitle("Uploading Please Wait")
        progressBar.show()
        if (imageUri != null) {
            val imageRef = storageReference.child("$userID.jpg")
            imageRef.putFile(imageUri!!)

            val uploadTask: StorageTask<*>
            uploadTask = imageRef.putFile(imageUri!!)

            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception.let {

                        throw it!!
                    }
                }
                return@Continuation imageRef.downloadUrl
            }).addOnCompleteListener{ task->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    myUri=downloadUri.toString()

                    val  userMap=WriteImageUrl(myUri)
                    docRef.set(userMap, SetOptions.merge()).addOnCompleteListener{
                        if (it.isSuccessful) {
                            Toast.makeText(this@AddPhotos, "Profile Photo Uploaded Successfully.",Toast.LENGTH_SHORT).show()
                            progressBar.dismiss()
                            finish()

                        } else {
                            Toast.makeText(this@AddPhotos, "Profile Photo Uploaded Failed.",Toast.LENGTH_SHORT).show()

                        }

                    }
                }

            }
        }
        else {
            progressBar.dismiss()
            Toast.makeText(this@AddPhotos, "Image Not Selected.",Toast.LENGTH_SHORT).show()
        }
    }
}

