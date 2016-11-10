package com.ecommerce.system.process;

public interface ProcessManager<T extends ProcessInstance<D>, D> {

    void removeProcess(T saga);

	D createNewProcessData();
}
