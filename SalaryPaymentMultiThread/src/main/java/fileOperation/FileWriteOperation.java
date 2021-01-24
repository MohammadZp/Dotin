package fileOperation;

import main.java.fileOperation.FileGenerator;
import model.Inventory;
import model.Payment;
import model.Transaction;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileWriteOperation {
    public void writePaymentFile(Payment payment) throws IOException {
        String s = payment.toString() + "\n";
        Files.write(
                Paths.get(FileGenerator.PaymentfilePath),
                s.getBytes(),
                StandardOpenOption.APPEND);
    }

    public void writeTranactionFile(Transaction transaction) throws IOException {
        String s = transaction.toString() + "\n";
        Files.write(
                Paths.get(FileGenerator.TransactionfilePath),
                s.getBytes(),
                StandardOpenOption.APPEND);
    }

    public void updateInventoryFile(List<Inventory> inventoryList) throws IOException {
        FileChannel.open(Paths.get(FileGenerator.InventoryfilePath), StandardOpenOption.WRITE).truncate(0).close();
        String s = "";
        for (Inventory inventory : inventoryList) {
            s += inventory.toString() + "\n";
        }
        Files.write(
                Paths.get(FileGenerator.InventoryfilePath),
                s.getBytes(),
                StandardOpenOption.APPEND);
    }
}


