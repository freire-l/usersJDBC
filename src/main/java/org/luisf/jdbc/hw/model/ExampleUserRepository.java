package org.luisf.jdbc.hw.model;

import org.luisf.jdbc.hw.reposity.Repository;
import org.luisf.jdbc.hw.utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ExampleUserRepository {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        try (Connection conn = DataBaseConnection.getInstance()){
            Integer option;
            Repository<User> repository = new UserRepository();
            String[] data;

        do {
            System.out.println("Select Action from menu: \n" +
                    "Insert  : 1 \n" +
                    "Delete  : 2 \n" +
                    "Update  : 3 \n" +
                    "ListAll : 4 \n" +
                    "Exit    : 5 \n" );

            System.out.print("Selection -- ");
            option = scanner.nextInt();

            if(option >= 1 && option <= 5){
                if(option < 5){
                    switch (option){
                        case 1:
                            System.out.println("\n\n *********** INSERTING ********** \n"+
                                    "Enter: username, pwd, email");

                            data = scanner.next().split(",");
                            repository.store(new User(0L, data[0], data[1], data[2]));
                            System.out.println("Done\n\n");
                            break;

                        case 2:
                            System.out.println("\n\n *********** DELETING ********** \n"+
                                    "Enter: ID");
                            repository.delete(Long.valueOf(scanner.next()));
                            System.out.println("Done\n\n");
                            break;

                        case 3:
                            System.out.println("\n\n *********** UPDATING ********** \n"+
                                    "Enter: username, pwd, email, id");

                            data = scanner.next().split(",");
                            repository.store(new User(Long.valueOf(data[3]), data[0], data[1], data[2]));
                            System.out.println("Done\n\n");
                            break;

                        case 4:
                            System.out.println("\n\n *********** LISTING ********** \n");
                            repository.list().forEach(System.out::println);
                            System.out.println("Done\n\n");
                            break;
                    }
                }
            }
            else {
                System.out.println("Enter a valid option");
            }
        }while (option != 5);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
