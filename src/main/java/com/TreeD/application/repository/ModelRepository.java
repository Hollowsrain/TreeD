package com.TreeD.application.repository;

import com.TreeD.application.model.entity.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModelRepository extends CrudRepository<Model, Long> {

    List<Model> findAllByUserId(Long userId);
}
