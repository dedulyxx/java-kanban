package ru.practicum.java.manager;

public class Node<Task> {

    Task task;
    Node<Task> next;
    Node<Task> prev;

    public Node(Node<Task> prev, Task task, Node<Task> next) {
        this.task = task;
        this.next = next;
        this.prev = prev;
    }
}
