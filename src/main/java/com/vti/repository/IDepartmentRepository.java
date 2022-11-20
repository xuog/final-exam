package com.vti.repository;

import com.vti.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IDepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
    Department findByName (String name);

    List<Department> findByTotalMembersGreaterThanEqual(int minTotalMembers);

    List<Department> findByNameAndTotalMembersGreaterThanEqualAndTotalMembersLessThanEqual(String name, int minTotalMembers, int maxTotalMembers);

    // @Query("FROM Department WHERE name = ?1")
//    @Query("FROM Department WHERE name = :name")
//    @Query(value = "SELECT * FROM department WHERE name = :name", nativeQuery = true);
//    Department findByName1 (@Param("name") String name);

    @Modifying
    @Query("DELETE FROM Department WHERE name = ?1")
    void deleteByName(String name);
}
