package com.cocoon.repository;

import com.cocoon.entity.Company;
import com.cocoon.exception.CocoonException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

    boolean existsByTitle(String title) throws CocoonException;

}
