package com.nefos.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nefos.spring.jpa.h2.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByCompleted(boolean completed);

  List<Task> findByTitleContainingIgnoreCase(String title);
}
