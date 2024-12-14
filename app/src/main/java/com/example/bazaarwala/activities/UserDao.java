package com.example.bazaarwala.activities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM `order`")
    List<Order> getAllOrder();

    @Query("SELECT EXISTS(SELECT * FROM User WHERE uid = :userId)")
    Boolean is_exixt(int userId);

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Delete
    void delete(User user);
    @Query("UPDATE User SET quantity=:qty WHERE uid=:id")
    int updateById(int id,int qty);

    @Insert
    void insertRecord(User user);
    @Insert
    void insertRecord(Order order);

    @Query("DELETE FROM user WHERE uid = :id")
    void deleteById(int id);
}
