package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.FormulaType;

import java.util.Set;

/**
 * Represents negation of original formula
 */
public class Negation extends Formula {

    private Formula subformula;

    public Negation(Formula formula) {
        super(FormulaType.NEGATION);
        this.subformula = formula;
    }

    @Override
    public String toString() {
        return "\u00ac" + subformula.toString();
    }

    @Override
    public boolean hasSubformula(Formula other) {
        return equals(other) || subformula.hasSubformula(other);
    }

    @Override
    public Set<Character> getAllAtomicFormulas() {
        return subformula.getAllAtomicFormulas();
    }


    public boolean equals(Formula other) {
        return super.equals(other) && subformula.equals(((Negation) other).subformula);
    }
}
