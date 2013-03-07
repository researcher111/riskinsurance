package com.riskIt.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.riskIt.controller.AgentManager;
import com.riskIt.controller.Requirements19_24;
import com.riskIt.controller.UserManager;
import com.riskIt.data.Invitation;

import junit.framework.TestCase;

public class RunTests extends TestCase {

	public void test() {

		// pads the entries by zero to accomodate for copy
		try {
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://honeypot/preist_riskinsurance",
					"root", "--password--");
			Statement stmt = (Statement) conn.createStatement();
			String query = "select SSN from userrecord limit 2000;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				String args = rs.getString("SSN");
				System.out.println("TestCase: Calculate Score");
				AgentManager agent;
				agent = new AgentManager();
				agent.calculateScore(Integer.parseInt(args));
				
				System.out.println("TestCase: Calculate CustomreQuotes");
				UserManager user;
				user = new UserManager();
				ArrayList<Invitation> inv1 = new ArrayList<Invitation>();
				Invitation cust1 = new Invitation();
				long ssn = Integer.parseInt(args);
				double deductible = 50;
				double worth = 10000;
				cust1 = user.calculateQuoteForCustomer(ssn, deductible, worth,
						inv1);
				cust1.getPremium();

				System.out
						.println("TestCase: Calculate Likeliness To Move Factor");
				Requirements19_24 requirements;
				requirements = new Requirements19_24();
				requirements.CalculateLikelinessToMoveFactor(Integer
						.parseInt(args));
			}
		} catch (Exception e) {
			System.out.println("exception");
		}
	}

}
