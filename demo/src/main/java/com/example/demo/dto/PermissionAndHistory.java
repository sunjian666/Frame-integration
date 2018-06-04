package com.example.demo.dto;

import com.example.demo.domain.Function;

import java.util.List;

public class PermissionAndHistory {

    private List<Function> functionList;
    private List<Integer> historyList;

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }

    public List<Integer> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<Integer> historyList) {
        this.historyList = historyList;
    }

    @Override
    public String toString() {
        return "PermissionAndHistory{" +
                "functionList=" + functionList +
                ", historyList=" + historyList +
                '}';
    }
}
