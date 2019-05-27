package com.kalbob.app.department.repository;

import com.kalbob.app.config.data.BaseRepository;
import com.kalbob.app.department.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Long> {

}
