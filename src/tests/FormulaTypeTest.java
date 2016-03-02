package tests;

import com.logic.propositional.formulas.Evaluation;
import com.logic.propositional.formulas.Value;
import com.logic.propositional.formulas.types.AtomicFormula;
import com.logic.propositional.formulas.types.Formula;
import org.junit.Test;

/**
 * Test Evaluation of Each Type of Formula
 */
public class FormulaTypeTest {
    private static final Formula ATOMIC_1 = new AtomicFormula('A');
    private static final Formula ATOMIC_2 = new AtomicFormula('B');
    private static final Formula NEGATION = ATOMIC_1.not();
    private static final Formula CONJUNCTION = ATOMIC_1.and(ATOMIC_2);
    private static final Formula DISJUNCTION = ATOMIC_1.or(ATOMIC_2);
    private static final Formula IMPLICATION = ATOMIC_1.imply(ATOMIC_2);
    private static final Formula EQUIVALENCE = ATOMIC_1.equal(ATOMIC_2);

    private static final Evaluation allFalse = new Evaluation();
    private static final Evaluation secondTrue = new Evaluation();
    private static final Evaluation firstTrue = new Evaluation();
    private static final Evaluation allTrue = new Evaluation();

    static {
        allFalse.add(new AtomicFormula('A'), Value.FALSE);
        allFalse.add(new AtomicFormula('B'), Value.FALSE);
        secondTrue.add(new AtomicFormula('A'), Value.FALSE);
        secondTrue.add(new AtomicFormula('B'), Value.TRUE);
        firstTrue.add(new AtomicFormula('A'), Value.TRUE);
        firstTrue.add(new AtomicFormula('B'), Value.FALSE);
        allTrue.add(new AtomicFormula('A'), Value.TRUE);
        allTrue.add(new AtomicFormula('B'), Value.TRUE);
    }

    @Test
    public void testAtomic() {
        if (ATOMIC_1.evaluate(allFalse) != Value.FALSE)
            throw new Error("Atomic should be False");

        if (ATOMIC_1.evaluate(allTrue) != Value.TRUE)
            throw new Error("Atomic should be True");
    }

    @Test
    public void testNegation() {
        if (NEGATION.evaluate(allFalse) != Value.TRUE)
            throw new Error("Negation should be True");

        if (NEGATION.evaluate(allTrue) != Value.FALSE)
            throw new Error("Negation should be False");
    }

    @Test
    public void testConjunction() {
        if (CONJUNCTION.evaluate(allFalse) != Value.FALSE)
            throw new Error("Conjunction should be False if both subformulas are false");

        if (CONJUNCTION.evaluate(firstTrue) != Value.FALSE)
            throw new Error("Conjunction should be False if first subformula is true");

        if (CONJUNCTION.evaluate(secondTrue) != Value.FALSE)
            throw new Error("Conjunction should be False if second subformula is true");

        if (CONJUNCTION.evaluate(allTrue) != Value.TRUE)
            throw new Error("Conjunction should be True if both subformulas are true");
    }

    @Test
    public void testDisjunction() {
        Formula equivalent = NEGATION.and(ATOMIC_2.not()).not();

        if (!DISJUNCTION.evaluate(allFalse).equals(equivalent.evaluate(allFalse)))
            throw new Error("All false evaluation for disjunction failed ");

        if (!DISJUNCTION.evaluate(firstTrue).equals(equivalent.evaluate(firstTrue)))
            throw new Error("First true evaluation for disjunction failed ");

        if (!DISJUNCTION.evaluate(secondTrue).equals(equivalent.evaluate(secondTrue)))
            throw new Error("Second true evaluation for disjunction failed ");

        if (!DISJUNCTION.evaluate(allTrue).equals(equivalent.evaluate(allTrue)))
            throw new Error("All true evaluation for disjunction failed ");
    }

    @Test
    public void testImplication() {
        Formula equivalent = ATOMIC_2.or(NEGATION);

        if (!IMPLICATION.evaluate(allFalse).equals(equivalent.evaluate(allFalse)))
            throw new Error("All false evaluation for implication failed ");

        if (!IMPLICATION.evaluate(firstTrue).equals(equivalent.evaluate(firstTrue)))
            throw new Error("First true evaluation for implication failed ");

        if (!IMPLICATION.evaluate(secondTrue).equals(equivalent.evaluate(secondTrue)))
            throw new Error("Second true evaluation for implication failed ");

        if (!IMPLICATION.evaluate(allTrue).equals(equivalent.evaluate(allTrue)))
            throw new Error("All true evaluation for implication failed ");
    }

    @Test
    public void testEquivalence() {
        Formula equivalent = ATOMIC_1.imply(ATOMIC_2).and(ATOMIC_2.imply(ATOMIC_1));

        if (!EQUIVALENCE.evaluate(allFalse).equals(equivalent.evaluate(allFalse)))
            throw new Error("All false evaluation for equivalence failed ");

        if (!EQUIVALENCE.evaluate(firstTrue).equals(equivalent.evaluate(firstTrue)))
            throw new Error("First true evaluation for equivalence failed ");

        if (!EQUIVALENCE.evaluate(secondTrue).equals(equivalent.evaluate(secondTrue)))
            throw new Error("Second true evaluation for equivalence failed ");

        if (!EQUIVALENCE.evaluate(allTrue).equals(equivalent.evaluate(allTrue)))
            throw new Error("All true evaluation for equivalence failed ");
    }
}
