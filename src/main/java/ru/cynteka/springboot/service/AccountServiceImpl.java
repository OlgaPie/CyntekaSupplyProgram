package ru.cynteka.springboot.service;

import java.util.List;

import ru.cynteka.springboot.model.Account;
import ru.cynteka.springboot.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account findById(Long id) {
		return accountRepository.findOne(id);
	}

	public Account findByProject(String name) {
		return accountRepository.findByProject(name);
	}

	public void saveAccount(Account account) {
		accountRepository.save(account);
	}

	public void updateAccount(Account account){
		saveAccount(account);
	}

	public void deleteAccountById(Long id){
		accountRepository.delete(id);
	}

	public void deleteAllAccounts(){
		accountRepository.deleteAll();
	}

	public List<Account> findAllAccounts(){
		return accountRepository.findAll();
	}

	public boolean isAccountExist(Account account) {
		return findByProject(account.getProject()) != null;
	}

}
