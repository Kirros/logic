package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.Evaluation;
import com.logic.propositional.formulas.FormulaType;
import com.logic.propositional.formulas.Value;

import java.util.List;

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
    public List<Formula> getAllSubformulas() {
        List<Formula> result = subformula.getAllSubformulas();

        return addToSubformulaList(result, subformula);
    }

    @Override
    public Value evaluate(Evaluation evaluation) {
        if (subformula.evaluate(evaluation) == Value.TRUE)
            return Value.FALSE;
        else
            return Value.TRUE;
    }

    @Override
    public List<AtomicFormula> getAllAtomicFormulas() {
        return subformula.getAllAtomicFormulas();
    }

    public boolean equals(Object other) {
        return super.equals(other) && subformula.equals(((Negation) other).subformula);
    }
}
