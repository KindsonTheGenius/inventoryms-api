package com.kindsonthegenius.inventoryms_springboot_api.services;

import com.kindsonthegenius.inventoryms_springboot_api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ProductRepository productRepository;

    public DashboardService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    /*
    TOTAL PRODUCTS
        Get the total products added this month: prevTotalProducts
        Get the total products added last month: curTotalProducts
        Compute the percentage difference: percentChangeProducts = (curTotalProducts - prevTotalProducts)/prevTotalProducts * 100
    */


    /*
    TOTAL ORDERS
        Get the total orders added this month: prevTotalOrders
        Get the total orders added last month: curTotalOrders
        Compute the percentage difference: percentChangeOrders = (curTotalOrders - prevTotalOrders)/prevTotalOrders * 100
    */

    /*
    TOTAL PURCHASES (Amount from Purchase Orders)
        Grand Total of purchase orders
    */

    /*
    TOTAL SALES (Amount from Customer Orders)
        Grand Total of customer orders
     */
}
