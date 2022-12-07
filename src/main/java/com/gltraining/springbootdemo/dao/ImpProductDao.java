package com.gltraining.springbootdemo.dao;

import com.gltraining.springbootdemo.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Repository
public class ImpProductDao implements IproductDao{
    /*
    @PersistenceContext allows you to specify which persistence unit you want to use. Your project might have multiple data sources connected to different DBs
    and @PersistenceContext allows you to say which one you want to operate on
    * */
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void add(Product product) {
       entityManager.persist(product);
    }
    @Override
    public Optional<Product> findById(int id) {
        Product product = entityManager.find(Product.class,id);
        if(product == null){
            return Optional.empty();
        }
        return Optional.of(product);
    }

    @Override
    public Product update(Product product) {
        Product product1 = entityManager.merge(product);
        return product1;
    }

    @Override
    public List<Product> fetchAllProductsByPrice() {
        String queryText = "from Product order by price";
       TypedQuery<Product> query = entityManager.createQuery(queryText,Product.class);
        List<Product> productList = query.getResultList();
        return productList;
    }
}
