package com.example.cubeia.bank.controller;

import com.example.cubeia.bank.common.param.TransactionParam;
import com.example.cubeia.bank.common.param.UserParam;
import com.example.cubeia.bank.common.util.ApiResponse;
import com.example.cubeia.bank.common.vo.TransactionVo;
import com.example.cubeia.bank.common.vo.UserVo;
import com.example.cubeia.bank.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }


    @GetMapping("/getBalance")
    public ApiResponse<UserVo> getUserBalance(@RequestParam String accountId) {
        UserParam param = new UserParam();
        param.setAccountId(accountId);
        return walletService.getUserBalance(param);
    }


    @PostMapping("/transfer")
    public ApiResponse<String> transferFund(@RequestBody TransactionParam param) {
        return walletService.transferFund(param);
    }


    @GetMapping("/transactions")
    public ApiResponse<List<TransactionVo>> getTransactionsList() {
        return walletService.getTransactionsList();
    }


    @PostMapping("/create-account")
    public ApiResponse<String> createAccount(@RequestBody UserParam param) {
        return walletService.createAccount(param);
    }
}
