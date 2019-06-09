package cn.edu.nju.lc.springstudy.tx.controller;

import cn.edu.nju.lc.springstudy.tx.dao.CustomerRepository;
import cn.edu.nju.lc.springstudy.tx.domain.Customer;
import cn.edu.nju.lc.springstudy.tx.service.CustomerServiceTxInAnnotation;
import cn.edu.nju.lc.springstudy.tx.service.CustomerServiceTxInCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);

    @Autowired
    private CustomerServiceTxInCode customerServiceInCode;
    @Autowired
    private CustomerServiceTxInAnnotation customerServiceTxInAnnotation;
    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/required")
//    @Transactional
    public Customer required() {
        //PROPAGATION_REQUIRED：有就使用当前的，没有就新建一个
        Customer customer = new Customer("test", "test", "test");
        LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
        return customerServiceInCode.create(customer);
    }


    @GetMapping("/supports")
    @Transactional
    public Customer supports() {
        //PROPAGATION_SUPPORTS：有就使用当前的，没有就不执行事务
        Customer customer = new Customer("test", "test", "test");
        LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
        return customerServiceTxInAnnotation.supports(customer);
    }


    @GetMapping("/mandatory")
    @Transactional
    public Customer mandatory() {
        //PROPAGATION_MANDATORY：有就使用当前的，没有的话就抛出异常
        //No existing transaction found for transaction marked with propagation 'mandatory'
        Customer customer = new Customer("test", "test", "test");
        LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
        return customerServiceTxInAnnotation.mandatory(customer);
    }


    @GetMapping("/requires_new")
    public Customer requiresNew() {
        //PROPAGATION_REQUIRES_NEW：无论如何都开启一个新的事务，挂起当前事务
        Customer customer = new Customer("test", "test", "test");
        LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
        return customerServiceTxInAnnotation.requiresNew(customer);
    }


    @GetMapping("/not_support")
    public Customer notSupport() {
        //PROPAGATION_NOT_SUPPORTED：不支持当前事务；而是始终以非事务方式执行。
        //会插入数据
        Customer customer = new Customer("test", "test", "test");
        LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
        return customerServiceTxInAnnotation.notSupport(customer);
    }


    @GetMapping("/never")
    @Transactional
    public Customer never() {
        //PROPAGATION_NEVER：不支持当前事务；如果存在当前事务就抛出异常
        //Existing transaction found for transaction marked with propagation 'never'
        //如果是事务切面抛出的异常，则无法进入到方法内部，即对逻辑没有影响
        Customer customer = new Customer("test", "test", "test");
        LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
        return customerServiceTxInAnnotation.never(customer);
    }


    @GetMapping("/all")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }


    @DeleteMapping
    public void removeAll() {
        customerRepository.deleteAll();
    }
}
