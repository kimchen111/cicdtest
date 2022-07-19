package com.ianunei.noname.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 帅小鸦
 * @date 2022/6/27
 */

@Slf4j
public class QueryUtils {
    /**
     * 多where条件and连接
     */
    public static <T> Specification<T> generateSpec(T entity) {
        return (root, query, builder) ->
                builder.and(getPredicateList(root, builder, entity));
    }

    /**
     * 获取查询条件数组
     */
    private static <T> Predicate[] getPredicateList(Root<T> root, CriteriaBuilder builder, T entity) {
        List<Predicate> predicates = new ArrayList<>();

        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(entity.getClass().getDeclaredFields()));
        fields.addAll(Arrays.asList(entity.getClass().getSuperclass().getDeclaredFields()));
        for (Field field : fields) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(entity);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            if (value != null) {
                Path<T> path = root.get(field.getName());
                Predicate predicate = builder.equal(path, value);
                predicates.add(predicate);
            }
        }

        return predicates.toArray(new Predicate[0]);
    }

}
