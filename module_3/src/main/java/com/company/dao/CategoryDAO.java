package com.company.dao;

import java.util.List;

public interface CategoryDAO<A, B> {
    List<A> getIncomeCategory();

    List<B> getExpenseCategory();

}
