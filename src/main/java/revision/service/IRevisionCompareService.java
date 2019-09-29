package revision.service;

import revision.exception.RevisionException;

public interface IRevisionCompareService {

	/**
	 * Comparator used to compare two revisions
	 * 
	 * @param revisionOne
	 * @param revisionTwo
	 * @return
	 * @throws RevisionException 
	 */
	public String compareRevision(String revisionOne, String revisionTwo) throws RevisionException;
	
}
