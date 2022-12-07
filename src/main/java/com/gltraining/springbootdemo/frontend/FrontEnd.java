package com.gltraining.springbootdemo.frontend;

import com.gltraining.springbootdemo.entity.Product;
import com.gltraining.springbootdemo.exceptions.InvalidArgumentException;
import com.gltraining.springbootdemo.exceptions.ProductNotFoundException;
import com.gltraining.springbootdemo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FrontEnd {
    private IProductService service;

    @Autowired
    public FrontEnd(IProductService service) {
        this.service = service;
    }

    public void runUI() {
        try {
            Product product = service.addProduct("Lafarge", 480.678);
            System.out.println("**added product");
            display(product);
            Product product2 = service.addProduct("moto", 30000.78);
            System.out.println("***added product");
            display(product2);

            int id = product2.getId();
            Product found = service.findById(id);
            System.out.println("***fetched product");
            display(found);
            Product changed = service.changePrice(id, 25000.00);
            display(changed);
            System.out.println("list products order by price");
            List<Product> productList = service.fetchProductsOrderByPrice();
            productList.stream().forEach(this::display);

        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid inputs");
        } catch (ProductNotFoundException ex) {
            System.out.println("Product not found");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("something went wrong, Please try again");
        }

    }

    void display(Product product) {
        System.out.println("id: " + product.getId() + "name: " + product.getName() + "price: " + product.getPrice());
    }
}
