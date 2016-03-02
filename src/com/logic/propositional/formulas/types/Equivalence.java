package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.Evaluation;
import com.logic.propositional.formulas.FormulaType;
import com.logic.propositional.formulas.Value;

/**
 * Represents imply relationship between two formulas
 */
public class Equivalence extends BinaryFormula {

    public Equivalence(Formula formula1, Formula formula2) {
        super(FormulaType.EQUIVALENCE, "↔", formula1, formula2);
    }

    @Override
    public Value evaluate(Evaluation evaluation) {
        if (subformula1.evaluate(evaluation) == subformula2.evaluate(evaluation))
            return Value.TRUE;
        else
            return Value.FALSE;
    }
}
