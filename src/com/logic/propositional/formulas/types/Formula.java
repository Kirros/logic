package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.FormulaParser;
import com.logic.propositional.formulas.FormulaType;
import com.logic.propositional.formulas.TruthTable;
import com.logic.propositional.formulas.Value;

import java.util.Set;

/**
 * Represents propositional logic formula
 */
public abstract class Formula {
    Value value = Value.UNASSIGNED;
    final FormulaType type;

    Formula(FormulaType type) {
        this.type = type;
    }

    public Formula not() {
        return new Negation(this);
    }

    public Formula and(Formula other) {
        return new Conjunction(this, other);
    }

    @Override
    public String toString() {
        return "Should not see this.";
    }

    public abstract boolean hasSubformula(Formula other);

    public abstract Set<Character> getAllAtomicFormulas();

    public TruthTable getTruthTable() {
        return new TruthTable(this);
    }

    public boolean equals(Formula other) {
        return type == other.type;
    }

    /**
     * Create Formula object from string.
     * ! is for negation
     * & is for conjunction
     *
     * @param input string containing well formed formula.
     */
    public  static Formula fromString(String input){
        FormulaParser parser = new FormulaParser(input);

        return parser.getFormula();
    }
}
