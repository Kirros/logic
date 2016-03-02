package tests;

import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Formula;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests basic functionality of Formulas
 */
public class FormulaMethodTest {
    private static final Formula ATOMIC = new AtomicFormula('A');
    private static final Formula NEGATION = ATOMIC.not();
    private static final Formula CONJUNCTION = ATOMIC.and(new AtomicFormula('B'));
    // ¬(¬A ∧ ¬B)
    private static final Formula COMPLEX_1 = ATOMIC.not().and(new AtomicFormula('B').not()).not();
    // ¬(¬(A ∧ B) ∧ ¬C)
    private static final Formula COMPLEX_2 = ATOMIC.and(new AtomicFormula('B')).not().and(new AtomicFormula('C').not()).not();

    @Test
    public void testSubformula() {
        if (!COMPLEX_1.hasSubformula(NEGATION))
            throw new Error(NEGATION + " should be subformula of " + COMPLEX_1);

        if (COMPLEX_1.hasSubformula(CONJUNCTION))
            throw new Error(CONJUNCTION + " should not be subformula of " + COMPLEX_1);
    }

    @Test
    public void testEquals() {
        if (!NEGATION.equals(new AtomicFormula('A').not()))
            throw new Error(NEGATION + " and " + new AtomicFormula('A').not() + " should be equal");

        if (ATOMIC.equals(new AtomicFormula('B')))
            throw new Error(NEGATION + " and " + new AtomicFormula('B') + " should not be equal");

        if (NEGATION.equals(CONJUNCTION))
            throw new Error(NEGATION + " and " + CONJUNCTION + " should not be equal");
    }

    @Test
    public void testGetAtomicFormulas() {
        List<AtomicFormula> expected = new ArrayList<>();
        expected.add(new AtomicFormula('A'));
        expected.add(new AtomicFormula('B'));
        expected.add(new AtomicFormula('C'));

        List<AtomicFormula> result = COMPLEX_2.getAllAtomicFormulas();

        if (!expected.equals(result))
            throw new Error("Got\n" + result + "\n\ninstead of\n" + expected);
    }

    @Test
    public void testTruthTable() {
        String expected = "A\tB\tC\t¬(¬(A ∧ B) ∧ ¬C)\n" +
                "0\t0\t0\t0\n" +
                "0\t0\t1\t1\n" +
                "0\t1\t0\t0\n" +
                "0\t1\t1\t1\n" +
                "1\t0\t0\t0\n" +
                "1\t0\t1\t1\n" +
                "1\t1\t0\t1\n" +
                "1\t1\t1\t1";
        String result = COMPLEX_2.getTruthTable().toString();

        if (!expected.equals(result))
            throw new Error("Got\n" + result + "\n\ninstead of\n" + expected);
    }
}
