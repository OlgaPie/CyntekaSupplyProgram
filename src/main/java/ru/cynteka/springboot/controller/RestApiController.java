package ru.cynteka.springboot.controller;

import ru.cynteka.springboot.model.Account;
import ru.cynteka.springboot.service.AccountService;
import ru.cynteka.springboot.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    AccountService accountService;


    @RequestMapping(value = "/account/", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> listAllAccounts() {
        List<Account> accounts = accountService.findAllAccounts();
        if (accounts.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // or return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }


    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(@PathVariable("id") long id) {
        logger.info("Fetching Account with id {}", id);
        Account account = accountService.findById(id);
        if (account == null) {
            logger.error("Account with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Account with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }


    @RequestMapping(value = "/account/", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody Account account, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Account : {}", account);

        if (accountService.isAccountExist(account)) {
            logger.error("Unable to create. A Account with name {} already exist", account.getPayer());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Account with name " +
                    account.getProject() + " already exist."), HttpStatus.CONFLICT);
        }
        accountService.saveAccount(account);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/account/{id}").buildAndExpand(account.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
        logger.info("Updating Account with id {}", id);

        Account currentAccount = accountService.findById(id);

        if (currentAccount == null) {
            logger.error("Unable to update. Account with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Account with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentAccount.setAccount(account.getAccount());
        currentAccount.setProject(account.getProject());
        currentAccount.setSupplier(account.getSupplier());
        currentAccount.setPayer(account.getPayer());
        currentAccount.setSum(account.getSum());
        currentAccount.setStatus(account.getStatus());

        accountService.updateAccount(currentAccount);
        return new ResponseEntity<Account>(currentAccount, HttpStatus.OK);
    }


    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Account with id {}", id);

        Account account = accountService.findById(id);
        if (account == null) {
            logger.error("Unable to delete. Account with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Account with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        accountService.deleteAccountById(id);
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/account/", method = RequestMethod.DELETE)
    public ResponseEntity<Account> deleteAllAccounts() {
        logger.info("Deleting All Accounts");

        accountService.deleteAllAccounts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}