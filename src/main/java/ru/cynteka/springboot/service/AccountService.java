package ru.cynteka.springboot.service;


import ru.cynteka.springboot.model.Account;

import java.util.List;

public interface AccountService {
	
	Account findById(Long id);

	Account findByProject(String name);

	void saveAccount(Account account);

	void updateAccount(Account account);

	void deleteAccountById(Long id);

	void deleteAllAccounts();

	List<Account> findAllAccounts();

	boolean isAccountExist(Account account);
}