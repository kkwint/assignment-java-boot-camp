package com.week1.assignment.service;

import com.week1.assignment.entity.*;
import com.week1.assignment.model.BasketItemIdDTO;
import com.week1.assignment.model.ConfirmOrderRequestDTO;
import com.week1.assignment.model.ConfirmEnquiryOrderResponseDTO;
import com.week1.assignment.repository.OrderDetailRepository;
import com.week1.assignment.repository.PayeeRepository;
import com.week1.assignment.repository.UserRepository;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private PayeeRepository payeeRepository;

    public OrderService(UserRepository userRepository, OrderDetailRepository orderDetailRepository, PayeeRepository payeeRepository) {
        this.userRepository = userRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.payeeRepository = payeeRepository;
    }

    public ConfirmEnquiryOrderResponseDTO confirmOrder(ConfirmOrderRequestDTO confirmOrderRequest) {

        ConfirmEnquiryOrderResponseDTO orderDetailResponse = new ConfirmEnquiryOrderResponseDTO();
        Optional<User> user = userRepository.findByUserName(confirmOrderRequest.getUserName());

        if (user.isPresent()) {
            List<BasketItem> basketItemList = getUserBasketItemByRequestBasketItem(user.get(), confirmOrderRequest.getBasketItemList());
            OrderDetail orderDetail = createOrderDetail(user.get(), basketItemList);
            orderDetailResponse = transformToOrderDetailDTO(orderDetail);
        }

        return orderDetailResponse;
    }

    private List<BasketItem> getUserBasketItemByRequestBasketItem(User user, List<BasketItemIdDTO> basketItemList) {

        List<BasketItem> resultList = new ArrayList<>();
        basketItemList.forEach(requestItem -> {
            user.getBasket().getBasketItem().forEach(basketItem -> {
                if (requestItem.getBasketItemId().equals(basketItem.getId())) {
                    resultList.add(basketItem);
                }
            });
        });
        return resultList;
    }

    private ConfirmEnquiryOrderResponseDTO transformToOrderDetailDTO(OrderDetail orderDetail) {
        ConfirmEnquiryOrderResponseDTO orderDetailResponse = new ConfirmEnquiryOrderResponseDTO();

        if (orderDetail != null) {
            Optional<Payee> payee = payeeRepository.findById(1);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Calendar cal = Calendar.getInstance();
            String transactionDate = dateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, 2);
            String expiredDate = dateFormat.format(cal.getTime());

            orderDetailResponse.setId(orderDetail.getId());
            orderDetailResponse.setInvoice_no(generateInvoice());
            orderDetailResponse.setPayer(String.format("%s %s", orderDetail.getUser().getFirstName(), orderDetail.getUser().getLastName()));
            orderDetailResponse.setTransactionDate(transactionDate);
            orderDetailResponse.setExpiredDate(expiredDate);
            orderDetailResponse.setPayee(payee.isPresent() ? payee.get().getPayee():"");
            orderDetailResponse.setDetail(payee.isPresent() ? payee.get().getDetail():"");
            orderDetailResponse.setTotal(orderDetail.getTotal());
        }

        return orderDetailResponse;
    }

    private List<OrderItem> transformBasketItemToOrderItem(List<BasketItem> basketItems, OrderDetail orderDetail) {

        List<OrderItem> orderItems = new ArrayList<>();

        basketItems.forEach(basketItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(basketItem.getProduct());
            orderItem.setQuantity(basketItem.getQuantity());
            orderItem.setSize(basketItem.getSize());
            orderItems.add(orderItem);
        });

        return orderItems;
    }

    private Double calculateOrderItemAmount(List<BasketItem> basketItems) {

        return basketItems.stream().mapToDouble(basketItem ->
                        (basketItem.getQuantity() * basketItem.getProduct().getPrice())).sum();
    }

    private OrderDetail createOrderDetail(User user, List<BasketItem> basketItems) {

        OrderDetail orderDetail = new OrderDetail();
        List<OrderItem> orderItems = transformBasketItemToOrderItem(basketItems, orderDetail);
        Double total = calculateOrderItemAmount(basketItems);
        PaymentDetail paymentDetail = transformUserPaymentToPaymentDetail(user, total, orderDetail);

        orderDetail.setOrderItems(orderItems);
        orderDetail.setUser(user);
        orderDetail.setTotal(total);
        orderDetail.setPaymentDetail(paymentDetail);

        return orderDetailRepository.save(orderDetail);
    }

    private PaymentDetail transformUserPaymentToPaymentDetail(User user, Double total, OrderDetail orderDetail) {
        PaymentDetail paymentDetail = new PaymentDetail();

        Optional<UserPayment> userPayment = user.getUserPaymentList().stream().findFirst();

        paymentDetail.setPaymentType(userPayment.get().getPaymentType());
        paymentDetail.setAmount(total);
        paymentDetail.setCardNo(userPayment.get().getCardNo());
        paymentDetail.setOrderDetail(orderDetail);

        return paymentDetail;
    }

    private String generateInvoice() {

        List<CharacterRule> rules = List.of(
                new CharacterRule(EnglishCharacterData.Digit, 10)
        );

        PasswordGenerator generator = new PasswordGenerator();
        return generator.generatePassword(10, rules);
    }

    public ConfirmEnquiryOrderResponseDTO getOrderDetail(String userName) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findByUserName(userName).stream().findFirst();
        return transformToOrderDetailDTO(orderDetail.get());
    }
}
