package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.Evaluation;
import com.logic.propositional.formulas.FormulaType;
import com.logic.propositional.formulas.Value;

import java.util.*;

/**
 * Atomic logical formula representing proposition.
 */
public class AtomicFormula extends Formula {

    private char name;

    public AtomicFormula(char name) {
        super(FormulaType.ATOMIC);
        this.name = name;
    }

    @Override
    public String toString() {
        return Character.toString(name);
    }

    @Override
    public boolean hasSubformula(Formula other) {
        return this.equals(other);
    }

    @Override
    public List<Formula> getAllSubformulas() {
        return new ArrayList<>();
    }

    @Override
    public Value evaluate(Evaluation evaluation) {
        return evaluation.get(this);
    }

    @Override
    public List<AtomicFormula> getAllAtomicFormulas() {
        List<AtomicFormula> result = new ArrayList<>();
        result.add(this);
        return result;
    }

    public static Comparator<AtomicFormula> getComparator() {
        return (formula1, formula2) -> Character.compare(formula1.name, formula2.name);
    }

    public boolean equals(Object other) {
        return super.equals(other) && new Character(name).equals(((AtomicFormula) other).name);
    }
}
