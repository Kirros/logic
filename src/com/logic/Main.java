package com.logic;

import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Formula;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Formula a = new AtomicFormula('A');
        Formula b = new AtomicFormula('A');
        Map<Formula, String> map = new HashMap<>();
        map.put(a, "TEST");

        a.equal(b);
    }
}
