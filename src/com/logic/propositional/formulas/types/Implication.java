package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.Evaluation;
import com.logic.propositional.formulas.FormulaType;
import com.logic.propositional.formulas.Value;

/**
 * Represents imply relationship between two formulas
 */
public class Implication extends BinaryFormula {

    public Implication(Formula formula1, Formula formula2) {
        super(FormulaType.IMPLICATION, "→", formula1, formula2);
    }

    @Override
    public Value evaluate(Evaluation evaluation) {
        if (subformula1.evaluate(evaluation) == Value.TRUE && subformula2.evaluate(evaluation) == Value.FALSE)
            return Value.FALSE;
        else
            return Value.TRUE;
    }
}
