package cn.edu.nju.lc.springstudy.tx.dao;

import cn.edu.nju.lc.springstudy.tx.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findOneByUsername(String username);
}
