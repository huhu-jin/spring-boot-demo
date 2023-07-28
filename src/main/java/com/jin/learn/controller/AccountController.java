package com.jin.learn.controller;

import com.jin.learn.dto.ApiResponse;
import com.jin.learn.entity.Account;
import com.jin.learn.entity.AccountTbl;
import com.jin.learn.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("")
    public String createAccount(@RequestBody Account account) {
        accountService.create(account);
        return "success";
    }


    @GetMapping()
    public ApiResponse getAccount(@RequestParam(name = "name") String name) {
        Account account = accountService.findByUsername(name);
        if (account == null) {
            return ApiResponse.NOT_FOUND();
        }else {
            return ApiResponse.OK(account);
        }
    }

    @PostMapping("debit")
    public String debitAccount(@RequestBody AccountTbl account) {
        accountService.debit(account.getUserId(), account.getMoney());
        return "success";
    }








}
