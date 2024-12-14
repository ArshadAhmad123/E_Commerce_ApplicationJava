package com.example.bazaarwala.activities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity
    public class Order {
        @PrimaryKey(autoGenerate = true)
        public int uid;
        @ColumnInfo(name = "productName")
        public String productName;

        @ColumnInfo(name = "image")
        public String image;

        @ColumnInfo(name = "price")
        public Integer price;

        @ColumnInfo(name = "quantity")
        public  Integer quantity;

        @ColumnInfo(name = "paymentId")
        public String paymentId;

        @ColumnInfo(name = "status")
        public String status;

        public Order(String productName,String image, Integer price, Integer quantity,String paymentId,String status) {
            this.productName = productName;
            this.price = price;
            this.quantity = quantity;
            this.image = image;
            this.paymentId = paymentId;
            this.status = status;
        }
        public String getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }


