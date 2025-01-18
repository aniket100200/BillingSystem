package com.example.BhandeBillingSystem.services.impl;

import com.example.BhandeBillingSystem.dtos.response.BillRequestDto;
import com.example.BhandeBillingSystem.exceptions.user.UserNotFoundException;
import com.example.BhandeBillingSystem.models.Bill;
import com.example.BhandeBillingSystem.models.User;
import com.example.BhandeBillingSystem.repository.BillRepository;
import com.example.BhandeBillingSystem.repository.UserRepository;
import com.example.BhandeBillingSystem.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BillServiceImp implements BillService {

    final BillRepository billRepository;
    final UserRepository userRepository;
    @Autowired
    public BillServiceImp(BillRepository billRepository, UserRepository userRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveBill(BillRequestDto bill) {
        Bill billToSave = new Bill();
        billToSave.setStatus(bill.getStatus());
        User user = userRepository.findByUuid(bill.getUserid());
        if(user == null){
            throw new UserNotFoundException("Unable to find User");
        }
        billToSave.setUser(user);

        billRepository.save(billToSave);


    }

    @Override
    public void deleteBill(BillRequestDto bill, String billUuid) {

    }

    @Override
    public void updateBill(BillRequestDto bill, String billUuid) {

    }

    @Override
    public List<Bill> getAllBills() {
        return List.of();
    }

    @Override
    public Bill getBillById(int id) {
        return null;
    }

    @Override
    public List<Bill> getBillsByCustomerId(int customerId) {
        return List.of();
    }

    @Override
    public List<Bill> getAllBillsByCustomerId(int customerId) {
        return List.of();
    }

    @Override
    public List<Bill> getBillsByCustomerIdAndDate(int customerId, Date date) {
        return List.of();
    }

    @Override
    public List<Bill> getBillsByDate(int customerId, Date date) {
        return List.of();
    }
}
