package bank;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import Bank.AccountPrx;
import Bank.AccountPrxHelper;
import Bank.BankManagerPrx;
import Bank.BankManagerPrxHelper;
import Bank.PersonalData;
import Bank.PremiumAccountPrx;
import Bank.PremiumAccountPrxHelper;
import Bank.accountType;

public class Client {
	
	private BufferedReader br = null;
	
	private String line = "";
	
	private Ice.Communicator ic = null;
	
	private int status = 0;
	
	private BankManagerPrx manager = null;
	
	private String thisLine() {
		return line;
	}
	
	private String nextLine() throws IOException {
		line = br.readLine();
		return line;
	}
	
	public Client() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private void input() throws IOException {
		System.out.println("[n] - nowe konto\n[p] - istniejace konto premium\n[s] - istniejace konto silver");
		if (nextLine().equals("p")) {
			existingAccountPremiumInput();
		} else if (thisLine().equals("s")) {
			existingAccountSilverInput();
		} else if (thisLine().equals("n")){
			newAccountInput();
		} else {
			System.out.println("Niepoprawna komenda " + thisLine());
			input();
		}
	}
	
	private void existingAccountPremiumInput() throws IOException {
		System.out.println("Podaj numer konta");
		String line = nextLine();
		
		try {
			Ice.ObjectPrx accountBase = ic.stringToProxy("premium/" + line + ":tcp -p 10000:ssl -p 12001:udp -p 10000");
			PremiumAccountPrx account = PremiumAccountPrxHelper.checkedCast(accountBase);
			premiumInput(account);
		} catch (Exception e) {
			System.out.println("Wystapil blad.");
			existingAccountPremiumInput();
		}
		
		
	}
	
	private void premiumInput(PremiumAccountPrx account) {
		System.out.println("[t] - transfer\n[l] - pozyczka");
	}
	
	private void existingAccountSilverInput() throws IOException {
		System.out.println("Podaj numer konta");
		String line = nextLine();
		
		try {
			Ice.ObjectPrx accountBase = ic.stringToProxy("silver/" + line + ":tcp -p 10000:ssl -p 12001:udp -p 10000");
			AccountPrx account = AccountPrxHelper.checkedCast(accountBase);
			silverInput(account);
		} catch (Exception e) {
			System.out.println("Wystapil blad.");
			existingAccountPremiumInput();
		}
	}
	
	private void silverInput(AccountPrx account) {
		System.out.println("[t] - transfer");
	}
	
	private void newAccountInput() {
		System.out.println("Podaj swoje dane.\nImie");
	}

	public static void main(String[] args) {
		
		Client client = new Client();
		
		client.status = 0;
		
		try {
			client.ic = Ice.Util.initialize(args);
			
			Ice.ObjectPrx managerBase = client.ic.propertyToProxy("SR.Manager");
			client.manager = BankManagerPrxHelper.checkedCast(managerBase);
			
			
			
			
			client.input();
			
			
			
			
//			Ice.StringHolder idHolder = new Ice.StringHolder();
//			manager.createAccount(new PersonalData("Tomasz", "Palys", "1234"), accountType.PREMIUM, idHolder);
//			
//			System.out.println(idHolder.value);
//			
//			Ice.ObjectPrx accountBase = ic.stringToProxy("premium/" + idHolder.value + ":tcp -p 10000:ssl -p 12001:udp -p 10000");
//			PremiumAccountPrx account = PremiumAccountPrxHelper.checkedCast(accountBase);
//			
//			account.transfer("1", 2000);
//			System.out.println(account.getBalance());
//			System.out.println(account.getAccountNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
