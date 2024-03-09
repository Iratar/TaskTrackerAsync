package com.toughdevs.school.popugtasktracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toughdevs.school.popugtasktracker.repository.model.TaskEntity;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {
	
	TaskEntity findByPublicId(String publicId);
	
	List<TaskEntity> findByStatus(String status);
}
