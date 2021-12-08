package com.example.miaupetshop.ui.account

import android.content.ContentValues
import android.content.Context
import com.example.miaupetshop.servuces.DataBaseConstants
import com.example.miaupetshop.ui.repository.GuestDataBaseHelper

class GuestRepository private constructor(context: Context) {

    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return GuestRepository(context)
        }
    }

    fun save(gust: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val insert = ContentValues()
            insert.put(DataBaseConstants.GUEST.COLUMNS.NAME, gust.name)
            insert.put(DataBaseConstants.GUEST.COLUMNS.EMAIL, gust.email)
            insert.put(DataBaseConstants.GUEST.COLUMNS.PASSWORD, gust.password)
            insert.put(DataBaseConstants.GUEST.COLUMNS.PHONE, gust.phone)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, insert)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun update(gust: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val insert = ContentValues()
            insert.put(DataBaseConstants.GUEST.COLUMNS.NAME, gust.name)
            insert.put(DataBaseConstants.GUEST.COLUMNS.EMAIL, gust.email)
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " =?"
            val args = arrayOf(gust.id.toString())
            db.update(DataBaseConstants.GUEST.TABLE_NAME, insert, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun get(id: Int): GuestModel? {
        var guest:GuestModel?= null

        return try {
            val db = mGuestDataBaseHelper.readableDatabase
            val projection = arrayOf(DataBaseConstants.GUEST.COLUMNS.NAME, DataBaseConstants.GUEST.COLUMNS.EMAIL,
                    DataBaseConstants.GUEST.COLUMNS.PHONE)
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " =?"
            val args = arrayOf(id.toString())

           val cursor =
               db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null)
           if (cursor !=null && cursor.count > 0 ){
               cursor.moveToFirst()
               val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
               val email = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.EMAIL))
               val password = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PASSWORD))
               val phone = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PHONE))
               guest = GuestModel(name, email, password, phone, id)
           }
            cursor.close()
            guest
        } catch (e: Exception) {
            guest
        }
    }
}