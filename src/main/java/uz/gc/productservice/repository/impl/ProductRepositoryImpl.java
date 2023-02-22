package uz.gc.productservice.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import uz.gc.productservice.model.ProductCategories;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl {

    private final EntityManager entityManager;

    public Page<ProductCategories> getProductsWithParams(Map<String, String> params){
        int page = 0;
        int size = Integer.MAX_VALUE;

        if (params.containsKey("page") && params.get("page") != null){
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size") && params.get("size") != null){
            size = Integer.parseInt(params.get("size"));
        }

        StringBuilder paramStr = buildQueryParams(params);
        String queryStr = "select p.id, p.name, amount, price, c.name as category_name from product p " +
                " left join category c on c.id = p.category_id " +
                " where 1=1 ";

        String orderStr = " order by p.id ";

        String countQueryStr = "select count(1) from product p " +
                " left join category c on c.id = p.category_id " +
                " where 1=1 ";

        Query query = entityManager.createNativeQuery(queryStr + paramStr.toString() + orderStr, ProductCategories.class);
        Query countQuery = entityManager.createNativeQuery(countQueryStr + paramStr.toString());

        query.setFirstResult(size * page);
        query.setMaxResults(size);

        setParams(params, countQuery);
        setParams(params, query);

        int totalCount = countQuery.getFirstResult();
        List<ProductCategories> result = (List<ProductCategories>) query.getResultList();

        return new PageImpl<>(result, PageRequest.of(page, size), totalCount);
    }

    public StringBuilder buildQueryParams(Map<String, String> params){
        StringBuilder paramStr = new StringBuilder();
        if (params.containsKey("name") && params.get("name") != null){
            paramStr.append(" AND upper(p.name) like :name");
        }
        if (params.containsKey("amount") && params.get("amount") != null){
            paramStr.append(" AND p.amount >= :amount");
        }
        //...

        return paramStr;
    }

    public void setParams(Map<String, String> params, Query query){
        if (params.containsKey("name") && params.get("name") != null){
            query.setParameter("name", "%" + params.get("name").toUpperCase() + "%");
        }
        if (params.containsKey("amount") && params.get("amount") != null){
            query.setParameter("amount", Integer.parseInt(params.get("amount")));
        }
        //...
    }
}
