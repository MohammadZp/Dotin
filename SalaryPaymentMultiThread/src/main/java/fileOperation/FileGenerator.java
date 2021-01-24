package main.java.fileOperation;

import model.Inventory;
import model.Payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class FileGenerator {
    public static final String PaymentfilePath = "main/java/src/files/PaymentFile.txt";
    public static final String InventoryfilePath = "src/files/InventoryFile.txt";
    public static final String TransactionfilePath = "src/files/TransactionFile.txt";

    public void createFiles() throws IOException {
        createPaymentFile();
        createInventoryFile();
        createTransactionFile();
    }

    public void createPaymentFile() throws IOException {
        if (Files.exists(Paths.get(PaymentfilePath))) {
            System.out.println(PaymentfilePath + " was created before!");
        } else {
            Files.createFile(Paths.get(PaymentfilePath));
            System.out.println("PaymentFile.txt Created in: " + PaymentfilePath);
        }
    }

    public void createInventoryFile() throws IOException {
        if (Files.exists(Paths.get(InventoryfilePath))) {
            System.out.println(InventoryfilePath + " was created before!");
        } else {
            Files.createFile(Paths.get(InventoryfilePath));
            System.out.println("InventoryFile.txt Created in: " + InventoryfilePath);
        }
    }

    public void createTransactionFile() throws IOException {
        if (Files.exists(Paths.get(TransactionfilePath))) {
            System.out.println(TransactionfilePath + " was created before!");
        } else {
            Files.createFile(Paths.get(TransactionfilePath));
            System.out.println("TransactionFile.txt Created in: " + TransactionfilePath);
        }
    }

    public void generateRandomPaymentData(int numberOfCreditors, int lowerBound, int upperBound) throws IOException {
        FileChannel.open(Paths.get(FileGenerator.PaymentfilePath), StandardOpenOption.WRITE).truncate(0).close();
        List<Payment> paymentList = new ArrayList<>();
        BigDecimal debtorAmount = new BigDecimal(0);
        fileOperation.FileWriteOperation fileWriteOperation = new fileOperation.FileWriteOperation();
        Set<Integer> amountSet = random(lowerBound, upperBound, numberOfCreditors);
        Set<Integer> lastDigitOfDepositNumber = random(lowerBound, upperBound, numberOfCreditors);
        Integer[] amounts = new Integer[amountSet.size()];
        for (Integer integer : amountSet) {
            debtorAmount = debtorAmount.add(new BigDecimal(integer.toString()));
        }
        fileWriteOperation.writePaymentFile(new Payment(true, "1.10.100.1", debtorAmount));
        amountSet.toArray(amounts);
        for (Integer integer : lastDigitOfDepositNumber) {
            paymentList.add(new Payment(false, "1.20.100." + integer));
        }
        int count = amountSet.size() - 1;
        for (Payment payment : paymentList) {
            payment.setAmount(new BigDecimal(amounts[count]));
            count--;
        }
        for (Payment payment : paymentList) {
            fileWriteOperation.writePaymentFile(payment);
        }
    }

    public Set<Integer> random(int lowerBound, int upperBound, int number) {
        Set<Integer> set = new LinkedHashSet<Integer>();
        while (set.size() < number) {
            set.add((int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound));
        }
        return set;
    }

    public void generateRandomInventories(List<Payment> paymentList, BigDecimal debtorInventory) throws IOException {
        List<Inventory> inventories = new ArrayList<>();
        fileOperation.FileWriteOperation fileWriteOperation = new fileOperation.FileWriteOperation();
        Set<Integer> randomInventorySet = random(0, 5000, paymentList.size());
        Integer[] randomInventoriesArray = new Integer[randomInventorySet.size()];
        randomInventorySet.toArray(randomInventoriesArray);
        int count = paymentList.size() - 1;
        for (Payment payment : paymentList) {
            if (payment.isDebtor()) {
                inventories.add(new Inventory(payment.getDepositNumber(), debtorInventory));
            } else {
                inventories.add(new Inventory(payment.getDepositNumber(), new BigDecimal(randomInventoriesArray[count])));
                count--;
            }
        }
        fileWriteOperation.updateInventoryFile(inventories);
    }

    public void clearTransactionFile() throws IOException {
        FileChannel.open(Paths.get(FileGenerator.TransactionfilePath), StandardOpenOption.WRITE).truncate(0).close();
    }

    public void clearPaymentFile() throws IOException {
        FileChannel.open(Paths.get(FileGenerator.PaymentfilePath), StandardOpenOption.WRITE).truncate(0).close();
    }

    public void clearInventoryFile() throws IOException {
        FileChannel.open(Paths.get(FileGenerator.InventoryfilePath), StandardOpenOption.WRITE).truncate(0).close();
    }
}
