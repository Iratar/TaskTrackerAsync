package com.toughdevs.school.popugtasktracker.accounting.repository.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toughdevs.school.popugtasktracker.accounting.repository.tasks.model.TaskEntity;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Long> {
	
	TaskEntity findByPublicId(String publicId);
}
