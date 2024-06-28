package ru.practicum.java.manager;

import ru.practicum.java.tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node> historyList = new HashMap<>();

    private Node<Task> head;
    private Node<Task> tail;
    int size = 0;

    @Override
    public void add(Task task) {
        LinkedListNew<Task> tasks = new LinkedListNew<>();
        remove(task.getId());
        Node node = tasks.linkLast(task);
        historyList.put(task.getId(),node);
    }

    @Override
    public void remove(Integer id) {
        Node node = historyList.remove(id);
        if(node == null) {
            return;
        }
        LinkedListNew<Task> tasks = new LinkedListNew<>();
        tasks.removeNode(node);
    }

    public void clear() {
        historyList.clear();
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public List<Task> getHistory() {
        LinkedListNew<Task> tasks = new LinkedListNew<>();
        List<Task> tasksList = tasks.getTasksInList();
        System.out.println(tasksList);
        return tasksList;
    }

    class LinkedListNew<Task> {

        public LinkedListNew() {

        }

        Node linkLast(Task task) {
                Node oldTail = tail;
                Node node = new Node(oldTail, task, null);
                tail = node;
                if (oldTail == null) {
                    head = node;
                } else {
                    oldTail.next = node;
                }
                size++;
                return node;
            }

//        public void linkFirst(Task task) {
//            Node oldHead = head;
//            Node node = new Node(null, task, oldHead);
//            head = node;
//            if (oldHead == null) {
//                tail = node;
//            } else {
//                oldHead.next = node;
//            }
//        }

        public Task removeNode(Node<Task> node) {
            final Task task = node.task;
            final Node next = node.next;
            final Node prev = node.prev;

            if (next == null) {
                tail = prev;
            } else {
                next.prev = prev;
                node.prev = null;
            }
            if (prev == null) {
                head = next;
            } else {
                prev.next = next;
                node.next = null;
            }
            node.task = null;
            return task;
        }



        List<Task> getTasksInList() {
            List<Task> tasksList = new ArrayList<>();
            Node<Task> node = (Node<Task>) head;
            for (int i = 0; i < size; i++) {
                if (node != null) {
                    tasksList.add(node.task);
                    node = node.next;
                }
            }
//            for (Node node : historyList.values()) {
//                if (node.task != null) {
//                    tasksList.add((Task) node.task);
//                }
//            }
            return tasksList;
        }
    }
}
