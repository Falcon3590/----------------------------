import java.util.Iterator;

public class SpreadLinkedList {
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(123, "Игорь Анатольевич Данилов", "+7 (964) 362-40-24"));
        contactList.addToEnd(new Contact(151, "Михаил Владимирович Донской", "+7 (979) 366-91-54"));
        contactList.addToEnd(new Contact(332, "Евгений Валентинович Касперский", "+7 (920) 977-93-37"));
        contactList.addToEnd(new Contact(432, "Лев Николаевич Королев", "+7 (990) 209-88-11"));
        contactList.addToEnd(new Contact(556, "Илья Валентинович Сегалович", "+7 (980) 711-23-54"));
        contactList.addToEnd(new Contact(556, "Дмитрий Витальевич Скляров", "+7 (950) 516-75-96"));
        contactList.addToEnd(new Contact(556, "Алекса́ндр Алекса́ндрович Степа́нов", "+7 (974) 989-17-78"));
        contactList.addToEnd(new Contact(556, "Андрей Николаевич Терехов", "+7 (972) 567-15-25"));

        for(Contact contact: contactList) {
            System.out.println(contact);
        }

        contactList.reverse();

        System.out.println("------------------------");

        for(Contact contact: contactList) {
            System.out.println(contact);
        }
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    public static class SingleLinkList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}
