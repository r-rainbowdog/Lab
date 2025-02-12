public class Stack {
    private int[] elements;
    private int size; // кiлькiсть елементiв у стеку
    private static final int INITIAL_CAPACITY = 10;

    public Stack() {
        elements = new int[INITIAL_CAPACITY];
        size = 0;
    }

    // Розширення масиву при потребi
    private void ensureCapacity() {
        if (size == elements.length) {
            int[] newElements = new int[elements.length * 2];
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    public void push(int value) {
        ensureCapacity();
        elements[size++] = value;
    }

    public void pushAt(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Некоректний індекс для вставки!");
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек порожній. Видалення неможливе!");
        }
        return elements[--size];
    }

    public int popAt(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("Стек порожній. Видалення неможливе!");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Некоректний індекс для видалення!");
        }
        int removedValue = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return removedValue;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек порожній. Огляд неможливий!");
        }
        return elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Вміст стеку після вставки: " + stack);

        stack.pushAt(1, 15);
        System.out.println("Вміст стеку після вставки числа 15 за індексом 1: " + stack);

        stack.popAt(2);
        System.out.println("Вміст стеку після видалення елемента за індексом 2: " + stack);
    }
}
