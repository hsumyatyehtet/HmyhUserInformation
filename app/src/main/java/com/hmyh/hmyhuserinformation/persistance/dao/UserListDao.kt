package com.hmyh.hmyhuserinformation.persistance.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import io.reactivex.Completable

@Dao
interface UserListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserList(userList: List<UserListVO>): Completable

    @Query("select * from user_list")
    fun getUserList(): LiveData<List<UserListVO>>

    @Query("select * from user_list where id =:userId")
    fun getUserByUserId(userId: Int): LiveData<UserListVO>

    @Query("select * from user_list where name like :searchWord")
    fun retrieveUserListBySearch(searchWord: String): LiveData<List<UserListVO>>

}