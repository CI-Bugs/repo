package org.obolibrary.obo2owl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.obolibrary.oboformat.diff.Diff;
import org.obolibrary.oboformat.diff.OBODocDiffer;
import org.obolibrary.oboformat.model.OBODoc;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

@SuppressWarnings("javadoc")
public class RoundTripTest extends OboFormatTestBasics {

    public List<Diff> roundTripOBOURL(String fn, boolean isExpectRoundtrip)
            throws Exception {
        OBODoc obodoc = parseOBOURL(fn);
        return roundTripOBODoc(obodoc, isExpectRoundtrip);
    }

    public List<Diff> roundTripOBOFile(String fn, boolean isExpectRoundtrip)
            throws Exception {
        OBODoc obodoc = parseOBOFile(fn);
        return roundTripOBODoc(obodoc, isExpectRoundtrip);
    }

    public List<Diff> roundTripOBODoc(OBODoc obodoc, boolean isExpectRoundtrip)
            throws Exception {
        OWLOntology oo = convert(obodoc);
        OBODoc obodoc2 = convert(oo);
        obodoc2.check();
        writeOBO(obodoc2);
        OBODocDiffer dd = new OBODocDiffer();
        List<Diff> diffs = dd.getDiffs(obodoc, obodoc2);
        if (isExpectRoundtrip) {
            assertEquals("Expected no diffs", 0, diffs.size());
        }
        return diffs;
    }

    public boolean roundTripOWLFile(String fn, boolean isExpectRoundtrip)
            throws IOException, OWLOntologyCreationException,
            OWLOntologyStorageException {
        OWLOntology oo = parseOWLFile(fn);
        return roundTripOWLOOntology(oo, isExpectRoundtrip);
    }

    public boolean roundTripOWLOOntology(@Nonnull OWLOntology oo,
            boolean isExpectRoundtrip) throws IOException,
            OWLOntologyCreationException, OWLOntologyStorageException {
        OWLAPIOwl2Obo bridge = new OWLAPIOwl2Obo(
                OWLManager.createOWLOntologyManager());
        OBODoc obodoc = bridge.convert(oo);
        writeOBO(obodoc);
        obodoc.check();
        OWLOntology oo2 = convert(obodoc);
        writeOWL(oo2);
        boolean ok = compareOWLOntologiesPartial(oo, oo2, isExpectRoundtrip,
                bridge.getUntranslatableAxioms());
        return ok || !isExpectRoundtrip;
    }

    private static boolean compareOWLOntologiesPartial(@Nonnull OWLOntology oo,
            @Nonnull OWLOntology oo2, boolean isExpectRoundtrip,
            @Nullable Collection<OWLAxiom> untranslatableAxioms) {
        if (isExpectRoundtrip) {
            int untranslatedSize = 0;
            if (untranslatableAxioms != null) {
                untranslatedSize = untranslatableAxioms.size();
            }
            Set<OWLAxiom> expectedAxioms = oo.getAxioms();
            Set<OWLAxiom> foundAxioms = oo2.getAxioms();
            int expectedSize = expectedAxioms.size();
            int foundSize = foundAxioms.size();
            assertEquals("Expected same number of axioms", expectedSize,
                    foundSize + untranslatedSize);
            return false;
        }
        return true;
    }
}
