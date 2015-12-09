package com.instabot.service.impl;

import com.instabot.models.Order;
import com.instabot.models.Post;
import com.instabot.models.Payment;
import com.instabot.utils.Constants;
import org.json.JSONObject;

import java.util.HashMap;

import static com.instabot.utils.RequestHelper.*;

public class ViaphoneService {

    public Payment createPayment(Post post, Order order) {

        double amount = post.getPrice() * order.getQty();
        String details = "product=" + post.getProductName() + "_price=" + post.getPrice() + "_qty=" + order.getQty();

        HashMap<String, Object> params = new HashMap<>();
        params.put("customer", order.getUserName());
        params.put("merchant", post.getUserName());
        params.put("amount", amount);
        params.put("details", details);
        params.put("instagram", true);
        JSONObject object = makeRequestJson(Constants.Viaphone.CREATE_PAYMENT, params);
        System.out.println(object);
        return new Payment(object);
    }
}