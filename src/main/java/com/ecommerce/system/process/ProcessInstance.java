package com.ecommerce.system.process;

public class ProcessInstance<D> {
    protected D data;
    private boolean completed;

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    protected void markAsCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }
}
