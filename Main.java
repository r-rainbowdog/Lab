class LinkedList {

    private static class Node {
        String value;
        Node next;
        Node prev;

        Node(String value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    // Додавання в кінець списку
    public void add(String arrayValue) {
        Node newNode = new Node(arrayValue);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Вставка на початок списку
    public void addFirst(String value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Вставка за індексом
    public void addAtIndex(int index, String value) {
        if (index == 0) {
            addFirst(value);
            return;
        }

        Node current = head;
        int currentIndex = 0;

        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        if (current == null) {
            add(value); // Якщо індекс більший за розмір списку, додаємо в кінець
        } else {
            Node newNode = new Node(value);
            newNode.prev = current.prev;
            newNode.next = current;
            if (current.prev != null) {
                current.prev.next = newNode;
            }
            current.prev = newNode;
        }
    }

    // Видалення за значенням
    public void removeByValue(String value) {
        Node current = head;

        while (current != null) {
            if (current.value.equals(value)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }

    // Видалення за індексом
    public void removeByIndex(int index) {
        if (index < 0) return;

        Node current = head;
        int currentIndex = 0;

        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        if (current != null) {
            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next;
            }

            if (current.next != null) {
                current.next.prev = current.prev;
            } else {
                tail = current.prev;
            }
        }
    }

    // Перевірка коректності значення (не використовується в цьому коді, але залишено на випадок потреби)
    private boolean isValidArrayValue(String value) {
        return value.matches("^[0-9a-fA-F]+$");
    }

    // Вивід списку в прямому порядку
    public void printForward() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Вивід списку в зворотному порядку
    public void printBackward() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.prev;
        }
        System.out.println();
    }

    // Видалення перших n елементів
    public void removeNElementsFromStart(int n) {
        while (n > 0 && head != null) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            n--;
        }
    }

    public static void main(String[] args) {
        LinkedList arrayList = new LinkedList();

        arrayList.add("1A");
        arrayList.add("F");
        arrayList.add("2B");
        arrayList.add("10");

        System.out.println("Прямий порядок (початковий):");
        arrayList.printForward();

        System.out.println("Зворотний порядок (початковий):");
        arrayList.printBackward();

        // Видалення перших 2 елементів
        arrayList.removeNElementsFromStart(2);
        System.out.println("Після видалення перших 2 елементів:");
        arrayList.printForward();

        // Вставка на початок
        arrayList.addFirst("3C");
        System.out.println("Після вставки на початок (3C):");
        arrayList.printForward();

        // Вставка за індексом
        arrayList.addAtIndex(1, "4D");
        System.out.println("Після вставки на індекс 1 (4D):");
        arrayList.printForward();

        // Видалення за значенням
        arrayList.removeByValue("2B");
        System.out.println("Після видалення елемента з значенням '2B':");
        arrayList.printForward();

        // Видалення за індексом
        arrayList.removeByIndex(1);
        System.out.println("Після видалення елемента на індексі 1:");
        arrayList.printForward();
    }
}
