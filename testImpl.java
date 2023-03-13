package com.nextyu.demo.test;


import com.nextyu.demo.service.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class testImpl{
    public static void main(String[] args) {
        distributeRedPacket(new BigDecimal(5),3);
    }

    /**
     * 提前分配红包,随机分(单位：分)
     * 因为每个用户必须保证摸到金额不能低于4角（40分），所以先将总金额减去红包个数乘以40分，再将剩下的金额随机分，之后将每个红包加上40分
     * @param totalAmount
     * @param num
     */
    public static void distributeRedPacket(BigDecimal totalAmount, int num) {
        BigDecimal b = totalAmount.multiply(new BigDecimal("100")).subtract(new BigDecimal("40").multiply(new BigDecimal(num)));
        int amount = b.intValue();

        List<Integer> points = new ArrayList<>();

        Random random = new Random();
        while (points.size() < num - 1) {
            points.add(random.nextInt(amount - 1) + 1);
        }
        points.add(amount);
        List<Integer> collects = points.stream().sorted().collect(Collectors.toList());
        int before = 0;
        int total = 0;
        for (int collect : collects) {
            System.out.println((double) (collect - before +40) / 100);
            Integer d = collect - before +40;
            before = collect;
            total +=d;
        }
        System.out.println("验证分配的红包总金额:"+total);
    }
}
