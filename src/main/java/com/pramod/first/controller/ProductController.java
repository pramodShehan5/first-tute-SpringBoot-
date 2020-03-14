package com.pramod.first.controller;

import com.pramod.first.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ProductController {
    private static final String template = "Hello, %s!";
    private final AtomicInteger counter = new AtomicInteger();
    private List list=new ArrayList();

    public ProductController() {
        list.add(new Product(1, "Suger", 130, 1000));
        list.add(new Product(2, "Milk", 110, 2000));
    }

    /*
    @GetMapping("/product")
    public List products(@RequestParam(value = "name", defaultValue = "") String name) {
        System.out.println(name);
        list.add(new Product(1, "Suger", 130, 1000));
        list.add(new Product(2, "Milk", 110, 2000));
        return list;
    }
*/

    @GetMapping("/product")
    public List getProductsByName(@RequestParam(value = "name", defaultValue = "") String name) {
        System.out.println(name);
        if(name.isEmpty()) {
            return list;
        } else {
            List listnew=new ArrayList();
            Product selectedProduct = null;
            Iterator iterator = list.iterator();
            while(iterator.hasNext()){
                Product product = (Product) iterator.next();
                if(product.getName().equals(name)){
                    selectedProduct = product;
                    break;
                }
            }
            listnew.add(selectedProduct);
            return listnew;
        }
    }
}
