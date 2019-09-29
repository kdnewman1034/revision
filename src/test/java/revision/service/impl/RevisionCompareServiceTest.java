package revision.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import revision.exception.RevisionException;

/**
 * Testing class for the Revision compare service
 * 
 * @author Kevin
 *
 */
public class RevisionCompareServiceTest {
	
	private static final String AFTER = "after";
	private static final String BEFORE = "before";
	private static final String EQUAL = "equal";
	
	private RevisionCompareService revisionCompareService = new RevisionCompareService();
	
	@Test
	public void testEqualRevisions() throws RevisionException {
		assertEquals(EQUAL,revisionCompareService.compareRevision("1.0.0", "1.0.0"));
	}
	
	@Test
	public void testEqualLargeRevisions() throws RevisionException {
		assertEquals(EQUAL,revisionCompareService.compareRevision("123.456.789", "123.456.789"));
	}
	
	@Test
	public void testBeforeRevisions() throws RevisionException {
		assertEquals(BEFORE,revisionCompareService.compareRevision("1.0.0", "1.0.1"));
	}
	
	@Test
	public void testBeforeLargeRevisions() throws RevisionException {
		assertEquals(BEFORE,revisionCompareService.compareRevision("123.456.789", "896.326.513"));
	}
	
	@Test
	public void testAfterRevisions() throws RevisionException {
		assertEquals(AFTER,revisionCompareService.compareRevision("2.0.0", "1.0.1"));
	}
	
	@Test
	public void testAfterLargeRevisions() throws RevisionException {
		assertEquals(AFTER,revisionCompareService.compareRevision("896.326.513", "123.456.789"));
	}
	
	@Test
	public void testFirstIncorrectFormatRevisions() {
		try {
			revisionCompareService.compareRevision("1.0.asdf", "1.0.0");
		} catch (RevisionException e) {
			assertEquals("Revision 1.0.asdf does not match the required format.", e.getMessage());
		}
	}
	
	@Test
	public void testSecondIncorrectFormatRevisions() {
		try {
			revisionCompareService.compareRevision("1.0.0", "1.asdf.0");
		} catch (RevisionException e) {
			assertEquals("Revision 1.asdf.0 does not match the required format.", e.getMessage());
		}
	}
	
	@Test
	public void testFirstIncorrectNumberFormatRevisions() {
		try {
			revisionCompareService.compareRevision("1.0", "1.0.0");
		} catch (RevisionException e) {
			assertEquals("Revisions 1.0 and 1.0.0 must both have a major, minor, and hotfix number.", e.getMessage());
		}
	}
	
	@Test
	public void testSecondIncorrectNumberFormatRevisions() {
		try {
			revisionCompareService.compareRevision("1.0.0", "1.0");
		} catch (RevisionException e) {
			assertEquals("Revisions 1.0.0 and 1.0 must both have a major, minor, and hotfix number.", e.getMessage());
		}
	}
	
	@Test
	public void testBothIncorrectNumberFormatRevisions() {
		try {
			revisionCompareService.compareRevision("1.0", "1.0");
		} catch (RevisionException e) {
			assertEquals("Revisions 1.0 and 1.0 must both have a major, minor, and hotfix number.", e.getMessage());
		}
	}

}
