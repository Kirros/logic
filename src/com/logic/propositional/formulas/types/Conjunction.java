package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.Evaluation;
import com.logic.propositional.formulas.FormulaType;
import com.logic.propositional.formulas.Value;

/**
 * Represents and relationship between two formulas
 */
public class Conjunction extends BinaryFormula {

    public Conjunction(Formula formula1, Formula formula2) {
        super(FormulaType.CONJUNCTION, "∧", formula1, formula2);
    }

    @Override
    public Value evaluate(Evaluation evaluation) {
        if (subformula1.evaluate(evaluation) == Value.TRUE && subformula2.evaluate(evaluation) == Value.TRUE)
            return Value.TRUE;
        else
            return Value.FALSE;
    }
}
