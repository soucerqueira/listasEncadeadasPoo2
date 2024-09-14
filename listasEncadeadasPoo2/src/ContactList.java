import javax.swing.JTextArea;

public class ContactList {
    private Node head;

    public ContactList() {
        head = null;
    }

    public void addContact(Contact contact) {
        Node newNode = new Node(contact);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public Contact searchContact(String nameOrPhoneNumber) {
        Node current = head;
        while (current != null) {
            if (current.getContact().getName().equals(nameOrPhoneNumber) || current.getContact().getPhoneNumber().equals(nameOrPhoneNumber)) {
                return current.getContact();
            }
            current = current.getNext();
        }
        return null;
    }

    public void removeContact(String nameOrPhoneNumber) {
        if (head == null) return;
        if (head.getContact().getName().equals(nameOrPhoneNumber) || head.getContact().getPhoneNumber().equals(nameOrPhoneNumber)) {
            head = head.getNext();
            return;
        }
        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getContact().getName().equals(nameOrPhoneNumber) || current.getNext().getContact().getPhoneNumber().equals(nameOrPhoneNumber)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
    }

    public void listContacts(JTextArea textArea) {
        Node current = head;
        textArea.setText("");
        while (current != null) {
            textArea.append(current.getContact().toString() + "\n");
            current = current.getNext();
        }
    }
}