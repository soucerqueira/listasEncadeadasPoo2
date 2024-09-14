import javax.swing.*;
import java.util.Scanner;

public class ContactManager {
    private ContactList contactList;
    private JTextArea contactArea; // Create a JTextArea object

    public ContactManager() {
        contactList = new ContactList();
        contactArea = new JTextArea(); // Initialize the JTextArea object
    }

    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu before each iteration
            System.out.println("Sistema de Gerenciamento de Contatos");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Buscar Contato");
            System.out.println("3. Remover Contato");
            System.out.println("4. Listar Contatos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma Opção: ");

            String optionInput = scanner.nextLine(); // Read the entire line
            int option;

            try {
                option = Integer.parseInt(optionInput); // Parse the input to an integer
            } catch (NumberFormatException e) {
                System.out.println("Opção Inválida. Por favor, digite um número inteiro.");
                continue; // Skip the rest of the loop and ask for input again
            }

            switch (option) {
                case 1:
                    System.out.print("Digite o Nome Completo: ");
                    String name = scanner.nextLine(); // Use scanner.nextLine() for full name
                    System.out.print("Digite o Número de Telefone: ");
                    String phoneNumber = scanner.nextLine(); // Use scanner.nextLine() for phone number
                    System.out.print("Digite o E-mail: ");
                    String email = scanner.nextLine(); // Use scanner.nextLine() for email
                    Contact contact = new Contact(name, phoneNumber, email);
                    contactManager.contactList.addContact(contact);
                    break;
                case 2:
                    System.out.print("Digite o Nome ou Número de Telefone Para Buscar: ");
                    String searchQuery = scanner.nextLine(); // Use scanner.nextLine() for search query
                    Contact foundContact = contactManager.contactList.searchContact(searchQuery);
                    if (foundContact != null) {
                        System.out.println("Contato Encontrado: " + foundContact.toString());
                    } else {
                        System.out.println("Contato Não Encontrado");
                    }
                    break;
                case 3:
                    System.out.print("Digite o Nome ou Número de Telefone Para Remover: ");
                    String removeQuery = scanner.nextLine(); // Use scanner.nextLine() for remove query
                    contactManager.contactList.removeContact(removeQuery);
                    break;
                case 4:
                    contactManager.contactList.listContacts(contactManager.contactArea); // Pass the JTextArea object
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }
}