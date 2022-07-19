package com.ianunei.noname.db.repository;

import com.ianunei.noname.util.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author 帅小鸦
 * @date 2022/5/12
 */

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    default Page<T> findAll(Pageable pageable, T entity) {
        Specification<T> spec = QueryUtils.generateSpec(entity);
        return findAll(spec, pageable);
    }
}