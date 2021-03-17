package com.dmitriyabdurazakov.springboot.data.specifications;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.data.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductSpecification {

    public static Specification<Product> productNameLike(String name) {
        return (Specification<Product>) (root, query, cb) -> name == null ? cb.conjunction() : cb.like(root.get("name"), String.format("%%%s%%", name));
    }

    public static Specification<Product> productIdEquals(Long id) {
        return (Specification<Product>) (root, query, cb) -> id == null ? cb.conjunction() : cb.equal(root.get("id"), id);
    }

    public static Specification<Product> productCategoriesHaveId(Long categoryId) {
        return (Specification<Product>) (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return criteriaBuilder.conjunction();
            }
            Predicate predicate = criteriaBuilder.conjunction();
            Subquery<Category> subQuery = query.subquery(Category.class);
            Root<Category> subRoot = subQuery.from(Category.class);
            Join<Category, Product> join = subRoot.join("products", JoinType.LEFT);
            subQuery.select(join.get("id"));
            Predicate subPredicate = criteriaBuilder.equal(subRoot.get("id"), categoryId);
            subQuery.where(subPredicate);
            Expression<String> exp = root.get("id");
            Predicate p1 = exp.in(subQuery);
            predicate.getExpressions().add(p1);
            return predicate;
        };
    }
}

// SELECT * FROM product p where p.name='acer' and p.id=1 and p.id in (select cp.product_id from category_product cp where cp.category_id=1)