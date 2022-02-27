package com.week1.assignment.service;

import com.week1.assignment.entity.*;
import com.week1.assignment.model.ConfirmEnquiryOrderResponseDTO;

import java.util.Date;
import java.util.List;

public class OrderDummy {

    public static User getUser() {
        return new User(1,"test","firstName","lastName",
                "0811110011","email@mail.com",getUserAddress(),
                getBasket(),getUserPaymentList(),getOrderDetail());
    }
    
    public static UserAddress getUserAddress() {
        return new UserAddress(1,"111/222", "10900", "JKN", "BANGKOK", new User());
    }
    
    public static Basket getBasket() {
        return new Basket(1, getBasketItemList(), new User(), 10.0);
    }
    
    public static List<BasketItem> getBasketItemList() {
        return List.of(getBasketItem());
    }

    public static BasketItem getBasketItem() {
        return new BasketItem(1,1.0,1.0, getProduct());
    }
    
    public static List<UserPayment> getUserPaymentList() {
        return List.of(getUserPayment());
    }
    
    public static UserPayment getUserPayment() {
        return new UserPayment(1,"DEBIT","12200110044","UBI SOFT",
                "12/12/2025","555",true, new User());
    }

    public static OrderDetail getOrderDetail() {
        User user = new User();
        user.setId(1);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        return new OrderDetail(1,getOrderItemList(),user,1.0,getPaymentDetail());
    }

    public static List<OrderItem> getOrderItemList() {
        return List.of(getOrderItem());
    }

    public static OrderItem getOrderItem() {
        return new OrderItem(1,1.0,1.0,new Product());
    }

    public static PaymentDetail getPaymentDetail() {
        return new PaymentDetail(1,1.0,"DEBIT","112112", new OrderDetail());
    }
    
    public static Product getProduct() {
        return new Product(1,"productTest","description",1.0,0.1,2.0,"shop","img",
                new Date(),"1 week",new Date(),new Date(),getProductAvailableList(),getBrand(),List.of(new BasketItem()));
    }

    public static List<ProductAvailable> getProductAvailableList() {
        return List.of(getProductAvailable());
    }
    
    public static ProductAvailable getProductAvailable() {
        return new ProductAvailable(1,1.0,1.0,new Date(),new Product());
    }

    public static Brand getBrand() {
        return new Brand(1,"TEST","DESCRIPTION",new Date());
    }

    public static ConfirmEnquiryOrderResponseDTO getConfirmOrderResponseDTO() {
        return new ConfirmEnquiryOrderResponseDTO(1,"111111","firstName lastName",
                "27/02/2022","01/03/2022","Laz","Laz Detail",1.0);
    }

    public static Payee getPayee() {
        return new Payee(1, "Laz","Laz Detail");
    }
}
