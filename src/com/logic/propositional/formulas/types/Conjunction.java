package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.FormulaType;

import java.util.Set;

/**
 * Represents and relationship between two formulas
 */
public class Conjunction extends Formula {

    private final Formula subformula1;
    private final Formula subformula2;

    public Conjunction(Formula formula1, Formula formula2) {
        super(FormulaType.CONJUNCTION);
        this.subformula1 = formula1;
        this.subformula2 = formula2;
    }


    @Override
    public String toString() {
        return "(" + subformula1.toString() + " \u2227 " + subformula2.toString() + ")";
    }

    @Override
    public boolean hasSubformula(Formula other) {
        return this.equals(other) || subformula1.hasSubformula(other) || subformula2.hasSubformula(other);
    }

    @Override
    public Set<Character> getAllAtomicFormulas() {
        Set<Character> result = subformula1.getAllAtomicFormulas();
        result.addAll(subformula2.getAllAtomicFormulas());
        return result;
    }

    public boolean equals(Formula other) {
        return super.equals(other) &&
                ((subformula1.equals(((Conjunction) other).subformula1) && subformula2.equals(((Conjunction) other).subformula2)) ||
                        (subformula1.equals(((Conjunction) other).subformula2) && subformula2.equals(((Conjunction) other).subformula1)));
    }
}
