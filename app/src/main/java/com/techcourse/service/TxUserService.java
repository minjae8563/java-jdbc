package com.techcourse.service;

import com.interface21.transaction.support.TransactionManager;
import com.techcourse.domain.User;

public class TxUserService {

    private final UserService userService;
    private final TransactionManager transactionManager;

    public TxUserService(final UserService userService, final TransactionManager transactionManager) {
        this.userService = userService;
        this.transactionManager = transactionManager;
    }

    public User findById(final long id) {
        return userService.findById(id);
    }

    public void changePassword(final long id, final String newPassword, final String createdBy) {
        transactionManager.execute(() -> {
            userService.changePassword(id, newPassword, createdBy);
            return null;
        });
    }
}
