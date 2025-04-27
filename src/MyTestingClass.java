public class MyTestingClass {
    private final int id;
    public MyTestingClass(int id) { this.id = id; }

    @Override
    public int hashCode() {
        return Integer.rotateLeft(id * 31, 5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTestingClass)) return false;
        return id == ((MyTestingClass) o).id;
    }

    @Override
    public String toString() {
        return "Obj" + id;
    }
}
