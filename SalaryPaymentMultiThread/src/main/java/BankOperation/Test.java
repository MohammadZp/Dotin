package main.java.BankOperation;


import fileOperation.FileReadOperation;
import fj.data.Java;
import main.java.fileOperation.FileGenerator;
import model.Payment;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;
public class Test {
    private static int NUMBER_OF_THREADS=4;
    private static int NUMBER_OF_SUBLISTS=4;
    static Logger log = Logger.getLogger(Test.class.getName());
    public static void main(String[] args) throws IOException {
        FileGenerator fileGenerator = new FileGenerator();
        fileGenerator.createPaymentFile();
//        List<Payment> paymentList;
//        Scanner scanner = new Scanner(System.in);
//        BankOperation.TransactionOperation transactionOperation = new BankOperation.TransactionOperation();
//        FileGenerator fileGenerator = new FileGenerator();
//        FileReadOperation fileReadOperation = new FileReadOperation();
//        System.out.println("Enter 1 to use existed files or 2 to generate new data!");
//        int y = scanner.nextInt();
//        if (y==2) {
//            fileGenerator.createFiles();
//            fileGenerator.clearTransactionFile();
//            fileGenerator.clearInventoryFile();
//            fileGenerator.clearPaymentFile();
//            fileGenerator.generateRandomPaymentData(50, 1000, 6000);
//            paymentList = fileReadOperation.readPaymentFile();
//            Random random = new Random();
//            BigDecimal debtorInventory = new BigDecimal(random.nextInt(60000000 - 40000000) + 40000000);
//            fileGenerator.generateRandomInventories(paymentList, debtorInventory);
//        } else {
//            fileGenerator.clearTransactionFile();
//            paymentList = fileReadOperation.readPaymentFile();
//       }
//        System.out.println("Enter 1 to start transactions:");
//        int x = scanner.nextInt();
//        while (x != 1) {
//            System.out.println("Transactions not started yet!");
//            System.out.println("Enter 1 to start transactions:");
//            x = scanner.nextInt();
//        }
//
//        try {
//            transactionOperation.checkIfTransacionsPossible();
//            ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//            int paymentListSize = paymentList.size() - 1;
//            Payment debtorPayment = paymentList.get(0);
//            paymentList.remove(debtorPayment);
//            ////////////
//            final List<List<Payment>> result = splitList(paymentList, NUMBER_OF_SUBLISTS);
//            for (final List<Payment> entry : result) {
//               executorService.execute(new BankOperation.TransactionOperation(entry, debtorPayment));
//            }
////            List<Payment> subPaymentList1 = new ArrayList<>(paymentList.subList(0, (paymentListSize) / 4));
////            List<Payment> subPaymentList2 = new ArrayList<>(paymentList.subList((paymentListSize) / 4, (paymentListSize) / 2));
////            List<Payment> subPaymentList3 = new ArrayList<>(paymentList.subList((paymentListSize) / 2, (3 * paymentListSize) / 4));
////            List<Payment> subPaymentList4 = new ArrayList<>(paymentList.subList((3 * (paymentListSize)) / 4, paymentListSize));
////            BankOperation.TransactionOperation transactionOperationThread1 = new BankOperation.TransactionOperation(subPaymentList1, debtorPayment);
////            BankOperation.TransactionOperation transactionOperationThread2 = new BankOperation.TransactionOperation(subPaymentList2, debtorPayment);
////            BankOperation.TransactionOperation transactionOperationThread3 = new BankOperation.TransactionOperation(subPaymentList3, debtorPayment);
////            BankOperation.TransactionOperation transactionOperationThread4 = new BankOperation.TransactionOperation(subPaymentList4, debtorPayment);
////            executorService.execute(transactionOperationThread1);
////            executorService.execute(transactionOperationThread2);
////            executorService.execute(transactionOperationThread3);
////            executorService.execute(transactionOperationThread4);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
    }


    private static List<List<Payment>> splitList(final List<Payment> list, final int maxElement) {

        final List<List<Payment>> result = new ArrayList<List<Payment>>();

        final int div = list.size() / maxElement;

        System.out.println(div);

        for (int i = 0; i <= div; i++) {

            final int startIndex = i * maxElement;

            if (startIndex >= list.size()) {
                return result;
            }

            final int endIndex = (i + 1) * maxElement;

            if (endIndex < list.size()) {
                result.add(list.subList(startIndex, endIndex));
            } else {
                result.add(list.subList(startIndex, list.size()));
            }

        }

        return result;
    }
}


