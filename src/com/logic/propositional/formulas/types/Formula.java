package com.logic.propositional.formulas.types;

import com.logic.propositional.formulas.*;

import java.util.List;

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

    /**
     * Factory method for joining two formulas together with disjunction.
     */
    public Formula or(Formula other) {
        return new Disjunction(this, other);
    }

    /**
     * Factory method for joining two formulas together with implication.
     */
    public Formula imply(Formula other) {
        return new Implication(this, other);
    }

    /**
     * Factory method for joining two formulas together with equivalence.
     */
    public Formula equal(Formula other) {
        return new Equivalence(this, other);
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
     * Get list of all subformulas in this formula. This function omits atomic subformulas.
     */
    public abstract List<Formula> getAllSubformulas();

    /**
     * Find logical value of formula given values of atomic formulas.
     * @param evaluation Mapping of values to each name of atomic formula.
     */
    public abstract Value evaluate(Evaluation evaluation);

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

    @Override
    public int hashCode() {
        return this.toString().hashCode();
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

    /**
     * Method for adding subformulas to lists of subformulas. This will not add Subformulas already present or atomic formulas.
     */
    protected List<Formula> addToSubformulaList(List<Formula> subformulas, Formula toBeAdded) {
        if (toBeAdded.type != FormulaType.ATOMIC && !subformulas.contains(toBeAdded))
            subformulas.add(toBeAdded);

        return subformulas;
    }
}
