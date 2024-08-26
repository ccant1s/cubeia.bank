package com.example.cubeia.bank.service;

import com.example.cubeia.bank.common.param.TransactionParam;
import com.example.cubeia.bank.common.param.UserParam;
import com.example.cubeia.bank.common.util.ApiResponse;
import com.example.cubeia.bank.common.vo.TransactionVo;
import com.example.cubeia.bank.common.vo.UserVo;

import java.util.List;

public interface WalletService {

    ApiResponse<UserVo> getUserBalance (UserParam param);

    ApiResponse<String> transferFund (TransactionParam param);

    ApiResponse<List<TransactionVo>> getTransactionsList ();

    ApiResponse<String> createAccount (UserParam param);



}
