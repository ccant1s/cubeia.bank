package com.example.cubeia.bank.service.impl;

import com.example.cubeia.bank.common.param.TransactionParam;
import com.example.cubeia.bank.common.param.UserParam;
import com.example.cubeia.bank.common.util.ApiResponse;
import com.example.cubeia.bank.common.vo.TransactionVo;
import com.example.cubeia.bank.common.vo.UserVo;
import com.example.cubeia.bank.service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final List<UserVo> userVoList = new ArrayList<>();
    private final List<TransactionVo> transactionVoList = new ArrayList<>();

    public WalletServiceImpl() {
        init(); // Initialize with temporary values
    }

    private void init() {
        userVoList.add(new UserVo("temp112", "Clint", "Cee", new BigDecimal("30000.00"), getCurrentTimestamp()));
        userVoList.add(new UserVo("temp114", "Dee", "Sy", new BigDecimal("10000.00"), getCurrentTimestamp()));
        transactionVoList.add(new TransactionVo("123123545", "1", "temp112", "temp114", new BigDecimal("1.00")));
    }

    @Override
    public ApiResponse<UserVo> getUserBalance(UserParam param) {
        return userVoList.stream()
                .filter(user -> user.getAccountId().equals(param.getAccountId()))
                .findFirst()
                .map(user -> new ApiResponse<>(200, "User balance retrieved successfully", user))
                .orElseGet(() -> new ApiResponse<>(404, "User not found", null));
    }

    @Override
    public ApiResponse<String> transferFund(TransactionParam param) {
        Optional<UserVo> fromUserOpt = userVoList.stream().filter(user -> user.getAccountId().equals(param.getFrom())).findFirst();
        Optional<UserVo> toUserOpt = userVoList.stream().filter(user -> user.getAccountId().equals(param.getTo())).findFirst();

        if (fromUserOpt.isEmpty() || toUserOpt.isEmpty()) {
            return new ApiResponse<>(404, "User should be an existing account", "Failure");
        }

        UserVo fromUser = fromUserOpt.get();
        UserVo toUser = toUserOpt.get();

        if (fromUser.getAccountBalance().compareTo(param.getAmount()) < 0) {
            return new ApiResponse<>(400, "Insufficient balance", "Failure");
        }

        fromUser.setAccountBalance(fromUser.getAccountBalance().subtract(param.getAmount()));
        toUser.setAccountBalance(toUser.getAccountBalance().add(param.getAmount()));

        transactionVoList.add(new TransactionVo(generateUUID(), "1", param.getFrom(), param.getTo(), param.getAmount()));

        return new ApiResponse<>(200, "Funds transferred successfully", "Success");
    }

    @Override
    public ApiResponse<List<TransactionVo>> getTransactionsList() {
        return new ApiResponse<>(200, "Transaction list retrieved successfully", transactionVoList);
    }

    @Override
    public ApiResponse<String> createAccount(UserParam param) {
        if (userVoList.stream().anyMatch(user -> user.getAccountId().equals(param.getAccountId()))) {
            return new ApiResponse<>(409, "Account with ID '" + param.getAccountId() + "' already exists.", "Failure");
        }

        UserVo newUser = new UserVo(param.getAccountId(), param.getFirstName(), param.getLastName(), param.getAccountBalance(), getCurrentTimestamp());
        userVoList.add(newUser);

        return new ApiResponse<>(201, "Account created successfully.", "Success");
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
