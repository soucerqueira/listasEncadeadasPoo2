import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ContactGUI extends JFrame {
    private ContactList contactList;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField searchField;
    private JTextArea contactArea;
    private JButton addButton;
    private JButton searchButton;
    private JButton removeButton;
    private JButton listButton;

    public ContactGUI() {
        contactList = new ContactList();

        // Criando componentes da interface
        nameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        emailField = new JTextField(20);
        searchField = new JTextField(20);
        contactArea = new JTextArea(10, 20);
        contactArea.setEditable(false); // Campo de texto não editável
        JScrollPane scrollPane = new JScrollPane(contactArea);

        // Botões em português
        addButton = new JButton("Adicionar Contato");
        searchButton = new JButton("Pesquisar Contato");
        removeButton = new JButton("Remover Contato");
        listButton = new JButton("Listar Contatos");

        // Painel de entrada de dados
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Layout em grid, todas as entradas alinhadas
        inputPanel.add(new JLabel("Nome Completo:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Número de Telefone:"));
        inputPanel.add(phoneNumberField);
        inputPanel.add(new JLabel("E-mail:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Pesquisar:"));
        inputPanel.add(searchField);

        // Painel de contatos (com rolagem)
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BorderLayout());
        contactPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(listButton);

        // Organizando os painéis principais
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10, 10));
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(contactPanel, BorderLayout.CENTER);

        // Adicionando componentes ao frame principal
        setLayout(new BorderLayout(10, 10));
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configurando os listeners dos botões
        addButton.addActionListener(new AddContactListener());
        searchButton.addActionListener(new SearchContactListener());
        removeButton.addActionListener(new RemoveContactListener());
        listButton.addActionListener(new ListContactsListener());

        // Configurações da janela
        setTitle("Sistema de Gerenciamento de Contatos Poo2");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abrir em tela cheia
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class AddContactListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();
            Contact contact = new Contact(name, phoneNumber, email);
            contactList.addContact(contact);
            nameField.setText("");
            phoneNumberField.setText("");
            emailField.setText("");
            contactArea.setText("Contato adicionado com sucesso!\n");
        }
    }

    private class SearchContactListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchQuery = searchField.getText();
            Contact foundContact = contactList.searchContact(searchQuery);
            if (foundContact != null) {
                contactArea.setText("Contato Encontrado: " + foundContact.toString());
            } else {
                contactArea.setText("Contato Não Encontrado");
            }
        }
    }

    private class RemoveContactListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String removeQuery = searchField.getText();
            contactList.removeContact(removeQuery);
            contactArea.setText("Contato removido com sucesso!\n");
        }
    }

    private class ListContactsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            contactList.listContacts(contactArea);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactGUI());
    }
}
