package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.FormulaType;

import java.util.HashSet;
import java.util.Set;

/**
 * Atomic logical formula
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
    public Set<Character> getAllAtomicFormulas() {
        Set<Character> result = new HashSet<>();
        result.add(name);
        return result;
    }

    public boolean equals(Formula other) {
        return super.equals(other) && name == ((AtomicFormula) other).name;
    }
}
