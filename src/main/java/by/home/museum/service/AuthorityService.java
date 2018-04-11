package by.home.museum.service;

import by.home.museum.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findById(Long id);

    List<Authority> findByname(String name);

}
