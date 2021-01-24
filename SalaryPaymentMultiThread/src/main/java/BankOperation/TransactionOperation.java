package BankOperation;

import fileOperation.FileReadOperation;
import fileOperation.FileWriteOperation;
import model.Inventory;
import model.Payment;
import model.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class TransactionOperation implements Runnable {
    private static int COUNT = 0;
    private List<Payment> paymentList;
    private Payment debtorPayment;

    public TransactionOperation() {
    }

    public TransactionOperation(List<Payment> paymentList, Payment debtorPayment) {
        this.paymentList = paymentList;
        this.debtorPayment = debtorPayment;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public Payment getDebtorPayment() {
        return debtorPayment;
    }

    public void setDebtorPayment(Payment debtorPayment) {
        this.debtorPayment = debtorPayment;
    }

    @Override
    public void run() {
        try {
            updateInventoryAndSaveTransaction();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void updateInventoryAndSaveTransaction() throws IOException {
        synchronized (TransactionOperation.class) {
            FileReadOperation fileReadOperation = new FileReadOperation();
            List<Inventory> inventoryList = fileReadOperation.readInventoryFile();
            Inventory debtorInventory = new Inventory();
            for (Inventory inventory : inventoryList) {
                if (debtorPayment.getDepositNumber().equals(inventory.getDepositNumber())) {
                    debtorInventory = inventory;
                }
            }
            for (Payment payment : getPaymentList()) {
                for (Inventory inventory : inventoryList) {
                    if (payment.getDepositNumber().equals(inventory.getDepositNumber())) {
                        inventory.setAmount(payment.getAmount().add(inventory.getAmount()));
                        saveTransaction(new Transaction(debtorInventory.getDepositNumber(), inventory.getDepositNumber(), payment.getAmount()));
                    }
                }
            }
            if (COUNT == 0) {
                for (Inventory inventory : inventoryList) {
                    if (inventory.getDepositNumber().equals(debtorPayment.getDepositNumber())) {
                        inventory.setAmount(inventory.getAmount().subtract(debtorPayment.getAmount()));
                    }
                }
                COUNT++;
            }
            saveNewInventories(inventoryList);

        }
    }

    private void saveNewInventories(List<Inventory> inventoryList) throws IOException {
        FileWriteOperation fileWriteOperation = new FileWriteOperation();
        fileWriteOperation.updateInventoryFile(inventoryList);
    }

    private void saveTransaction(Transaction transaction) throws IOException {
        FileWriteOperation fileWriteOperation = new FileWriteOperation();
        fileWriteOperation.writeTranactionFile(transaction);
    }

    public void checkIfTransacionsPossible() throws Exception {
        BigDecimal debtorInventory = new BigDecimal(0);
        String debtorDepositNumber = "";
        BigDecimal debtorPayment = new BigDecimal(0);
        FileReadOperation fileReadOperation = new FileReadOperation();
        List<Payment> paymentList = fileReadOperation.readPaymentFile();
        List<Inventory> inventoryList = fileReadOperation.readInventoryFile();
        for (Payment payment : paymentList) {
            if (payment.isDebtor()) {
                debtorPayment = payment.getAmount();
                debtorDepositNumber = payment.getDepositNumber();
            }
        }
        for (Inventory inventory : inventoryList) {
            if (inventory.getDepositNumber().equals(debtorDepositNumber)) {
                debtorInventory = inventory.getAmount();
            }
        }
        if (debtorInventory.compareTo(debtorPayment) > 0) {
            System.out.println("Transactions are allowed!");
        } else {
            throw new Exception("Transactions failed! Not Enough Inventory!");
        }
    }
}
