package dev.adeleke.techwiztemp.repository;

import dev.adeleke.techwiztemp.model.Tutorial;

import java.util.List;

public interface TemplateRepository {
    int save(Tutorial book);

    int update(Tutorial book);

    Tutorial findById(Long id);

    int deleteById(Long id);

    List<Tutorial> findAll();

    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    int deleteAll();
}
