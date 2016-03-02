package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.Evaluation;
import com.logic.propositional.formulas.FormulaType;
import com.logic.propositional.formulas.Value;

import java.util.List;

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
    public Value evaluate(Evaluation evaluation) {
        if (subformula1.evaluate(evaluation) == Value.TRUE && subformula2.evaluate(evaluation) == Value.TRUE)
            return Value.TRUE;
        else
            return Value.FALSE;
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
                ((subformula1.equals(((Conjunction) other).subformula1) && subformula2.equals(((Conjunction) other).subformula2)) ||
                        (subformula1.equals(((Conjunction) other).subformula2) && subformula2.equals(((Conjunction) other).subformula1)));
    }
}
