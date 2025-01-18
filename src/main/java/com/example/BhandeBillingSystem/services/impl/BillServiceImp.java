package com.example.BhandeBillingSystem.services.impl;

import com.example.BhandeBillingSystem.Transformer.BillTransformer;
import com.example.BhandeBillingSystem.dtos.request.BillRequestDto;
import com.example.BhandeBillingSystem.dtos.response.BillResponseDto;
import com.example.BhandeBillingSystem.exceptions.BillNotFoundException;
import com.example.BhandeBillingSystem.exceptions.user.UserNotFoundException;
import com.example.BhandeBillingSystem.models.Bill;
import com.example.BhandeBillingSystem.models.User;
import com.example.BhandeBillingSystem.repository.BillRepository;
import com.example.BhandeBillingSystem.repository.UserRepository;
import com.example.BhandeBillingSystem.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
           Bill bill1= billRepository.findBillByUuid(billUuid);

           if (bill1 == null){
               throw new BillNotFoundException("Unable to find Bill");
           }
           billRepository.delete(bill1);



    }

    @Override
    public void updateBill(BillRequestDto dto, String billUuid) {
      Bill bill = billRepository.findBillByUuid(billUuid);
      if (bill == null){
          throw new BillNotFoundException("Unable to find Bill");
      }
      bill.setStatus(dto.getStatus());
      billRepository.save(bill);

    }

    @Override
    public List<BillResponseDto> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        List<BillResponseDto> billResponseDtos = new ArrayList<>();
        for (Bill bill : bills) {
            BillResponseDto dto = BillTransformer.billToBillResponseDto(bill);
            billResponseDtos.add(dto);
        }
        return billResponseDtos;
    }


    @Override
    public BillResponseDto getBillById(String id) {
        Bill bill = billRepository.findBillByUuid(id);
        if (bill == null){
            throw new BillNotFoundException("Unable to find Bill");
        }
        return BillTransformer.billToBillResponseDto(bill);
    }

    @Override
    public List<BillResponseDto> getBillsByCustomerId(String customerId) {
        Bill bill = billRepository.findByUserUuid(customerId);
        if (bill == null){
            throw new BillNotFoundException("Unable to find Bill");
        }
        List<BillResponseDto> billResponseDtos = new ArrayList<>();
        billResponseDtos.add(BillTransformer.billToBillResponseDto(bill));

       return billResponseDtos;
    }

    @Override
    public List<BillResponseDto> getAllBillsByCustomerId(String customerId) {
        List<Bill> bills = billRepository.findAllByUserUuid(customerId);
        List<BillResponseDto> billResponseDtos = new ArrayList<>();
        for (Bill bill : bills) {
            BillResponseDto dto = BillTransformer.billToBillResponseDto(bill);
            billResponseDtos.add(dto);
        }
        return billResponseDtos;
    }

    @Override
    public List<BillResponseDto> getBillsByCustomerIdAndDate(String customerId, Date date) {
        return List.of();
    }

    @Override
    public List<BillResponseDto> getBillsByDate(String customerId, Date date) {
        return List.of();
    }




}
