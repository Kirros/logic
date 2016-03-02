package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.FormulaType;

import java.util.List;

/**
 * Abstract constructor for binary formulas
 */
public abstract class BinaryFormula extends Formula {
    private final String symbol;

    protected final Formula subformula1;
    protected final Formula subformula2;

    protected BinaryFormula(FormulaType type, String symbol, Formula formula1, Formula formula2) {
        super(type);
        this.symbol = symbol;
        this.subformula1 = formula1;
        this.subformula2 = formula2;
    }

    @Override
    public String toString() {
        return "(" + subformula1.toString() + " " + symbol + " " + subformula2.toString() + ")";
    }

    @Override
    public boolean hasSubformula(Formula other) {
        return this.equals(other) || subformula1.hasSubformula(other) || subformula2.hasSubformula(other);
    }

    @Override
    public List<Formula> getAllSubformulas() {
        List<Formula> result = subformula1.getAllSubformulas();
        List<Formula> toAdd = subformula2.getAllSubformulas();

        addToSubformulaList(result, subformula1);

        for (Formula formula : toAdd) {
            addToSubformulaList(result, formula);
        }

        addToSubformulaList(result, subformula2);

        return result;
    }

    @Override
    public List<AtomicFormula> getAllAtomicFormulas() {
        List<AtomicFormula> result = subformula1.getAllAtomicFormulas();
        List<AtomicFormula> toBeAdded = subformula2.getAllAtomicFormulas();

        for (AtomicFormula atomicFormula : toBeAdded) {
            if (result.contains(atomicFormula))
                continue;

            result.add(atomicFormula);
        }

        result.sort(AtomicFormula.getComparator());
        return result;
    }

    public boolean equals(Object other) {
        return super.equals(other) &&
                ((subformula1.equals(((BinaryFormula) other).subformula1) && subformula2.equals(((BinaryFormula) other).subformula2)) ||
                        (subformula1.equals(((BinaryFormula) other).subformula2) && subformula2.equals(((BinaryFormula) other).subformula1)));
    }
}
