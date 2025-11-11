package entity;

public class Todolist {

    private int id;

    private String todo;

    public Todolist(){

    }

    public Todolist(String todo){
        this.todo = todo;
    }

    public String getTodo(){
        return  todo;
    }

    public void setTodo(String todo){
        this.todo = todo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
