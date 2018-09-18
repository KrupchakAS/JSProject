package app.dao.impl;

import app.dao.GenericDaoImpl;
import app.dao.api.ProductDao;
import app.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

    @Override
    public Product getByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        if (name != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(productRoot.get("name"), name));
        }
        List<Product> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Product> getAllByCategory(Integer categoryId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        if (categoryId != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().equal(productRoot.get("category_id"), categoryId));
        }
        List<Product> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public List<Product> getProductsByParameters(String categoryName, String productName, Integer minPrice, Integer maxPrice) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
//        if (categoryName != null) {
//            criteriaQuery.where(entityManager.getCriteriaBuilder().like(productRoot.get("categoryName"), "%"+categoryName+"%"));
//        }
        if (productName != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().like(productRoot.get("name"), "%"+productName+"%"));
        }
        if (minPrice != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().gt(productRoot.get("price"), minPrice));
        }
        if (maxPrice != null) {
            criteriaQuery.where(entityManager.getCriteriaBuilder().le(productRoot.get("price"), maxPrice));
        }
        List<Product> list = entityManager.createQuery(criteriaQuery).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }
}
