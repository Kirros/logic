package tests;

import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Formula;
import com.logic.propositional.formulas.FormulaError;
import com.logic.propositional.formulas.FormulaParser;
import org.junit.Test;

/**
 * Tests function of FormulaParser
 */
public class FormulaParserTest {

    @Test
    public void testVoid() {
        if(testFormula("") != null)
            throw new Error("Incorrectly handled null value");
    }

    @Test
    public void testAtomic() {
        if(!testFormula("A").equals(new AtomicFormula('A')))
            throw new Error("Incorrectly handled atomic formula");
    }

    @Test
    public void testNegation() {
        if (!testFormula("!A").equals(new AtomicFormula('A').not()))
            throw new Error("Incorrectly handled negation.");
    }

    @Test
    public void testConjunction() {
        if (!testFormula("(A & B)").equals(new AtomicFormula('A').and(new AtomicFormula('B'))))
            throw new Error("Incorrectly handled conjunction.");
    }

    @Test
    public void testDisjunction() {
        if (!testFormula("(A | B)").equals(new AtomicFormula('A').or(new AtomicFormula('B'))))
            throw new Error("Incorrectly handled disjunction.");
    }

    @Test
    public void testImplication() {
        if (!testFormula("(A - B)").equals(new AtomicFormula('A').imply(new AtomicFormula('B'))))
            throw new Error("Incorrectly handled implication.");
    }

    @Test
    public void testEquivalence() {
        if (!testFormula("(A = B)").equals(new AtomicFormula('A').equal(new AtomicFormula('B'))))
            throw new Error("Incorrectly handled equality.");
    }

    @Test
    public void testComplex() {
        Formula result = testFormula("!(!(A & B) & !C)");
        Formula expected = new AtomicFormula('A').and(new AtomicFormula('B')).not().and(new AtomicFormula('C').not()).not();

        if (!result.equals(expected))
            throw new Error("Incorrectly handled complex formula. Result: " + result.toString() + " Expected: " + expected.toString());
    }

    @Test
    public void testWrong() {
        try {
            testFormula("((A!&)B(C!");
        } catch (FormulaError error) {
            // Everything right here
            return;
        }

        throw new Error("This formula should have failed.");
    }


    @Test
    public void testDroppingParentheses() {
        if (!testFormula("A & B & C & D").equals(new AtomicFormula('A').and(new AtomicFormula('B')).and(new AtomicFormula('C')).and(new AtomicFormula('D'))))
            throw new Error("Error during parsing formula with dropped parentheses");
    }

    private Formula testFormula(String formula) {
        FormulaParser parser = new FormulaParser(formula);
        return parser.getFormula();
    }
}
