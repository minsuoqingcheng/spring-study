package cn.edu.nju.lc.springstudy.tx.service;

import cn.edu.nju.lc.springstudy.tx.dao.CustomerRepository;
import cn.edu.nju.lc.springstudy.tx.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerServiceTxInAnnotation {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceTxInAnnotation.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Customer supports(Customer customer) {
        return logic(customer);
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public Customer mandatory(Customer customer) {
        return logic(customer);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Customer requiresNew(Customer customer) {
        return logic(customer);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Customer notSupport(Customer customer) {
        return logic(customer);
    }


    @Transactional(propagation = Propagation.NEVER)
    public Customer never(Customer customer) {
        return logic(customer);
    }


    private Customer logic(Customer customer) {
        LOG.info("CustomerService In Annotation create customer:{}", customer.getUsername());
        if (customer.getId() != null) {
            throw new RuntimeException("用户已经存在");
        }
        Customer result = customerRepository.save(customer);
        throwException();
        return result;
    }

    private void throwException() {
        throw new RuntimeException("test");
    }
}
