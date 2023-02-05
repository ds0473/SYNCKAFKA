package com.nt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindAccountNumber {

	private static List<String> listofAccounts = Arrays.asList("STS1011", "STS1022", "STS1033", "STS1044", "1055");

	public static String getAccountNumber(String accountNo) {
		Optional<String> accno = listofAccounts.stream().filter(b -> b.equals(accountNo)).findFirst();

		System.out.println("hello world from account");
		return accno.isPresent() ? "Given account number is identified" : "Account number does not exist";
	}

}
