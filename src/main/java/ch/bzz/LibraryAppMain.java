package ch.bzz;
import java.sql.*;
import java.util.Scanner;

public class LibraryAppMain {


    private static final Book
            BOOK_1 = new Book(1, "978-3-8362-9544-4", "Java ist auch eine Insel", "Christian Ullenboom", 2023),
            BOOK_2 = new Book(2, "978-3-658-43573-8", "Grundkurs Java", "Dietmar Abts", 2024);

    public static void main(String[] args) throws SQLException {

        try (Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost/localdb", "localuser", "")) {
            try (Statement stmt = con.createStatement()) {
                try (ResultSet resultSet = stmt.executeQuery("SELECT * FROM BOOKS")) {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(metaData.getColumnName(i) + "\t");
                    }
                    System.out.println();

                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(resultSet.getString(i) + "\t");
                        }
                        System.out.println();
                    }
                }
            }

        } catch (Error e) {
            System.out.println(e);
            return;
        };
        System.out.println("HelloWorld");
        Scanner myObj = new Scanner(System.in);
        String command = "";
        boolean running = true;

        while (running) {
            System.out.println("Gebe einen Befehl an: ");
            command = myObj.nextLine();

            if (command.equals("quit")) {
                System.out.println("Programm wird beendet.");
                running = false;
            } else if (command.equals("help")) {
                System.out.println("Liste der Befehle:");
                System.out.println("quit - Programm beenden");
                System.out.println("help - Liste der Befehle anzeigen");
            }
            else if (command.equals("listBooks")){
                System.out.println("Liste der Bücher:");
                System.out.println(BOOK_1);
                System.out.println(BOOK_2);
            }else {
                System.out.println("Unbekannter Befehl: " + command);

            }
        }
        myObj.close();

    }
}
