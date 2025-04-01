package com.employee.demo.repository;


import com.employee.demo.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {


    Department findByName(String name);

   Optional<Department> findByNameAndLocation(String name, String location);

//    Page<Department> search(Pageable pageable, String searchBy, Integer status);
//
//    Page<Department> searchNotStatus(Pageable pageable, String searchBy, int i);
//
//    Page<Department> findByStatus(Pageable pageable, Integer status);
//
//    Page<Department> findByStatusNot(Pageable pageable, int i);
    @Query("SELECT e FROM Department e WHERE " +

            "(LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +

            "LOWER(e.location) LIKE LOWER(CONCAT('%', :search, '%')) OR " +

            "LOWER(e.description) LIKE LOWER(CONCAT('%', :search, '%')) OR " +

            "CAST(e.id AS string) LIKE (CONCAT('%', :search, '%'))) AND " +

            " e.status = :status")

    Page<Department> search(Pageable pageable, @Param("search") String searchBy,@Param("status") Integer status);

    Page<Department> findByStatus(Pageable pageable, Integer status);

    Page<Department> findByStatusNot(Pageable pageable, int i);

//    @Query(

//            nativeQuery = true,

//            value = "select * from department as d where " +

//                    " lower(d.name) like lower(concat('%',:search,'%')) or " +

//                    " lower(d.location) like lower(concat('%',:search,'%')) or "+

//                    " lower(d.description) like lower(concat('%',:search,'%')) or "+

//                    " cast(d.id as string) like concat('%',:search,'%') and "+

//                    " d.status!=:status ")

    @Query(

            nativeQuery = true,

            value = "SELECT * FROM department AS d WHERE " +

                    " (LOWER(d.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +

                    " LOWER(d.location) LIKE LOWER(CONCAT('%', :search, '%')) OR " +

                    " LOWER(d.description) LIKE LOWER(CONCAT('%', :search, '%')) OR " +

                    " CAST(d.id AS CHAR) LIKE CONCAT('%', :search, '%')) AND " +

                    " d.status != :status"

    )

    Page<Department> searchNotStatus(Pageable pageable, @Param("search") String searchBy, @Param("status") int i);


}
