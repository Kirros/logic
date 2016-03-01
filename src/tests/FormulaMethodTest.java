package tests;

import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Formula;
import org.junit.Test;

/**
 * Tests basic functionality of Formulas
 */
public class FormulaMethodTest {

    @Test
    public void testSubformula() {
        Formula testFormula = new AtomicFormula('A').not().and(new AtomicFormula('B').not()).not();
        Formula testSubformula = new AtomicFormula('A').not();

        if (!testFormula.hasSubformula(testSubformula))
            throw new Error(testSubformula + " should be subformula of " + testFormula);
    }

    @Test
    public void testTruthTable() {
        Formula formula = new AtomicFormula('A').and(new AtomicFormula('B')).not().and(new AtomicFormula('C').not()).not();
        String expected = "A\tB\tC\t¬(¬(A ∧ B) ∧ ¬C)";
        String result = formula.getTruthTable().toString();

        if (!expected.equals(result))
            throw new Error("Got\n" + result + "\n\ninstead of\n" + expected);
    }
}
