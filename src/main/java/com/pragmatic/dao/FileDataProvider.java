package main.java.com.pragmatic.dao;

import main.java.com.pragmatic.model.Account;
import main.java.com.pragmatic.model.Currency;
import main.java.com.pragmatic.model.Transaction;
import main.java.com.pragmatic.model.User;
import main.java.com.pragmatic.repository.CurrencyRepository;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class FileDataProvider {
    private final String filePath;
    private final String table;
    private final File file;

    public FileDataProvider(String table) {
//        load file
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main" , "java", "com", "pragmatic", "dao", "tables", table.toLowerCase());
//        C:\Users\vitalii.tsomyk\Desktop\Bank_client\src\main\java\com\pragmatic\dao\tables
        this.filePath = path.toString();
        System.out.println("Initiated: ");
        System.out.println(this.filePath);
        File f = new File(this.filePath);

//        перевірка на існування файла і його створення, якщо що. Можна ремувнути на майбутнє, і
//        кидати ексепшен на запуск коду, якщо файлу нема.
        if  (!f.exists()) {
            try {
                f.createNewFile();
            }
            catch (Exception e) {
                System.out.println("Can not create destination file");
            }

            System.out.println("table has been created with name: " + table.toLowerCase());
        }
        else  {
            //If file exists, no comments to output
        }

        this.table = table;

        this.file = f;
    }

    public List<User>  initUserData() {
        try (Scanner reader = new Scanner(this.file)) {
            if (reader.hasNextLine()) {
                reader.nextLine();
            }

            List<User> users = new ArrayList<>();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                users.add(UserConverter.parseUser(data));
            }
            return users;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("User parse exception. " + e.toString());
        }
        return null;
    }

    public List<Currency>  initCurrencyData() {
        try (Scanner reader = new Scanner(this.file)) {
            if (reader.hasNextLine()) {
                reader.nextLine();
            }

            List<Currency> currencies = new ArrayList<>();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                currencies.add(CurrencyConverter.parseCurrency(data));


            }
            return currencies;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Currency parse exception. " + e.toString());
        }
        return null;
    }


    public List<Account> initAccountData(){
        try (Scanner reader = new Scanner(this.file)) {
            if (reader.hasNextLine()) {
                reader.nextLine();
            }

            List<Account> accounts = new ArrayList<>();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                accounts.add(AccountConverter.parseAccount(data));
            }
            return accounts;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Account parse exception. " + e.toString());
        }
        return null;
    }

    public List<Transaction> initTransactionData() {

        try (Scanner reader = new Scanner(this.file)) {
            if (reader.hasNextLine()) {
                reader.nextLine();
            }

            List<Transaction> transactions = new ArrayList<>();
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                transactions.add(TransactionConverter.parseTransaction(data));
            }
            return transactions;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Transaction parse exception. " + e.toString());
        }
        return null;

    }


    public Boolean writeFile(String content) {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.write("\n" + content);

            System.out.println("Success file write");

            writer.close();


//           результат успнішного закінчення методу і запису в файл
            return true;
        }
        catch (IOException e) {
            System.out.println("There is no output file created");
        }
        return  false;
    }

    public  String readLine(Integer lineNumber) {
        if (lineNumber >= 1) {
            try (Stream<String> lines = Files.lines(Paths.get(this.filePath))) {
                return lines.skip(lineNumber - 1).findFirst().get();
            } catch (Exception e) {
                System.out.println("Read file exception.");

            }
        }
        return "";
    }

}
