package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.FormulaParser;
import com.logic.propositional.formulas.FormulaType;
import com.logic.propositional.formulas.TruthTable;
import com.logic.propositional.formulas.Value;

import java.util.List;
import java.util.Map;

/**
 * Represents propositional logic formula.
 */
public abstract class Formula {
    final FormulaType type;

    protected Formula(FormulaType type) {
        this.type = type;
    }

    /**
     * Factory method for negating given formula.
     */
    public Formula not() {
        return new Negation(this);
    }

    /**
     * Factory method for joining two formulas together with conjunction.
     */
    public Formula and(Formula other) {
        return new Conjunction(this, other);
    }

    @Override
    public String toString() {
        return "Should not see this.";
    }

    /**
     * Test if given formula is subformula of this formula.
     */
    public abstract boolean hasSubformula(Formula other);

    /**
     * Find logical value of formula given values of atomic formulas.
     * @param valueMap Mapping of values to each name of atomic formula.
     */
    public abstract Value evaluate(Map<AtomicFormula, Value> valueMap);

    /**
     * Returns sorted list of all Atomic formulas used in this formula
     */
    public abstract List<AtomicFormula> getAllAtomicFormulas();

    /**
     * Returns table evaluating all subformulas of this formula.
     */
    public TruthTable getTruthTable() {
        return new TruthTable(this);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Formula && type == ((Formula) other).type;
    }

    /**
     * Create Formula object from string.
     * ! is for negation
     * & is for conjunction
     * Can also contain brackets and atomic formula names
     *
     * @param input string containing well formed formula.
     */
    public  static Formula fromString(String input){
        FormulaParser parser = new FormulaParser(input);

        return parser.getFormula();
    }
}
