package com.kindsonthegenius.inventoryms_springboot_api.services;

import com.kindsonthegenius.inventoryms_springboot_api.models.Item;
import com.kindsonthegenius.inventoryms_springboot_api.models.Order;
import com.kindsonthegenius.inventoryms_springboot_api.models.OrderItem;
import com.kindsonthegenius.inventoryms_springboot_api.models.OrderStats;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.ItemRepository;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order save(Order order) {

        order.getOrderItems().forEach(orderItem -> {
            Item item = orderItem.getItem();
            Short newQuantity = (short) (item.getQuantity() - orderItem.getQuantity());
            if(newQuantity < item.getReorder_level()) {
                throw new IllegalArgumentException(String.format("Quantity of item %s is not available. Please reduce to %s or less",
                        item.getDescription(), item.getReorder_level()));
            }
            item.setQuantity(newQuantity);
            itemRepository.save(item);
        });

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public OrderStats getOrderStats() {
        Calendar cal = Calendar.getInstance();
        int currentMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int currentYear = cal.get(Calendar.YEAR);
        int previousMonth, previousYear;
        if (currentMonth == 1) {
            // If it's January, we compare with December of the previous year
            previousMonth = 12;
            previousYear = currentYear - 1;
        } else {
            // Otherwise, we compare with the previous month in the current year
            previousMonth = currentMonth - 1;
            previousYear = currentYear;
        }
        // Fetch the count of orders added in the current and previous months
        Long currentMonthCount = orderRepository.countOrdersAddedInCurrentMonth(currentMonth, currentYear);
        Long previousMonthCount = (previousMonth == 12 && previousYear < currentYear) ?
                orderRepository.countOrdersAddedInDecemberOfPreviousYear(previousYear) :
                orderRepository.countOrdersAddedInPreviousMonth(previousMonth, previousYear);
        // Calculate percentage difference
        double percentageDifference = 0;
        if (previousMonthCount > 0) {
            percentageDifference = ((double) (currentMonthCount - previousMonthCount) / previousMonthCount) * 100;
        } else if (currentMonthCount > 0) {
            percentageDifference = 100;  // If there were no orders in the previous month but some in the current month
        }
        // Create and return an OrderStats object (similar to ProductStats)
        return new OrderStats(currentMonthCount, previousMonthCount, percentageDifference);
    }
}