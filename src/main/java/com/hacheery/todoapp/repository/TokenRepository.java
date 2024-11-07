package com.hacheery.todoapp.repository;

import com.hacheery.todoapp.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
