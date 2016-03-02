package com.logic.propositional.formulas;

import com.logic.propositional.formulas.types.AtomicFormula;

import java.util.HashMap;
import java.util.Map;

/**
 * Evaluation of atomic formulas
 */
public class Evaluation {
    private Map<AtomicFormula, Value> evaluation = new HashMap<>();

    public void add(AtomicFormula formula, Value value) {
        evaluation.put(formula, value);
    }

    public Value get(AtomicFormula formula) {
        return evaluation.get(formula);
    }
}
