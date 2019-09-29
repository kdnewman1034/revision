package revision.service.impl;

import org.springframework.stereotype.Service;

import revision.exception.RevisionException;
import revision.service.IRevisionCompareService;

/**
 * Service used to implement the revision compare
 * 
 * @author Kevin
 *
 */
@Service
public class RevisionCompareService implements IRevisionCompareService {

	private static final String EQUAL = "equal";
	private static final String AFTER = "after";
	private static final String BEFORE = "before";
	private static final int MAX_REVISION_SIZE = 3;
	private static final String PERIOD = "\\.";

	@Override
	public String compareRevision(String revisionOne, String revisionTwo) throws RevisionException{
		validateRevision(revisionOne);
		validateRevision(revisionTwo);
		
		String[] first = revisionOne.split(PERIOD);
		String[] second = revisionTwo.split(PERIOD);
		
		if(first.length != second.length || first.length != MAX_REVISION_SIZE) {
			throw new RevisionException(String.format("Revisions %s and %s must both have a major, minor, and hotfix number.",
					revisionOne, revisionTwo));
		}
		
		for(int i=0; i<MAX_REVISION_SIZE; i++) {
			int revOne = Integer.parseInt(first[i]);
			int revTwo = Integer.parseInt(second[i]);
			
			if(revOne < revTwo) {
				return BEFORE;
			}else if(revOne > revTwo) {
				return AFTER;
			}
		}
		
		return EQUAL;
	}
	
	/**
	 * Validates a given revision only contains numbers and periods
	 * 
	 * @param revision
	 * @throws RevisionException
	 */
	private void validateRevision(String revision) throws RevisionException{
		if(!revision.matches("[0-9.]+")) {
			throw new RevisionException(String.format("Revision %s does not match the required format.", revision));
		}
	}

}
