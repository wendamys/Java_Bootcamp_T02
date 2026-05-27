class Counter {
    private int count = 0;

    // Ключевое слово synchronized гарантирует, что только
    // ОДИН поток может выполнять этот метод в конкретный момент времени.
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}